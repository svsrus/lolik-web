package ru.svs.lolik.web.odd;


/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 22.03.2011
 */
public interface ObshiiODD {

	<T> T naiti(Class<T> klass, Object kluch);
	
	void soxranitNovii(Object obiekt);
	
	void soxranit(Object obiekt);
}
