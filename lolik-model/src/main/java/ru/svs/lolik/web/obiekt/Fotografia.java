package ru.svs.lolik.web.obiekt;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import ru.svs.lolik.web.raznoe.Chislo;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 02.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="fotografia")
public class Fotografia extends AbstraktniyObiekt {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="fotografia_id")
	private Integer id;
	
	@Column(name="opisanie")
	private String opisanie;
	
	@Column(name="ssilka")
	private String ssilka;
	
	@Column(name="ssilka_malenkaia")
	private String ssilkaMalenkaia;
	
	@Column(name="aktivnaia")
	private Boolean aktivnaia;
	
	@OneToMany(targetEntity=FotografiaOcenka.class, mappedBy="fotografia", fetch=FetchType.LAZY)
	private List<Ocenka> ocenki;

	@Transient
	private Double srednaiaOcenka;

	@Transient
	private boolean segodniaOcenenPolzovatelem;
	
	
	public Double getOkruglonnuiuSrednuiuOcenku() {
		return Chislo.dvuxRazradnoeFormatirovanie(this.srednaiaOcenka);
	}
	
	
	/**
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id = id
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return aktivnaia
	 */
	public Boolean getAktivnaia() {
		return aktivnaia;
	}

	/**
	 * @param aktivnaia = aktivnaia
	 */
	public void setAktivnaia(Boolean aktivnaia) {
		this.aktivnaia = aktivnaia;
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
	 * @return the ssilka
	 */
	public String getSsilka() {
		return ssilka;
	}

	/**
	 * @param ssilka the ssilka to set
	 */
	public void setSsilka(String ssilka) {
		this.ssilka = ssilka;
	}

	/**
	 * @return ssilkaMalenkaia
	 */
	public String getSsilkaMalenkaia() {
		return ssilkaMalenkaia;
	}

	/**
	 * @param ssilkaMalenkaia = ssilkaMalenkaia
	 */
	public void setSsilkaMalenkaia(String ssilkaMalenkaia) {
		this.ssilkaMalenkaia = ssilkaMalenkaia;
	}

	/**
	 * @return srednaiaOcenka
	 */
	public Double getSrednaiaOcenka() {
		return srednaiaOcenka;
	}


	/**
	 * @param srednaiaOcenka = srednaiaOcenka
	 */
	public void setSrednaiaOcenka(Double srednaiaOcenka) {
		this.srednaiaOcenka = srednaiaOcenka;
	}


	/**
	 * @return segodniaOcenenPolzovatelem
	 */
	public boolean isSegodniaOcenenPolzovatelem() {
		return segodniaOcenenPolzovatelem;
	}


	/**
	 * @param segodniaOcenenPolzovatelem = segodniaOcenenPolzovatelem
	 */
	public void setSegodniaOcenenPolzovatelem(boolean segodniaOcenenPolzovatelem) {
		this.segodniaOcenenPolzovatelem = segodniaOcenenPolzovatelem;
	}
}