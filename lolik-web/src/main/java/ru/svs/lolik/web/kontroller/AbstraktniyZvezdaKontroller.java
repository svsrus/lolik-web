package ru.svs.lolik.web.kontroller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import ru.svs.lolik.web.obiekt.Ocenka;
import ru.svs.lolik.web.obiekt.Znachenie;
import ru.svs.lolik.web.odd.ObshiiODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 01.03.2011
 */

@Controller
public abstract class AbstraktniyZvezdaKontroller {
	@Autowired
	@Qualifier(value="obshiiODDKlass")
	private ObshiiODD obshiiODD;
	
	public ModelAndView otmetitZvezdoiObiekt(HttpServletRequest request) {
		final Integer znachenieKod  = Integer.valueOf(request.getParameter("znachenie"));
		final Integer obiektId      = Integer.valueOf(request.getParameter("obiektId"));
		final Ocenka  ocenka		= poluchitOcenku();
		zapolnitOcenku(ocenka, znachenieKod, obiektId, request.getRemoteAddr());
		soxranitOcenku(ocenka, obiektId);
		return vichislitOcenku(obiektId);
	}

	protected abstract Ocenka poluchitOcenku();

	protected abstract void soxranitOcenku(Ocenka ocenka, Integer obiektId);
	
	protected abstract Double vichislitSrednuiuOcenkuObiekta(Integer obiektId);
	
	private void zapolnitOcenku(Ocenka ocenka, Integer znachenie, Integer tipRazdelaKod, String idAdres) {
		ocenka.setChislo(new Timestamp(new Date().getTime()));
		ocenka.setIpAdres(idAdres);
		ocenka.setZnachenie(obshiiODD.naiti(Znachenie.class, znachenie));
	}

	@SuppressWarnings("unchecked")
	private ModelAndView vichislitOcenku(Integer obiektId) {
		final JSONObject jsonObject  = new JSONObject();
		jsonObject.put("srednaiaOcenkaObiekta", vichislitSrednuiuOcenkuObiekta(obiektId));
		return new ModelAndView("jsonView", jsonObject);
	}
}
