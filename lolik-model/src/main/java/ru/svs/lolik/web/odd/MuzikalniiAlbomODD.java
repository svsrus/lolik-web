package ru.svs.lolik.web.odd;

import java.util.List;

import ru.svs.lolik.web.obiekt.MuzikalniiAlbom;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 11.09.2011
 */
public interface MuzikalniiAlbomODD extends ObshiiODD {

	List<MuzikalniiAlbom> naitiMuzikalniiAlbomi(Integer razdelKod);
}
