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
	function callMe() {
		alert("联系电话：183xxxx2053\n微信：Kraken0");
	}
	function message() {
		alert("账户使用者:Admin");
	}
	function show(msg) {
		var message = msg;
		alert(message);
	}
</script>

<style type="text/css">
* {
	margin: 0;
	padding: 0;
}

li {
	margin: auto 8px;
}

#del, #pred {
	text-decoration: none;
	color: block;
	font-weight: bold;
}

#pred:hover {
	color: green;
	text-decoration: none;
}

#del:hover {
	color: red;
	text-decoration: none;
}

#togo {
	text-align: center;
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
					<li><a href="<c:url value='/Admin/index.jsp' />">主页</a></li>
					<li class="active"><a
						href="<c:url value='/GoodsServlet?method=findAll' />">商品</a></li>
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
		<h1 style="text-align: center;">商品信息</h1>
		<h3 style="color: red;">${msg }</h3>
	</div>

	<div class="container">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>名称</th>
					<th>库存数量</th>
					<th>售价</th>
					<th>进价</th>
					<th>参数</th>
					<th>备注</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.pb.beanList }" var="cstm">
					<tr>
						<td>${cstm.gname }</td>
						<td>${cstm.gnum }</td>
						<td>${cstm.gsale }</td>
						<td>${cstm.gprice }</td>
						<td style="width:50px;"><textarea rows="2" cols="25"
								readonly="readonly" onclick="show('${cstm.param }')">${cstm.param }</textarea></td>
						<td style="width:50px;"><textarea rows="2" cols="25"
								readonly="readonly" onclick="show('${cstm.remarks }')">${cstm.remarks }</textarea></td>
						<td><a id="pred"
							href="<c:url value='/GoodsServlet?method=preEdit&gid=${cstm.gid }&type=goodsEdit' />">编辑</a>
							<br /> <a id="del"
							href="<c:url value='/GoodsServlet?method=delete&gid=${cstm.gid }&type=goodsDelete' />">删除</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="container" id="togo"
		style="position: relative; bottom: 25px; left: 100px;">
		<ul class="pagination">
			<li><span>第${pb.pc }页 / 共${pb.tp }页</span></li>
			<li><a href="${pb.url }&pc=1">首页</a></li>
			<c:if test="${pb.pc > 1 }">
				<li><a href="${pb.url }&pc=${pb.pc-1 }">上一页</a></li>
			</c:if>
			<%--计算begin和end --%>
			<c:choose>
				<%--如果总页数不足10页，把所有的页数显示出来 --%>
				<c:when test="${pb.tp < 10 }">
					<c:set var="begin" value="1" />
					<c:set var="end" value="${pb.tp }" />
				</c:when>
				<%--总页数大于10时，通过公式计算begin和end --%>
				<c:otherwise>
					<c:set var="begin" value="${pb.pc-5 }" />
					<c:set var="end" value="${pb.pc+4 }" />
					<%--考虑头溢出 --%>
					<c:if test="${begin < 1 }">
						<c:set var="begin" value="1" />
						<c:set var="end" value="10" />
					</c:if>
					<%--考虑尾溢出 --%>
					<c:if test="${end > pb.tp }">
						<c:set var="begin" value="${pb.tp-9 }" />
						<c:set var="end" value="${pb.tp }" />
					</c:if>
				</c:otherwise>
			</c:choose>
			<%--循环显示页码列表 --%>
			<c:forEach var="i" begin="${begin }" end="${end }">
				<c:choose>
					<c:when test="${i eq pb.pc }">
						<li><span style="color: #ccc;">${i }</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pb.url }&pc=${i }">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${pb.pc < pb.tp }">
				<li><a href="${pb.url }&pc=${pb.pc+1 }">下一页</a></li>
			</c:if>
			<c:if test="${pb.tp eq 0 }">
				<li><a href="${pb.url }&pc=1">尾页</a></li>
			</c:if>
			<c:if test="${pb.tp != 0 }">
				<li><a href="${pb.url }&pc=${pb.tp }">尾页</a></li>
			</c:if>
		</ul>
	</div>

	<div
		style="background: #666; position: fixed; bottom: 0px; width: 100%; height: 40px;">
		<div style="margin: 10px auto; text-align: center;">
			<span style="color: #fff;">@紫郡&nbsp;&nbsp;版权所有</span>
		</div>
	</div>
</body>
</html>