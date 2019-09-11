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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 14.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="polzovatel_otvet")
public class PolzovatelOtvet extends AbstraktniyObiekt {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="polzovatel_otvet_id")
	private Integer id;
	
	@Column(name="ip_adres")
	private String ipAdres;
	
	@Column(name="chislo")
	private Timestamp chislo;

	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Opros.class)
	@JoinColumn(name="opros_id")
	private Opros opros;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(schema=AbstraktniyObiekt.SXEMA,
			   name="polzovatel_otvet_vopros",
			   joinColumns=@JoinColumn(name="polzovatel_otvet_id"),
			   inverseJoinColumns=@JoinColumn(name="vopros_id"),
			   uniqueConstraints={@UniqueConstraint(columnNames={"polzovatel_otvet_id", "vopros_id"})})
	private List<Vopros> otveti;
	
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
	 * @return the otveti
	 */
	public List<Vopros> getOtveti() {
		return otveti;
	}

	/**
	 * @param otveti the otveti to set
	 */
	public void setOtveti(List<Vopros> otveti) {
		this.otveti = otveti;
	}

	/**
	 * @return opros
	 */
	public Opros getOpros() {
		return opros;
	}

	/**
	 * @param opros = opros
	 */
	public void setOpros(Opros opros) {
		this.opros = opros;
	}
}