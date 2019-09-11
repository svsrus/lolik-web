package ru.svs.lolik.web.raznoe;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Chislo {
	private static DecimalFormat dvuxRazradnoeChislo;
	
	static {
		final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
		decimalFormatSymbols.setDecimalSeparator('.');
		dvuxRazradnoeChislo = new DecimalFormat("#.##", decimalFormatSymbols);
		dvuxRazradnoeChislo.setGroupingUsed(false);
		dvuxRazradnoeChislo.setMaximumFractionDigits(2);
	}
	
	public static Double dvuxRazradnoeFormatirovanie(Double chislo) {
		if (chislo != null) {
			return Double.valueOf(dvuxRazradnoeChislo.format(chislo));
		}
		return null;
	}
}
