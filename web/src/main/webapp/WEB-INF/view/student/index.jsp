<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
	<title>主页</title>
	<meta charset="UTF-8">
	<base href="<%=basePath%>">
	<link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/static/images/icon.ico">
	<link rel="BOOKMARK" href="${pageContext.request.contextPath}/static/images/icon.ico">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/head.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/list_main.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-admin-theme.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/script/time.js"></script>
</head>
<body>
<!--头部-->
<jsp:include page="share/head.jsp" />
<div class="container">
	<div class="row">
		<jsp:include page="share/left.jsp" />
		<div class="col-md-10">
			<div class="welcome">请点击相应的选项进行操作</div>
		</div>
	</div>
</div>
</body>
</html>