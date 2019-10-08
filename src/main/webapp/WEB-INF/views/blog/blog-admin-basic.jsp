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
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/admin-header.jsp"/>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp" />
				
				<form:form modelAttribute="blogVo" action="${pageContext.request.contextPath}/${sessionScope.authUser.id }/admin" method="post">
					<form:hidden path="logo" id="logo"/>
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td>
			      				<form:input path="title" size="40"/>
			      				<form:errors path="title" cssStyle="display:block; color:red;" />
			      			</td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td>
			      				<img id="logo-img" src="${blogVo.logo }">
			      			</td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="file" name="logo-file" id="file"></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/admin-footer.jsp" />
	</div>
	<script>
		$(function() {
			$('#file').on('change', function() {
				let file = $('#file')[0].files[0]
				
				if (file != null) {
					// file 확장자 검사 해야함
					let reader = new FileReader()
					reader.readAsDataURL(file)
					reader.onload = function(e) {
						$('#logo').val(e.target.result)
						$('#logo-img').attr('src', e.target.result)
					}
				}
			})
		})
	</script>
</body>
</html>