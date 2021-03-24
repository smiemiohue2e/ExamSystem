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
	<title>考试记录</title>
	<meta charset="UTF-8">
	<base href="<%=basePath%>">
	<link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/static/images/icon.ico">
	<link rel="BOOKMARK" href="${pageContext.request.contextPath}/static/images/icon.ico">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/head.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/student/exam_list.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-admin-theme.css">

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
						<caption>您的考试记录:</caption>
						<thead>
						<tr>
							<th>id</th>
							<th>试题名称</th>
							<th>考试分数</th>
							<th>考试时间</th>
						</tr>
						</thead>
						<tbody>
						<c:forEach items="${pageBean.list}" var="note">
							<tr>
								<td>${note.id}</td>
								<td>
									<a href="student/notes/view/${note.id}">${note.examtitle}</a>
								</td>
								<td>${note.point}</td>
								<td>
									<fmt:formatDate value="${note.time}" pattern="yyyy-MM-dd"/>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<!--分页-->
					<div class="pull-right">
						<script type="text/javascript">
                            function page(pageCode) {
                                window.location.href = "${pageContext.request.contextPath}/student/notes/" + pageCode;
                            }
						</script>
						<jsp:include page="../share/page.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/script/time.js"></script>
</html>