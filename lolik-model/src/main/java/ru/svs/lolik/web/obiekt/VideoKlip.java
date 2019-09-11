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
@Table(schema=AbstraktniyObiekt.SXEMA, name="video_klip")
public class VideoKlip extends AbstraktniyObiekt {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="video_klip_id")
	private Integer id;
	
	@Column(name="nazvanie")
	private String nazvanie;
	
	@Column(name="opisanie")
	private String opisanie;
	
	@Column(name="ssilka")
	private String ssilka;
	
	@Column(name="aktivnii")
	private Boolean aktivnii;
	
	@OneToMany(targetEntity=VideoKlipOcenka.class, mappedBy="videoKlip", fetch=FetchType.LAZY)
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
	 * @return ssilka
	 */
	public String getSsilka() {
		return ssilka;
	}

	/**
	 * @param ssilka = ssilka
	 */
	public void setSsilka(String ssilka) {
		this.ssilka = ssilka;
	}

	/**
	 * @return aktivnii
	 */
	public Boolean getAktivnii() {
		return aktivnii;
	}

	/**
	 * @param aktivnii = aktivnii
	 */
	public void setAktivnii(Boolean aktivnii) {
		this.aktivnii = aktivnii;
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
	public boolean getSegodniaOcenenPolzovatelem() {
		return segodniaOcenenPolzovatelem;
	}

	/**
	 * @param segodniaOcenenPolzovatelem = segodniaOcenenPolzovatelem
	 */
	public void setSegodniaOcenenPolzovatelem(boolean segodniaOcenenPolzovatelem) {
		this.segodniaOcenenPolzovatelem = segodniaOcenenPolzovatelem;
	}
}