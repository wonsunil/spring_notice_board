<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Content write page</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<link rel="stylesheet" href="/css/form.css" />
</head>
<body>
	<div id="wrap">
		<form method="POST" action="/content/write" class="form content">
			<div class="form-group">
				<label for="title">제목</label>
				<input type="text" id="title" name="contentTitle" class="form-control" placeholder="Title" autofocus>
			</div>
			<div class="form-group">
				<label for="writer">작성자</label>
				<input id="writer" name="contentWriter" class="form-control" value="${user.getId()}" readonly>
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea id="content" name="contentContent" class="form-control" placeholder="내용"></textarea>
			</div>
			<div class="form-group">
				<label for="date">작성일</label>
				<input type="date" id="date" name="writtenDate" class="form-control" readonly>
			</div>
			<div class="form-group">
				<button class="btn btn-primary">글쓰기</button>
				<a href="/index" class="btn btn-primary">취소</a>
			</div>
		</form>
	</div>
</body>
<script>
	document.querySelector("input[type=date]").value = new Date().toISOString().substring(0, 10);
</script>
</html>