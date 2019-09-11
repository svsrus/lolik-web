<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ru.svs.lolik.web.kontroller.url.KartaURL"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="zagolovokRamka">
	<div class="zagolovok"><spring:message code="muzika.zagolovok"/>&nbsp;</div>
	<%@include file="/WEB-INF/jsp/ZvezdaRazdela.jsp"%>
</div>
<div id="scrollbar1">
	<div class="scrollbar"><div class="track"><div class="thumb"><div class="end"></div></div></div></div>
	<div class="viewport">
		 <div class="overview">
			<div style="margin-top:20px;">
				<c:set var="otmetitZvezdoiURL" value="<%=KartaURL.URL_OTMETIT_ZVEZDOI_KOMPOZICIU%>" scope="page"/>
				<c:set var="divId" value="zvezdaMuzikalniiAlbom" scope="page"/>
				<c:set var="divClass" value="muzikalniiAlbomRamkaZvezda" scope="page"/>
				<c:set var="obiektOcenenPolzovatelem" value="true" scope="page"/>
				<c:forEach var="muzikalniiAlbom" items="${requestScope.muzikalniiAlbomi}" varStatus="status">
					<c:set var="obiektId" value="${muzikalniiAlbom.id}" scope="page"/>
					<c:set var="obiektSrednaiaOcenka" value="${muzikalniiAlbom.srednaiaOcenka}" scope="page"/>
					<c:choose>
						<c:when test="${status.index % 2 == 0}">
							<table id="ramka${pageScope.obiektId}" border="0" cellpadding="0" cellspacing="0" class="bolshaiaLevaiaRamka">
								<tr><td colspan="3"><font class="bolshaiaRamkaZagolovok">${muzikalniiAlbom.gruppa} - ${muzikalniiAlbom.nazvanie}&nbsp;</font></td></tr>
								<tr><td id="verxnaiaSrednaiaRamka" colspan="3" background="img/lolik-srednaia-ramka_v.png" width="280" height="11"></td></tr>
								<tr>
									<td id="levaiaSrednaiaRamka" background="img/lolik-srednaia-ramka_l.png" width="11" height="240"></td>
									<td><img src="${muzikalniiAlbom.ssilkaOblozhkaMalenkaia}" onclick="otkritStranicuMuzikalnogoAlboma(${muzikalniiAlbom.id})" onmouseover="nazhatSrednuiuRamku('ramka${pageScope.obiektId}')" onmouseout="otzhatSrednuiuRamku('ramka${pageScope.obiektId}')" width="266" height="240"/></td>
									<td id="pravaiaSrednaiaRamka" background="img/lolik-srednaia-ramka_p.png" width="9" height="240"></td>
								</tr>
								<tr><td id="nizhnaiaSrednaiaRamka" colspan="3" background="img/lolik-srednaia-ramka_n.png" width="280" height="9"></td></tr>
								<tr><td colspan="3"><div class="bolshaiaRamkaOpisanie"><spring:message code="muzika.albom.vipusk"/>: <fmt:formatDate pattern="yyyy.MM.dd." value="${muzikalniiAlbom.dataVipuska}"/></div></td></tr>
								<tr><td colspan="3"><%@include file="/WEB-INF/jsp/Zvezda.jsp"%></td></tr>
							</table>
						</c:when>
						<c:otherwise>
							<table id="ramka${pageScope.obiektId}" border="0" cellpadding="0" cellspacing="0" class="bolshaiaPravaiaRamka">
								<tr><td colspan="3"><font class="bolshaiaRamkaZagolovok">${muzikalniiAlbom.gruppa} - ${muzikalniiAlbom.nazvanie}</font></td></tr>
								<tr><td id="verxnaiaSrednaiaRamka" colspan="3" background="img/lolik-srednaia-ramka_v.png" width="280" height="11"></td></tr>
								<tr>
									<td id="levaiaSrednaiaRamka" background="img/lolik-srednaia-ramka_l.png" width="11" height="240"></td>
									<td><img src="${muzikalniiAlbom.ssilkaOblozhkaMalenkaia}" onclick="otkritStranicuMuzikalnogoAlboma(${muzikalniiAlbom.id})" onmouseover="nazhatSrednuiuRamku('ramka${pageScope.obiektId}')" onmouseout="otzhatSrednuiuRamku('ramka${pageScope.obiektId}')" width="266" height="240"/></td>
									<td id="pravaiaSrednaiaRamka" background="img/lolik-srednaia-ramka_p.png" width="9" height="240"></td>
								</tr>
								<tr><td id="nizhnaiaSrednaiaRamka" colspan="3" background="img/lolik-srednaia-ramka_n.png" width="280" height="9"></td></tr>
								<tr><td colspan="3"><div class="bolshaiaRamkaOpisanie"><spring:message code="muzika.albom.vipusk"/>: <fmt:formatDate pattern="yyyy.MM.dd." value="${muzikalniiAlbom.dataVipuska}"/></div></td></tr>
								<tr><td colspan="3"><%@include file="/WEB-INF/jsp/Zvezda.jsp"%></td></tr>
							</table>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</div>
</div>