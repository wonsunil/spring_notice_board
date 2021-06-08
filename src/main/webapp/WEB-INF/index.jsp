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

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/js/jszip.min.js"></script>
    <script src="/js/realgridjs-lic.js"></script>
    <script src="/js/realgridjs_eval.1.1.37.min.js"></script>
    <script src="/js/realgridjs-api.1.1.37.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
    <script src="https://code.highcharts.com/modules/series-label.js"></script>
    <script src="https://code.highcharts.com/modules/exporting.js"></script>
    <script src="https://code.highcharts.com/modules/export-data.js"></script>
    <script src="https://code.highcharts.com/modules/accessibility.js"></script>
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
                <figure class="highcharts-figure">
                    <div id="container"></div>
                    <div id="container2"></div>
                </figure>
                <c:if test="${!empty contents}">
                    <c:forEach var="content" items="${contents}">
                        <div class="content">
                            <div class="content-title">
                                <a href="/content/${content.getContentId()}">${content.getContentTitle()}</a>
                            </div>
                            <div class="content-writer">${content.getContentWriter()}</div>
                            <div class="content-content">${content.getContentContent()}</div>
                            <div>조회수 : ${content.getViewCount() == null ? 0 : content.getViewCount()}</div>
                        </div>
                        <hr>
                    </c:forEach>
                </c:if>
                <div id="search-box">
                    <select name="" id="search-column">
                        <option value="number">번호</option>
                        <option value="writer">작성자</option>
                        <option value="title">제목</option>
                        <option value="content">내용</option>
                    </select>
                    <input type="text" placeholder="제목" id="search-input">
                </div>
                <div id="button-box">
                    <button id="prev">이전</button>
                    <button id="next">다음</button>
                </div>
                <div id="realgrid" style="width: 100%; height: 300px;"></div>
            </div>
        </div>
        <%@ include file="./html/footer.jsp" %>
    </div>
</div>
</body>
</html>