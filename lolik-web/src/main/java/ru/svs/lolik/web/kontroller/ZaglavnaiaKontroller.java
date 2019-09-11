package ru.svs.lolik.web.kontroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ru.svs.lolik.web.kontroller.url.KartaURL;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 10.01.2011
 */
@Controller
public class ZaglavnaiaKontroller {

	@RequestMapping(value=KartaURL.URL_ZAGLAVNAIA, method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView smenitYazik(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getRemoteHost() + KartaURL.URL_ZAGLAVNAIA);
		return new ModelAndView(KartaURL.JSP_ZAGLAVNAIA);
    }
}
