package ru.svs.lolik.web.odd.klass;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ru.svs.lolik.web.obiekt.Kompozicia;
import ru.svs.lolik.web.odd.KompoziciaODD;
import ru.svs.lolik.web.raznoe.Chislo;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 11.09.2011
 */
@Repository
public class KompoziciaODDKlass extends ObshiiODDKlass implements KompoziciaODD {

	public List<Kompozicia> naitiKompozicii(Integer albomId, String ipAdres) {
		final List<Kompozicia> kompozicii = naitiKompoziciiMuzikalnogoAlboma(albomId);
		postavitOcenkuKompoziciam(kompozicii);
		postavitOtmetkuOtmechenuiuPolzovatelem(kompozicii, ipAdres);
		return kompozicii;
	}
	
	public Double vichislitSrednuiuOcenku(Integer kompoziciaId) {
		final Query query = em.createQuery("SELECT AVG(z.chislo) " +
										   "  FROM Kompozicia k " +
										   "  JOIN k.ocenki o" +
										   "  JOIN o.znachenie z " +
										   " WHERE k.id = :kompoziciaId");

		query.setParameter("kompoziciaId", kompoziciaId);
		return Chislo.dvuxRazradnoeFormatirovanie((Double)query.getSingleResult());
	}
	
	@SuppressWarnings("unchecked")
	private List<Kompozicia> naitiKompoziciiMuzikalnogoAlboma(Integer muzikalniiAlbomId) {
		final Query query = em.createQuery("SELECT k " +
										   "  FROM MuzikalniiAlbom ma" +
										   "  JOIN ma.kompozicii k " +
										   " WHERE ma.id = :muzikalniiAlbomId");

		query.setParameter("muzikalniiAlbomId", muzikalniiAlbomId);
		return query.getResultList();
	}
	
	private void postavitOcenkuKompoziciam(List<Kompozicia> kompozicii) {
		final Query query = em.createQuery("SELECT AVG(z.chislo) " +
										   "  FROM Kompozicia k" +
										   "  JOIN k.ocenki o" +
										   "  JOIN o.znachenie z " +
										   " WHERE k.id = :kompoziciaId");

		for (Kompozicia kompozicia : kompozicii) {
			query.setParameter("kompoziciaId", kompozicia.getId());
			kompozicia.setSrednaiaOcenka((Double)query.getSingleResult());
		}
	}
	
	private void postavitOtmetkuOtmechenuiuPolzovatelem(List<Kompozicia> kompozicii, String ipAdres) {
		final Query query = em.createQuery("SELECT COUNT(o) " +
										   "  FROM Kompozicia k " +
										   "  JOIN k.ocenki o" +
										   " WHERE k.id = :kompoziciaId" +
										   "   AND o.ipAdres = :ipAdres" +
										   "   AND DATE(o.chislo) = DATE(:chislo)");
		query.setParameter("ipAdres", ipAdres);
		query.setParameter("chislo", new Date());
		
		for (Kompozicia kompozicia : kompozicii) {
			query.setParameter("kompoziciaId", kompozicia.getId());
			if ((Long) query.getSingleResult() > 0) {
				kompozicia.setSegodniaOcenenPolzovatelem(true);
			}
		}
	}
}
