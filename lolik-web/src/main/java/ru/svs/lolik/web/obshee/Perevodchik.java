package ru.svs.lolik.web.obshee;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 18.03.2011
 */
public class Perevodchik {

	public static BigDecimal perevesti(String chislo) {
		if (StringUtils.isNotBlank(chislo)) {
			return new BigDecimal(chislo);
		}
		return null;
	}
}
