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
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/admin-header.jsp"/>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp" />
<%-- 				<form action="${pageContext.request.contextPath}/${sessionScope.authUser.id }/admin/write" method="post"> --%>
					<form:form modelAttribute="postVo" action="${pageContext.request.contextPath}/${sessionScope.authUser.id }/admin/write" method="post">
			      	<table class="admin-cat-write">
			      		<tr>
			      			<td class="t">제목</td>
			      			<td>
<!-- 			      				<input type="text" size="60" name="title"> -->
			      				<form:input path="title" size="60"/>
			      				<form:select path="categoryNo">
			      					<c:forEach items="${categoryList }" var="category">
			      						<option value="${category.no }">${category.title }</option>
			      					</c:forEach>
			      				</form:select>
			      				<form:errors path="title" cssStyle="display:block; color:red;" />
<!-- 			      				<select name="categoryId"> -->
<%-- 			      					<c:forEach items="${categoryList }" var="category"> --%>
<%-- 			      						<option value="${category.no }">${category.title }</option> --%>
<%-- 			      					</c:forEach> --%>
<!-- 			      				</select> -->
				      		</td>
			      		</tr>
			      		<tr>
			      			<td class="t">내용</td>
			      			<td>
			      				<form:textarea path="contents"/>
			      				<form:errors path="contents" cssStyle="display:block; color:red;" />
<!-- 			      				<textarea name="contents"></textarea> -->
			      			</td>
			      		</tr>
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td class="s"><input type="submit" value="포스트하기"></td>
			      		</tr>
			      	</table>
<%-- 				</form> --%>
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/admin-footer.jsp" />
	</div>
</body>
</html>