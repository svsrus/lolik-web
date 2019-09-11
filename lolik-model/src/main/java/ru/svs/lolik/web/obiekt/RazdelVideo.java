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
@Table(schema=AbstraktniyObiekt.SXEMA, name="razdel_video")
public class RazdelVideo extends Razdel {
	@OneToMany(fetch=FetchType.LAZY)
	@JoinColumn(name="razdel_kod")
	private List<VideoKlip> videoKlipi;

	
	/**
	 * @return videoKlipi
	 */
	public List<VideoKlip> getVideoKlipi() {
		return videoKlipi;
	}

	/**
	 * @param videoKlipi = videoKlipi
	 */
	public void setVideoKlipi(List<VideoKlip> videoKlipi) {
		this.videoKlipi = videoKlipi;
	}
}