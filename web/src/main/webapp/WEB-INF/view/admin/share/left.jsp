<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jf
  Date: 2018/5/6
  Time: 9:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-2 bootstrap-admin-col-left">
    <ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">

        <li <c:if test="${function == 1}">class="active"</c:if> >
            <a href="${pageContext.request.contextPath}/admin/index?function=1">
                <i class="glyphicon glyphicon-chevron-right"></i> 主页
            </a>
        </li>

        <li <c:if test="${function == 2}">class="active"</c:if> >
            <a href="${pageContext.request.contextPath}/admin/grade/list">
                <i class="glyphicon glyphicon-chevron-right"></i> 年级管理
            </a>
        </li>
        <li <c:if test="${function == 3}">class="active"</c:if> >
            <a href="${pageContext.request.contextPath}/admin/major/list">
                <i class="glyphicon glyphicon-chevron-right"></i> 专业管理
            </a>
        </li>
        <li <c:if test="${function == 4}">class="active"</c:if> >
            <a href="${pageContext.request.contextPath}/admin/clazz/list">
                <i class="glyphicon glyphicon-chevron-right"></i> 班级管理
            </a>
        </li>
        <li <c:if test="${function == 5}">class="active"</c:if> >
            <a href="${pageContext.request.contextPath}/admin/student/list">
                <i class="glyphicon glyphicon-chevron-right"></i> 学生管理
            </a>
        </li>
        <li <c:if test="${function == 6}">class="active"</c:if> >
            <a href="${pageContext.request.contextPath}/admin/teacher/list">
                <i class="glyphicon glyphicon-chevron-right"></i> 教师管理
            </a>
        </li>

        <li <c:if test="${function == 7}">class="active"</c:if> >
            <a href="${pageContext.request.contextPath}/admin/sys/toSystem">
                <i class="glyphicon glyphicon-chevron-right"></i> 安全设置
            </a>
        </li>
        <li <c:if test="${function == 8}">class="active"</c:if> >
            <a href="${pageContext.request.contextPath}/">
                <i class="glyphicon glyphicon-chevron-right"></i> 关于我们
            </a>
        </li>
    </ul>
</div>




