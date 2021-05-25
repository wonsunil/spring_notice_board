<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Content detail page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/form.css"/>
    <link rel="stylesheet" href="/css/detail.css"/>
</head>
<body>
<div id="wrap" style="background: white;">
    <div class="form content">
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" id="title" class="form-control" value="${content.getContentTitle()}" readonly>
        </div>
        <div class="form-group">
            <label for="writer">작성자</label>
            <input id="writer" name="contentWriter" class="form-control" value="${content.getContentWriter()}" readonly>
        </div>
        <div class="form-group">
            <label for="content">내용</label>
            <textarea id="content" name="contentContent" class="form-control" readonly>${content.getContentContent()}</textarea>
        </div>
        <div class="form-group">
            <label for="date">작성일</label>
            <input type="date" id="date" name="writtenDate" class="form-control" readonly value="${content.getWrittenDate()}">
        </div>
        <div class="form-group">
            <c:if test="${!empty user && user.getId() == content.getContentWriter()}">
                <a class="btn btn-primary" href="/content/${content.getContentId()}/update">수정하기</a>
                <a href="/content/${content.getContentId()}/delete" class="btn btn-primary">삭제</a>
            </c:if>
            <a class="btn btn-primary" href="/index">홈</a>
        </div>
    </div>
</div>
</body>
</html>