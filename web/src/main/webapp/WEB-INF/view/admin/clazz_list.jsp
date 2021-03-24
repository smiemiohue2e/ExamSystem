<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
    pageContext.setAttribute("basePath", basePath);
%>
<html>
<head>
    <title>班级管理</title>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/static/images/icno.ico">
    <link rel="BOOKMARK" href="${pageContext.request.contextPath}/static/images/icon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/head.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/list_main.css">
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
                <div class="panel panel-default bootstrap-admin-no-table-panel">
                    <div class="panel-heading">
                        <div class="text-muted bootstrap-admin-box-title">查询</div>
                    </div>
                    <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                        <form class="form-inline" action="admin/clazz/list" method="post"
                              onsubmit="return search(this);">
                            <div class="col-lg-3 form-group">
                                <label class="control-label">年级：</label>
                                <select class="form-control" name="grade" onchange="changeMajor(this)">
                                    <option value="0">年级...</option>
                                    <c:forEach items="${grades}" var="grade">
                                        <option value="${grade.id}">${grade.grade}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-lg-5 form-group">
                                <!-- 此处由ajax动态加载 -->
                                <label class="control-label">专业名：</label>
                                <select class="form-control" id="major_select" name="major">
                                    <option value="0">专业...</option>
                                </select>
                            </div>
                            <div class="col-lg-3 form-group">
                                <button type="submit" class="btn btn-primary">搜索
                                </button>
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#clazz_add">添加
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="row">
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th width="15%">id</th>
                        <th width="20%">年级</th>
                        <th width="30%">专业</th>
                        <th width="20%">班级</th>
                        <th width="25%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageBean.list}" var="clazz">
                        <tr>
                            <td>${clazz.id}</td>
                            <td>${clazz.grade.grade}</td>
                            <td>${clazz.major.name}</td>
                            <td>${clazz.cno}</td>
                            <td>
                                <button class="btn btn-danger btn-xs" onclick="deleteClazz(this);">删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="pull-right">
                    <!-- 用于javascript提交，搜索内容 -->
                    <input type="hidden" id="search_content" value="${search}">
                    <script type="text/javascript">
                        function page(pageCode) {
                            var search = document.getElementById("search_content").value;
                            window.location.href = "${pageContext.request.contextPath}/admin/clazz/list?pageCode=" + pageCode + "&search=" + search;
                        }
                    </script>
                    <jsp:include page="../share/page.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="clazz_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="admin/clazz/add" method="post" onsubmit="return addClazz(this);">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">添加班级</h4>
                </div>
                <div class="modal-body form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">所属年级:</label>
                        <div class="col-sm-6">
                            <select class="form-control " name="grade" onchange="changeMajor(this);">
                                <option value="0">年级...</option>
                                <c:forEach items="${grades}" var="grade">
                                    <option value="${grade.id}">${grade.grade}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">所属专业:</label>
                        <div class="col-sm-6">

                            <select class="form-control"<%-- id="major_select"--%> name="major">
                                <option value="0">专业...</option>
                                <c:forEach items="${majors}" var="major">
                                    <option value="${major.id}">${major.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">班号:</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="clazz"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="error" id="clazz_add_error">&nbsp;</span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">提交</button>
                </div>
            </form>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/script/admin/clazz.js"></script>
<script src="${pageContext.request.contextPath}/static/script/tips.js"></script>
</html>