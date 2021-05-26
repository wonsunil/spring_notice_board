<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Content write page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/form.css"/>
</head>
<body>
<div id="wrap">
    <c:choose>
        <c:when test="${empty content}">
            존재하지 않는 게시글입니다
        </c:when>
        <c:otherwise>
            <form action="/content/${content.getContentId()}/update" method="POST" class="form content">
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" id="title" name="contentTitle" class="form-control" value="${content.getContentTitle()}" required>
                </div>
                <div class="form-group">
                    <label for="writer">작성자</label>
                    <input id="writer" name="contentWriter" class="form-control" value="${content.getContentWriter()}" readonly>
                </div>
                <div class="form-group">
                    <label for="content">내용</label>
                    <textarea id="content" name="contentContent" class="form-control" placeholder="내용" required>${content.getContentContent()}</textarea>
                </div>
                <div class="form-group">
                    <label for="date">작성일</label>
                    <input type="date" id="date" name="writtenDate" class="form-control" value="${content.getWrittenDate()}" readonly>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary">저장하기</button>
                    <a href="/content/${content.getContentId()}" class="btn btn-primary">취소</a>
                </div>
            </form>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>