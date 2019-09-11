package ru.svs.lolik.web.odd.klass;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.svs.lolik.web.odd.ObshiiODD;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 22.03.2011
 */
@Repository
public class ObshiiODDKlass extends AbstraktniyODDKlass implements ObshiiODD {

	@Override
	public <T> T naiti(Class<T> klass, Object kluch) {
		return em.find(klass, kluch);
	}
	
	@Transactional
	@Override
	public void soxranitNovii(Object obiekt) {
		em.persist(obiekt);
	}

	@Transactional
	@Override
	public void soxranit(Object obiekt) {
		em.merge(obiekt);
	}
}
