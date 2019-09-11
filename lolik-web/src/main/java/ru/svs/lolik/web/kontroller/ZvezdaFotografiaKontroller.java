package ru.svs.lolik.web.kontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.svs.lolik.web.kontroller.url.KartaURL;
import ru.svs.lolik.web.obiekt.Fotografia;
import ru.svs.lolik.web.obiekt.FotografiaOcenka;
import ru.svs.lolik.web.obiekt.Ocenka;
import ru.svs.lolik.web.odd.FotografiaODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 01.03.2011
 */
@Controller
public class ZvezdaFotografiaKontroller extends AbstraktniyZvezdaKontroller {
	@Autowired
	private FotografiaODD fotografiaODD;
	
	@RequestMapping(value=KartaURL.URL_OTMETIT_ZVEZDOI_FOTOGRAFIU, method=RequestMethod.GET)
    public ModelAndView otmetitZvezdoiVideoKlip(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTMETIT_ZVEZDOI_FOTOGRAFIU);
		return otmetitZvezdoiObiekt(request);
    }

	@Override
	protected Ocenka poluchitOcenku() {
		return new FotografiaOcenka();
	}

	@Override
	protected void soxranitOcenku(Ocenka ocenka, Integer obiektId) {
		final Fotografia fotografia = fotografiaODD.naiti(Fotografia.class, obiektId);
		final FotografiaOcenka fotografiaOcenka = (FotografiaOcenka) ocenka;
		fotografiaOcenka.setFotografia(fotografia);
		fotografiaODD.soxranit(fotografiaOcenka);
	}

	@Override
	protected Double vichislitSrednuiuOcenkuObiekta(Integer obiektId) {
		return fotografiaODD.vichislitSrednuiuOcenku(obiektId);
	}
}
