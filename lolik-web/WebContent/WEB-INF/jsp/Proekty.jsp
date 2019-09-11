<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="zagolovokRamka">
	<div class="zagolovok"><spring:message code="proekty.zagolovok"/></div>
	<%@include file="/WEB-INF/jsp/ZvezdaRazdela.jsp"%>
</div>
<div id="scrollbar1">
	<div class="scrollbar"><div class="track"><div class="thumb"><div class="end"></div></div></div></div>
	<div class="viewport">
		 <div id="fotki" class="overview">
			<p><font class="zagolovokTekst"><spring:message code="proekty.lola.mora.tango"/></font></p>
			<table id="ramkaLolaMoraTango" border="0" cellpadding="0" cellspacing="0" style="float:left;">
				<tr><td id="verxnaiaMalenkaiaRamka" colspan="3" background="img/lolik-malenkaia-ramka_v.png" width="150" height="10"></td></tr>
				<tr>
					<td id="levaiaMalenkaiaRamka" background="img/lolik-malenkaia-ramka_l.png" width="11" height="125"></td>
					<td><a href="img/proekty/LolaMoraTango1988a.jpg" title="Lola Mora Tango" onmouseover="nazhatMalenkuiuRamku('ramkaLolaMoraTango')" onmouseout="otzhatMalenkuiuRamku('ramkaLolaMoraTango')"><img src="img/proekty/LolaMoraTango1988a_m.jpg"/></a></td>
					<td id="pravaiaMalenkaiaRamka" background="img/lolik-malenkaia-ramka_p.png" width="14" height="125"></td>
				</tr>
				<tr><td id="nizhnaiaMalenkaiaRamka" colspan="3" background="img/lolik-malenkaia-ramka_n.png" width="150" height="15"></td></tr>
			</table>
			<p><spring:message code="proekty.lola.mora.tango.tekst"/></p>
			<p><font class="zagolovokTekst"><spring:message code="proekty.leleka"/></font></p>
			<table id="ramkaDuoLeleka" border="0" cellpadding="0" cellspacing="0" style="float:left;">
				<tr><td id="verxnaiaMalenkaiaRamka" colspan="3" background="img/lolik-malenkaia-ramka_v.png" width="150" height="10"></td></tr>
				<tr>
					<td id="levaiaMalenkaiaRamka" background="img/lolik-malenkaia-ramka_l.png" width="11" height="125"></td>
					<td><a href="img/albomKoncerti/2007-LelekaKoncertSession039small.jpg" title="Dúo Leleka" onmouseover="nazhatMalenkuiuRamku('ramkaDuoLeleka')" onmouseout="otzhatMalenkuiuRamku('ramkaDuoLeleka')"><img src="img/albomKoncerti/2007-LelekaKoncertSession039small_m.jpg"/></a></td>
					<td id="pravaiaMalenkaiaRamka" background="img/lolik-malenkaia-ramka_p.png" width="14" height="125"></td>
				</tr>
				<tr><td id="nizhnaiaMalenkaiaRamka" colspan="3" background="img/lolik-malenkaia-ramka_n.png" width="150" height="15"></td></tr>
			</table>
			<p><spring:message code="proekty.leleka.tekst"/></p>
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