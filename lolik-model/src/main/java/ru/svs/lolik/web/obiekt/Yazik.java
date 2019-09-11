package ru.svs.lolik.web.obiekt;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 12.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="yazik")
public class Yazik extends AbstraktniyObiekt {

	@Id
	@Column(name="yazik_kod", length=2)
	private String kod;
	
	@Column(name="opisanie")
	private String opisanie;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="yazik", cascade={CascadeType.ALL})
	private LolikWeb lolikWeb;
	

	public void dobavitOcenkuRazdelGlavnaia(Integer tipRazdelaKod, Ocenka ocenka) {
		this.lolikWeb.dobavitOcenkuRazdela(tipRazdelaKod, ocenka);
	}
	
	/**
	 * @return kod
	 */
	public String getKod() {
		return kod;
	}

	/**
	 * @param kod = kod
	 */
	public void setKod(String kod) {
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
	 * @return lolikWeb
	 */
	public LolikWeb getLolikWeb() {
		return lolikWeb;
	}

	/**
	 * @param lolikWeb = lolikWeb
	 */
	public void setLolikWeb(LolikWeb lolikWeb) {
		this.lolikWeb = lolikWeb;
	}
}