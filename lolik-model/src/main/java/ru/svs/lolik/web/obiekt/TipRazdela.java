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
@Table(schema=AbstraktniyObiekt.SXEMA, name="tip_razdela")
public class TipRazdela extends AbstraktniyObiekt {
	public static final Integer TIP_RAZDELA_GLAVNAIA_KOD	= 1;
	public static final Integer TIP_RAZDELA_BIOGRAFIA_KOD	= 2;
	public static final Integer TIP_RAZDELA_PROEKTY_KOD		= 3;
	public static final Integer TIP_RAZDELA_MUZIKA_KOD		= 4;
	public static final Integer TIP_RAZDELA_GALEREIA_KOD	= 5;
	public static final Integer TIP_RAZDELA_VIDEO_KOD		= 6;
	public static final Integer TIP_RAZDELA_KONTAKT_KOD		= 7;
	
	@Id
	@Column(name="tip_razdela_kod")
	private Integer kod;
	
	@Column(name="nazvanie")
	private String nazvanie;

	
	public boolean ravenTipu(Integer tipRazdelaKod) {
		return this.kod.equals(tipRazdelaKod);
	}
	
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
	 * @return nazvanie
	 */
	public String getNazvanie() {
		return nazvanie;
	}

	/**
	 * @param nazvanie = nazvanie
	 */
	public void setNazvanie(String nazvanie) {
		this.nazvanie = nazvanie;
	}
}