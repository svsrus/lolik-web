package ru.svs.lolik.web.obiekt;

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
 * @version 1.0 - 14.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="ocenka")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Ocenka extends AbstraktniyObiekt {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ocenka_id")
	private Integer id;
	
	@Column(name="ip_adres")
	private String ipAdres;
	
	@Column(name="chislo")
	private Timestamp chislo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="znachenie_kod")
	private Znachenie znachenie;

	
	
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
	 * @return znachenie
	 */
	public Znachenie getZnachenie() {
		return znachenie;
	}

	/**
	 * @param znachenie = znachenie
	 */
	public void setZnachenie(Znachenie znachenie) {
		this.znachenie = znachenie;
	}
}