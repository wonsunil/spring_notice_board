<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/index.css">

    <script src="/js/jszip.min.js"></script>
    <script src="/js/realgridjs-lic.js"></script>
    <script src="/js/realgridjs_eval.1.1.37.min.js"></script>
    <script src="/js/realgridjs-api.1.1.37.js"></script>
    <script src="/js/index.js" defer></script>
</head>
<body>
<div id="wrap">
    <%@ include file="./html/header.jsp" %>
    <div>
        <div id="write-box"></div>
        <div id="content">
            <div id="content-header">
                <a href="/content/write" id="content-write" class="btn btn-primary">글쓰기</a>
            </div>
            <div id="content-body">
                <c:if test="${!empty contents}">
                    <c:forEach var="content" items="${contents}">
                        <div class="content">
                            <div class="content-title">
                                <a href="/content/${content.getContentId()}"><c:out value="${content.getContentTitle()}"/></a>
                            </div>
                            <div class="content-writer"><c:out value="${content.getContentWriter()}"/></div>
                            <div class="content-content"><c:out value="${content.getContentContent()}"/></div>
                        </div>
                        <hr>
                    </c:forEach>
                </c:if>
            </div>
        </div>
        <%@ include file="./html/footer.jsp" %>
        <div id="realgrid" style="width: 100%; height: 200px;"></div>
    </div>
</div>
</body>
</html>