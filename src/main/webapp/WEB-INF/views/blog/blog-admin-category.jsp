<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	<div id="container">
		<c:import url="/WEB-INF/views/includes/admin-header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp" />
				<table class="admin-cat" id="category-table">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					<c:forEach items="${categoryList }" var="category">
						<tr id="cid-${category.no }">
							<td>${category.no }</td>
							<td>${category.title }</td>
							<td>${category.postCnt }</td>
							<td>${category.description }</td>
							<td><img src="${pageContext.request.contextPath}/assets/images/delete.jpg" class="category-delete" id="category-${category.no }" category-no="${category.no }"></td>
						</tr>
					</c:forEach>
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<table id="admin-cat-add">
					<tr>
						<td class="t">카테고리명</td>
						<td><input type="text" name="name" id="category-title"></td>
					</tr>
					<tr>
						<td class="t">설명</td>
						<td><input type="text" name="description" id="category-description"></td>
					</tr>
					<tr>
						<td class="s">&nbsp;</td>
						<td><input type="button" value="카테고리 추가"
							id="category-add-btn"></td>
					</tr>
				</table>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/admin-footer.jsp" />
	</div>

	<script>
		$(function() {
			$('#category-add-btn').on('click', addCategory)
			$('.category-delete').on('click', deleteCategory)
			
		})
		
		function deleteCategory(event) {
			let categoryNo = $(event.target).attr('category-no')
			
			$.ajax({
				url: '${pageContext.request.contextPath}/api/${sessionScope.authUser.id}/admin/category?no=' + categoryNo,
				method: 'delete',
				dataType: 'json',
				success: function(response) {
					console.log(response)
					removeCategory(categoryNo)
				},
				error: function(error) {
					console.log('error', error)
				}
			})
		}
		
		function addCategory() {
			let category = {
				title: $('#category-title').val(),
				description: $('#category-description').val()
			}

			console.log(category)

			$.ajax({
				url: '${pageContext.request.contextPath}/api/${sessionScope.authUser.id}/admin/category',
				method: 'post',
				data: category,
				success: function(response) {
					if (response.status) {
						clearInput()
						createCategory(response.data)	
					} else {
						console.log('response: ', response)
					}
				},
				error: function(error) {
					console.log('error')
				}
			})
		}
		
		function clearInput() {
			$('#category-title').val('')
			$('#category-description').val('')
		}
		
		function createCategory(category) {
			let trTag = $('<tr/>')
			trTag.attr('id', 'cid-' + category.no)
			
			let noTdTag = $('<td/>')
			noTdTag.text(category.no)
			trTag.append(noTdTag)
			
			let titleTdTag = $('<td/>')
			titleTdTag.text(category.title)
			trTag.append(titleTdTag)
			
			let postCntTdTag = $('<td/>')
			postCntTdTag.text(category.postCnt)
			trTag.append(postCntTdTag)
			
			let descriptionTdTag = $('<td/>')
			descriptionTdTag.text(category.description)
			trTag.append(descriptionTdTag)
			
			let deleteTdTag = $('<td/>')
			let deleteImgTag = $('<img/>')
			deleteImgTag.attr('src', '${pageContext.request.contextPath}/assets/images/delete.jpg')
			deleteImgTag.attr('class', 'category-delete')
			deleteImgTag.attr('id', 'category-' + category.no)
			deleteImgTag.attr('category-no', category.no)
			deleteImgTag.on('click', deleteCategory)
			deleteTdTag.append(deleteImgTag)
			trTag.append(deleteTdTag)
			
			$('#category-table').append(trTag)
		}
		
		function removeCategory(categoryNo) {
			let kk = $('#category-table').children('tr#cid-' + categoryNo)
			console.log(kk)
			$('#category-table tr').remove('#cid-' + categoryNo)
		}
	</script>
</body>
</html>