<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ru.svs.lolik.web.kontroller.url.KartaURL"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="zagolovokRamka">
	<div class="zagolovok"><spring:message code="galereia.zagolovok"/></div>
	<%@include file="/WEB-INF/jsp/ZvezdaRazdela.jsp"%>
</div>
<div id="scrollbar1">
	<div class="scrollbar"><div class="track"><div class="thumb"><div class="end"></div></div></div></div>
	<div class="viewport">
		 <div class="overview">
			<div style="margin-top:20px;">
				<c:set var="otmetitZvezdoiURL" value="<%=KartaURL.URL_OTMETIT_ZVEZDOI_ALBOM%>" scope="page"/>
				<c:set var="divId" value="zvezdaAlbom" scope="page"/>
				<c:set var="divClass" value="bolshaiaRamkaZvezda" scope="page"/>
				<c:set var="obiektOcenenPolzovatelem" value="true" scope="page"/>
				<c:forEach var="albom" items="${requestScope.albomi}" varStatus="status">
					<c:set var="obiektId" value="${albom.id}" scope="page"/>
					<c:set var="obiektSrednaiaOcenka" value="${albom.srednaiaOcenka}" scope="page"/>
					<c:choose>
						<c:when test="${status.index % 2 == 0}">
							<table id="ramka${pageScope.obiektId}" border="0" cellpadding="0" cellspacing="0" class="bolshaiaLevaiaRamka">
								<tr><td colspan="3"><font class="bolshaiaRamkaZagolovok">${albom.nazvanie}</font></td></tr>
								<tr><td id="verxnaiaBolshaiaRamka" colspan="3" background="img/lolik-bolshaia-ramka_v.png" width="390" height="11"></td></tr>
								<tr>
									<td id="levaiaBolshaiaRamka" background="img/lolik-bolshaia-ramka_l.png" width="11" height="240"></td>
									<td><img src="${albom.ssilka}" onclick="otkritStranicuFotografii(${albom.id})" onmouseover="nazhatBolshuiuRamku('ramka${pageScope.obiektId}')" onmouseout="otzhatBolshuiuRamku('ramka${pageScope.obiektId}')" width="370" height="240"/></td>
									<td id="pravaiaBolshaiaRamka" background="img/lolik-bolshaia-ramka_p.png" width="9" height="240"></td>
								</tr>
								<tr><td id="nizhnaiaBolshaiaRamka" colspan="3" background="img/lolik-bolshaia-ramka_n.png" width="390" height="9"></td></tr>
								<tr><td colspan="3"><div class="bolshaiaRamkaOpisanie">${albom.opisanie}</div><%@include file="/WEB-INF/jsp/Zvezda.jsp"%></td></tr>
							</table>
						</c:when>
						<c:otherwise>
							<table id="ramka${pageScope.obiektId}" border="0" cellpadding="0" cellspacing="0" class="bolshaiaPravaiaRamka">
								<tr><td colspan="3"><font class="bolshaiaRamkaZagolovok">${albom.nazvanie}</font></td></tr>
								<tr><td id="verxnaiaBolshaiaRamka" colspan="3" background="img/lolik-bolshaia-ramka_v.png" width="390" height="11"></td></tr>
								<tr>
									<td id="levaiaBolshaiaRamka" background="img/lolik-bolshaia-ramka_l.png" width="11" height="240"></td>
									<td><img src="${albom.ssilka}" onclick="otkritStranicuFotografii(${albom.id})" onmouseover="nazhatBolshuiuRamku('ramka${pageScope.obiektId}')" onmouseout="otzhatBolshuiuRamku('ramka${pageScope.obiektId}')" width="370" height="240"/></td>
									<td id="pravaiaBolshaiaRamka" background="img/lolik-bolshaia-ramka_p.png" width="9" height="240"></td>
								</tr>
								<tr><td id="nizhnaiaBolshaiaRamka" colspan="3" background="img/lolik-bolshaia-ramka_n.png" width="390" height="9"></td></tr>
								<tr><td colspan="3"><div class="bolshaiaRamkaOpisanie">${albom.opisanie}</div><%@include file="/WEB-INF/jsp/Zvezda.jsp"%></td></tr>
							</table>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</div>
</div>