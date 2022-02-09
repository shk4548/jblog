<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<div id="header">
			<h1>${blogVo.title }</h1>
			<ul>
		<c:choose>
			<c:when test="${empty authUser }">
			<li><a href="${pageContext.request.contextPath}/user/login">로그인</a></li>
			<li><a href="${pageContext.request.contextPath}/user/join">회원가입</a></li>
			</c:when>
			<c:otherwise>
			<li><a href="${pageContext.request.contextPath}/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.request.contextPath}/${id}">내블로그</a></li>
			<c:choose>
				<c:when test = "${authUser.id eq blogVo.user_id }">
				<li>
					<a href ="${pageContext.request.contextPath }/${id}/admin/basic">블로그관리</a></li>
				</c:when>	 
			</c:choose>
				<li>${authUser.name } 님 안녕하세요;</li>
			</c:otherwise>
		</c:choose>
			</ul>
		</div>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a href="${pageContext.request.contextPath}/${id}/admin/basic">기본설정</a></li>
					<li><a href="${pageContext.request.contextPath}/${id}/admin/category">카테고리</a></li>
					<li><a href="${pageContext.request.contextPath}/${id}/admin/post">글작성</a></li>
				</ul>
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		<c:set var="count" value="${fn:length(categoryList) }"/>
		      		<c:forEach items="${categoryList }" var ="categoryList" varStatus = "status">
					<tr>
						<td>${count - status.index }</td>
						<td>${categoryList.name }</td>
						<td>${nolist.get(status.index)}</td>
						<td>${categoryList.description }</td>
						
						<td>
						<c:if test="${count gt 1 }">
						<c:if test ="${nolist.get(status.index)== 0 }">
						<a href="${pageContext.request.contextPath}/${authUser.id}/delete/${categoryList.no}">
						<img src="${pageContext.request.contextPath}/assets/images/delete.jpg">
						</a>
						</c:if>
						</c:if>
						</td>
					</tr>  
					</c:forEach>	
				</table>
      			<form class="category-insert" id="category-insert" method="post"
				action="${pageContext.request.contextPath}/${authUser.id }/admin/category">
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" required></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="description" required></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
		      	</form>
		      	
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>