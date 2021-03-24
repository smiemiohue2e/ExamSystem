<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
	<title>试卷</title>
	<meta charset="UTF-8">
	<base href="<%=basePath%>">
	<link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/static/images/icon.ico">
	<link rel="BOOKMARK" href="${pageContext.request.contextPath}/static/images/icon.ico">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-admin-theme.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/head.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/list_main.css">

	<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/script/time.js"></script>
</head>
<body>
<!--头部-->
<jsp:include page="share/head.jsp"/>
<div class="container">
	<div class="row">
		<jsp:include page="share/left.jsp"/>
		<div class="col-md-10">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default bootstrap-admin-no-table-panel">
						<div class="panel-heading">
							<div class="text-muted bootstrap-admin-box-title">您的考试</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<table class="table table-bordered">

						<thead>
						<tr>
							<th>id</th>
							<th>名称</th>
							<th>状态</th>
							<th>结束时间</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${pageBean.list}" var="exam">
							<tr>
								<td>${exam.id}</td>
								<c:choose>
									<c:when test="${exam.status == 'NOTRUN'}">
										<td>
											<a href="javascript:alert('考试尚未开始');">${exam.title}</a>
										</td>
										<td>尚未开始</td>
										<td>-</td>
									</c:when>
									<c:when test="${exam.status == 'RUNNING'}">
										<td>
											<a href="student/exam/${exam.id}">${exam.title}</a>
										</td>
										<td class="running">正在运行</td>
										<td>
											<fmt:formatDate value="${exam.endtime}" pattern="yyyy-MM-dd"/>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<a href="javascript:alert('考试已经结束');">${exam.title}</a>
										</td>
										<td class="runned">已经结束</td>
										<td>
											<fmt:formatDate value="${exam.endtime}" pattern="yyyy-MM-dd"/>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
						</tbody>
					</table>

					<!--分页-->
					<div class="pull-right">
						<script type="text/javascript">
                            function page(pageCode) {
                                window.location.href = "${pageContext.request.contextPath}/student/exam/list/" + pageCode;
                            }
						</script>
						<jsp:include page="../share/page.jsp"></jsp:include>
					</div>
				</div>
			</div>

		</div>
	</div>
</div>
</body>
</html>