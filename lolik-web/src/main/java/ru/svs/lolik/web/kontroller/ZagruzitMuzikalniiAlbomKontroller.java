package ru.svs.lolik.web.kontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import ru.svs.lolik.web.kontroller.url.KartaURL;
import ru.svs.lolik.web.obiekt.MuzikalniiAlbomTranzakcia;
import ru.svs.lolik.web.obiekt.Razdel;
import ru.svs.lolik.web.obiekt.TipRazdela;
import ru.svs.lolik.web.obshee.PomoshnikChisel;
import ru.svs.lolik.web.odd.MuzikalniiAlbomTranzakciaODD;
import ru.svs.lolik.web.odd.RazdelODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 27.02.2011
 */
@Controller
public class ZagruzitMuzikalniiAlbomKontroller {
	private static final Logger LOGGER = Logger.getLogger(ZagruzitMuzikalniiAlbomKontroller.class);
	
	private static final String PAPKA_ZAGRUZOK 				= "/WEB-INF/zagruzka";

	private static final String MUZIKALNII_ALBOM_TRANZAKCIA = "muzikalniiAlbomTranzakcia";

	@Autowired
	private ApplicationContext kontekstLolikWeb;
	
	@Autowired
	private RazdelODD razdelODD;
	
	@Autowired
	private MuzikalniiAlbomTranzakciaODD muzikalniiAlbomTranzakciaODD;
	
	/**
	 * Метод перенаправляет пользователя на заглавную страницу, а потом на страницу загрузки музыкального альбома.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_OTKRIT_ZAGRUZKU_MUZIKALNOGO_ALBOMA_HTML, method=RequestMethod.GET)
    public ModelAndView otkritPokupku(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTKRIT_ZAGRUZKU_MUZIKALNOGO_ALBOMA_HTML);
		
		request.setAttribute("adresPerenapravlenia", KartaURL.URL_OTKRIT_ZAGRUZKU_MUZIKALNOGO_ALBOMA_JSON);
		
        return new ModelAndView(KartaURL.JSP_ZAGLAVNAIA);
    }
	
	/**
	 * Метод перенаправляет пользователя на фрагмент страницы загрузки музыкального альбома.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_OTKRIT_ZAGRUZKU_MUZIKALNOGO_ALBOMA_JSON, method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView otkritZagruzkuMuzikalnogoAlboma(ModelMap model, HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTKRIT_ZAGRUZKU_MUZIKALNOGO_ALBOMA_JSON);
		final String yazikKod = RequestContextUtils.getLocale(request).getLanguage();
		final Razdel razdel = razdelODD.naitiRazdel(yazikKod, TipRazdela.TIP_RAZDELA_MUZIKA_KOD, request.getRemoteHost());
		final List<MuzikalniiAlbomTranzakcia> muzikalniiAlbomiTranzakcii = muzikalniiAlbomTranzakciaODD.naitiTranzakciiMuzikalnixAlbomov(request.getParameter("pokupatelEmail"));
		
		request.setAttribute("razdel", razdel);
		request.setAttribute("muzikalniiAlbomiTranzakcii", muzikalniiAlbomiTranzakcii);
		
		final MuzikalniiAlbomTranzakcia muzikalniiAlbomTranzakcia = new MuzikalniiAlbomTranzakcia();
		muzikalniiAlbomTranzakcia.setPokupatelEmail(request.getParameter("pokupatelEmail"));
		model.addAttribute(MUZIKALNII_ALBOM_TRANZAKCIA, muzikalniiAlbomTranzakcia);
		
		return new ModelAndView(KartaURL.JSP_MUZIKALNII_ALBOM_ZAGRUZKA);
	}
	
	/**
	 * Метод исчет купленные пользователем, музыкальные альбомы.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value=KartaURL.URL_NAITI_MUZIKALNIE_ALBOMI_POLZOVATELA, method=RequestMethod.GET)
    public ModelAndView naitiMuzikalnieAlbomiPolzovatelia(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_NAITI_MUZIKALNIE_ALBOMI_POLZOVATELA);
		final List<MuzikalniiAlbomTranzakcia> muzikalniiAlbomiTranzakcii = muzikalniiAlbomTranzakciaODD.naitiTranzakciiMuzikalnixAlbomov(request.getParameter("pokupatelEmail"));
		final JSONObject jsonObject = new JSONObject();
		final JSONArray muzikalniiAlbomiTranzakciiJSON = new JSONArray();
		
		for (MuzikalniiAlbomTranzakcia muzikalniiAlbomTranzakcia : muzikalniiAlbomiTranzakcii) {
			final JSONObject muzikalniiAlbomTranzakciaJSON = new JSONObject();
			
			muzikalniiAlbomTranzakciaJSON.put("muzikalniiAlbomTranzakciaId", muzikalniiAlbomTranzakcia.getId());
			muzikalniiAlbomTranzakciaJSON.put("muzikalniiAlbomTranzakciaMuzikalniiAlbomNazvanie", muzikalniiAlbomTranzakcia.getMuzikalniiAlbom().getNazvanie());
			muzikalniiAlbomTranzakciaJSON.put("muzikalniiAlbomTranzakciaMuzikalniiAlbomIntegranti", muzikalniiAlbomTranzakcia.getMuzikalniiAlbom().getIntegranti());
			muzikalniiAlbomTranzakciaJSON.put("muzikalniiAlbomTranzakciaChislo", PomoshnikChisel.formatirovatChislo(muzikalniiAlbomTranzakcia.getChislo()));
			muzikalniiAlbomTranzakciaJSON.put("muzikalniiAlbomTranzakciaKolichestvoOstavshixsiaZagruzok", muzikalniiAlbomTranzakcia.getKolichestvoOstavshixsiaZagruzok());
			
			muzikalniiAlbomiTranzakciiJSON.add(muzikalniiAlbomTranzakciaJSON);
		}
		
		jsonObject.put("muzikalniiAlbomiTranzakcii", muzikalniiAlbomiTranzakciiJSON);
		jsonObject.put("muzikalniiAlbomiTranzakciiKolichestvo", muzikalniiAlbomiTranzakcii.size());
		
        return new ModelAndView("jsonView", jsonObject);
    }
	
	
	/**
	 * Метод перенаправляет пользователя на фрагмент страницы загрузки музыкального альбома.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_ZAGRUZIT_MUZIKALNII_ALBOM, method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
    public byte[] zagruzitMuzikalniiAlbom(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_ZAGRUZIT_MUZIKALNII_ALBOM);
		final MuzikalniiAlbomTranzakcia muzikalniiAlbomiTranzakcii = muzikalniiAlbomTranzakciaODD.naiti(MuzikalniiAlbomTranzakcia.class, Integer.valueOf(request.getParameter("muzikalniiAlbomTranzakcia.id")));
		byte[] muzikalniiAlbomFileBytes = null;
		
		if (muzikalniiAlbomiTranzakcii.getKolichestvoZagruzok() < MuzikalniiAlbomTranzakcia.MAKSIMALNOE_KOLICHESTVO_ZAGRUZOK) {
			final Resource muzikalniiAlbomFile = kontekstLolikWeb.getResource(PAPKA_ZAGRUZOK + muzikalniiAlbomiTranzakcii.getMuzikalniiAlbom().getSsilkaFail());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + muzikalniiAlbomFile.getFilename() + "\"");
		    response.setHeader("Pragma", "no-cache");
		    response.setHeader("Cache-Control", "no-cache");
		    response.setContentLength(konvertirovatRazmerFaila(muzikalniiAlbomFile, muzikalniiAlbomiTranzakcii));
		    muzikalniiAlbomFileBytes = konvertirovatFailVbyte(muzikalniiAlbomFile, muzikalniiAlbomiTranzakcii);

		    if (muzikalniiAlbomFileBytes != null) {
		    	muzikalniiAlbomiTranzakcii.uvelichitKolichestvoZagruzok();
		    	muzikalniiAlbomTranzakciaODD.soxranit(muzikalniiAlbomiTranzakcii);
		    }
		}
		
		return muzikalniiAlbomFileBytes;
	}

	/**
	 * Метод возвращяет размер файла, в случае ошибки пишет лог.
	 * 
	 * @param muzikalniiAlbomFile - Resource.
	 * @param muzikalniiAlbomiTranzakcii - MuzikalniiAlbomTranzakcia.
	 * 
	 * @return razmerFaila - int.
	 */
	private int konvertirovatRazmerFaila(Resource muzikalniiAlbomFile, MuzikalniiAlbomTranzakcia muzikalniiAlbomiTranzakcii) {
		int razmerFaila = 0;
		
		try {
			razmerFaila = (int) muzikalniiAlbomFile.contentLength();
		} catch (IOException e) {
			zapisatOshikuChteniaFaila(muzikalniiAlbomiTranzakcii, e);
		}
		
		return razmerFaila;
	}

	/**
	 * Метод возвращяет байты файла.
	 * 
	 * @param muzikalniiAlbomFile - Resource.
	 * @param muzikalniiAlbomiTranzakcii - MuzikalniiAlbomTranzakcia.
	 * 
	 * @return fail - byte[].
	 */
	private byte[] konvertirovatFailVbyte(Resource muzikalniiAlbomFile, MuzikalniiAlbomTranzakcia muzikalniiAlbomiTranzakcii) {
		byte[] fail = null;
		
		try {
			fail = IOUtils.toByteArray(muzikalniiAlbomFile.getInputStream());
		} catch (IOException e) {
			zapisatOshikuChteniaFaila(muzikalniiAlbomiTranzakcii, e);
		}
		
		return fail;
	}

	/**
	 * Метод записывает в лог данные об ошибке.
	 * 
	 * @param muzikalniiAlbomiTranzakcii - MuzikalniiAlbomTranzakcia.
	 * @param e - IOException.
	 */
	private void zapisatOshikuChteniaFaila(MuzikalniiAlbomTranzakcia muzikalniiAlbomiTranzakcii, IOException e) {
		LOGGER.error("Kriticheskaia oshibka pri chtenii faila muzikalnogo alboma, muzikalniiAlbomTranzakciiId = '" + muzikalniiAlbomiTranzakcii.getId() + "', pokupatelEmail = '"  + muzikalniiAlbomiTranzakcii.getPokupatelEmail() + "'", e);		
	}
}
