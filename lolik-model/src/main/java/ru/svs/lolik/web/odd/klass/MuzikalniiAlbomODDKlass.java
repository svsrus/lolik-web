package ru.svs.lolik.web.odd.klass;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ru.svs.lolik.web.obiekt.MuzikalniiAlbom;
import ru.svs.lolik.web.odd.MuzikalniiAlbomODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 11.09.2011
 */
@Repository
public class MuzikalniiAlbomODDKlass extends ObshiiODDKlass implements MuzikalniiAlbomODD {

	@Override
	public List<MuzikalniiAlbom> naitiMuzikalniiAlbomi(Integer razdelKod) {
		final List<MuzikalniiAlbom> muzikalniiAlbomi = naitiMuzikalniiAlbomiRazdela(razdelKod);
		postavitOcenkuMuzikalnimAlbomam(muzikalniiAlbomi);
		return muzikalniiAlbomi;
	}
	
	@SuppressWarnings("unchecked")
	private List<MuzikalniiAlbom> naitiMuzikalniiAlbomiRazdela(Integer razdelKod) {
		final Query query = em.createQuery("SELECT ma " +
										   "  FROM RazdelMuzika rm" +
										   "  JOIN rm.muzikalniiAlbomi ma " +
										   " WHERE rm.kod = :razdelKod");

		query.setParameter("razdelKod", razdelKod);
		return query.getResultList();
	}
	
	private void postavitOcenkuMuzikalnimAlbomam(List<MuzikalniiAlbom> muzikalniiAlbomi) {
		final Query query = em.createQuery("SELECT AVG(z.chislo) " +
										   "  FROM MuzikalniiAlbom ma" +
										   "  JOIN ma.kompozicii k " +
										   "  JOIN k.ocenki o" +
										   "  JOIN o.znachenie z " +
										   " WHERE ma.id = :muzikalniiAlbomId");

		for (MuzikalniiAlbom muzikalniiAlbom : muzikalniiAlbomi) {
			query.setParameter("muzikalniiAlbomId", muzikalniiAlbom.getId());
			muzikalniiAlbom.setSrednaiaOcenka((Double)query.getSingleResult());
		}
	}

}
