package ru.svs.lolik.web.obiekt;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 02.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="albom")
public class Albom extends AbstraktniyObiekt {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="albom_id")
	private Integer id;
	
	@Column(name="nazvanie")
	private String nazvanie;
	
	@Column(name="opisanie")
	private String opisanie;
	
	@Column(name="ssilka")
	private String ssilka;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="albom_id")
	private List<Fotografia> fotografii;

	@Transient
	private Double srednaiaOcenka;
	
	
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
	 * @return fotografii
	 */
	public List<Fotografia> getFotografii() {
		return fotografii;
	}

	/**
	 * @param fotografii = fotografii
	 */
	public void setFotografii(List<Fotografia> fotografii) {
		this.fotografii = fotografii;
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
}