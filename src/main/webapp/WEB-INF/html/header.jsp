<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header>
	<div id="profile">
		<c:choose>
			<%--  <c:when test=${empty request.getAttribute("user")}> --%>
			<c:when test="${empty user}">
				<div id="non-login">
					<a href="/login">로그인</a>
					<a href="/register">회원가입</a>
				</div>
			</c:when>
			<c:otherwise>
				<div id="inner-profile">
					<div id="profile-name">
						<p><c:out value="${user.getName()}" /></p>
					</div>
					<div id="profile-image-box">
						<img src="<c:out value= "${user.getProfileImage()}" />" id="profile-image">
					</div>
					<hr>
					<a href="/user/<c:out value="${user.getId()}"/>/profile/">About Me</a>
					<a href="/user/logout">로그아웃</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</header>