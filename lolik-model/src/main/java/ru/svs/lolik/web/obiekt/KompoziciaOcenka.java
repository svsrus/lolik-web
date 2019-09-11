package ru.svs.lolik.web.obiekt;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 11.09.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="kompozicia_ocenka")
public class KompoziciaOcenka extends Ocenka {
		
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Kompozicia.class)
	@JoinColumn(name="kompozicia_id")
	private Kompozicia kompozicia;

	
	/**
	 * @return kompozicia
	 */
	public Kompozicia getKompozicia() {
		return kompozicia;
	}

	/**
	 * @param kompozicia = kompozicia
	 */
	public void setKompozicia(Kompozicia kompozicia) {
		this.kompozicia = kompozicia;
	}
}
