package ru.svs.lolik.web.obiekt;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 11.09.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="razdel_muzika")
public class RazdelMuzika extends Razdel {
	
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="razdel_kod")
	private List<MuzikalniiAlbom> muzikalniiAlbomi;

	/**
	 * @return muzikalniiAlbomi
	 */
	public List<MuzikalniiAlbom> getMuzikalniiAlbomi() {
		return muzikalniiAlbomi;
	}

	/**
	 * @param muzikalniiAlbomi = muzikalniiAlbomi
	 */
	public void setMuzikalniiAlbomi(List<MuzikalniiAlbom> muzikalniiAlbomi) {
		this.muzikalniiAlbomi = muzikalniiAlbomi;
	}
}
