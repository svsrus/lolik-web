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
 * @version 1.0 - 11.09.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="kompozicia")
public class Kompozicia extends AbstraktniyObiekt {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="kompozicia_id")
	private Integer id;
	
	@Column(name="kompozitor")
	private String kompozitor;
	
	@Column(name="proizvedenie")
	private String proizvedenie;
	
	@Column(name="nazvanie")
	private String nazvanie;
	
	@Column(name="prodolzhitelnost")
	private String prodolzhitelnost;
	
	@OneToMany(targetEntity=KompoziciaOcenka.class, mappedBy="kompozicia", fetch=FetchType.LAZY)
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
	 * @return prodolzhitelnost
	 */
	public String getProdolzhitelnost() {
		return prodolzhitelnost;
	}

	/**
	 * @param prodolzhitelnost = prodolzhitelnost
	 */
	public void setProdolzhitelnost(String prodolzhitelnost) {
		this.prodolzhitelnost = prodolzhitelnost;
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

	/**
	 * @return kompozitor
	 */
	public String getKompozitor() {
		return kompozitor;
	}

	/**
	 * @param kompozitor = kompozitor
	 */
	public void setKompozitor(String kompozitor) {
		this.kompozitor = kompozitor;
	}

	/**
	 * @return proizvedenie
	 */
	public String getProizvedenie() {
		return proizvedenie;
	}

	/**
	 * @param proizvedenie = proizvedenie
	 */
	public void setProizvedenie(String proizvedenie) {
		this.proizvedenie = proizvedenie;
	}

}
