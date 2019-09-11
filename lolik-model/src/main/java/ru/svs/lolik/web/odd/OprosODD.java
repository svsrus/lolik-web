package ru.svs.lolik.web.odd;

import ru.svs.lolik.web.obiekt.Opros;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 22.03.2011
 */
public interface OprosODD extends ObshiiODD {

	Opros naitiOpros(String yazikKod, String ipAdres);
	
	Opros naitiRezultatiOprosa(Integer oprosId);
	
}
