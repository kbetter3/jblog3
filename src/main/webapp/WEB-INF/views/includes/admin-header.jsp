<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div id="header">
	<h1>Spring 이야기</h1>
	<ul>
		<c:choose>
			<c:when test="${not empty sessionScope.authUser }">
				<li><a href="">로그아웃</a></li>
				<li><a href="">블로그 관리</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="">로그인</a></li>
			</c:otherwise>
		</c:choose>
	</ul>
</div>