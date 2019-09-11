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
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tr>
					<td width="315">
						<table border="0" cellpadding="0" cellspacing="0" class="bolshaiaLevaiaRamka">
							<tr><td id="verxnaiaSrednaiaRamka" colspan="3" background="img/lolik-srednaia-ramka_v.png" width="280" height="11"></td></tr>
							<tr>
								<td id="levaiaSrednaiaRamka" background="img/lolik-srednaia-ramka_l.png" width="11" height="240"></td>
								<td><img src="${requestScope.muzikalniiAlbom.ssilkaOblozhka}" width="266" height="240"/></td>
								<td id="pravaiaSrednaiaRamka" background="img/lolik-srednaia-ramka_p.png" width="9" height="240"></td>
							</tr>
							<tr><td id="nizhnaiaSrednaiaRamka" colspan="3" background="img/lolik-srednaia-ramka_n.png" width="280" height="9"></td></tr>
						</table>
					</td>
					<td valign="top">
						<table>
							<tr><td valign="top" class="tekst" height="40"><spring:message code="muzika.albom.pokupka.konec.zagolovok"/></td></tr>
							<tr><td valign="top" class="tekst"><spring:message code="muzika.albom.pokupka.spasibo"/><td></tr>
							<tr><td valign="top" class="tekst"><spring:message code="muzika.albom.skachka.instrukcia"/>:<td></tr>
							<tr>
								<td valign="bottom" class="ssilka" height="40">
									<input type="hidden" id="pokupatelEmail" name="pokupatelEmail" value="${requestScope.pokupatelEmail}"/>
									<a id="ssilkaOtkritZagruzkuMuzikalnogoAlbomaHTML" class="kopiruemaiaSsilka" href="${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}/<%=KartaURL.URL_OTKRIT_ZAGRUZKU_MUZIKALNOGO_ALBOMA_HTML%>">
										${pageContext.request.scheme}://${pageContext.request.serverName}${pageContext.request.contextPath}/<%=KartaURL.URL_OTKRIT_ZAGRUZKU_MUZIKALNOGO_ALBOMA_HTML%>
									</a>
								<td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">inicializaciaMuzikalniiAlbomInstrukcia();</script>