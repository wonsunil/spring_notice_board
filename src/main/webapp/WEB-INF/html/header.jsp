<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header>
	<div id="profile">
		<c:choose>
			<c:when test="${empty user}">
				<div id="non-login">
					<a href="/login">로그인</a>
					<a href="/register">회원가입</a>
				</div>
			</c:when>
			<c:otherwise>
				<div id="inner-profile">
					<div id="profile-name">
						<p>${user.getName()}</p>
					</div>
					<div id="profile-image-box">
						<img src="${user.getProfileImage()}" id="profile-image">
					</div>
					<hr>
					<a href="/user/${user.getId()}/profile/">프로필</a><br>
					<a href="/user/logout">로그아웃</a>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</header>