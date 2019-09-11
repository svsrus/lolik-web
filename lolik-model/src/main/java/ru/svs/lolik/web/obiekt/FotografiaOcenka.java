package ru.svs.lolik.web.obiekt;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 14.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="fotografia_ocenka")
public class FotografiaOcenka extends Ocenka {
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Fotografia.class)
	@JoinColumn(name="fotografia_id")
	private Fotografia fotografia;

	/**
	 * @return fotografia
	 */
	public Fotografia getFotografia() {
		return fotografia;
	}

	/**
	 * @param fotografia = fotografia
	 */
	public void setFotografia(Fotografia fotografia) {
		this.fotografia = fotografia;
	}
}