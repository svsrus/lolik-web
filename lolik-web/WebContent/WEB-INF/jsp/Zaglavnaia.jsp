<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="ru.svs.lolik.web.obiekt.TipRazdela"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:choose>
	<c:when test="${pageContext.response.locale.language == 'ru' || pageContext.response.locale.language == 'en'}">
		<c:set var="yazik" value="${pageContext.response.locale.language}" scope="session"/>
	</c:when>
	<c:otherwise>
		<c:set var="yazik" value="es" scope="session"/>
	</c:otherwise>
</c:choose>
<html>
<head>
	<title>Lolik Web</title>
	<meta http-equiv="Pragma" content="no-cache"> 
	<meta http-equiv="Expires" content="-1">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link type="text/css" rel="stylesheet" href="css/jquery.lightbox-0.5.css" media="screen"/>
	<link type="text/css" rel="stylesheet" href="css/lolik-web-obshee.css"/>
	<![if !IE]><link type="text/css" rel="stylesheet" href="css/lolik-web.css"/><![endif]>
	<!--[if IE]><link type="text/css" rel="stylesheet" href="css/lolik-web-ie.css"/><![endif]-->
	<script type="text/javascript" src="js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="js/jquery.tinyscrollbar.min.js"></script>
	<script type="text/javascript" src="js/jquery.lightbox-0.5.min.js"></script>
	<script type="text/javascript" src="js/fadeslideshow.js"></script>
	<script type="text/javascript" src="js/lolik-web.js"></script>
	<script type="text/javascript">var yazik = '${yazik}';</script>
</head>
<body background="img/lolik_fon.jpg" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
	<form id="formaPerenapravlenia">
		<input type="hidden" id="adresPerenapravlenia" name="adresPerenapravlenia" value="${requestScope.adresPerenapravlenia}"/>
		<input type="hidden" name="${requestScope.imiaObiekta}" value="${requestScope.obiektId}"/>
	</form>
	<table id="zagruzochnaiaRamka" width="900" height="700" border="0" cellpadding="0" cellspacing="0" align="center">
		<tr>
			<td class="zagruzka" align="center"><font face="Monotype Corsiva" color="#9601ff" size="5"><spring:message code="zaglavnaia.zagruzka"/></font></td>
		</tr>
	</table>
	<table id="zaglavnaiaRamka" width="100%" height="100%" border="0" align="center" style="display: none;">
		<tr>
			<td style="vertical-align:middle;">
				<table width="900" height="700" border="0" cellpadding="0" cellspacing="0" align="center">
					<tr>
						<td colspan="11" background="img/lolik_01.png" width="900" height="19"></td>
					</tr>
					<tr>
						<td background="img/lolik_02.png" width="19" height="171"></td>
						<td colspan="4" width="247" height="171"><div id="fotki"></div></td>
						<td colspan="5" background="img/${yazik}/lolik_04.jpg" width="614" height="171" class="kolonkaYazikov">
							<form id="yazikForm" action="zaglavnaia.html" method="post">
								<img src="img/lolik_ru.jpg" class="knopka" onclick="smenitYazik('ru')" onmouseover="nazhatFlag(this,'ru')" onmouseout="otzhatFlag(this,'ru')" title="русский" class="flag" /><img src="img/lolik_es.jpg" class="knopka" onclick="smenitYazik('es')" onmouseover="nazhatFlag(this,'es')" onmouseout="otzhatFlag(this,'es')" title="castellano" class="flag" /><img src="img/lolik_en.jpg" class="knopka" onclick="smenitYazik('en')" onmouseover="nazhatFlag(this,'en')" onmouseout="otzhatFlag(this,'en')" title="english" class="flag" />
								<input id="yazik" name="yazik" type="hidden" value="${pageContext.response.locale.language}"/>
							</form>						
						</td>
						<td background="img/lolik_05.png" width="20" height="171"></td>
					</tr>
					<tr>
						<td background="img/lolik_06.png" width="19" height="33"></td>
						<td colspan="2"><img src="img/${yazik}/lolik_07.jpg" class="knopka" width="122" height="33" onmouseover="nazhatKnopku(this,'07')" onmouseout="otzhatKnopku(this,'07')" onclick="otkritStranicu(<%=TipRazdela.TIP_RAZDELA_GLAVNAIA_KOD%>)"/></td>
						<td><img src="img/${yazik}/lolik_08.jpg" class="knopka" width="122" height="33" onmouseover="nazhatKnopku(this,'08')" onmouseout="otzhatKnopku(this,'08')" onclick="otkritStranicu(<%=TipRazdela.TIP_RAZDELA_BIOGRAFIA_KOD%>)"></td>
						<td colspan="2"><img src="img/${yazik}/lolik_09.jpg" class="knopka" width="122" height="33" onmouseover="nazhatKnopku(this,'09')" onmouseout="otzhatKnopku(this,'09')" onclick="otkritStranicu(<%=TipRazdela.TIP_RAZDELA_PROEKTY_KOD%>)"></td>
						<td><img src="img/${yazik}/lolik_10.jpg" class="knopka" width="128" height="33" onmouseover="nazhatKnopku(this,'10')" onmouseout="otzhatKnopku(this,'10')" onclick="otkritStranicu(<%=TipRazdela.TIP_RAZDELA_MUZIKA_KOD%>)"></td>
						<td><img src="img/${yazik}/lolik_11.jpg" class="knopka" width="122" height="33" onmouseover="nazhatKnopku(this,'11')" onmouseout="otzhatKnopku(this,'11')" onclick="otkritStranicu(<%=TipRazdela.TIP_RAZDELA_GALEREIA_KOD%>)"></td>
						<td><img src="img/${yazik}/lolik_12.jpg" class="knopka" width="122" height="33" onmouseover="nazhatKnopku(this,'12')" onmouseout="otzhatKnopku(this,'12')" onclick="otkritStranicu(<%=TipRazdela.TIP_RAZDELA_VIDEO_KOD%>)"></td>
						<td><img src="img/${yazik}/lolik_13.jpg" class="knopka" width="123" height="33" onmouseover="nazhatKnopku(this,'13')" onmouseout="otzhatKnopku(this,'13')" onclick="otkritStranicu(<%=TipRazdela.TIP_RAZDELA_KONTAKT_KOD%>)"></td>
						<td background="img/lolik_14.png" width="20" height="33"></td>
					</tr>
					<tr>
						<td colspan="11" background="img/lolik_15.png" width="900" height="3"></td>
					</tr>
					<tr>
						<td colspan="2" background="img/lolik_16.png" width="20" height="454"></td>
						<td colspan="8" background="img/lolik_17.jpg" width="860" height="454"><div id="glavnaiaRamka" class="glavnaiaRamka"></div></td>
						<td background="img/lolik_18.png" width="20" height="454"></td>
					</tr>
					<tr>
						<td colspan="11" background="img/lolik_19.png" width="900" height="20"></td>
					</tr>
					<tr>
						<td><img src="img/spacer.gif" width="19" height="1"></td>
						<td><img src="img/spacer.gif" width="1" height="1"></td>
						<td><img src="img/spacer.gif" width="121" height="1"></td>
						<td><img src="img/spacer.gif" width="122" height="1"></td>
						<td><img src="img/spacer.gif" width="3" height="1"></td>
						<td><img src="img/spacer.gif" width="119" height="1"></td>
						<td><img src="img/spacer.gif" width="128" height="1"></td>
						<td><img src="img/spacer.gif" width="122" height="1"></td>
						<td><img src="img/spacer.gif" width="122" height="1"></td>
						<td><img src="img/spacer.gif" width="123" height="1"></td>
						<td><img src="img/spacer.gif" width="20" height="1"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>