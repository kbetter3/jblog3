<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		<c:import url="/WEB-INF/views/includes/umenu.jsp" />
		<form:form method="post" action="${pageContext.request.contextPath}/user/login" cssClass="login-form" modelAttribute="userVo">
      		<label>아이디</label>
      		<form:input path="id"/>
      		<form:errors path="id" cssStyle="color:red; display:block;" />
      		<label>패스워드</label>
      		<input type="text" name="passwd">
      		<form:errors path="passwd" cssStyle="color:red; display:block;" />
      		<input type="submit" value="로그인">
		</form:form>
	</div>
</body>
</html>
