package ru.svs.lolik.web.kontroller.url;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 01.03.2011
 */
public class URLKodirovshik {
	private static final String UTF_8 = "UTF-8";
	
	public static String zakodirovatUTF8(String url) {
		try {
			return URLEncoder.encode(url, UTF_8);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
