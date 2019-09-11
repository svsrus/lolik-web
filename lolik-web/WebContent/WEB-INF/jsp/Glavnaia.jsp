<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="zagolovokRamka">
	<div class="zagolovok"><spring:message code="glavnaia.zagolovok"/></div>
	<%@include file="/WEB-INF/jsp/ZvezdaRazdela.jsp"%>
</div>
<div id="scrollbar1">
	<div id="scrollbar1Hack" class="scrollbar"><div class="track"><div class="thumb"><div class="end"></div></div></div></div>
	<div class="viewport">
		<div class="overview">
			<br/><br/><p><spring:message code="glavnaia.tekst"/></p>
		</div>
	</div>
</div>