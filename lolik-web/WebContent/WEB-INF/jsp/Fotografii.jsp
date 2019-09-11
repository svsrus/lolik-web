<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ru.svs.lolik.web.kontroller.url.KartaURL"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="zagolovokRamka">
	<div class="zagolovok"><spring:message code="galereia.zagolovok"/></div>
	<%@include file="/WEB-INF/jsp/ZvezdaRazdela.jsp"%>
</div>
<div id="scrollbar1">
	<div id="scrollbar1Hack" class="scrollbar"><div class="track"><div class="thumb"><div class="end"></div></div></div></div>
	<div class="viewport">
		<div class="overview">
			<table id="galereiaFotografii" style="width:840;">
				<tr height="190">
				<c:set var="otmetitZvezdoiURL" value="<%=KartaURL.URL_OTMETIT_ZVEZDOI_FOTOGRAFIU%>" scope="page"/>
				<c:set var="divId" value="zvezdaFotografia" scope="page"/>
				<c:set var="divClass" value="malenkaiaRamkaZvezda" scope="page"/>
				<c:forEach var="fotografia" items="${requestScope.fotografii}" varStatus="status">
					<c:set var="obiektId" value="${fotografia.id}" scope="page"/>
					<c:set var="obiektSrednaiaOcenka" value="${fotografia.srednaiaOcenka}" scope="page"/>
					<c:set var="obiektOcenenPolzovatelem" value="${fotografia.segodniaOcenenPolzovatelem}" scope="page"/>
					<td align="center" style="text-align: center;">
						<table id="ramka${pageScope.obiektId}" border="0" cellpadding="0" cellspacing="0">
							<tr><td id="verxnaiaMalenkaiaRamka" colspan="3" background="img/lolik-malenkaia-ramka_v.png" width="150" height="10"></td></tr>
							<tr>
								<td id="levaiaMalenkaiaRamka" background="img/lolik-malenkaia-ramka_l.png" width="11" height="125"></td>
								<td><a href="${fotografia.ssilka}" title="${fotografia.opisanie}" onmouseover="nazhatMalenkuiuRamku('ramka${pageScope.obiektId}')" onmouseout="otzhatMalenkuiuRamku('ramka${pageScope.obiektId}')"><img src="${fotografia.ssilkaMalenkaia}"/></a></td>
								<td id="pravaiaMalenkaiaRamka" background="img/lolik-malenkaia-ramka_p.png" width="14" height="125"></td>
							</tr>
							<tr><td id="nizhnaiaMalenkaiaRamka" colspan="3" background="img/lolik-malenkaia-ramka_n.png" width="150" height="15"></td></tr>
							<tr><td colspan="3"><%@include file="/WEB-INF/jsp/Zvezda.jsp"%></td></tr>
						</table>
					</td>
					<c:if test="${(status.index + 1) % 4 == 0}">
						</tr><tr height="190">
					</c:if>
				</c:forEach>
				</tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$("#galereiaFotografii a").lightBox(
			{
				overlayBgColor: '#9603ff',
				overlayOpacity: 0.5,
				imageLoading:  'img/lightbox/lightbox-ico-loading.gif',
				imageBtnClose: 'img/${yazik}/lightbox/lightbox-zakrit.jpg',
				imageBtnPrev:  'img/${yazik}/lightbox/lightbox-prebeduschaia.jpg',
				imageBtnNext:  'img/${yazik}/lightbox/lightbox-sleduiuschaia.jpg',
				containerResizeSpeed: 350,
				txtImage: '<spring:message code="galereia.lightbox.fotografia"/>',
				txtOf: '<spring:message code="galereia.lightbox.iz"/>'
			}
		); 
	});
	$("#scrollbar1").tinyscrollbar();
</script>
