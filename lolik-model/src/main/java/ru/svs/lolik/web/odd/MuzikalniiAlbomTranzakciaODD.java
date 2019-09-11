package ru.svs.lolik.web.odd;

import java.util.List;

import ru.svs.lolik.web.obiekt.MuzikalniiAlbomTranzakcia;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 30.10.2011
 */
public interface MuzikalniiAlbomTranzakciaODD extends ObshiiODD {

	List<MuzikalniiAlbomTranzakcia> naitiTranzakciiMuzikalnixAlbomov(String pokupatelEmail);
}
