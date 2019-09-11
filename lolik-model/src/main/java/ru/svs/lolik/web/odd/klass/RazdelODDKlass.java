package ru.svs.lolik.web.odd.klass;

import java.util.Date;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ru.svs.lolik.web.obiekt.Razdel;
import ru.svs.lolik.web.odd.RazdelODD;
import ru.svs.lolik.web.raznoe.Chislo;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 22.03.2011
 */
@Repository
public class RazdelODDKlass extends ObshiiODDKlass implements RazdelODD {

	/**
	 * Метод возвращяет объект раздела со средней оценкой и меткой если пользователь уже оценил этот раздел.
	 * 
	 * @param yazikKod - String.
	 * @param tipRazdelaKod - Integer.
	 * @param ipAdres - String.
	 * 
	 * @return srednaiaOcenka - Double.
	 */
	public Razdel naitiRazdel(String yazikKod, Integer tipRazdelaKod, String ipAdres) {
		final Query query = em.createQuery("SELECT r" +
										   "  FROM LolikWeb lw " +
										   "  JOIN lw.razdeli r " +
										   " WHERE lw.yazik.kod = :yazikKod" +
										   "   AND r.tipRazdela.kod = :tipRazdelaKod");

		query.setParameter("yazikKod", yazikKod);
		query.setParameter("tipRazdelaKod", tipRazdelaKod);
		
		final Razdel razdel = (Razdel) query.getSingleResult();
		razdel.setSrednaiaOcenka(vichislitSredniuOcenku(razdel.getKod()));
		razdel.setSegodniaOcenenPolzovatelem(polzovatelPostavilOcenkuSegodnia(razdel.getKod(), ipAdres));
		
		return razdel;
	}
	
	/**
	 * Метод возвращяет среднюю оценку данного языка и раздела.
	 * 
	 * @param yazikKod - String.
	 * 
	 * @param tipRazdelaKod - Integer.
	 * 
	 * @return srednaiaOcenka - Double.
	 */
	public Double vichislitSredniuOcenku(Integer razdelKod) {
		final Query query = em.createQuery("SELECT AVG(z.chislo) " +
										   "  FROM Razdel r " +
										   "  JOIN r.ocenki o" +
										   "  JOIN o.znachenie z " +
										   " WHERE r.kod = :razdelKod");
		
		query.setParameter("razdelKod", razdelKod);
		
		return Chislo.dvuxRazradnoeFormatirovanie((Double) query.getSingleResult());
	}
	
	/**
	 * Метод оценку данного со следующим фильтром: язык, раздел, пользователь и сегодняшнее число.
	 * 
	 * @param tipRazdelaKod - Integer.
	 * 
	 * @return srednaiaOcenka - Double.
	 */
	private Boolean polzovatelPostavilOcenkuSegodnia(Integer razdelKod, String ipAdres) {
		final Query query = em.createQuery("SELECT COUNT(o) " +
										   "  FROM Razdel r " +
										   "  JOIN r.ocenki o" +
										   " WHERE r.kod = :razdelKod" +
										   "   AND o.ipAdres = :ipAdres" +
										   "   AND DATE(o.chislo) = DATE(:chislo)");

		query.setParameter("razdelKod", razdelKod);
		query.setParameter("ipAdres", ipAdres);
		query.setParameter("chislo", new Date());
		
		return (Long) query.getSingleResult() > 0;
	}
}
