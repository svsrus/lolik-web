<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="zagolovokRamka">
	<div class="zagolovok"><spring:message code="kontakt.zagolovok"/></div>
	<%@include file="/WEB-INF/jsp/ZvezdaRazdela.jsp"%>
</div>
<div id="scrollbar1">
	<div class="scrollbar"><div class="track"><div class="thumb"><div class="end"></div></div></div></div>
	<div class="viewport">
		 <div class="overview">
			<spring:message code="kontakt.text"/>
		</div>
	</div>
</div>