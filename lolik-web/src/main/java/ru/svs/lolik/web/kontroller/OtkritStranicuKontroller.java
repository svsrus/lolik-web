package ru.svs.lolik.web.kontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import ru.svs.lolik.web.kontroller.url.KartaURL;
import ru.svs.lolik.web.obiekt.Albom;
import ru.svs.lolik.web.obiekt.Fotografia;
import ru.svs.lolik.web.obiekt.Kompozicia;
import ru.svs.lolik.web.obiekt.MuzikalniiAlbom;
import ru.svs.lolik.web.obiekt.Razdel;
import ru.svs.lolik.web.obiekt.TipRazdela;
import ru.svs.lolik.web.obiekt.VideoKlip;
import ru.svs.lolik.web.odd.AlbomODD;
import ru.svs.lolik.web.odd.FotografiaODD;
import ru.svs.lolik.web.odd.KompoziciaODD;
import ru.svs.lolik.web.odd.MuzikalniiAlbomODD;
import ru.svs.lolik.web.odd.RazdelODD;
import ru.svs.lolik.web.odd.VideoKlipODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 28.02.2011
 */
@Controller
public class OtkritStranicuKontroller {
	@Autowired
	private RazdelODD razdelODD;
	
	@Autowired
	private AlbomODD albomODD;
	
	@Autowired
	private MuzikalniiAlbomODD muzikalniiAlbomODD;
	
	@Autowired
	private KompoziciaODD kompoziciaODD;
	
	@Autowired
	private FotografiaODD fotografiaODD;
	
	@Autowired
	private VideoKlipODD videoKlipODD;
	
	/**
	 * Метод открывает запрошенную страницу.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_OTKRIT_STRANICU, method=RequestMethod.GET)
    public ModelAndView otkritStranicu(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTKRIT_STRANICU + "?stranica=" + request.getParameter("tipRazdelaKod"));
		
		final String  yazikKod      = RequestContextUtils.getLocale(request).getLanguage();
		final Integer tipRazdelaKod = Integer.valueOf(request.getParameter("tipRazdelaKod"));
		final Razdel razdel = razdelODD.naitiRazdel(yazikKod, tipRazdelaKod, request.getRemoteHost());
		request.setAttribute("razdel", razdel);
		
		return new ModelAndView(KartaURL.sleduiuschaiaStranica(razdel.getTipRazdela().getKod()));
    }

	/**
	 * Метод открывает страницу музыки с различными альбомами.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_OTKRIT_STRANICU_MUZIKI, method=RequestMethod.GET)
    public ModelAndView otkritStranicuMuziki(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTKRIT_STRANICU_MUZIKI);
		
		final Razdel	  razdel = (Razdel) request.getAttribute("razdel");
		final List<MuzikalniiAlbom> muzikalniiAlbomi = muzikalniiAlbomODD.naitiMuzikalniiAlbomi(razdel.getKod());
		request.setAttribute("muzikalniiAlbomi", muzikalniiAlbomi);
		
		return new ModelAndView(KartaURL.JSP_MUZIKA);
    }
	
	/**
	 * Метод открывает страницу выбранного альмома.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_OTKRIT_STRANICU_MUZIKALNII_ALBOM, method=RequestMethod.GET)
    public ModelAndView otkritStranicuMuzikalniiAlbom(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTKRIT_STRANICU_MUZIKALNII_ALBOM);
		
		final String  yazikKod      = RequestContextUtils.getLocale(request).getLanguage();
		final Razdel razdel = razdelODD.naitiRazdel(yazikKod, TipRazdela.TIP_RAZDELA_MUZIKA_KOD, request.getRemoteHost());
		request.setAttribute("razdel", razdel);
		
		final MuzikalniiAlbom	muzikalniiAlbom 	= muzikalniiAlbomODD.naiti(MuzikalniiAlbom.class, new Integer(request.getParameter("muzikalniiAlbomId"))); 
		final List<Kompozicia> 	kompozicii			= kompoziciaODD.naitiKompozicii(muzikalniiAlbom.getId(), request.getRemoteHost());
		request.setAttribute("muzikalniiAlbom", muzikalniiAlbom);
		request.setAttribute("kompozicii", kompozicii);
		
		return new ModelAndView(KartaURL.JSP_MUZIKALNII_ALBOM);
    }
	
	/**
	 * Метод открывает страницу фото альбомов.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_OTKRIT_STRANICU_ALBOMOV, method=RequestMethod.GET)
    public ModelAndView otkritStranicuAlbomov(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTKRIT_STRANICU_ALBOMOV);
		
		final Razdel	  razdel = (Razdel) request.getAttribute("razdel");
		final List<Albom> albomi = albomODD.naitiAlbomi(razdel.getKod());
		request.setAttribute("albomi", albomi);
		
		return new ModelAndView(KartaURL.JSP_GALEREIA);
    }
	
	/**
	 * Метод открывает страницу альбома с фотографиями.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_OTKRIT_STRANICU_FOTOGRAFII, method=RequestMethod.GET)
    public ModelAndView otkritStranicuFotografii(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTKRIT_STRANICU_FOTOGRAFII);
		
		final String  yazikKod      = RequestContextUtils.getLocale(request).getLanguage();
		final Razdel razdel = razdelODD.naitiRazdel(yazikKod, TipRazdela.TIP_RAZDELA_GALEREIA_KOD, request.getRemoteHost());
		request.setAttribute("razdel", razdel);
		
		final Integer			albomId		= new Integer(request.getParameter("albomId"));
		final List<Fotografia> 	fotografii	= fotografiaODD.naitiFotografii(albomId, request.getRemoteHost());
		request.setAttribute("fotografii", fotografii);
		
		return new ModelAndView(KartaURL.JSP_FOTOGRAFII);
    }
	
	/**
	 * Метод открывает страницу с видеозаписями.
	 * 
	 * @param request - HttpServletRequest.
	 * 
	 * @return ModelAndView.
	 */
	@RequestMapping(value=KartaURL.URL_OTKRIT_STRANICU_VIDEO, method=RequestMethod.GET)
    public ModelAndView otkritStranicuVideo(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTKRIT_STRANICU_VIDEO);
		
		final Razdel		  razdel	 = (Razdel) request.getAttribute("razdel");
		final List<VideoKlip> videoKlipi = videoKlipODD.naitiVideoKlipi(razdel.getKod(), request.getRemoteHost());
		request.setAttribute("videoKlipi", videoKlipi);
		
		return new ModelAndView(KartaURL.JSP_VIDEO);
    }
}
