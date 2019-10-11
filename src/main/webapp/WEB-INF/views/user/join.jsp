<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<div class="center-content">
		<h1 class="logo">JBlog</h1>
		
		<c:import url="/WEB-INF/views/includes/umenu.jsp" />
		
		<form:form modelAttribute="userVo" cssClass="join-form" id="join-form"
			method="post" action="${pageContext.request.contextPath}/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name" id="name"/>
			<form:errors path="name" cssStyle="color:red; display:block;" />

			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="id" id="blog-id"/>
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;"
				src="${pageContext.request.contextPath}/assets/images/check.png">
			<form:errors path="id" cssStyle="color:red; display:block;" id="error-id" />

			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="passwd" type="password" />
			<form:errors path="passwd" cssStyle="color:red; display:block;" />

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>
			
			<input type="button" id="submit-btn" value="가입하기">
		</form:form>
	</div>
	
	<script type="text/javascript">
		$(function() {
			$('#blog-id').on('change', idChanged)
			$('#btn-checkemail').on('click', idDuplicationCheck)
			
			// TODO: id 중복체크 및 약관동의 후 submit 처리로직
			$('#submit-btn').on('click', submitForm)
		});
		
		// id 중복체크 완료 여부
		let idChecked = false
		
		// 아이디 변경
		function idChanged() {
			if (idChecked) {
				idChecked = false
				$('#img-checkemail').hide()
				$('#btn-checkemail').show()
			}
		}
		
		// 아이디 중복 체크
		function idDuplicationCheck() {
			let userId = $('#blog-id').val()
			
			if (userId == '') {
				alert('아이디를 입력해주세요.')
				return
			}
			
			$.ajax({
				url: '${pageContext.request.contextPath}/api/user/idcheck',
				method: 'post',
				data: {
					'id': $('#blog-id').val()
				},
				success: function(response) {
					if (response.status) {
						idChecked = true
						$('#img-checkemail').show()
						$('#btn-checkemail').hide()
					} else {
						alert('이미 사용중인 아이디입니다.')
					}
				},
				error: function(error) {
					console.log('error: ', error)
				}
			})
		}
		
		function submitForm() {
			if (!idChecked) {
				alert('아이디 중복확인을 해주세요.')
				return
			}
			
			if (!$('#agree-prov').prop('checked')) {
				alert('서비스 이용약관에 동의해주세요.')
				return
			}
			
			$('#join-form').submit()
		}
	</script>
</body>
</html>
