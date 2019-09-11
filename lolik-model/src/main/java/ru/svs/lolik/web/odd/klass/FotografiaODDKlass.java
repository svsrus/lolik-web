package ru.svs.lolik.web.odd.klass;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ru.svs.lolik.web.obiekt.Fotografia;
import ru.svs.lolik.web.odd.FotografiaODD;
import ru.svs.lolik.web.raznoe.Chislo;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 06.02.2011
 */
@Repository
public class FotografiaODDKlass extends ObshiiODDKlass implements FotografiaODD {

	public List<Fotografia> naitiFotografii(Integer albomId, String ipAdres) {
		final List<Fotografia> fotografii = naitiFotografiiAlboma(albomId);
		postavitOcenkuFotografiam(fotografii);
		postavitOtmetkuOtmechenuiuPolzovatelem(fotografii, ipAdres);
		return fotografii;
	}
	
	public Double vichislitSrednuiuOcenku(Integer fotografiaId) {
		final Query query = em.createQuery("SELECT AVG(z.chislo) " +
										   "  FROM Fotografia f " +
										   "  JOIN f.ocenki o" +
										   "  JOIN o.znachenie z " +
										   " WHERE f.id = :fotografiaId");

		query.setParameter("fotografiaId", fotografiaId);
		return Chislo.dvuxRazradnoeFormatirovanie((Double)query.getSingleResult());
	}
	
	@SuppressWarnings("unchecked")
	private List<Fotografia> naitiFotografiiAlboma(Integer albomId) {
		final Query query = em.createQuery("SELECT f " +
										   "  FROM Albom a" +
										   "  JOIN a.fotografii f " +
										   " WHERE a.id = :albomId" +
										   "   AND f.aktivnaia = :aktivnaia" +
										   " ORDER BY f.id DESC");

		query.setParameter("albomId", albomId);
		query.setParameter("aktivnaia", Boolean.TRUE);
		return query.getResultList();
	}
	
	private void postavitOcenkuFotografiam(List<Fotografia> fotografii) {
		final Query query = em.createQuery("SELECT AVG(z.chislo) " +
										   "  FROM Fotografia f" +
										   "  JOIN f.ocenki o" +
										   "  JOIN o.znachenie z " +
										   " WHERE f.id = :fotografiaId");

		for (Fotografia fotografia : fotografii) {
			query.setParameter("fotografiaId", fotografia.getId());
			fotografia.setSrednaiaOcenka((Double)query.getSingleResult());
		}
	}
	
	private void postavitOtmetkuOtmechenuiuPolzovatelem(List<Fotografia> fotografii, String ipAdres) {
		final Query query = em.createQuery("SELECT COUNT(o) " +
										   "  FROM Fotografia f " +
										   "  JOIN f.ocenki o" +
										   " WHERE f.id = :fotografiaId" +
										   "   AND o.ipAdres = :ipAdres" +
										   "   AND DATE(o.chislo) = DATE(:chislo)");
		query.setParameter("ipAdres", ipAdres);
		query.setParameter("chislo", new Date());
		
		for (Fotografia fotografia : fotografii) {
			query.setParameter("fotografiaId", fotografia.getId());
			if ((Long) query.getSingleResult() > 0) {
				fotografia.setSegodniaOcenenPolzovatelem(true);
			}
		}
	}
}
