package ru.svs.lolik.web.kontroller.konverter;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang.StringUtils;

import ru.svs.lolik.web.obiekt.Vopros;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 23.03.2011
 */
public class VoprosKonverter extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isNumeric(text)) {
			final Vopros vopros = new Vopros();
			vopros.setId(Integer.parseInt(text));
			setValue(vopros);
		}
	}
}
