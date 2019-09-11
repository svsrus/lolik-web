package ru.svs.lolik.web.odd.klass;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ru.svs.lolik.web.obiekt.MuzikalniiAlbomTranzakcia;
import ru.svs.lolik.web.odd.MuzikalniiAlbomTranzakciaODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 31.10.2011
 */
@Repository
public class MuzikalniiAlbomTranzakciaKlassODD extends ObshiiODDKlass implements MuzikalniiAlbomTranzakciaODD {

	@Override
	@SuppressWarnings("unchecked")
	public List<MuzikalniiAlbomTranzakcia> naitiTranzakciiMuzikalnixAlbomov(String pokupatelEmail) {
		final Query query = em.createQuery("SELECT mat " +
										   "  FROM MuzikalniiAlbomTranzakcia mat" +
										   " WHERE mat.pokupatelEmail = :pokupatelEmail");

		query.setParameter("pokupatelEmail", pokupatelEmail);
		return query.getResultList();
	}
}
