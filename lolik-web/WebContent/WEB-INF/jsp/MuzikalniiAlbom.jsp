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
				<div id="soobsheniaTekstZagruzka"><spring:message code="muzika.albom.soobshenie.podozhdite.zagruzka"/></div>
			</div>
			<div id="soobshenia" class="soobshenia">
				<img id="kartinkaOpasnost" src="img/lolik-web-opasnost.png" class="kartinkaSoobshenia">
				<div id="soobsheniaTekst"></div>
			</div>
			<table border="0" cellpadding="0" cellspacing="0" width="700">
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
							<tr><td valign="top" class="leviiTekst"><spring:message code="muzika.albom.gruppa"/>:</td><td class="tekst">${requestScope.muzikalniiAlbom.gruppa}</td></tr>
							<tr><td valign="top" class="leviiTekst"><spring:message code="muzika.albom.integranti"/>:</td><td class="tekst">${requestScope.muzikalniiAlbom.integranti}</td></tr>
							<tr><td valign="top" class="leviiTekst"><spring:message code="muzika.albom.nazvanie"/>:</td><td class="tekst">${requestScope.muzikalniiAlbom.nazvanie}</td></tr>
							<tr><td valign="top" class="leviiTekst"><spring:message code="muzika.albom.vipusk"/>:</td><td class="tekst"><fmt:formatDate pattern="yyyy.MM.dd." value="${requestScope.muzikalniiAlbom.dataVipuska}"/></td></tr>
							<tr><td valign="top" class="leviiTekst"><spring:message code="muzika.albom.format.razmer"/>:</td><td class="tekst">${requestScope.muzikalniiAlbom.format}, <fmt:formatNumber pattern="###,###.##" value="${requestScope.muzikalniiAlbom.razmer}" /><spring:message code="muzika.albom.razmer.mb"/></td></tr>
							<tr><td valign="top" class="leviiTekst"><spring:message code="muzika.albom.cena"/>:</td><td class="tekst"><spring:message code="muzika.albom.znak.dollar"/> <fmt:formatNumber pattern="###,###.00" value="${requestScope.muzikalniiAlbom.cena}" /></td></tr>
							<tr><td valign="top" class="leviiTekst"><spring:message code="muzika.albom.kupit"/>:</td><td><img src="img/paypal/btn_expressCheckout.png" onmouseover="nazhatKnopkuPaypal(this)" onmouseout="otzhatKnopkuPaypal(this)" onclick="kupitMuzikalniiAlbom('${requestScope.muzikalniiAlbom.id}')"><td></tr>
						</table>
					</td>
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" width="800">
				<tr>
					<td class="kompoziciaZagolovok"><spring:message code="muzika.albom.kompozitor"/></td>
					<td class="kompoziciaZagolovok"><spring:message code="muzika.albom.proizvedenie"/></td>
					<td class="kompoziciaZagolovok"><spring:message code="muzika.albom.kompozicia"/></td>
					<td class="kompoziciaZagolovok"><spring:message code="muzika.albom.prodolzhitelnost"/></td>
					<td class="kompoziciaZagolovok" style="text-align:center;"><spring:message code="muzika.albom.ocenka"/></td>
				</tr>
				<c:set var="otmetitZvezdoiURL" value="<%=KartaURL.URL_OTMETIT_ZVEZDOI_KOMPOZICIU%>" scope="page"/>
				<c:set var="divId" value="zvezdaKompozicia" scope="page"/>
				<c:set var="divClass" value="kompoziciaRamkaZvezda" scope="page"/>
				<c:forEach var="kompozicia" items="${requestScope.kompozicii}" varStatus="status">
					<c:set var="obiektId" value="${kompozicia.id}" scope="page"/>
					<c:set var="obiektSrednaiaOcenka" value="${kompozicia.srednaiaOcenka}" scope="page"/>
					<c:set var="obiektOcenenPolzovatelem" value="${kompozicia.segodniaOcenenPolzovatelem}" scope="page"/>
					<tr>
						<c:choose><c:when test="${status.index > 0 && requestScope.kompozicii[status.index-1].kompozitor == kompozicia.kompozitor}"><td></td></c:when><c:otherwise><td class="kompoziciaTekst">${kompozicia.kompozitor}</td></c:otherwise></c:choose>
						<c:choose><c:when test="${status.index > 0 && requestScope.kompozicii[status.index-1].proizvedenie == kompozicia.proizvedenie}"><td></td></c:when><c:otherwise><td class="kompoziciaTekst">${kompozicia.proizvedenie}</td></c:otherwise></c:choose>
						<td class="kompoziciaTekst">${kompozicia.nazvanie}</td>
						<td class="kompoziciaTekst" style="text-align:center;">${kompozicia.prodolzhitelnost}</td>
						<td width="150"><%@include file="/WEB-INF/jsp/Zvezda.jsp"%></td>
					</tr>
				</c:forEach>
				<tr><td colspan="5" height="20">&nbsp;</td></tr>
				<tr><td class="opisanieZagolovok" colspan="5"><spring:message code="muzika.albom.opisanie"/></td></tr>
				<tr><td class="opisanieTekst" colspan="5">${requestScope.muzikalniiAlbom.opisanie}</td></tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">$("#scrollbar1").tinyscrollbar();</script>