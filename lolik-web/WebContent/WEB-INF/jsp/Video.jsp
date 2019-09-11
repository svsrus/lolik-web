<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ru.svs.lolik.web.kontroller.url.KartaURL"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="zagolovokRamka">
	<div class="zagolovok"><spring:message code="video.zagolovok"/></div>
	<%@include file="/WEB-INF/jsp/ZvezdaRazdela.jsp"%>
</div>
<div id="scrollbar1">
	<div class="scrollbar"><div class="track"><div class="thumb"><div class="end"></div></div></div></div>
	<div class="viewport">
		<div class="overview">
			<div style="margin-top:20px;">
				<c:set var="otmetitZvezdoiURL" value="<%=KartaURL.URL_OTMETIT_ZVEZDOI_VIDEO_KLIP%>" scope="page"/>
				<c:set var="divId" value="zvezdaVideo" scope="page"/>
				<c:set var="divClass" value="bolshaiaRamkaZvezda" scope="page"/>
				<c:forEach var="videoKlip" items="${requestScope.videoKlipi}" varStatus="status">
					<c:set var="obiektId" value="${videoKlip.id}" scope="page"/>
					<c:set var="obiektSrednaiaOcenka" value="${videoKlip.srednaiaOcenka}" scope="page"/>
					<c:set var="obiektOcenenPolzovatelem" value="${videoKlip.segodniaOcenenPolzovatelem}" scope="page"/>
					<c:choose>
						<c:when test="${status.index % 2 == 0}">
							<table border="0" cellpadding="0" cellspacing="0" class="bolshaiaLevaiaRamka">
								<tr><td colspan="3"><font class="bolshaiaRamkaZagolovok">${videoKlip.nazvanie}</font></td></tr>
								<tr><td colspan="3" background="img/lolik-bolshaia-ramka_v.png" width="390" height="11"></td></tr>
								<tr>
									<td background="img/lolik-bolshaia-ramka_l.png" width="11" height="240"></td>
									<td><object><param name="movie" value="${videoKlip.ssilka}"/><embed src="${videoKlip.ssilka}" type="application/x-shockwave-flash" width="370" height="240" /></object></td>
									<td background="img/lolik-bolshaia-ramka_p.png" width="9" height="240"></td>
								</tr>
								<tr><td colspan="3" background="img/lolik-bolshaia-ramka_n.png" width="390" height="9"></td></tr>
								<tr><td colspan="3"><div class="bolshaiaRamkaOpisanie">${videoKlip.opisanie}</div><%@include file="/WEB-INF/jsp/Zvezda.jsp"%></td></tr>
							</table>
						</c:when>
						<c:otherwise>
							<table border="0" cellpadding="0" cellspacing="0" class="bolshaiaPravaiaRamka">
								<tr><td colspan="3"><font class="bolshaiaRamkaZagolovok">${videoKlip.nazvanie}</font></td></tr>
								<tr><td colspan="3" background="img/lolik-bolshaia-ramka_v.png" width="390" height="11"></td></tr>
								<tr>
									<td background="img/lolik-bolshaia-ramka_l.png" width="11" height="240"></td>
									<td><object><param name="movie" value="${videoKlip.ssilka}"/><embed src="${videoKlip.ssilka}" type="application/x-shockwave-flash" width="370" height="240" /></object></td>
									<td background="img/lolik-bolshaia-ramka_p.png" width="9" height="240"></td>
								</tr>
								<tr><td colspan="3" background="img/lolik-bolshaia-ramka_n.png" width="390" height="9"></td></tr>
								<tr><td colspan="3"><div class="bolshaiaRamkaOpisanie">${videoKlip.opisanie}</div><%@include file="/WEB-INF/jsp/Zvezda.jsp"%></td></tr>
							</table>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<%-- <iframe title="YouTube video player" class="youtube-player" width="370" height="240" src="http://www.youtube.com/embed/BeUAkSwVflw" frameborder="0"></iframe> --%>