package ru.svs.lolik.web.kontroller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/test.json", method=RequestMethod.GET)
    public ModelAndView testJSON(HttpServletRequest request) {
		System.out.println("/test.json");
		final JSONObject jsonObject = new JSONObject();
		jsonObject.put("message", request.getParameter("testParameter"));
        return new ModelAndView("jsonView", jsonObject);
    }
	
	@RequestMapping(value="/test.html", method=RequestMethod.GET)
    public ModelAndView testHTML(HttpServletRequest request) {
		System.out.println("/test.html");
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", request.getParameter("testParameter"));
		return new ModelAndView("Test", map);
    }
	
	@RequestMapping(value="/testGet.json", method=RequestMethod.GET)
    public ModelAndView testGet(HttpServletRequest request) {
		System.out.println("/testGet.json");
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("message", request.getParameter("testParameter"));
		return new ModelAndView("TestGet", map);
    }
}
