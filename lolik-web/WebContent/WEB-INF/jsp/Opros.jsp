<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:choose>
	<c:when test="${fn:containsIgnoreCase(header['User-Agent'], 'msie')}">
		<table id="oprosRamka" class="oprosTablica" cellpadding="0" cellspacing="0">
			<tbody id="oprosKonteiner" style="display: none;">
				<tr>
					<td background="img/lolik-opros_l.png" width="17"></td>
					<td background="img/lolik-opros-fon.png">
						<form:form action="otvetitNaOpros.json" commandName="polzovatelOtvet" method="post">
							<table border="0" style="width:367px;">
								<tr><td><p class="oprosVopros">${requestScope.polzovatelOtvet.opros.opisanie}</p></td></tr>
								<tr><td><form:errors path="otveti" cssClass="oprosOshibka" /></td></tr>
								<c:forEach var="vopros" items="${polzovatelOtvet.opros.voprosi}" varStatus="status">
									<c:choose>
										<c:when test="${polzovatelOtvet.opros.segodniaPolzovatelOtvetil}">
											<tr><td class="noWrap"><font class="oprosOtvet"><fmt:formatNumber maxFractionDigits="2" value="${vopros.kolichestvoOtvetov / polzovatelOtvet.opros.kolichestvoOtvetov * 100}" />% - ${vopros.tekst}</font></td></tr>
										</c:when>
										<c:otherwise>
											<c:choose>
												<c:when test="${polzovatelOtvet.opros.monoOtvet}">
													<tr><td class="noWrap"><form:radiobutton cssClass="radio" path="otveti" value="${vopros.id}" /> <label for="otveti${status.index+1}" class="oprosOtvet">${vopros.tekst}</label></td></tr>
												</c:when>
												<c:otherwise>
													<tr><td class="noWrap"><form:checkbox cssClass="checkbox" path="otveti" value="${vopros.id}" /> <label for="otveti${status.index+1}" class="oprosOtvet">${vopros.tekst}</label></td></tr>
												</c:otherwise>
											</c:choose>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<c:choose>
									<c:when test="${polzovatelOtvet.opros.segodniaPolzovatelOtvetil}">
										<tr><td class="noWrap"><font class="oprosKolichestvoOtvetov"> <spring:message code="opros.kolichestvo.otvetov"/>: ${polzovatelOtvet.opros.kolichestvoOtvetov}</font></td></tr>
									</c:when>
									<c:otherwise>
										<tr><td class="noWrap">
											<input type="hidden" name="opros.id" value="${polzovatelOtvet.opros.id}"/>
											<img src="img/${yazik}/lolik-knopka-otvetit.png" class="oprosKnopka" onclick="otvetitNaOpros()" onmouseover="nazhatPngKnopku(this)" onmouseout="otzhatPngKnopku(this)"/>
										</td></tr>
									</c:otherwise>
								</c:choose>
							</table>
						</form:form>
					</td>
					<td background="img/lolik-opros_p.png" width="16"></td>
				</tr>
			</tbody>
			<tfoot><tr><td colspan="3"><img id="oprosKnopka" class="knopka" src="img/${yazik}/lolik-opros.png" onclick="pokazatOpros()" onmouseover="nazhatOpros(this)" onmouseout="otzhatOpros(this)"/></td></tr></tfoot>
		</table>
	</c:when>
	<c:otherwise>
		<div id="oprosRamka" class="oprosRamka">
			<div id="oprosKonteiner" class="oprosKonteiner">
				<div class="oprosLevaiaRamka"></div>
				<div class="oprosPravaiaRamka"></div>
				<div class="oprosCentr">
					<form:form action="otvetitNaOpros.json" commandName="polzovatelOtvet" method="post">
						<p class="oprosVopros">${requestScope.polzovatelOtvet.opros.opisanie}</p>
						<div class="oprosOtvet">
							<form:errors path="otveti" cssClass="oprosOshibka" />
							<c:forEach var="vopros" items="${polzovatelOtvet.opros.voprosi}" varStatus="status">
								<c:choose>
									<c:when test="${polzovatelOtvet.opros.segodniaPolzovatelOtvetil}">
										<fmt:formatNumber maxFractionDigits="2" value="${vopros.kolichestvoOtvetov / polzovatelOtvet.opros.kolichestvoOtvetov * 100}" />% - ${vopros.tekst}<br/>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${polzovatelOtvet.opros.monoOtvet}">
												<form:radiobutton cssClass="radio" path="otveti" value="${vopros.id}" /> <label for="otveti${status.index+1}" class="oprosOtvet">${vopros.tekst}</label><br/>
											</c:when>
											<c:otherwise>
												<form:checkbox cssClass="checkbox" path="otveti" value="${vopros.id}" /> <label for="otveti${status.index+1}" class="oprosOtvet">${vopros.tekst}</label><br/>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
						<div class="oprosKolichestvoOtvetov">
							<c:choose>
								<c:when test="${polzovatelOtvet.opros.segodniaPolzovatelOtvetil}">
									 <spring:message code="opros.kolichestvo.otvetov"/>: ${polzovatelOtvet.opros.kolichestvoOtvetov}
								</c:when>
								<c:otherwise>
									<input type="hidden" name="opros.id" value="${polzovatelOtvet.opros.id}"/>
									<img src="img/${yazik}/lolik-knopka-otvetit.png" class="oprosKnopka" onclick="otvetitNaOpros()" onmouseover="nazhatPngKnopku(this)" onmouseout="otzhatPngKnopku(this)"/>
								</c:otherwise>
							</c:choose>
						</div>
					</form:form>
				</div>
			</div>
			<div class="oprosMenu"><img id="oprosKnopka" src="img/${yazik}/lolik-opros.png" class="knopka" onclick="pokazatOpros()" onmouseover="nazhatOpros(this)" onmouseout="otzhatOpros(this)"/></div>
		</div>
	</c:otherwise>
</c:choose>