package ru.svs.lolik.web.obiekt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
 * @version 1.0 - 11.09.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="muzikalnii_albom")
public class MuzikalniiAlbom extends AbstraktniyObiekt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="muzikalnii_albom_id")
	private Integer id;
	
	@Column(name="gruppa")
	private String gruppa;
	
	@Column(name="integranti")
	private String integranti;
	
	@Column(name="nazvanie")
	private String nazvanie;
	
	@Column(name="opisanie")
	private String opisanie;
	
	@Column(name="data_vipuska")
	private Date dataVipuska;
	
	@Column(name="format")
	private String format;
	
	@Column(name="razmer")
	private Double razmer;

	@Column(name="cena")
	private BigDecimal cena;
	
	@Column(name="ssilka_oblozhka")
	private String ssilkaOblozhka;
	
	@Column(name="ssilka_oblozhka_malenkaia")
	private String ssilkaOblozhkaMalenkaia;
	
	/*1162597519*/
	@Column(name="ssilka_fail")
	private String ssilkaFail;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="muzikalnii_albom_id")
	private List<Kompozicia> kompozicii;
	
	/*
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(schema=AbstraktniyObiekt.SXEMA,
			   name="muzikalnii_albom_pokupka",
			   joinColumns=@JoinColumn(name="muzikalnii_albom_id"),
			   inverseJoinColumns=@JoinColumn(name="tranzakcia_id"),
			   uniqueConstraints={@UniqueConstraint(columnNames={"muzikalnii_albom_id", "tranzakcia_id"})})
   */
	@OneToMany(targetEntity=MuzikalniiAlbomTranzakcia.class, mappedBy="muzikalniiAlbom", fetch=FetchType.LAZY)
	private List<Tranzakcia> pokupki;
	
	@Transient
	private Double srednaiaOcenka;

	
	public MuzikalniiAlbom() {
		this.pokupki = new ArrayList<Tranzakcia>();
	}
	
	public void dobavitPokupku(MuzikalniiAlbomTranzakcia muzikalniiAlbomTranzakcia) {
		muzikalniiAlbomTranzakcia.setMuzikalniiAlbom(this);
		this.pokupki.add(muzikalniiAlbomTranzakcia);
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
	 * @return dataVipuska
	 */
	public Date getDataVipuska() {
		return dataVipuska;
	}

	/**
	 * @param dataVipuska = dataVipuska
	 */
	public void setDataVipuska(Date dataVipuska) {
		this.dataVipuska = dataVipuska;
	}

	/**
	 * @return ssilkaOblozhka
	 */
	public String getSsilkaOblozhka() {
		return ssilkaOblozhka;
	}

	/**
	 * @param ssilkaOblozhka = ssilkaOblozhka
	 */
	public void setSsilkaOblozhka(String ssilkaOblozhka) {
		this.ssilkaOblozhka = ssilkaOblozhka;
	}

	/**
	 * @return ssilkaOblozhkaMalenkaia
	 */
	public String getSsilkaOblozhkaMalenkaia() {
		return ssilkaOblozhkaMalenkaia;
	}

	/**
	 * @param ssilkaOblozhkaMalenkaia = ssilkaOblozhkaMalenkaia
	 */
	public void setSsilkaOblozhkaMalenkaia(String ssilkaOblozhkaMalenkaia) {
		this.ssilkaOblozhkaMalenkaia = ssilkaOblozhkaMalenkaia;
	}

	/**
	 * @return kompozicii
	 */
	public List<Kompozicia> getKompozicii() {
		return kompozicii;
	}

	/**
	 * @param kompozicii = kompozicii
	 */
	public void setKompozicii(List<Kompozicia> kompozicii) {
		this.kompozicii = kompozicii;
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
	 * @return gruppa
	 */
	public String getGruppa() {
		return gruppa;
	}

	/**
	 * @param gruppa = gruppa
	 */
	public void setGruppa(String gruppa) {
		this.gruppa = gruppa;
	}

	/**
	 * @return integranti
	 */
	public String getIntegranti() {
		return integranti;
	}

	/**
	 * @param integranti = integranti
	 */
	public void setIntegranti(String integranti) {
		this.integranti = integranti;
	}

	/**
	 * @return format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format = format
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return razmer
	 */
	public Double getRazmer() {
		return razmer;
	}

	/**
	 * @param razmer = razmer
	 */
	public void setRazmer(Double razmer) {
		this.razmer = razmer;
	}

	/**
	 * @return cena
	 */
	public BigDecimal getCena() {
		return cena;
	}

	/**
	 * @param cena = cena
	 */
	public void setCena(BigDecimal cena) {
		this.cena = cena;
	}

	/**
	 * @return pokupki
	 */
	public List<Tranzakcia> getPokupki() {
		return pokupki;
	}

	/**
	 * @param pokupki = pokupki
	 */
	public void setPokupki(List<Tranzakcia> pokupki) {
		this.pokupki = pokupki;
	}

	/**
	 * @return ssilkaFail
	 */
	public String getSsilkaFail() {
		return ssilkaFail;
	}

	/**
	 * @param ssilkaFail = ssilkaFail
	 */
	public void setSsilkaFail(String ssilkaFail) {
		this.ssilkaFail = ssilkaFail;
	}
}
