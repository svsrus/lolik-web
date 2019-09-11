package ru.svs.lolik.web.odd.klass;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ru.svs.lolik.web.obiekt.VideoKlip;
import ru.svs.lolik.web.odd.VideoKlipODD;
import ru.svs.lolik.web.raznoe.Chislo;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 23.02.2011
 */
@Repository
public class VideoKlipODDKlass extends ObshiiODDKlass implements VideoKlipODD {

	@Override
	public List<VideoKlip> naitiVideoKlipi(Integer razdelKod, String ipAdres) {
		final List<VideoKlip> videoKlipi = naitiVideoKlipiPoYaziku(razdelKod);
		postavitOcenkuVideoKlipam(videoKlipi);
		postavitOtmetkuOtmechenuiuPolzovatelem(videoKlipi, ipAdres);
		return videoKlipi;
	}

	@Override
	public Double vichislitSrednuiuOcenku(Integer videoKlipId) {
		final Query query = em.createQuery("SELECT AVG(z.chislo) " +
										   "  FROM VideoKlip vk " +
										   "  JOIN vk.ocenki o" +
										   "  JOIN o.znachenie z " +
										   " WHERE vk.id = :videoKlipId");

		query.setParameter("videoKlipId", videoKlipId);
		return Chislo.dvuxRazradnoeFormatirovanie((Double)query.getSingleResult());
	}
	
	@SuppressWarnings("unchecked")
	private List<VideoKlip> naitiVideoKlipiPoYaziku(Integer razdelKod) {
		final Query query = em.createQuery("SELECT vk " +
										   "  FROM RazdelVideo rv" +
										   "  JOIN rv.videoKlipi vk " +
										   " WHERE rv.kod = :razdelKod" +
										   "   AND vk.aktivnii = :aktivnii");

		query.setParameter("razdelKod", razdelKod);
		query.setParameter("aktivnii", Boolean.TRUE);
		return query.getResultList();
	}
	
	private void postavitOcenkuVideoKlipam(List<VideoKlip> videoKlipi) {
		final Query query = em.createQuery("SELECT AVG(z.chislo) " +
										   "  FROM VideoKlip vk " +
										   "  JOIN vk.ocenki o" +
										   "  JOIN o.znachenie z " +
										   " WHERE vk.id = :videoKlipId");

		for (VideoKlip videoKlip : videoKlipi) {
			query.setParameter("videoKlipId", videoKlip.getId());
			videoKlip.setSrednaiaOcenka((Double)query.getSingleResult());
		}
	}
	
	private void postavitOtmetkuOtmechenuiuPolzovatelem(List<VideoKlip> videoKlipi, String ipAdres) {
		final Date chislo = new Date();
		final Query query = em.createQuery("SELECT COUNT(o) " +
										   "  FROM VideoKlip vk " +
										   "  JOIN vk.ocenki o" +
										   " WHERE vk.id = :videoKlipId" +
										   "   AND o.ipAdres = :ipAdres" +
										   "   AND DATE(o.chislo) = DATE(:chislo)");
		
		query.setParameter("ipAdres", ipAdres);
		query.setParameter("chislo", chislo);
		
		for (VideoKlip videoKlip : videoKlipi) {
			query.setParameter("videoKlipId", videoKlip.getId());
			if ((Long) query.getSingleResult() > 0) {
				videoKlip.setSegodniaOcenenPolzovatelem(true);
			}
		}
	}
}
