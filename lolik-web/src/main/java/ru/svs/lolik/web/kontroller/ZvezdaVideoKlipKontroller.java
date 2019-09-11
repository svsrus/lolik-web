package ru.svs.lolik.web.kontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.svs.lolik.web.kontroller.url.KartaURL;
import ru.svs.lolik.web.obiekt.Ocenka;
import ru.svs.lolik.web.obiekt.VideoKlip;
import ru.svs.lolik.web.obiekt.VideoKlipOcenka;
import ru.svs.lolik.web.odd.VideoKlipODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 01.03.2011
 */
@Controller
public class ZvezdaVideoKlipKontroller extends AbstraktniyZvezdaKontroller {
	@Autowired
	private VideoKlipODD videoKlipODD;
	
	@RequestMapping(value=KartaURL.URL_OTMETIT_ZVEZDOI_VIDEO_KLIP, method=RequestMethod.GET)
    public ModelAndView otmetitZvezdoiVideoKlip(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTMETIT_ZVEZDOI_VIDEO_KLIP);
		return otmetitZvezdoiObiekt(request);
    }

	@Override
	protected Ocenka poluchitOcenku() {
		return new VideoKlipOcenka();
	}

	@Override
	protected void soxranitOcenku(Ocenka ocenka, Integer obiektId) {
		final VideoKlip videoKlip = videoKlipODD.naiti(VideoKlip.class, obiektId);
		final VideoKlipOcenka videoKlipOcenka = (VideoKlipOcenka) ocenka;
		videoKlipOcenka.setVideoKlip(videoKlip);
		videoKlipODD.soxranit(videoKlipOcenka);
	}

	@Override
	protected Double vichislitSrednuiuOcenkuObiekta(Integer obiektId) {
		return videoKlipODD.vichislitSrednuiuOcenku(obiektId);
	}
}
