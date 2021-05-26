<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x"
	crossorigin="anonymous">
<link rel="stylesheet" href="/css/form.css">
</head>
<body>
	<div id="wrap" class="login">
		<div id="form-blind">
			<div>
				<h2 id="blind-title">Welcome</h2>
				<p>아직 계정이 없으신가요?</p>
			</div>
			<a id="register-button" class="btn blind-btn" href="/register">회원가입</a>
		</div>
		<form method="POST" action="/login" id="login" class="form">
			<div id="form-decoration">
				<img src="/images/user.svg">
			</div>
			<div class="form-group">
				<label for="id">Id</label>
				<input type="text" id="id" name="id" class="form-control"
					placeholder="Id" autofocus>
			</div>
			<div class="form-group">
				<label for="password">Password</label>
				<input type="password" id="password" name="password"
					class="form-control" placeholder="Password">
			</div>
			<div class="form-group">
				<button class="btn btn-primary">로그인</button>
			</div>
		</form>
	</div>
</body>
</html>