package ru.svs.lolik.web.obiekt;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Sergei Shurpenkov
 * @version 1.0 - 14.03.2011
 */
@Entity
@Table(schema=AbstraktniyObiekt.SXEMA, name="opros_ocenka")
public class OprosOcenka extends Ocenka {
}