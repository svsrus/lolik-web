package ru.svs.lolik.web.odd;

import java.util.List;

import ru.svs.lolik.web.obiekt.VideoKlip;


/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 23.02.2011
 */
public interface VideoKlipODD extends ObshiiODD {
	
	List<VideoKlip> naitiVideoKlipi(Integer razdelKod, String ipAdres);

	Double vichislitSrednuiuOcenku(Integer videoKlipId);
}
