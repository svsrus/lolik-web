package ru.svs.lolik.web.obiekt;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 02.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="lolik_web")
public class LolikWeb extends AbstraktniyObiekt {
	@Id
	@Column(name="lolik_web_kod")
	private Integer kod;
	
	@Column(name="opisanie")
	private String opisanie;
	
	@OneToMany(fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	@JoinColumn(name="lolik_web_kod")
	private List<Razdel> razdeli;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="lolik_web_kod")
	private List<Opros> oprosi;
	
	@OneToMany(targetEntity=LolikWebOcenka.class, fetch=FetchType.LAZY)
	@JoinColumn(name="lolik_web_kod")
	private List<Ocenka> ocenki;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="yazik_kod")
	private Yazik yazik;

	

	public void dobavitOcenkuRazdela(Integer tipRazdelaKod, Ocenka ocenka) {
		getRazdel(tipRazdelaKod).dobavitOcenku(ocenka);
	}

	public Razdel getRazdel(Integer tipRazdelaKod) {
		for (Razdel razdel : razdeli) {
			if (razdel.tipa(tipRazdelaKod)) {
				return razdel;
			}
		}
		return null;
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
	 * @return razdeli
	 */
	public List<Razdel> getRazdeli() {
		return razdeli;
	}

	/**
	 * @param razdeli = razdeli
	 */
	public void setRazdeli(List<Razdel> razdeli) {
		this.razdeli = razdeli;
	}

	/**
	 * @return oprosi
	 */
	public List<Opros> getOprosi() {
		return oprosi;
	}

	/**
	 * @param oprosi = oprosi
	 */
	public void setOprosi(List<Opros> oprosi) {
		this.oprosi = oprosi;
	}

	/**
	 * @return ocenki
	 */
	public List<Ocenka> getOcenki() {
		return ocenki;
	}

	/**
	 * @param ocenki = ocenki
	 */
	public void setOcenki(List<Ocenka> ocenki) {
		this.ocenki = ocenki;
	}

	/**
	 * @return yazik
	 */
	public Yazik getYazik() {
		return yazik;
	}

	/**
	 * @param yazik = yazik
	 */
	public void setYazik(Yazik yazik) {
		this.yazik = yazik;
	}
}