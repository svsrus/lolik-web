package ru.svs.lolik.web.kontroller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.svs.lolik.web.kontroller.url.KartaURL;
import ru.svs.lolik.web.obiekt.Ocenka;
import ru.svs.lolik.web.obiekt.Razdel;
import ru.svs.lolik.web.obiekt.RazdelOcenka;
import ru.svs.lolik.web.odd.RazdelODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 22.03.2011
 */
@Controller
public class ZvezdaRazdelKontroller extends AbstraktniyZvezdaKontroller {
	@Autowired
	private RazdelODD razdelODD;
	
	@RequestMapping(value=KartaURL.URL_OTMETIT_ZVEZDOI_RAZDEL, method=RequestMethod.GET)
    public ModelAndView otmetitZvezdoiRazdel(HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTMETIT_ZVEZDOI_RAZDEL);
		return otmetitZvezdoiObiekt(request);
    }

	@Override
	protected Ocenka poluchitOcenku() {
		return new RazdelOcenka();
	}

	@Override
	protected void soxranitOcenku(Ocenka ocenka, Integer obiektId) {
		final Razdel razdel = razdelODD.naiti(Razdel.class, obiektId);
		final RazdelOcenka razdelOcenka = (RazdelOcenka) ocenka;
		razdelOcenka.setRazdel(razdel);
		razdelODD.soxranit(razdelOcenka);
	}

	@Override
	protected Double vichislitSrednuiuOcenkuObiekta(Integer obiektId) {
		return razdelODD.vichislitSredniuOcenku(obiektId);
	}
}
