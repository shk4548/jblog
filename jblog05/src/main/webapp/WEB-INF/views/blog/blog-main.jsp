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
			<li><a href="${pageContext.request.contextPath}/${authUser.id}">내블로그</a></li>
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
			<div id="content">
				<div class="blog-content">
					<h4>${postVo.title }</h4>
					<p>
						${postVo.contents }
					<p>
				</div>
				<ul class="blog-list">
				<c:forEach items="${postList }" var ="postList" varStatus = "status">
					<li><a href="${pageContext.request.contextPath}/${id}/${postList.category_no}/${postList.no}">${postList.title }</a> <span>${postList.reg_date }</span>	</li>
				</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach items="${categoryList }" var ="categoryList" varStatus = "status">
				
				<li><a href="${pageContext.request.contextPath}/${id}/${categoryList.no}">${categoryList.name }</a></li>
				
			</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>