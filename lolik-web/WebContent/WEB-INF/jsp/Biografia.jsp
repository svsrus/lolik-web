<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="zagolovokRamka">
	<div class="zagolovok"><spring:message code="biografia.zagolovok"/></div>
	<%@include file="/WEB-INF/jsp/ZvezdaRazdela.jsp"%>
</div>
<div id="scrollbar1">
	<div id="scrollbar1Hack" class="scrollbar"><div class="track"><div class="thumb"><div class="end"></div></div></div></div>
	<div class="viewport">
		 <div id="fotki" class="overview">
			<table id="ramkaLolikCCCP" border="0" cellpadding="0" cellspacing="0" style="float:left;">
				<tr><td id="verxnaiaMalenkaiaRamka" colspan="3" background="img/lolik-malenkaia-ramka_v.png" width="150" height="10"></td></tr>
				<tr>
					<td id="levaiaMalenkaiaRamka" background="img/lolik-malenkaia-ramka_l.png" width="11" height="125"></td>
					<td><a href="img/biografia/LolikCCCP.jpg" onmouseover="nazhatMalenkuiuRamku('ramkaLolikCCCP')" onmouseout="otzhatMalenkuiuRamku('ramkaLolikCCCP')"><img src="img/biografia/LolikCCCP_m.jpg"/></a></td>
					<td id="pravaiaMalenkaiaRamka" background="img/lolik-malenkaia-ramka_p.png" width="14" height="125"></td>
				</tr>
				<tr><td id="nizhnaiaMalenkaiaRamka" colspan="3" background="img/lolik-malenkaia-ramka_n.png" width="150" height="15"></td></tr>
			</table>
			<spring:message code="biografia.text"/><br/>
			<font class="zagolovokTekst"><spring:message code="biografia.diplomi"/></font>
			<p><spring:message code="biografia.diplomi.tekst"/></p>
			<font class="zagolovokTekst"><spring:message code="biografia.repertuar"/></font>
			<p><spring:message code="biografia.repertuar.tekst"/></p><br/>
			<font class="zagolovokTekst"><spring:message code="biografia.zali"/></font>
			<p><spring:message code="biografia.zali.tekst"/></p>
		</div>
	</div>
</div>
<script type="text/javascript">
	$(function() {
		$("#fotki a").lightBox(
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