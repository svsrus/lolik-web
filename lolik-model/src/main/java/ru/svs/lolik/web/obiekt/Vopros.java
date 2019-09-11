package ru.svs.lolik.web.obiekt;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 02.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="vopros")
public class Vopros extends AbstraktniyObiekt {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vopros_id")
	private Integer id;
	
	@Column(name="tekst")
	private String tekst;
	
	@ManyToMany(fetch=FetchType.LAZY, targetEntity=PolzovatelOtvet.class, mappedBy="otveti")
	private List<PolzovatelOtvet> otveti;
	
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
	 * @return tekst
	 */
	public String getTekst() {
		return tekst;
	}

	/**
	 * @param tekst = tekst
	 */
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	/**
	 * @return otveti
	 */
	public List<PolzovatelOtvet> getOtveti() {
		return otveti;
	}

	/**
	 * @param otveti = otveti
	 */
	public void setOtveti(List<PolzovatelOtvet> otveti) {
		this.otveti = otveti;
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
}