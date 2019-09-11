package ru.svs.lolik.web.obiekt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 14.10.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="tranzakcia_sostoianie")
public class TranzakciaSostoianie extends AbstraktniyObiekt {
	public static final Integer KOD_NACHALO_TRANZAKCII				 = 1;
	public static final Integer KOD_VVEDENA_INFORMACIA_TRANZAKCII	 = 2;
	public static final Integer KOD_PODTVERZHDENA_TRANZAKCIA		 = 3;
	public static final Integer KOD_USPESHNO_ZAKONCHENA_TRANZAKCIA	 = 4;
	public static final Integer KOD_OTMENA_TRANZAKCII				 = 5;
	public static final Integer KOD_OSHIBKA_TRANZAKCII				 = 6;
	public static final Integer KOD_KRITICHESKAIA_OSHIBKA_TRANZAKCII = 7;

	public TranzakciaSostoianie() {}
	
	public TranzakciaSostoianie(Integer kod) {
		this.kod = kod;
	}
	
	@Id
	@Column(name="tranzakcia_sostoianie_kod")
	private Integer kod;
	
	@Column(name="opisanie")
	private String opisanie;

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
}