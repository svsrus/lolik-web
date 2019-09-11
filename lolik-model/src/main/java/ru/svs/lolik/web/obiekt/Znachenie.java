package ru.svs.lolik.web.obiekt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 02.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="znachenie")
public class Znachenie extends AbstraktniyObiekt {
	public static final Integer ZNACHENIE_KOD_1 = 1;
	public static final Integer ZNACHENIE_KOD_2 = 2;
	public static final Integer ZNACHENIE_KOD_3 = 3;
	public static final Integer ZNACHENIE_KOD_4 = 4;
	public static final Integer ZNACHENIE_KOD_5 = 5;
	
	@Id
	@Column(name="znachenie_kod")
	private Integer kod;
	
	@Column(name="opisanie",nullable=false)
	private String opisanie;
	
	@Column(name="chislo",nullable=false)
	private Integer chislo;

	
	/**
	 * @return kod
	 */
	public Integer getKod() {
		return kod;
	}

	/**
	 * @param kod = kod
	 */
	public void setKod(Integer kod) {
		this.kod = kod;
	}

	/**
	 * @return opisanie
	 */
	public String getOpisanie() {
		return opisanie;
	}

	/**
	 * @param opisanie = opisanie
	 */
	public void setOpisanie(String opisanie) {
		this.opisanie = opisanie;
	}

	/**
	 * @return chislo
	 */
	public Integer getChislo() {
		return chislo;
	}

	/**
	 * @param chislo = chislo
	 */
	public void setChislo(Integer chislo) {
		this.chislo = chislo;
	}
}