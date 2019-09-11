package ru.svs.lolik.web.odd;

import java.util.List;

import ru.svs.lolik.web.obiekt.Kompozicia;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 11.09.2011
 */
public interface KompoziciaODD extends ObshiiODD {

	List<Kompozicia> naitiKompozicii(Integer muzikalniiAlbomId, String ipAdres);
	
	Double vichislitSrednuiuOcenku(Integer kompoziciaId);
}
