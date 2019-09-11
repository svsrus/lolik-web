package ru.svs.lolik.web.odd;

import java.util.List;

import ru.svs.lolik.web.obiekt.Albom;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 02.02.2011
 */
public interface AlbomODD extends ObshiiODD {

	List<Albom> naitiAlbomi(Integer razdelKod);
}
