package ru.svs.lolik.web.odd;

import ru.svs.lolik.web.obiekt.Razdel;


/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 22.03.2011
 */
public interface RazdelODD extends ObshiiODD {
	
	Razdel naitiRazdel(String yazikKod, Integer tipRazdelaKod, String ipAdres);

	Double vichislitSredniuOcenku(Integer razdelId);
}
