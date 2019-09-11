package ru.svs.lolik.web.obiekt;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import ru.svs.lolik.web.raznoe.Chislo;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 02.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="razdel")
@Inheritance(strategy=InheritanceType.JOINED)
public class Razdel extends AbstraktniyObiekt {
	
	@Id
	@Column(name="razdel_kod")
	private Integer kod;
	
	@Column(name="opisanie")
	private String opisanie;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="tip_razdela_kod")
	private TipRazdela tipRazdela;
	
	@OneToMany(targetEntity=RazdelOcenka.class, mappedBy="razdel", fetch=FetchType.LAZY, cascade={CascadeType.ALL})
	private List<Ocenka> ocenki;
	
//	@Formula(value="(SELECT AVG(z.chislo) " +
//				   "   FROM lolik_web.razdel_ocenka ro " +
//				   "  INNER JOIN lolik_web.ocenka o ON ro.ocenka_id = o.ocenka_id" +
//				   "  INNER JOIN lolik_web.znachenie z ON o.znachenie_kod = z.znachenie_kod" +
//				   "  WHERE ro.razdel_kod = razdel_kod)")
	@Transient
	private Double srednaiaOcenka;
	
	@Transient
	private boolean segodniaOcenenPolzovatelem;
	

	public Razdel() {
		this.ocenki = new ArrayList<Ocenka>();
	}
	

	public boolean tipa(Integer tipRazdelaKod) {
		return this.tipRazdela.ravenTipu(tipRazdelaKod);
	}
	
	public void dobavitOcenku(Ocenka ocenka) {
		this.ocenki.add(ocenka);
	}
	
	public Double getOkruglonnuiuSrednuiuOcenku() {
		return Chislo.dvuxRazradnoeFormatirovanie(this.srednaiaOcenka);
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
	 * @return tipRazdela
	 */
	public TipRazdela getTipRazdela() {
		return tipRazdela;
	}

	/**
	 * @param tipRazdela = tipRazdela
	 */
	public void setTipRazdela(TipRazdela tipRazdela) {
		this.tipRazdela = tipRazdela;
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
	public Boolean getSegodniaOcenenPolzovatelem() {
		return segodniaOcenenPolzovatelem;
	}


	/**
	 * @param segodniaOcenenPolzovatelem = segodniaOcenenPolzovatelem
	 */
	public void setSegodniaOcenenPolzovatelem(Boolean segodniaOcenenPolzovatelem) {
		this.segodniaOcenenPolzovatelem = segodniaOcenenPolzovatelem;
	}
}