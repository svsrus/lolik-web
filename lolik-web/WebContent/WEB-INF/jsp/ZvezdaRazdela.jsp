<%@ page import="ru.svs.lolik.web.kontroller.url.KartaURL"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="otmetitZvezdoiURL" value="<%=KartaURL.URL_OTMETIT_ZVEZDOI_RAZDEL%>" scope="page"/>
<c:set var="divId" value="zvezdaRazdela" scope="page"/>
<c:set var="divClass" value="zvezdaRazdela" scope="page"/>
<c:set var="obiektId" value="${requestScope.razdel.kod}" scope="page"/>
<c:set var="obiektSrednaiaOcenka" value="${requestScope.razdel.srednaiaOcenka}" scope="page"/>
<c:set var="obiektOcenenPolzovatelem" value="${requestScope.razdel.segodniaOcenenPolzovatelem}" scope="page"/>
<%@include file="/WEB-INF/jsp/Zvezda.jsp"%>