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

<title>管理主页</title>
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>


<script type="text/javascript">
	function callMe() {
		alert("联系电话：183xxxx2053\n微信：Kraken0");
	}
	function message() {
		alert("账户使用者:Admin");
	}
</script>

<style type="text/css">
li {
	margin: auto 8px;
}
</style>
</head>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<a class="navbar-brand" href="#">管理者，您好</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a
						href="<c:url value='/Admin/index.jsp' />">主页</a></li>
					<li><a href="<c:url value='/GoodsServlet?method=findAll' />">商品</a></li>
					<li><a
						href="<c:url value='/AdminServlet?method=add&type=addTypes' />">添加分类</a></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">更多功能 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<c:url value='/Admin/query.jsp' />">高级搜索</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="#" onclick="callMe()">联系开发者</a></li>
						</ul></li>
				</ul>

				<form class="navbar-form navbar-left"
					action="<c:url value='/GoodsServlet' />" method="get">
					<input type="hidden" name="method" value="query" />
					<div class="form-group">
						<input type="text" class="form-control" placeholder="请输入要搜索的商品名称"
							style="width: 300px;" name="gname" required>
					</div>
					<input class="btn btn-defult" type="submit" value="搜索"></input>
				</form>

				<ul class="nav navbar-nav navbar-right">
					<a class="navbar-brand" href="#">Admin</a>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">管理员 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#" onclick="message()">管理员信息</a></li>
							<li role="separator" class="divider"></li>
							<li><a href="<c:url value='/AdminServlet?method=quit' />">退出</a>
							</li>
						</ul></li>

				</ul>

			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<div class="container">
		<iframe src="<c:url value='/TypesServlet?method=findAll' />"
			width="20%" height="600px" scrolling="auto" name="left"></iframe>
		<iframe src="<c:url value='/TypesServlet?method=query' />" width="79%"
			height="600px" scrolling="no" name="main"></iframe>
	</div>

	<div
		style="background: #666; position: fixed; bottom: 0px; width: 100%; height: 50px;">
		<div style="margin: 20px auto; text-align: center;">
			<span style="color: #fff;">@紫郡&nbsp;&nbsp;版权所有</span>
		</div>
	</div>
</body>
</html>