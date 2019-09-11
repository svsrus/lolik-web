package ru.svs.lolik.web.kontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.svs.lolik.web.kontroller.url.KartaURL;
import ru.svs.lolik.web.obiekt.Kompozicia;
import ru.svs.lolik.web.obiekt.KompoziciaOcenka;
import ru.svs.lolik.web.obiekt.Ocenka;
import ru.svs.lolik.web.odd.KompoziciaODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 11.09.2011
 */
@Controller
public class ZvezdaKompoziciaKontroller extends AbstraktniyZvezdaKontroller {
	@Autowired
	private KompoziciaODD kompoziciaODD;
	
	@RequestMapping(value=KartaURL.URL_OTMETIT_ZVEZDOI_KOMPOZICIU, method=RequestMethod.GET)
    public ModelAndView otmetitZvezdoiVideoKlip(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTMETIT_ZVEZDOI_KOMPOZICIU);
		return otmetitZvezdoiObiekt(request);
    }

	@Override
	protected Ocenka poluchitOcenku() {
		return new KompoziciaOcenka();
	}

	@Override
	protected void soxranitOcenku(Ocenka ocenka, Integer obiektId) {
		final Kompozicia kompozicia = kompoziciaODD.naiti(Kompozicia.class, obiektId);
		final KompoziciaOcenka kompoziciaOcenka = (KompoziciaOcenka) ocenka;
		kompoziciaOcenka.setKompozicia(kompozicia);
		kompoziciaODD.soxranit(kompoziciaOcenka);
	}

	@Override
	protected Double vichislitSrednuiuOcenkuObiekta(Integer obiektId) {
		return kompoziciaODD.vichislitSrednuiuOcenku(obiektId);
	}
}
