<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ru.svs.lolik.web.kontroller.url.KartaURL"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="zagolovokRamka">
	<div class="zagolovok"><spring:message code="muzika.zagruzka.zagolovok"/></div>
	<%@include file="/WEB-INF/jsp/ZvezdaRazdela.jsp"%>
</div>
<div id="scrollbar1">
	<div id="scrollbar1Hack" class="scrollbar"><div class="track"><div class="thumb"><div class="end"></div></div></div></div>
	<div class="viewport">
		<div class="overview">
			<div id="soobshenia" class="soobshenia">
				<img id="kartinkaOpasnost" src="img/lolik-web-opasnost.png" class="kartinkaSoobshenia">
				<div id="soobsheniePolzovatelNeNaiden"><spring:message code="muzika.zagruzka.soobshenie.pokupka.ne.naidena"/></div>
				<div id="soobshenieZagruzokNeOstalos"><spring:message code="muzika.zagruzka.soobshenie.zagruzok.ne.ostalos"/></div>
			</div>
			<form:form action="otkritZagruzkuMuzikalnogoAlboma.json" commandName="muzikalniiAlbomTranzakcia" method="post">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td valign="middle" class="opisanieVvoda" height="60"><spring:message code="muzika.albom.skachka.pochta"/>: </td>
						<td valign="middle"><form:input cssClass="vvodTexta" path="pokupatelEmail"/></td>
						<td><img src="img/${yazik}/lolik-knopka-podtverdit.png" class="knopka" onclick="naitiMuzikalnieAlbomiKuplenniePolzovatelem()" onmouseover="nazhatPngKnopku(this)" onmouseout="otzhatPngKnopku(this)"/></td>
					</tr>
				</table>
			</form:form>
			<form:form id="zagruzitMurikalniiAlbomForm" action="zagruzitMurikalniiAlbom.json" method="post">
				<table id="zagruzkaMuzikalnogoAlbomaTable" border="0" cellpadding="0" cellspacing="0" width="100%" style="display:<c:if test='${empty requestScope.muzikalniiAlbomiTranzakcii}'>none</c:if>;">
					<thead>
						<tr height="40">
							<td class="kompoziciaZagolovok" width="150"><spring:message code="muzika.albom.nazvanie"/></td>
							<td class="kompoziciaZagolovok" width="250"><spring:message code="muzika.albom.integranti"/></td>
							<td class="kompoziciaZagolovok" width="170"><spring:message code="muzika.albom.skachka.data.pokupki"/></td>
							<td class="kompoziciaZagolovok" width="230" align="center"><spring:message code="muzika.albom.skachka.kolichestvo.zagruzok"/></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="muzikalniiAlbomTranzakcia" items="${requestScope.muzikalniiAlbomiTranzakcii}">
							<tr height="60">
								<td valign="top" class="kompoziciaTekst">
									<input type="radio" class="radio" id="muzikalniiAlbomTranzakcia.id.${muzikalniiAlbomTranzakcia.id}" name="muzikalniiAlbomTranzakcia.id" value="${muzikalniiAlbomTranzakcia.id}"/>  
									<label for="muzikalniiAlbomTranzakcia.id.${muzikalniiAlbomTranzakcia.id}">${muzikalniiAlbomTranzakcia.muzikalniiAlbom.nazvanie}</label>
								</td>
								<td valign="top" class="kompoziciaTekst">${muzikalniiAlbomTranzakcia.muzikalniiAlbom.integranti}</td>
								<td valign="top" class="kompoziciaTekst"><fmt:formatDate pattern="yyyy.MM.dd. HH:mm" value="${muzikalniiAlbomTranzakcia.chislo}"/></td>
								<td valign="top" class="kompoziciaTekst" align="center" id="kolichestvoOstavshixsiaZagruzok${muzikalniiAlbomTranzakcia.id}">${muzikalniiAlbomTranzakcia.kolichestvoOstavshixsiaZagruzok}</td>
							</tr>
						</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4" height="50" valign="bottom"><img id="knopkaZagruzitMuzikalniiAlbom" src="img/${yazik}/lolik-knopka-zagruzit.png" class="knopka" onmouseover="nazhatPngKnopku(this)" onmouseout="otzhatPngKnopku(this)"/></td>
						</tr>
					</tfoot>
				</table>
			</form:form>
		</div>
	</div>
</div>
<script type="text/javascript">inicializaciaZagruzkiMuzikalnogoAlboma();</script>