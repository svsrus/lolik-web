package ru.svs.lolik.web.obiekt;

import javax.persistence.Column;
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
@Table(schema=AbstraktniyObiekt.SXEMA, name="muzikalnii_albom_tranzakcia")
public class MuzikalniiAlbomTranzakcia extends Tranzakcia {
	public static final int MAKSIMALNOE_KOLICHESTVO_ZAGRUZOK = 3;

	@Column(name="kolichestvo_zagruzok")
	private Integer kolichestvoZagruzok;
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=MuzikalniiAlbom.class)
	@JoinColumn(name="muzikalnii_albom_id")
	private MuzikalniiAlbom muzikalniiAlbom;
	
	
	public MuzikalniiAlbomTranzakcia() {
		this.kolichestvoZagruzok = 0;
	}
	
	public void uvelichitKolichestvoZagruzok() {
		this.kolichestvoZagruzok++;
	}
	
	public int getKolichestvoOstavshixsiaZagruzok() {
		return MAKSIMALNOE_KOLICHESTVO_ZAGRUZOK - this.kolichestvoZagruzok;
	}
	
	/**
	 * @return kolichestvoZagruzok
	 */
	public Integer getKolichestvoZagruzok() {
		return kolichestvoZagruzok;
	}

	/**
	 * @param kolichestvoZagruzok = kolichestvoZagruzok
	 */
	public void setKolichestvoZagruzok(Integer kolichestvoZagruzok) {
		this.kolichestvoZagruzok = kolichestvoZagruzok;
	}

	/**
	 * @return muzikalniiAlbom
	 */
	public MuzikalniiAlbom getMuzikalniiAlbom() {
		return muzikalniiAlbom;
	}

	/**
	 * @param muzikalniiAlbom = muzikalniiAlbom
	 */
	public void setMuzikalniiAlbom(MuzikalniiAlbom muzikalniiAlbom) {
		this.muzikalniiAlbom = muzikalniiAlbom;
	}
}
