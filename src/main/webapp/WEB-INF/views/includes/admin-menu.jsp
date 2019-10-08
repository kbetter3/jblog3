<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:set var="url" value="${pageContext.request.requestURI }"></c:set>


<ul class="admin-menu">
	<c:choose>
		<c:when test="${fn:endsWith(url, 'basic.jsp') }">
			<li class="selected">기본설정</li>
		</c:when>
		<c:otherwise>
			<li class="selected"><a href="">기본설정</a></li>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${fn:endsWith(url, 'category.jsp') }">
			<li class="selected">카테고리</li>
		</c:when>
		<c:otherwise>
			<li class="selected"><a href="">카테고리</a></li>
		</c:otherwise>
	</c:choose>

	<c:choose>
		<c:when test="${fn:endsWith(url, 'write.jsp') }">
			<li class="selected">글작성</li>
		</c:when>
		<c:otherwise>
			<li class="selected"><a href="">글작성</a></li>
		</c:otherwise>
	</c:choose>
</ul>