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
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-admin-theme.css">

	<script src="${pageContext.request.contextPath}/static/script/time.js"></script>
</head>
<body>
<!--头部-->
<jsp:include page="share/head.jsp"></jsp:include>
<div class="container">
	<div class="row">
		<jsp:include page="share/left.jsp" />
		<div class="col-md-10">
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">试卷列表</div>
							</div>
							<div class="bootstrap-admin-panel-content">
								<ul>
									<li>查看试卷列表</li>
									<li>启动、编辑、删除试卷</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">试卷添加</div>
							</div>
							<div class="bootstrap-admin-panel-content">
								<ul>
									<li>添加试卷</li>
									<li>添加试卷，设置考试班级</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">单选题</div>
							</div>
							<div class="bootstrap-admin-panel-content">
								<ul>
									<li>根据题目查询单选题</li>
									<li>添加、删除、编辑单选题</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">多选题</div>
							</div>
							<div class="bootstrap-admin-panel-content">
								<ul>
									<li>根据题目查询多选题</li>
									<li>添加、删除、编辑多选题</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">判断题</div>
							</div>
							<div class="bootstrap-admin-panel-content">
								<ul>
									<li>根据题目查询判断题</li>
									<li>添加、删除、编辑判断题</li>
								</ul>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel panel-default">
							<div class="panel-heading">
								<div class="text-muted bootstrap-admin-box-title">关于我们</div>
							</div>
							<div class="bootstrap-admin-panel-content">
								<ul>
									<li>查询项目基本信息</li>
									<li>无</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
		</div>
	</div>
</div>
</body>
</html>