package ru.svs.lolik.web.odd.klass;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ru.svs.lolik.web.obiekt.Albom;
import ru.svs.lolik.web.odd.AlbomODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 02.02.2011
 */
@Repository
public class AlbomODDKlass extends ObshiiODDKlass implements AlbomODD {

	@Override
	public List<Albom> naitiAlbomi(Integer razdelKod) {
		final List<Albom> albomi = naitiAlbomiRazdela(razdelKod);
		postavitOcenkuAlbomam(albomi);
		return albomi;
	}
	
	@SuppressWarnings("unchecked")
	private List<Albom> naitiAlbomiRazdela(Integer razdelKod) {
		final Query query = em.createQuery("SELECT a " +
										   "  FROM RazdelFotografii rf" +
										   "  JOIN rf.albomi a " +
										   " WHERE rf.kod = :razdelKod");

		query.setParameter("razdelKod", razdelKod);
		return query.getResultList();
	}
	
	private void postavitOcenkuAlbomam(List<Albom> albomi) {
		final Query query = em.createQuery("SELECT AVG(z.chislo) " +
										   "  FROM Albom a" +
										   "  JOIN a.fotografii f " +
										   "  JOIN f.ocenki o" +
										   "  JOIN o.znachenie z " +
										   " WHERE a.id = :albomId");

		for (Albom albom : albomi) {
			query.setParameter("albomId", albom.getId());
			albom.setSrednaiaOcenka((Double)query.getSingleResult());
		}
	}
}
