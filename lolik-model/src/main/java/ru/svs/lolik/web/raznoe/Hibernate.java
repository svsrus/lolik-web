package ru.svs.lolik.web.raznoe;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import ru.svs.lolik.web.obiekt.AbstraktniyObiekt;
import ru.svs.lolik.web.obiekt.Albom;
import ru.svs.lolik.web.obiekt.Fotografia;
import ru.svs.lolik.web.obiekt.FotografiaOcenka;
import ru.svs.lolik.web.obiekt.Kompozicia;
import ru.svs.lolik.web.obiekt.KompoziciaOcenka;
import ru.svs.lolik.web.obiekt.LolikWeb;
import ru.svs.lolik.web.obiekt.LolikWebOcenka;
import ru.svs.lolik.web.obiekt.MuzikalniiAlbom;
import ru.svs.lolik.web.obiekt.MuzikalniiAlbomTranzakcia;
import ru.svs.lolik.web.obiekt.Ocenka;
import ru.svs.lolik.web.obiekt.Opros;
import ru.svs.lolik.web.obiekt.OprosOcenka;
import ru.svs.lolik.web.obiekt.ParametrSistemi;
import ru.svs.lolik.web.obiekt.PolzovatelOtvet;
import ru.svs.lolik.web.obiekt.Razdel;
import ru.svs.lolik.web.obiekt.RazdelFotografii;
import ru.svs.lolik.web.obiekt.RazdelMuzika;
import ru.svs.lolik.web.obiekt.RazdelOcenka;
import ru.svs.lolik.web.obiekt.RazdelVideo;
import ru.svs.lolik.web.obiekt.TipRazdela;
import ru.svs.lolik.web.obiekt.Tranzakcia;
import ru.svs.lolik.web.obiekt.TranzakciaSostoianie;
import ru.svs.lolik.web.obiekt.VideoKlip;
import ru.svs.lolik.web.obiekt.VideoKlipOcenka;
import ru.svs.lolik.web.obiekt.Vopros;
import ru.svs.lolik.web.obiekt.Yazik;
import ru.svs.lolik.web.obiekt.Znachenie;

/**
 * Generacia DDL
 * 
 * @author Sergei Shurpenkov
 */
public class Hibernate {

	public static void main(String[] args) {
		final AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");

		configuration.addAnnotatedClass(AbstraktniyObiekt.class);
		configuration.addAnnotatedClass(Albom.class);
		configuration.addAnnotatedClass(Fotografia.class);
		configuration.addAnnotatedClass(FotografiaOcenka.class);
		configuration.addAnnotatedClass(Kompozicia.class);
		configuration.addAnnotatedClass(KompoziciaOcenka.class);
		configuration.addAnnotatedClass(LolikWeb.class);
		configuration.addAnnotatedClass(LolikWebOcenka.class);
		configuration.addAnnotatedClass(MuzikalniiAlbom.class);
		configuration.addAnnotatedClass(MuzikalniiAlbomTranzakcia.class);
		configuration.addAnnotatedClass(Ocenka.class);
		configuration.addAnnotatedClass(Opros.class);
		configuration.addAnnotatedClass(OprosOcenka.class);
		configuration.addAnnotatedClass(ParametrSistemi.class);
		configuration.addAnnotatedClass(PolzovatelOtvet.class);
		configuration.addAnnotatedClass(Razdel.class);
		configuration.addAnnotatedClass(RazdelFotografii.class);
		configuration.addAnnotatedClass(RazdelMuzika.class);
		configuration.addAnnotatedClass(RazdelOcenka.class);
		configuration.addAnnotatedClass(RazdelVideo.class);
		configuration.addAnnotatedClass(TipRazdela.class);
		configuration.addAnnotatedClass(Tranzakcia.class);
		configuration.addAnnotatedClass(TranzakciaSostoianie.class);
		configuration.addAnnotatedClass(VideoKlip.class);
		configuration.addAnnotatedClass(VideoKlipOcenka.class);
		configuration.addAnnotatedClass(Vopros.class);
		configuration.addAnnotatedClass(Yazik.class);
		configuration.addAnnotatedClass(Znachenie.class);
		
		final SchemaExport export = new SchemaExport(configuration);
		export.setOutputFile("d:\\export.sql");
		export.setDelimiter(";");
		export.create(true, false);
	}
}
