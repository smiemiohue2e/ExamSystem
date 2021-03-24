<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small"
	 role="navigation">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="collapse navbar-collapse main-navbar-collapse">
					<a class="navbar-brand" href="#">
						<strong>欢迎使用在线考试系统</strong>
						<span style="font-size: 8px" id="cur_time"></span>
					</a>
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a type="button" class="dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">
								<i class="glyphicon glyphicon-user"></i>
								欢迎您，${sessionScope.admin.name}
								<c:if test="${!sessionScope.admin.modified}">
									<!-- 如果是初始密码，提示修改密码 -->
									<span style="color: red;">当前初是始密码，请尽快修改</span>
								</c:if>
								<i class="caret"></i>
							</a>
							<ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
								<li><a href="${pageContext.request.contextPath}/student/password" data-toggle="modal">修改密码</a></li>
								<li role="presentation" class="divider"></li>
								<li><a
										href="${pageContext.request.contextPath}/student/logout">退出</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</nav>