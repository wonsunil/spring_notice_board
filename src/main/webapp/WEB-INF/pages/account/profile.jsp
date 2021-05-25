<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
    <c:when test="${!empty user}">
        아이디 : <c:out value="${user.getId()}"/><br>
        이름 : <c:out value="${user.getName()}"/>
    </c:when>
    <c:otherwise>
        <p>존재하지 않는 유저입니다.</p>
    </c:otherwise>
</c:choose>
<br>
<a href="/index" class="btn btn-primary">홈으로</a>
</body>
</html>