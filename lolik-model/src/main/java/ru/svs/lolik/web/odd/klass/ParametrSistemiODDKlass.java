package ru.svs.lolik.web.odd.klass;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ru.svs.lolik.web.obiekt.ParametrSistemi;
import ru.svs.lolik.web.odd.ParametrSistemiODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 25.03.2011
 */
@Repository
public class ParametrSistemiODDKlass extends ObshiiODDKlass implements ParametrSistemiODD {

	public ParametrSistemi naiti(String kluch) {
		final Query query = em.createQuery("SELECT ps " +
										   "  FROM ParametrSistemi ps" +
										   " WHERE ps.kluch = :kluch");
		query.setParameter("kluch", kluch);
		return (ParametrSistemi) query.getSingleResult();
	}
}
