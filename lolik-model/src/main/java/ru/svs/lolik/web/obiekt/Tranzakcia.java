package ru.svs.lolik.web.obiekt;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 14.10.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="tranzakcia")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Tranzakcia extends AbstraktniyObiekt {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tranzakcia_id")
	private Integer	id;
	
	@Column(name="chislo")
	private Timestamp chislo;

	@Column(name="ip_adres")
	private String ipAdres;
	
	@Column(name="kluch", nullable=true)
	private String kluch;
	
	@Column(name="tranzakcia", nullable=true)
	private String tranzakcia;

	@Column(name="pokupatel_id", nullable=true)
	private String pokupatelId;
	
	@Column(name="pokupatel_imia", nullable=true)
	private String pokupatelImia;
	
	@Column(name="pokupatel_familia", nullable=true)
	private String pokupatelFamilia;
	
	@Column(name="pokupatel_email", nullable=true)
	private String pokupatelEmail;
	
	@Column(name="pokupatel_strana", nullable=true)
	private String pokupatelStrana;
	
	@Column(name="stoimost_tranzakcii", nullable=true)
	private BigDecimal stoimostTranzakcii;
	
	@Column(name="stoimost_tovara", nullable=true)
	private BigDecimal stoimostTovara;
	
	@Column(name="valuta", nullable=true)
	private String valuta;

	@Column(name="soobshenie", nullable=true)
	private String soobshenie;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tranzakcia_sostoianie_kod")
	private TranzakciaSostoianie sostoianie;

	
	
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
	 * @return chislo
	 */
	public Timestamp getChislo() {
		return chislo;
	}

	/**
	 * @param chislo = chislo
	 */
	public void setChislo(Timestamp chislo) {
		this.chislo = chislo;
	}

	/**
	 * @return ipAdres
	 */
	public String getIpAdres() {
		return ipAdres;
	}

	/**
	 * @param ipAdres = ipAdres
	 */
	public void setIpAdres(String ipAdres) {
		this.ipAdres = ipAdres;
	}

	/**
	 * @return kluch
	 */
	public String getKluch() {
		return kluch;
	}

	/**
	 * @param kluch = kluch
	 */
	public void setKluch(String kluch) {
		this.kluch = kluch;
	}

	/**
	 * @return tranzakcia
	 */
	public String getTranzakcia() {
		return tranzakcia;
	}

	/**
	 * @param tranzakcia = tranzakcia
	 */
	public void setTranzakcia(String tranzakcia) {
		this.tranzakcia = tranzakcia;
	}

	/**
	 * @return pokupatelId
	 */
	public String getPokupatelId() {
		return pokupatelId;
	}

	/**
	 * @param pokupatelId = pokupatelId
	 */
	public void setPokupatelId(String pokupatelId) {
		this.pokupatelId = pokupatelId;
	}

	/**
	 * @return pokupatelImia
	 */
	public String getPokupatelImia() {
		return pokupatelImia;
	}

	/**
	 * @param pokupatelImia = pokupatelImia
	 */
	public void setPokupatelImia(String pokupatelImia) {
		this.pokupatelImia = pokupatelImia;
	}

	/**
	 * @return pokupatelFamilia
	 */
	public String getPokupatelFamilia() {
		return pokupatelFamilia;
	}

	/**
	 * @param pokupatelFamilia = pokupatelFamilia
	 */
	public void setPokupatelFamilia(String pokupatelFamilia) {
		this.pokupatelFamilia = pokupatelFamilia;
	}

	/**
	 * @return pokupatelEmail
	 */
	public String getPokupatelEmail() {
		return pokupatelEmail;
	}

	/**
	 * @param pokupatelEmail = pokupatelEmail
	 */
	public void setPokupatelEmail(String pokupatelEmail) {
		this.pokupatelEmail = pokupatelEmail;
	}

	/**
	 * @return pokupatelStrana
	 */
	public String getPokupatelStrana() {
		return pokupatelStrana;
	}

	/**
	 * @param pokupatelStrana = pokupatelStrana
	 */
	public void setPokupatelStrana(String pokupatelStrana) {
		this.pokupatelStrana = pokupatelStrana;
	}

	/**
	 * @return stoimostTranzakcii
	 */
	public BigDecimal getStoimostTranzakcii() {
		return stoimostTranzakcii;
	}

	/**
	 * @param stoimostTranzakcii = stoimostTranzakcii
	 */
	public void setStoimostTranzakcii(BigDecimal stoimostTranzakcii) {
		this.stoimostTranzakcii = stoimostTranzakcii;
	}

	/**
	 * @return stoimostTovara
	 */
	public BigDecimal getStoimostTovara() {
		return stoimostTovara;
	}

	/**
	 * @param stoimostTovara = stoimostTovara
	 */
	public void setStoimostTovara(BigDecimal stoimostTovara) {
		this.stoimostTovara = stoimostTovara;
	}

	/**
	 * @return valuta
	 */
	public String getValuta() {
		return valuta;
	}

	/**
	 * @param valuta = valuta
	 */
	public void setValuta(String valuta) {
		this.valuta = valuta;
	}

	/**
	 * @return soobshenie
	 */
	public String getSoobshenie() {
		return soobshenie;
	}

	/**
	 * @param soobshenie = soobshenie
	 */
	public void setSoobshenie(String soobshenie) {
		this.soobshenie = soobshenie;
	}

	/**
	 * @return sostoianie
	 */
	public TranzakciaSostoianie getSostoianie() {
		return sostoianie;
	}

	/**
	 * @param sostoianie = sostoianie
	 */
	public void setSostoianie(TranzakciaSostoianie sostoianie) {
		this.sostoianie = sostoianie;
	}
}