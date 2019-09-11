package ru.svs.lolik.web.kontroller.url;

import ru.svs.lolik.web.obiekt.TipRazdela;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 23.02.2011
 */
public class KartaURL {
	public static final String JSP_ZAGLAVNAIA					= "Zaglavnaia";
	public static final String JSP_GLAVNAIA						= "Glavnaia";
	public static final String JSP_BIOGRAFIA					= "Biografia";
	public static final String JSP_PROEKTY						= "Proekty";
	public static final String JSP_MUZIKA						= "Muzika";
	public static final String JSP_MUZIKALNII_ALBOM				= "MuzikalniiAlbom";
	public static final String JSP_MUZIKALNII_ALBOM_POKUPKA		= "MuzikalniiAlbomPokupka";
	public static final String JSP_MUZIKALNII_ALBOM_INSTRUKCIA	= "MuzikalniiAlbomInstrukcia";
	public static final String JSP_MUZIKALNII_ALBOM_ZAGRUZKA	= "MuzikalniiAlbomZagruzka";
	public static final String JSP_GALEREIA						= "Galereia";
	public static final String JSP_FOTOGRAFII					= "Fotografii";
	public static final String JSP_VIDEO						= "Video";
	public static final String JSP_KONTAKT						= "Kontakt";
	public static final String JSP_OPROS						= "Opros";
	public static final String JSP_OPROS_REZULTAT				= "OprosRezultat";
	public static final String FORWARD 										= "forward:";
	public static final String REDIRECT										= "redirect:";
	public static final String URL_ZAGLAVNAIA								= "zaglavnaia.html";
	public static final String URL_OTKRIT_STRANICU							= "otkritStranicu.json";
	public static final String URL_OTKRIT_STRANICU_MUZIKI					= "otkritStranicuMuziki.json";
	public static final String URL_OTKRIT_STRANICU_MUZIKALNII_ALBOM			= "otkritStranicuMuzikalniiAlbom.json";
	public static final String URL_OTKRIT_STRANICU_MUZIKALNII_ALBOM_POKUPKA	= "otkritStranicuMuzikalniiAlbomPokupka.json";
	public static final String URL_OTKRIT_STRANICU_ALBOMOV					= "otkritStranicuAlbomov.json";
	public static final String URL_OTKRIT_STRANICU_FOTOGRAFII				= "otkritStranicuFotografii.json";
	public static final String URL_OTKRIT_STRANICU_VIDEO					= "otkritStranicuVideo.json";
	public static final String URL_OTMETIT_ZVEZDOI_RAZDEL					= "otmetitZvezdoiRazdel.json";
	public static final String URL_OTMETIT_ZVEZDOI_ALBOM					= "otmetitZvezdoiAlbom.json";
	public static final String URL_OTMETIT_ZVEZDOI_KOMPOZICIU				= "otmetitZvezdoiKompoziciu.json";
	public static final String URL_OTMETIT_ZVEZDOI_FOTOGRAFIU				= "otmetitZvezdoiFotografiu.json";
	public static final String URL_OTMETIT_ZVEZDOI_VIDEO_KLIP				= "otmetitZvezdoiVideoKlip.json";
	public static final String URL_OTKRIT_OPROS								= "otkritOpros.json";
	public static final String URL_OTVETIT_NA_OPROS							= "otvetitNaOpros.json";
	public static final String URL_KUPIT_MUZIKALNII_ALBOM					= "kupitMuzikalniiAlbom.json";
	public static final String URL_OTMENIT_POKUPKU_MUZIKALNOGO_ALBOMA		= "otmenitPokupkuMuzikalogoAlboma.html";
	public static final String URL_OTMENIT_POKUPKU							= "otmenitPokupku.html";
	public static final String URL_OTKRIT_POKUPKU_MUZIKALNOGO_ALBOMA		= "otkritPokupkuMuzikalnogoAlboma.html";
	public static final String URL_OTKRIT_POKUPKU							= "otkritPokupku.html";
	public static final String URL_PODTVERDIT_POKUPKU_MUZIKALNOGO_ALBOMA	= "podtverditPokupkuMuzikalnogoAlboma.json";
	public static final String URL_OTKRIT_ZAGRUZKU_MUZIKALNOGO_ALBOMA_HTML	= "otkritZagruzkuMuzikalnogoAlboma.html";
	public static final String URL_OTKRIT_ZAGRUZKU_MUZIKALNOGO_ALBOMA_JSON	= "otkritZagruzkuMuzikalnogoAlboma.json";
	public static final String URL_ZAGRUZIT_MUZIKALNII_ALBOM				= "zagruzitMurikalniiAlbom.json";
	public static final String URL_NAITI_MUZIKALNIE_ALBOMI_POLZOVATELA		= "naitiMuzikalnieAlbomiPolzovatela.json";
	
	
	/**
	 * Карта всех разделов, последующих ссылок и страниц. 
	 */
	private enum TipRazdelaURL {
		GLAVNAIA		(TipRazdela.TIP_RAZDELA_GLAVNAIA_KOD,	JSP_GLAVNAIA),
		BIOGRAFIA		(TipRazdela.TIP_RAZDELA_BIOGRAFIA_KOD,	JSP_BIOGRAFIA),
		PROEKTY			(TipRazdela.TIP_RAZDELA_PROEKTY_KOD,	JSP_PROEKTY),
		MUZIKA			(TipRazdela.TIP_RAZDELA_MUZIKA_KOD,		FORWARD + URL_OTKRIT_STRANICU_MUZIKI),
		GALEREIA		(TipRazdela.TIP_RAZDELA_GALEREIA_KOD,	FORWARD + URL_OTKRIT_STRANICU_ALBOMOV),
		VIDEO			(TipRazdela.TIP_RAZDELA_VIDEO_KOD,		FORWARD + URL_OTKRIT_STRANICU_VIDEO),
		KONTAKT			(TipRazdela.TIP_RAZDELA_KONTAKT_KOD,	JSP_KONTAKT);
		private Integer tipRazdelaKod; 
		private String  stranica;
		
		private TipRazdelaURL(Integer tipRazdelaKod, String stranica) {
			this.tipRazdelaKod = tipRazdelaKod;
			this.stranica = stranica;
		}
		
		public Integer getTipRazdelaKod() { return tipRazdelaKod; }
		public String getStranica() { return stranica; }
	}
	
	/**
	 * Метод ищет следующую ссылку, по данному типу раздела.
	 * 
	 * @param tipRazdelaKod - Integer.
	 * 
	 * @return String.
	 */
	public static String sleduiuschaiaStranica(Integer tipRazdelaKod) {
		for (TipRazdelaURL tipRazdelaURL : TipRazdelaURL.values()) {
			if (tipRazdelaURL.getTipRazdelaKod().equals(tipRazdelaKod)) {
				return tipRazdelaURL.getStranica();
			}
		}
		return null;
	}
}
