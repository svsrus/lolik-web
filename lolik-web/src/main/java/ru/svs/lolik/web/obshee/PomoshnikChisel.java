package ru.svs.lolik.web.obshee;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 15.11.2011
 */
public class PomoshnikChisel {
	final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd. HH:mm");
	
	public static String formatirovatChislo(Timestamp chislo) {
		return SIMPLE_DATE_FORMAT.format(chislo);
	}
}
