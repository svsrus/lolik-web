package ru.svs.lolik.web.obiekt;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 14.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="lolik_web_ocenka")
public class LolikWebOcenka extends Ocenka {
}