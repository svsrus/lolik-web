package ru.svs.lolik.web.servis;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 26.03.2011
 */
public interface EmailServis {
	
	void poslatPismo(String ot, String komu, String tema, String soobshenie);
}
