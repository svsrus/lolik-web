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
@Table(schema=AbstraktniyObiekt.SXEMA, name="razdel_ocenka")
public class RazdelOcenka extends Ocenka {
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Razdel.class)
	@JoinColumn(name="razdel_kod")
	private Razdel razdel;

	/**
	 * @return razdel
	 */
	public Razdel getRazdel() {
		return razdel;
	}

	/**
	 * @param razdel = razdel
	 */
	public void setRazdel(Razdel razdel) {
		this.razdel = razdel;
	}
}