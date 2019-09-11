package ru.svs.lolik.web.kontroller;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import ru.svs.lolik.web.kontroller.konverter.VoprosKonverter;
import ru.svs.lolik.web.kontroller.pogranichnik.OprosPogranichnik;
import ru.svs.lolik.web.kontroller.url.KartaURL;
import ru.svs.lolik.web.obiekt.PolzovatelOtvet;
import ru.svs.lolik.web.obiekt.Vopros;
import ru.svs.lolik.web.odd.OprosODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 23.03.2011
 */
@Controller
public class OprosKontroller {
	private static final String POLZOVATEL_OTVET = "polzovatelOtvet";
	
	@Autowired
	private OprosPogranichnik oprosPogranichnik;
	
	@Autowired
	private OprosODD oprosODD;
	
	@InitBinder
    public void initBinder(WebDataBinder binder, WebRequest webRequest) {
        binder.registerCustomEditor(Vopros.class, new VoprosKonverter());
    }
		
	@RequestMapping(value=KartaURL.URL_OTKRIT_OPROS, method=RequestMethod.GET)
    public ModelAndView otkritOpros(ModelMap model, HttpServletRequest request) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTKRIT_OPROS);
		
		final PolzovatelOtvet polzovatelOtvet = new PolzovatelOtvet();
		polzovatelOtvet.setOpros(oprosODD.naitiOpros(RequestContextUtils.getLocale(request).getLanguage(), request.getRemoteAddr()));
		
		model.addAttribute(POLZOVATEL_OTVET, polzovatelOtvet);
		
		return new ModelAndView(KartaURL.JSP_OPROS);
    }

	@RequestMapping(value=KartaURL.URL_OTVETIT_NA_OPROS, method=RequestMethod.POST)
    public ModelAndView otvetitNaOpros(HttpServletRequest request, @ModelAttribute(POLZOVATEL_OTVET) PolzovatelOtvet polzovatelOtvet, BindingResult bindingResult, SessionStatus sessionStatus) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_OTVETIT_NA_OPROS);
		
		oprosPogranichnik.validate(polzovatelOtvet, bindingResult);
		
		if (bindingResult.hasErrors()) {
			polzovatelOtvet.setOpros(oprosODD.naitiOpros(RequestContextUtils.getLocale(request).getLanguage(), request.getRemoteAddr()));
		} else {
			polzovatelOtvet.setIpAdres(request.getRemoteAddr());
			polzovatelOtvet.setChislo(new Timestamp(new Date().getTime()));
			oprosODD.soxranit(polzovatelOtvet);
			
			polzovatelOtvet.setOpros(oprosODD.naitiRezultatiOprosa(polzovatelOtvet.getOpros().getId()));
			sessionStatus.setComplete();
		}
		
		return new ModelAndView(KartaURL.JSP_OPROS);
    }
}
