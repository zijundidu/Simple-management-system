<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
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
	function callMe() {
		alert("联系电话：183xxxx2053\n微信：Kraken0");
	}
	function message() {
		alert("账户使用者:Admin");
	}
	function show(theType) {
		var types = theType;
		var type = document.getElementById(types).getAttribute("name");
		document.getElementById("typeShow").value = type;
	}
</script>

<style type="text/css">
li {
	margin: auto 8px;
}

#input div {
	margin: 10px auto;
}

#input {
	width: 60%;
}
</style>
</head>

<body>
	<%--如果是商品分类 --%>
	<c:if test="${sessionScope.editMessage eq 'goodsEdit' }">
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="#">管理者，您好</a>
				</div>

				<%--导航栏 --%>
				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse"
					id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li><a href="<c:url value='/Admin/index.jsp' />">主页</a></li>
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
	</c:if>

	<div class="container" id="input">
		<%--如果不是商品分类 --%>
		<c:if test="${sessionScope.editMessage != 'goodsEdit' }">
			<div class="container" style="height: 50px;"></div>
		</c:if>

		<form action="<c:url value='/GoodsServlet' />" method="post">
			<input type="hidden" name="method" value="edit" /> <input
				type="hidden" name="gid" value="${editGoods.gid }" />
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">商品名称</span> <input
					type="text" name="gname" value="${editGoods.gname }"
					class="form-control" aria-describedby="basic-addon1" required>
			</div>

			<div class="input-group">
				<span class="input-group-addon" id="basic-addon1">库存数量</span> <input
					type="text" name="gnum" value="${editGoods.gnum }"
					class="form-control" aria-describedby="basic-addon1" required>
			</div>


			<div class="input-group">
				<div class="input-group-btn">
					<button type="button" class="btn btn-default data-toggle"
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						商品分类 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<c:forEach items="${sessionScope.typesList }" var="type"
							varStatus="status">
							<li><a id="${type.gtype }" name="${type.gtype }"
								onclick="show('${type.gtype }')" href="#">${type.gtype }</a></li>
						</c:forEach>
					</ul>
				</div>
				<!-- /btn-group -->

				<input type="text" name="gtype" id="typeShow"
					value="${editGoods.gtype }" class="form-control" readonly required>
			</div>
			<!-- /input-group -->

			<div class="input-group">
				<span class="input-group-addon">售价 $</span> <input type="text"
					name="gsale" value="${editGoods.gsale }" class="form-control"
					aria-label="Amount (to the nearest dollar)" required> <span
					class="input-group-addon">.00</span>
			</div>

			<div class="input-group">
				<span class="input-group-addon">进价 $</span> <input type="text"
					name="gprice" value="${editGoods.gprice }" class="form-control"
					aria-label="Amount (to the nearest dollar)" required> <span
					class="input-group-addon">.00</span>
			</div>
			
			<label for="basic-url">备注信息</label>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon3"></span> <input
					type="text" name="param" value="${editGoods.param }"
					class="form-control" id="basic-url" aria-describedby="basic-addon3">
				<span class="input-group-addon"></span>
			</div>

			<label for="basic-url">备注信息</label>
			<div class="input-group">
				<span class="input-group-addon" id="basic-addon3"></span> <input
					type="text" name="remarks" value="${editGoods.remarks }"
					class="form-control" id="basic-url" aria-describedby="basic-addon3">
				<span class="input-group-addon"></span>
			</div>

			<div>
				<input class="btn btn-lg btn-primary btn-block" type="submit"
					value="确定修改"></input>
			</div>

			<h3>${msg }</h3>
		</form>
	</div>

	<%--如果是商品分类 --%>
	<c:if test="${sessionScope.editMessage eq 'goodsEdit' }">
		<div
			style="background: #666; position: fixed; bottom: 0; width: 100%; height: 50px;">
			<div style="margin: 20px auto; text-align: center;">
				<span style="color: #fff;">@紫郡&nbsp;&nbsp;版权所有</span>
			</div>
		</div>
	</c:if>

</body>
</html>