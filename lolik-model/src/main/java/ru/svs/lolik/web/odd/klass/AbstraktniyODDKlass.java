package ru.svs.lolik.web.odd.klass;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 19.03.2011
 */
public abstract class AbstraktniyODDKlass {
	@PersistenceContext(unitName="lolikWebPersistanceUnit")
	protected EntityManager em;
	
}
