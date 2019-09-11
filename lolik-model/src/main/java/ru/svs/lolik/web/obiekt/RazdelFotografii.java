package ru.svs.lolik.web.obiekt;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 02.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="razdel_fotografii")
public class RazdelFotografii extends Razdel {
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="razdel_kod")
	private List<Albom> albomi;

	
	/**
	 * @return albomi
	 */
	public List<Albom> getAlbomi() {
		return albomi;
	}

	/**
	 * @param albomi = albomi
	 */
	public void setAlbomi(List<Albom> albomi) {
		this.albomi = albomi;
	}
}