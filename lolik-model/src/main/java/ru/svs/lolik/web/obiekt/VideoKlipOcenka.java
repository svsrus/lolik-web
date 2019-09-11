package ru.svs.lolik.web.obiekt;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 14.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="video_klip_ocenka")
public class VideoKlipOcenka extends Ocenka {
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=VideoKlip.class)
	@JoinColumn(name="video_klip_id")
	private VideoKlip videoKlip;

	/**
	 * @return videoKlip
	 */
	public VideoKlip getVideoKlip() {
		return videoKlip;
	}

	/**
	 * @param videoKlip = videoKlip
	 */
	public void setVideoKlip(VideoKlip videoKlip) {
		this.videoKlip = videoKlip;
	}
}