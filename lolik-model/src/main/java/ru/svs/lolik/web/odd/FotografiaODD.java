package ru.svs.lolik.web.odd;

import java.util.List;

import ru.svs.lolik.web.obiekt.Fotografia;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 06.02.2011
 */
public interface FotografiaODD extends ObshiiODD {

	List<Fotografia> naitiFotografii(Integer albomId, String ipAdres);
	
	Double vichislitSrednuiuOcenku(Integer fotografiaId);
}
