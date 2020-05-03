<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>管理员登录</title>
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>


<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

input {
	margin: 10px auto;
}
</style>
</head>

<body>
	<div class="container" style="width: 50%; padding-top: 50px;">
		<h1 style="text-align: center;">管理员登录</h1>
		<form action="<c:url value='/AdminServlet' />" method="post"
			class="form-signin" id="form-signin">
			<input type="hidden" name="method" value="login" />
			<h2 class="form-signin-heading">Please sign in</h2>
			<h2 style="color: red;">${msg }</h2>
			<input type="text" name="username" value="${admin.username }"
				id="inputUsername" class="form-control" placeholder="请输入你的用户名"
				required autofocus> <input type="password" name="password"
				value="${admin.password }" id="inputPassword" class="form-control"
				placeholder="请输入密码" required> <input
				class="btn btn-lg btn-primary btn-block" type="submit" value="登录"></input>
		</form>

	</div>
	<!-- /container -->

</body>
</html>