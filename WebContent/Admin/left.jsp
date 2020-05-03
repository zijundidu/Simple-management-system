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

<title>管理页面</title>
<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script
	src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>

<script type="text/javascript">
	
<%-- 点击按钮实时刷新main.jsp --%>
	function refresh() {
		window.parent.document.getElementsByName('main')[0].contentWindow.location
				.reload();
	}
	function method(msg) {
		var message = msg;
		if(msg != "不能删除默认的分类！"){
			alert("分类已删除，此分类下商品已被移入默认分类！");
		}else{
			alert(message);
		}
	}
</script>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

button {
	text-align: center;
	margin: auto;
	padding: 0px;
}

a {
	display: block;
	width: 100%;
	text-decoration: none;
}

a:hover {
	color: gold;
	text-decoration: none;
}
</style>
</head>

<body style="background: #ccc;">
	<h2 style="text-align: center;">分 类</h2>
	<div class="list-group">
		<c:forEach items="${sessionScope.typesList }" var="type">
			<!-- Split button -->
			<div class="btn-group" style="width: 100%;">
				<button type="button" class="btn btn-default" style="width: 90%;"
					title="${type.remarks }">
					<a onclick="refresh()"
						href="<c:url value='/TypesServlet?method=preQuery&gtype=${type.gtype }' />">${type.gtype }</a>
				</button>
				<button type="button" class="btn btn-default dropdown-toggle"
					style="width: 10%;" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">
					<span class="caret"></span> <span class="sr-only">Toggle
						Dropdown</span>
				</button>
				<ul class="dropdown-menu">
					<li><a onclick="window.parent.location='<c:url value='/AdminServlet?method=add&type=addGoods&gtype=${type.gtype }' />'"
						href="#">添加商品</a></li>
					<li role="separator" class="divider"></li>
					<li><a onclick="method('${requestScope.msg }')"
						href="<c:url value='/TypesServlet?method=delete&gtype=${type.gtype }' />">删除当前分类</a></li>
				</ul>
			</div>
		</c:forEach>
	</div>
</body>
</html>