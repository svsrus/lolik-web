package ru.svs.lolik.web.obiekt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 25.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="parametr_sistemi")
public class ParametrSistemi extends AbstraktniyObiekt {
	public static final String ПП_СЛК		= "ПП_СЛК";
	public static final String ПП_ПЛЬ		= "ПП_ПЛЬ";
	public static final String ПП_ПРЬ		= "ПП_ПРЬ";
	public static final String ПП_ПОД		= "ПП_ПОД";
	public static final String ПП_ОКР		= "ПП_ОКР";
	public static final String ПП_ВВД		= "ПП_ВВД";
	public static final String РБТ_ПОЧТА	= "РБТ_ПОЧТА";
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="parametr_sistemi_id")
	private Integer id;
	
	@Column(name="kluch")
	private String kluch;
	
	@Column(name="znachenie")
	private String znachenie;
	
	@Column(name="opisanie")
	private String opisanie;

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
	 * @return znachenie
	 */
	public String getZnachenie() {
		return znachenie;
	}

	/**
	 * @param znachenie = znachenie
	 */
	public void setZnachenie(String znachenie) {
		this.znachenie = znachenie;
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
	
}
