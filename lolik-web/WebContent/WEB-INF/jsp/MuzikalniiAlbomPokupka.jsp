<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ru.svs.lolik.web.kontroller.url.KartaURL"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="zagolovokRamka">
	<div class="zagolovok"><spring:message code="muzika.zagolovok"/></div>
	<%@include file="/WEB-INF/jsp/ZvezdaRazdela.jsp"%>
</div>
<div id="scrollbar1">
	<div id="scrollbar1Hack" class="scrollbar"><div class="track"><div class="thumb"><div class="end"></div></div></div></div>
	<div class="viewport">
		<div class="overview">
			<div id="zagruzkaSoobshenia" class="soobshenia">
				<img id="kartinkaZagruzka" src="img/zagruzka.gif" class="kartinkaSoobshenia">
				<div id="soobsheniaTekstZagruzka"><spring:message code="muzika.albom.soobshenie.podozhdite.tranzakciu.pokupki"/></div>
			</div>
			<c:if test="${not empty requestScope.soobshenieOshibki}">
				<div id="soobshenia" class="soobshenia" style="display:block;">
					<img id="kartinkaOpasnost" src="img/lolik-web-opasnost.png" class="kartinkaSoobshenia">
					<div id="soobsheniaTekst">${requestScope.soobshenieOshibki}</div>
				</div>
			</c:if>
			<table border="0" cellpadding="0" cellspacing="0" width="740">
				<tr>
					<td>
						<table border="0" cellpadding="0" cellspacing="0" class="bolshaiaLevaiaRamka">
							<tr><td id="verxnaiaSrednaiaRamka" colspan="3" background="img/lolik-srednaia-ramka_v.png" width="280" height="11"></td></tr>
							<tr>
								<td id="levaiaSrednaiaRamka" background="img/lolik-srednaia-ramka_l.png" width="11" height="240"></td>
								<td><img src="${muzikalniiAlbom.ssilkaOblozhka}" width="266" height="240"/></td>
								<td id="pravaiaSrednaiaRamka" background="img/lolik-srednaia-ramka_p.png" width="9" height="240"></td>
							</tr>
							<tr><td id="nizhnaiaSrednaiaRamka" colspan="3" background="img/lolik-srednaia-ramka_n.png" width="280" height="9"></td></tr>
						</table>
					</td>
					<td valign="top">
						<table>
							<tr><td valign="top" class="pokupkaZagolovok" colspan="2" height="40"><spring:message code="muzika.albom.pokupka"/></td></tr>
							<tr><td valign="top" class="leviiTekst" style="width:180px;"><spring:message code="muzika.albom.gruppa"/>:</td><td class="tekst">${requestScope.muzikalniiAlbom.gruppa}</td></tr>
							<tr><td valign="top" class="leviiTekst"><spring:message code="muzika.albom.integranti"/>:</td><td class="tekst">${requestScope.muzikalniiAlbom.integranti}</td></tr>
							<tr><td valign="top" class="leviiTekst"><spring:message code="muzika.albom.nazvanie"/>:</td><td class="tekst">${requestScope.muzikalniiAlbom.nazvanie}</td></tr>
							<tr><td valign="top" class="leviiTekst"><spring:message code="muzika.albom.format.razmer"/>:</td><td class="tekst">${requestScope.muzikalniiAlbom.format}, <fmt:formatNumber pattern="###,###.##" value="${requestScope.muzikalniiAlbom.razmer}" /><spring:message code="muzika.albom.razmer.mb"/></td></tr>
							<tr><td valign="top" class="leviiTekst"><spring:message code="muzika.albom.cena"/>:</td><td class="tekst"><spring:message code="muzika.albom.znak.dollar"/> <fmt:formatNumber pattern="###,###.00" value="${requestScope.muzikalniiAlbom.cena}" /></td></tr>
							<c:if test="${empty requestScope.soobshenieOshibki}">
								<tr><td valign="middle" class="leviiTekst"><spring:message code="muzika.albom.kupit"/>:</td><td valign="middle"><img src="img/${yazik}/lolik-knopka-podtverdit-pokupku.png" class="knopka" onclick="podtverditPokupkuMuzikalnogoAlboma('${requestScope.muzikalniiAlbom.id}')" onmouseover="nazhatPngKnopku(this)" onmouseout="otzhatPngKnopku(this)"/></tr>
							</c:if>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">$("#scrollbar1").tinyscrollbar();</script>