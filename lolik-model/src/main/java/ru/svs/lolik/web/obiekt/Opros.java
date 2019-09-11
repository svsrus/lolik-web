package ru.svs.lolik.web.obiekt;

import java.sql.Timestamp;
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
@Table(schema=AbstraktniyObiekt.SXEMA, name="opros")
public class Opros extends AbstraktniyObiekt {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="opros_id")
	private Integer id;
	
	@Column(name="opisanie")
	private String opisanie;
	
	@Column(name="aktivnii")
	private Boolean aktivnii;
	
	@Column(name="nachalo")
	private Timestamp nachalo;
	
	@Column(name="konec")
	private Timestamp konec;
	
	@Column(name="mono_otvet")
	private Boolean monoOtvet;
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="opros_id")
	private List<Vopros> voprosi;
	
	@OneToMany(fetch=FetchType.LAZY, targetEntity=PolzovatelOtvet.class, mappedBy="opros")
	private List<PolzovatelOtvet> polzovateliOtveti;
	
	@OneToMany(targetEntity=OprosOcenka.class, fetch=FetchType.LAZY)
	@JoinColumn(name="opros_id")
	private List<Ocenka> ocenki;
	
	@Transient
	private Boolean segodniaPolzovatelOtvetil;
	
	@Transient
	private Long kolichestvoOtvetov;
	
	
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
	 * @return nachalo
	 */
	public Timestamp getNachalo() {
		return nachalo;
	}

	/**
	 * @param nachalo = nachalo
	 */
	public void setNachalo(Timestamp nachalo) {
		this.nachalo = nachalo;
	}

	/**
	 * @return konec
	 */
	public Timestamp getKonec() {
		return konec;
	}

	/**
	 * @param konec = konec
	 */
	public void setKonec(Timestamp konec) {
		this.konec = konec;
	}

	/**
	 * @return voprosi
	 */
	public List<Vopros> getVoprosi() {
		return voprosi;
	}

	/**
	 * @param voprosi = voprosi
	 */
	public void setVoprosi(List<Vopros> voprosi) {
		this.voprosi = voprosi;
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
	 * @return polzovateliOtveti
	 */
	public List<PolzovatelOtvet> getPolzovateliOtveti() {
		return polzovateliOtveti;
	}

	/**
	 * @param polzovateliOtveti = polzovateliOtveti
	 */
	public void setPolzovateliOtveti(List<PolzovatelOtvet> polzovateliOtveti) {
		this.polzovateliOtveti = polzovateliOtveti;
	}

	/**
	 * @return kolichestvoOtvetov
	 */
	public Long getKolichestvoOtvetov() {
		return kolichestvoOtvetov;
	}

	/**
	 * @param kolichestvoOtvetov = kolichestvoOtvetov
	 */
	public void setKolichestvoOtvetov(Long kolichestvoOtvetov) {
		this.kolichestvoOtvetov = kolichestvoOtvetov;
	}

	/**
	 * @return segodniaPolzovatelOtvetil
	 */
	public Boolean getSegodniaPolzovatelOtvetil() {
		return segodniaPolzovatelOtvetil;
	}

	/**
	 * @param segodniaPolzovatelOtvetil = segodniaPolzovatelOtvetil
	 */
	public void setSegodniaPolzovatelOtvetil(Boolean segodniaPolzovatelOtvetil) {
		this.segodniaPolzovatelOtvetil = segodniaPolzovatelOtvetil;
	}

	/**
	 * @return monoOtvet
	 */
	public Boolean getMonoOtvet() {
		return monoOtvet;
	}

	/**
	 * @param monoOtvet = monoOtvet
	 */
	public void setMonoOtvet(Boolean monoOtvet) {
		this.monoOtvet = monoOtvet;
	}
}