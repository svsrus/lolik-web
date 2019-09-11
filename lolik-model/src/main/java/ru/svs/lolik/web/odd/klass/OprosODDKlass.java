package ru.svs.lolik.web.odd.klass;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ru.svs.lolik.web.obiekt.Opros;
import ru.svs.lolik.web.obiekt.Vopros;
import ru.svs.lolik.web.odd.OprosODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 22.03.2011
 */
@Repository
public class OprosODDKlass extends ObshiiODDKlass implements OprosODD {

	/**
	 * Метод ищет опрос, если пользователь сегодня ответил на опрос, то идёт расчёт результатов опроса.
	 * 
	 * @param yazikKod - String.
	 * @param ipAdres - String.
	 * 
	 * @return opros - Opros.
	 */
	@Override
	public Opros naitiOpros(String yazikKod, String ipAdres) {
		final Opros opros = naitiOpros(yazikKod);
		opros.setSegodniaPolzovatelOtvetil(polzovatelOtvetilNaOprosSegodnia(opros.getId(), ipAdres));
		if (Boolean.TRUE.equals(opros.getSegodniaPolzovatelOtvetil())) {
			postavitRezultatiVoprosov(opros, naitiRezultatiVoprosov(opros.getId()));
		}
		return opros;
	}

	/**
	 * Метод ищет результаты опроса, подразумевая что пользователь только что ответил на опрос.
	 * 
	 * @param oprosId - Integer.
	 * 
	 * @return opros - Opros.
	 */
	@Override
	public Opros naitiRezultatiOprosa(Integer oprosId) {
		final Opros opros = naiti(Opros.class, oprosId);
		opros.setSegodniaPolzovatelOtvetil(Boolean.TRUE);
		postavitRezultatiVoprosov(opros, naitiRezultatiVoprosov(oprosId));
		return opros;
	}

	private Opros naitiOpros(String yazikKod) {
		final Query query = em.createQuery("SELECT o " +
				   "  FROM LolikWeb lw" +
				   "  JOIN lw.oprosi o " +
				   " WHERE lw.yazik.kod = :yazikKod" +
				   "   AND o.aktivnii = :aktivnii");

		query.setParameter("yazikKod", yazikKod);
		query.setParameter("aktivnii", Boolean.TRUE);
		
		return (Opros) query.getSingleResult();
	}
	
	private Boolean polzovatelOtvetilNaOprosSegodnia(Integer oprosId, String ipAdres) {
		final Query query = em.createQuery("SELECT COUNT(po) " +
										   "  FROM PolzovatelOtvet po " +
										   " WHERE po.opros.id = :oprosId" +
										   "   AND po.ipAdres = :ipAdres" +
										   "   AND DATE(po.chislo) = DATE(:chislo)");

		query.setParameter("oprosId", oprosId);
		query.setParameter("ipAdres", ipAdres);
		query.setParameter("chislo", new Date());
		
		return (Long) query.getSingleResult() > 0;
	}
	
	@SuppressWarnings("unchecked")
	private List<Object[]> naitiRezultatiVoprosov(Integer oprosId) {
		final Query query = em.createQuery("SELECT v.id, count(v.id) " +
										   "  FROM Opros o" +
										   "  JOIN o.voprosi v" +
										   "  JOIN v.otveti ot " +
										   " WHERE o.id = :oprosId" +
										   " GROUP BY v.id");
		query.setParameter("oprosId", oprosId);
		
		return query.getResultList();
	}

	private void postavitRezultatiVoprosov(Opros opros, List<Object[]> naitiRezultatiVoprosov) {
		Long oprosKolichestvoOtvetov = new Long(0);
		for (Vopros vopros : opros.getVoprosi()) {
			for (Object[] voprosKolichestvoOtvetov : naitiRezultatiVoprosov) {
				if (vopros.getId().equals(voprosKolichestvoOtvetov[0])) {
					vopros.setKolichestvoOtvetov((Long)voprosKolichestvoOtvetov[1]);
					oprosKolichestvoOtvetov += (Long)voprosKolichestvoOtvetov[1];
				}
			}
		}
		opros.setKolichestvoOtvetov(oprosKolichestvoOtvetov);
	}
}
