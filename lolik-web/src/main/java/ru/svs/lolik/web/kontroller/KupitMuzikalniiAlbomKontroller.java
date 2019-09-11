package ru.svs.lolik.web.kontroller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import ru.svs.lolik.web.kontroller.url.KartaURL;
import ru.svs.lolik.web.kontroller.url.URLKodirovshik;
import ru.svs.lolik.web.obiekt.MuzikalniiAlbom;
import ru.svs.lolik.web.obiekt.MuzikalniiAlbomTranzakcia;
import ru.svs.lolik.web.obiekt.ParametrSistemi;
import ru.svs.lolik.web.obiekt.TipRazdela;
import ru.svs.lolik.web.obiekt.Tranzakcia;
import ru.svs.lolik.web.obiekt.TranzakciaSostoianie;
import ru.svs.lolik.web.obshee.Perevodchik;
import ru.svs.lolik.web.odd.MuzikalniiAlbomODD;
import ru.svs.lolik.web.odd.ObshiiODD;
import ru.svs.lolik.web.odd.ParametrSistemiODD;
import ru.svs.lolik.web.odd.RazdelODD;
import ru.svs.lolik.web.servis.EmailServis;
import ru.svs.paypal.constants.Constants;
import ru.svs.paypal.dto.ApiProfileDTO;
import ru.svs.paypal.dto.CheckoutExpressDTO;
import ru.svs.paypal.security.Shifrovshik;
import ru.svs.paypal.service.PaypalService;

import com.paypal.sdk.exceptions.PayPalException;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 23.02.2011
 */
@Controller
public class KupitMuzikalniiAlbomKontroller {
	private static final Logger LOGGER = Logger.getLogger(KupitMuzikalniiAlbomKontroller.class);

	private static final String SOOBSHENIE_OSHIBKI = "soobshenieOshibki";
	
	@Autowired
	@Qualifier(value="obshiiODDKlass")
	private ObshiiODD obshiiODD;
	
	@Autowired
	private RazdelODD razdelODD;
	
	@Autowired
	private MuzikalniiAlbomODD muzikalniiAlbomODD;
	
	@Autowired
	private ParametrSistemiODD parametrSistemiODD;
	
	@Autowired
	private PaypalService paypalService;
	
	@Autowired
	private EmailServis emailService;
	
	@Autowired
	private ApplicationContext kontekstLolikWeb;
	
	/**
	 * Метод открывает страницу ПайПала для покупки музыкального альбома.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value=KartaURL.URL_KUPIT_MUZIKALNII_ALBOM, method=RequestMethod.GET)
    public ModelAndView kupitMuzikalniiAlbom(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_KUPIT_MUZIKALNII_ALBOM);
		final JSONObject		jsonObject = new JSONObject();
		final Locale			vibraniiYazik = RequestContextUtils.getLocale(request);
		final Integer			muzikalniiAlbomId = new Integer(request.getParameter("muzikalniiAlbomId"));
		final MuzikalniiAlbom	muzikalniiAlbom = muzikalniiAlbomODD.naiti(MuzikalniiAlbom.class, muzikalniiAlbomId);

		final MuzikalniiAlbomTranzakcia muzikalniiAlbomTranzakcia = new MuzikalniiAlbomTranzakcia();
		muzikalniiAlbomTranzakcia.setChislo(new Timestamp(new Date().getTime()));
		muzikalniiAlbomTranzakcia.setIpAdres(request.getRemoteAddr());
		muzikalniiAlbomTranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_NACHALO_TRANZAKCII));
		obshiiODD.soxranitNovii(muzikalniiAlbomTranzakcia); //Soxranenie i dobavlenie ID
		muzikalniiAlbom.dobavitPokupku(muzikalniiAlbomTranzakcia);
		muzikalniiAlbomODD.soxranit(muzikalniiAlbom);
		
		final ParametrSistemi ppVvd = parametrSistemiODD.naiti(ParametrSistemi.ПП_ВВД);
		final ApiProfileDTO apiProfileDTO = vvestiDanniePP(ppVvd.getZnachenie());
		final CheckoutExpressDTO checkoutExpressDTO = new CheckoutExpressDTO();
		checkoutExpressDTO.setProductId(muzikalniiAlbomId);
		checkoutExpressDTO.setTransactionId(muzikalniiAlbomTranzakcia.getId());
		checkoutExpressDTO.setReturnURL("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" + KartaURL.URL_OTKRIT_POKUPKU_MUZIKALNOGO_ALBOMA);
		checkoutExpressDTO.setCancelURL("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/" + KartaURL.URL_OTMENIT_POKUPKU_MUZIKALNOGO_ALBOMA);
		checkoutExpressDTO.setGrossAmount(muzikalniiAlbom.getCena().toString());
		checkoutExpressDTO.setLocaleCode(vibraniiYazik.getLanguage().toUpperCase());
		checkoutExpressDTO.setOrderDescription(getOrderDescription(muzikalniiAlbom, vibraniiYazik));
		request.getSession().setAttribute(CheckoutExpressDTO.CHECKOUT_EXPRESS_DTO, checkoutExpressDTO);
		
		doSetExpressCheckout(apiProfileDTO, checkoutExpressDTO, jsonObject, vibraniiYazik, muzikalniiAlbomTranzakcia);
		
		obshiiODD.soxranit(muzikalniiAlbomTranzakcia);
				
		jsonObject.put("url", Shifrovshik.rasshifrovat(ppVvd.getZnachenie(), parametrSistemiODD.naiti(ParametrSistemi.ПП_СЛК).getZnachenie()) + checkoutExpressDTO.getToken());
		
        return new ModelAndView("jsonView", jsonObject);
    }

	/**
	 * Метод создаёт описание продукта.
	 * 
	 * @param muzikalniiAlbom - MuzikalniiAlbom.
	 * @param vibraniiYazik - Locale.
	 * 
	 * @return orderDescription - String.
	 */
	private String getOrderDescription(MuzikalniiAlbom muzikalniiAlbom,	Locale vibraniiYazik) {
		final StringBuffer orderDescription = new StringBuffer();
		orderDescription.append(kontekstLolikWeb.getMessage("muzika.albom.nazvanie", null, vibraniiYazik));
		orderDescription.append(": ");
		orderDescription.append(muzikalniiAlbom.getNazvanie());
		orderDescription.append(", ");
		orderDescription.append(muzikalniiAlbom.getFormat());
		orderDescription.append(" - ");
		orderDescription.append(kontekstLolikWeb.getMessage("muzika.albom.znak.dollar", null, vibraniiYazik));
		orderDescription.append(muzikalniiAlbom.getCena());
		return orderDescription.toString();
	}

	/**
	 * Метод вводит обязательные данные для ПП операции.
	 * 
	 * @return apiProfileDTO - ApiProfileDTO.
	 * 
	 * @param znachenie - String.
	 */
	private ApiProfileDTO vvestiDanniePP(String znachenie) {
		final ApiProfileDTO apiProfileDTO = new ApiProfileDTO();
		apiProfileDTO.setApiUsername(Shifrovshik.rasshifrovat(znachenie, parametrSistemiODD.naiti(ParametrSistemi.ПП_ПЛЬ).getZnachenie()));
		apiProfileDTO.setApiPassword(Shifrovshik.rasshifrovat(znachenie, parametrSistemiODD.naiti(ParametrSistemi.ПП_ПРЬ).getZnachenie()));
		apiProfileDTO.setSignature(Shifrovshik.rasshifrovat(znachenie, parametrSistemiODD.naiti(ParametrSistemi.ПП_ПОД).getZnachenie().toLowerCase()));
		apiProfileDTO.setEnvironment(Shifrovshik.rasshifrovat(znachenie, parametrSistemiODD.naiti(ParametrSistemi.ПП_ОКР).getZnachenie()));
		return apiProfileDTO;
	}
	
	/**
	 * Метод делает первый запрос ПайПала, в случае ошибки, возвращяется сообщение об ошибки. 
	 * 
	 * @param apiProfileDTO - ApiProfileDTO.
	 * @param checkoutExpressDTO - CheckoutExpressDTO.
	 * @param jsonObject - JSONObject.
	 * @param vibraniiYazik - Locale.
	 * @param tranzakcia - Tranzakcia.
	 */
	private void doSetExpressCheckout(ApiProfileDTO apiProfileDTO, CheckoutExpressDTO checkoutExpressDTO, JSONObject jsonObject, Locale vibraniiYazik, Tranzakcia tranzakcia) {
		try {
			paypalService.doSetExperessCheckout(apiProfileDTO, checkoutExpressDTO);
			if (PaypalService.INVOCATION_SUCCESS.equalsIgnoreCase(checkoutExpressDTO.getResultMessage())) {
				tranzakcia.setKluch(checkoutExpressDTO.getToken());
				jsonObject.put("prodolzhit", Boolean.TRUE);
			} else {
				LOGGER.error("kluch = '" + checkoutExpressDTO.getToken() + "' oshibki = '" + checkoutExpressDTO.printErrorMessages() + "'");
				tranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_OSHIBKA_TRANZAKCII));
				obshiiODD.soxranit(tranzakcia);
				jsonObject.put(SOOBSHENIE_OSHIBKI, kontekstLolikWeb.getMessage("muzika.albom.soobshenie.soedinenie.paypal.oshibka", null, vibraniiYazik));
				jsonObject.put("prodolzhit", Boolean.FALSE);	
			}
		} catch (PayPalException e) {
			LOGGER.error("Kriticheskaia oshibka Paypala, kluch = '" + checkoutExpressDTO.getToken() + "'", e);
			tranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_KRITICHESKAIA_OSHIBKA_TRANZAKCII));
			jsonObject.put(SOOBSHENIE_OSHIBKI, kontekstLolikWeb.getMessage("muzika.albom.soobshenie.soedinenie.paypal.oshibka", null, vibraniiYazik));
			jsonObject.put("prodolzhit", Boolean.FALSE);
		} catch (Exception e) {
			LOGGER.error("Kriticheskaia oshibka Paypala, kluch = '" + checkoutExpressDTO.getToken() + "'", e);
			tranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_KRITICHESKAIA_OSHIBKA_TRANZAKCII));
			jsonObject.put(SOOBSHENIE_OSHIBKI, kontekstLolikWeb.getMessage("muzika.albom.soobshenie.globalnaia.oshibka", null, vibraniiYazik));
			jsonObject.put("prodolzhit", Boolean.FALSE);
		}
	}

	/**
	 * Метод отменяет покупку музыкального альбома, уберает параметры из адреса, и перенаправляет пользователя на последний альбом.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_OTMENIT_POKUPKU_MUZIKALNOGO_ALBOMA, method=RequestMethod.GET)
    public ModelAndView otmenitPokupkuMuzikalnogoAlboma(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTMENIT_POKUPKU_MUZIKALNOGO_ALBOMA);

		final CheckoutExpressDTO checkoutExpressDTO = (CheckoutExpressDTO) request.getSession().getAttribute(CheckoutExpressDTO.CHECKOUT_EXPRESS_DTO);
		final MuzikalniiAlbomTranzakcia MuzikalniiAlbomTranzakcia = obshiiODD.naiti(MuzikalniiAlbomTranzakcia.class, checkoutExpressDTO.getTransactionId());
		MuzikalniiAlbomTranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_OTMENA_TRANZAKCII));
		obshiiODD.soxranit(MuzikalniiAlbomTranzakcia);
		
		return new ModelAndView(KartaURL.REDIRECT + KartaURL.URL_OTMENIT_POKUPKU);
    }
	
	/**
	 * Метод перенаправляет пользователя на последний альбом.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_OTMENIT_POKUPKU, method=RequestMethod.GET)
    public ModelAndView otmenitPokupku(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTMENIT_POKUPKU);
		final CheckoutExpressDTO checkoutExpressDTO = (CheckoutExpressDTO) request.getSession().getAttribute(CheckoutExpressDTO.CHECKOUT_EXPRESS_DTO);
		
		request.setAttribute("adresPerenapravlenia", KartaURL.URL_OTKRIT_STRANICU_MUZIKALNII_ALBOM);
		request.setAttribute("imiaObiekta", "muzikalniiAlbomId");
		request.setAttribute("obiektId", checkoutExpressDTO.getProductId());
		
		request.getSession().removeAttribute(CheckoutExpressDTO.CHECKOUT_EXPRESS_DTO);
		
        return new ModelAndView(KartaURL.JSP_ZAGLAVNAIA);
    }
	
	/**
	 * Метод перенаправляет на страницу подтверждении покупки музыкального альбома, уберает параметры из адреса.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_OTKRIT_POKUPKU_MUZIKALNOGO_ALBOMA, method=RequestMethod.GET)
    public ModelAndView otkritPokupkuMuzikalnogoAlboma(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTKRIT_POKUPKU_MUZIKALNOGO_ALBOMA);
		final Locale			 vibraniiYazik 		= RequestContextUtils.getLocale(request);
		final CheckoutExpressDTO checkoutExpressDTO = (CheckoutExpressDTO) request.getSession().getAttribute(CheckoutExpressDTO.CHECKOUT_EXPRESS_DTO);
		final MuzikalniiAlbomTranzakcia muzikalniiAlbomTranzakcia = obshiiODD.naiti(MuzikalniiAlbomTranzakcia.class, checkoutExpressDTO.getTransactionId());
		checkoutExpressDTO.setPayerId(request.getParameter(Constants.PAYPAL_PAYERID_PARAMETER));
		request.getSession().removeAttribute(SOOBSHENIE_OSHIBKI);
		
		final ApiProfileDTO apiProfileDTO = vvestiDanniePP(parametrSistemiODD.naiti(ParametrSistemi.ПП_ВВД).getZnachenie());
		
		doGetExpressCheckout(apiProfileDTO, checkoutExpressDTO, muzikalniiAlbomTranzakcia, request, vibraniiYazik);
		
		obshiiODD.soxranit(muzikalniiAlbomTranzakcia);
		
		return new ModelAndView(KartaURL.REDIRECT + KartaURL.URL_OTKRIT_POKUPKU);
    }
	
	/**
	 * Метод делает запрос ПайПала который выдаёт данные клиента, в случае ошибки, возвращяется сообщение об ошибки. 
	 * 
	 * @param apiProfileDTO - ApiProfileDTO.
	 * @param checkoutExpressDTO - CheckoutExpressDTO.
	 * @param tranzakcia - Tranzakcia.
	 * @param request - HttpServletRequest.
	 * @param vibraniiYazik - Locale.
	 */
	private void doGetExpressCheckout(ApiProfileDTO apiProfileDTO, CheckoutExpressDTO checkoutExpressDTO, Tranzakcia tranzakcia, HttpServletRequest request, Locale vibraniiYazik) {
		try {
			paypalService.doGetExpressCheckout(apiProfileDTO, checkoutExpressDTO);
			if (PaypalService.INVOCATION_SUCCESS.equalsIgnoreCase(checkoutExpressDTO.getResultMessage())) {
				tranzakcia.setPokupatelId(checkoutExpressDTO.getPayerId());
				tranzakcia.setPokupatelEmail(checkoutExpressDTO.getPayerEmail());
				tranzakcia.setPokupatelStrana(checkoutExpressDTO.getPayerCountry());
				tranzakcia.setPokupatelImia(checkoutExpressDTO.getPayerName());
				tranzakcia.setPokupatelFamilia(checkoutExpressDTO.getPayerLastname());
				tranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_VVEDENA_INFORMACIA_TRANZAKCII));
			} else {
				LOGGER.error("Oshibka v otvete Paypala, kluch = '" + checkoutExpressDTO.getToken() + "' oshibki = '" + checkoutExpressDTO.printErrorMessages() + "'");
				request.getSession().setAttribute(SOOBSHENIE_OSHIBKI, kontekstLolikWeb.getMessage("muzika.albom.soobshenie.soedinenie.paypal.oshibka", null, vibraniiYazik));
				tranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_OSHIBKA_TRANZAKCII));
			}
		} catch (PayPalException e) {
			LOGGER.error("Kriticheskaia oshibka Paypala, kluch = '" + checkoutExpressDTO.getToken() + "'", e);
			tranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_KRITICHESKAIA_OSHIBKA_TRANZAKCII));
			request.getSession().setAttribute(SOOBSHENIE_OSHIBKI, kontekstLolikWeb.getMessage("muzika.albom.soobshenie.soedinenie.paypal.oshibka", null, vibraniiYazik));
		} catch (Exception e) {
			LOGGER.error("Kriticheskaia oshibka Paypala, kluch = '" + checkoutExpressDTO.getToken() + "'", e);
			tranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_KRITICHESKAIA_OSHIBKA_TRANZAKCII));
			request.getSession().setAttribute(SOOBSHENIE_OSHIBKI, kontekstLolikWeb.getMessage("muzika.albom.soobshenie.globalnaia.oshibka", null, vibraniiYazik));
		}
	}
	
	/**
	 * Метод перенаправляет пользователя на страницу подтверждении покупки музыкального альбома.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_OTKRIT_POKUPKU, method=RequestMethod.GET)
    public ModelAndView otkritPokupku(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTKRIT_POKUPKU);
		final CheckoutExpressDTO checkoutExpressDTO = (CheckoutExpressDTO) request.getSession().getAttribute(CheckoutExpressDTO.CHECKOUT_EXPRESS_DTO);
		
		request.setAttribute("adresPerenapravlenia", KartaURL.URL_OTKRIT_STRANICU_MUZIKALNII_ALBOM_POKUPKA);
		request.setAttribute("imiaObiekta", "muzikalniiAlbomId");
		request.setAttribute("obiektId", checkoutExpressDTO.getProductId());
		
        return new ModelAndView(KartaURL.JSP_ZAGLAVNAIA);
    }
	
	/**
	 * Метод перенаправляет пользователя на страницу подтверждении покупки музыкального альбома.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_OTKRIT_STRANICU_MUZIKALNII_ALBOM_POKUPKA, method=RequestMethod.GET)
    public ModelAndView otkritStranicuMuzikalniiAlbomPokupka(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTKRIT_STRANICU_MUZIKALNII_ALBOM_POKUPKA);
		
		request.setAttribute(SOOBSHENIE_OSHIBKI, request.getSession().getAttribute(SOOBSHENIE_OSHIBKI));
		request.setAttribute("razdel", 			razdelODD.naitiRazdel(RequestContextUtils.getLocale(request).getLanguage(), TipRazdela.TIP_RAZDELA_MUZIKA_KOD, request.getRemoteHost()));
		request.setAttribute("muzikalniiAlbom",	muzikalniiAlbomODD.naiti(MuzikalniiAlbom.class, new Integer(request.getParameter("muzikalniiAlbomId"))));
		request.getSession().removeAttribute(SOOBSHENIE_OSHIBKI);
		
        return new ModelAndView(KartaURL.JSP_MUZIKALNII_ALBOM_POKUPKA);
    }

	/**
	 * Метод подтверждает покупку музыкального альбома, записывает данные операции в базе данных, и перенаправляет на страницу скачки музыкального альбома.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_PODTVERDIT_POKUPKU_MUZIKALNOGO_ALBOMA, method=RequestMethod.GET)
    public ModelAndView podtverditPokupkuMuzikalnogoAlboma(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_PODTVERDIT_POKUPKU_MUZIKALNOGO_ALBOMA);
		final Locale			 vibraniiYazik 		= RequestContextUtils.getLocale(request);
		final CheckoutExpressDTO checkoutExpressDTO = (CheckoutExpressDTO) request.getSession().getAttribute(CheckoutExpressDTO.CHECKOUT_EXPRESS_DTO);
		final MuzikalniiAlbomTranzakcia	muzikalniiAlbomTranzakcia = obshiiODD.naiti(MuzikalniiAlbomTranzakcia.class, checkoutExpressDTO.getTransactionId());
		muzikalniiAlbomTranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_PODTVERZHDENA_TRANZAKCIA));
		obshiiODD.soxranit(muzikalniiAlbomTranzakcia);
		
		final ApiProfileDTO apiProfileDTO = vvestiDanniePP(parametrSistemiODD.naiti(ParametrSistemi.ПП_ВВД).getZnachenie());
		
		doExpressCheckout(apiProfileDTO, checkoutExpressDTO, muzikalniiAlbomTranzakcia, request, vibraniiYazik);

		obshiiODD.soxranit(muzikalniiAlbomTranzakcia);
		
		request.setAttribute("razdel", 			razdelODD.naitiRazdel(RequestContextUtils.getLocale(request).getLanguage(), TipRazdela.TIP_RAZDELA_MUZIKA_KOD, request.getRemoteHost()));
		request.setAttribute("muzikalniiAlbom",	muzikalniiAlbomODD.naiti(MuzikalniiAlbom.class, new Integer(request.getParameter("muzikalniiAlbomId"))));
		request.setAttribute("pokupatelEmail",  muzikalniiAlbomTranzakcia.getPokupatelEmail());
		request.getSession().removeAttribute(CheckoutExpressDTO.CHECKOUT_EXPRESS_DTO);
		
		poslatPismoPokupki(muzikalniiAlbomTranzakcia, request, vibraniiYazik);
		
		if (request.getAttribute(SOOBSHENIE_OSHIBKI) != null) {
			return new ModelAndView(KartaURL.JSP_MUZIKALNII_ALBOM_POKUPKA);
		}
		
		return new ModelAndView(KartaURL.JSP_MUZIKALNII_ALBOM_INSTRUKCIA);
    }

	/**
	 * Метод делает первый запрос ПайПала, в случае ошибки, возвращяется сообщение об ошибки. 
	 * 
	 * @param apiProfileDTO - ApiProfileDTO.
	 * @param checkoutExpressDTO - CheckoutExpressDTO.
	 * @param tranzakcia - Tranzakcia.
	 * @param request - HttpServletRequest.
	 * @param vibraniiYazik - Locale.
	 */
	private void doExpressCheckout(ApiProfileDTO apiProfileDTO, CheckoutExpressDTO checkoutExpressDTO, Tranzakcia tranzakcia, HttpServletRequest request, Locale vibraniiYazik) {
		try {
			paypalService.doExpressCheckout(apiProfileDTO, checkoutExpressDTO);
			if (PaypalService.INVOCATION_SUCCESS.equalsIgnoreCase(checkoutExpressDTO.getResultMessage())) {
				tranzakcia.setTranzakcia(checkoutExpressDTO.getTransactionCode());
				tranzakcia.setValuta(checkoutExpressDTO.getCurrencyCode());
				tranzakcia.setStoimostTovara(Perevodchik.perevesti(checkoutExpressDTO.getGrossAmount()));
				tranzakcia.setStoimostTranzakcii(Perevodchik.perevesti(checkoutExpressDTO.getFeeAmount()));
				tranzakcia.setSoobshenie(checkoutExpressDTO.getTransactionStatus());
				tranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_USPESHNO_ZAKONCHENA_TRANZAKCIA));
				
				/* На данный момент не используется так как не понятно есть ли реальные случаи когда покупка поизводится с использованием eCheck. 
				if (PaypalService.TRANSACTION_COMPLETED.equalsIgnoreCase(checkoutExpressDTO.getTransactionStatus())) {
					tranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_USPESHNO_ZAKONCHENA_TRANZAKCIA));
				} else {
					LOGGER.error("Oshibka v tranzakcii Paypala, kluch = '" + checkoutExpressDTO.getToken() + "' oshibki = '" + checkoutExpressDTO.printErrorMessages() + "'");
					tranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_OSHIBKA_TRANZAKCII));
					request.setAttribute(SOOBSHENIE_OSHIBKI, kontekstLolikWeb.getMessage("muzika.albom.soobshenie.tranzakcia.oshibka", null, vibraniiYazik));
				}*/
			} else {
				LOGGER.error("Oshibka v otvete Paypala, kluch = '" + checkoutExpressDTO.getToken() + "' oshibki = '" + checkoutExpressDTO.printErrorMessages() + "'");
				tranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_OSHIBKA_TRANZAKCII));
				request.setAttribute(SOOBSHENIE_OSHIBKI, kontekstLolikWeb.getMessage("muzika.albom.soobshenie.soedinenie.paypal.oshibka", null, vibraniiYazik));
			}
		} catch (PayPalException e) {
			LOGGER.error("Kriticheskaia oshibka Paypala, kluch = '" + checkoutExpressDTO.getToken() + "'", e);
			tranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_KRITICHESKAIA_OSHIBKA_TRANZAKCII));
			request.setAttribute(SOOBSHENIE_OSHIBKI, kontekstLolikWeb.getMessage("muzika.albom.soobshenie.soedinenie.paypal.oshibka", null, vibraniiYazik));
		} catch (Exception e) {
			LOGGER.error("Kriticheskaia oshibka Paypala, kluch = '" + checkoutExpressDTO.getToken() + "'", e);
			tranzakcia.setSostoianie(new TranzakciaSostoianie(TranzakciaSostoianie.KOD_KRITICHESKAIA_OSHIBKA_TRANZAKCII));
			request.setAttribute(SOOBSHENIE_OSHIBKI, kontekstLolikWeb.getMessage("muzika.albom.soobshenie.globalnaia.oshibka", null, vibraniiYazik));
		}
	}
	
	/**
	 * Метод посылает письмо покупателю музыкального альбома, с данными покупки.
	 * 
	 * @param muzikalniiAlbomTranzakcia - MuzikalniiAlbomTranzakcia.
	 * @param request - HttpServletRequest.
	 * @param vibraniiYazik - Locale.
	 */
	private void poslatPismoPokupki(MuzikalniiAlbomTranzakcia muzikalniiAlbomTranzakcia, HttpServletRequest request, Locale vibraniiYazik) {
		final StringBuffer ssilka = new StringBuffer();
		ssilka.append(request.getScheme());
		ssilka.append("://");
		ssilka.append(request.getServerName());
		ssilka.append(request.getContextPath());
		ssilka.append("/");
		ssilka.append(KartaURL.URL_OTKRIT_ZAGRUZKU_MUZIKALNOGO_ALBOMA_HTML);
		ssilka.append("?yazik=");
		ssilka.append(vibraniiYazik.getLanguage());
		ssilka.append("&pokupatelEmail=");
		ssilka.append(muzikalniiAlbomTranzakcia.getPokupatelEmail());
		
		final StringBuffer pismo = new StringBuffer();
		pismo.append(kontekstLolikWeb.getMessage("email.muzikalnii.albom.pokupka.tekst.1", new String[] {muzikalniiAlbomTranzakcia.getMuzikalniiAlbom().getNazvanie()}, vibraniiYazik));
		pismo.append(kontekstLolikWeb.getMessage("email.muzikalnii.albom.pokupka.tekst.2", new Integer[] {muzikalniiAlbomTranzakcia.getKolichestvoOstavshixsiaZagruzok()}, vibraniiYazik));
		pismo.append(kontekstLolikWeb.getMessage("email.muzikalnii.albom.pokupka.tekst.3", new String[] {URLKodirovshik.zakodirovatUTF8(ssilka.toString())}, vibraniiYazik));
		pismo.append(kontekstLolikWeb.getMessage("email.muzikalnii.albom.pokupka.tekst.4", null, vibraniiYazik));
		pismo.append(kontekstLolikWeb.getMessage("email.muzikalnii.albom.pokupka.tekst.5", null, vibraniiYazik));
		
		try {
			emailService.poslatPismo(parametrSistemiODD.naiti(ParametrSistemi.РБТ_ПОЧТА).getZnachenie(), 
									 "sergei.shurpenkov@gmail.com",/*muzikalniiAlbomTranzakcia.getPokupatelEmail()*/ //TODO, 
									 kontekstLolikWeb.getMessage("email.muzikalnii.albom.pokupka.tema", null, vibraniiYazik), 
									 pismo.toString());
		} catch (Exception e) {
			LOGGER.error("Oshibka pri otsilki pisma polzovatelu = '" + muzikalniiAlbomTranzakcia.getPokupatelEmail() + "'", e);
		}
	}
}
