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
    <title>年级管理</title>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/static/images/icno.ico">
    <link rel="BOOKMARK" href="${pageContext.request.contextPath}/static/images/icno.ico">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
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
            <%--搜索区--%>
            <div class="row">
                <div class="panel panel-default bootstrap-admin-no-table-panel">
                    <div class="panel-heading">
                        <div class="text-muted bootstrap-admin-box-title">查询</div>
                    </div>
                    <form class="form-inline" action="admin/grade/list" method="post">
                        <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                            <div class="col-lg-4 form-group">
                                <label class="control-label">年级名</label>
                                <input value="${search }" class="form-control" name="search" type="text"/>
                            </div>
                            <div class="col-lg-6 form-group">
                                <button type="submit" class="btn btn-primary">搜索
                                </button>
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#grade_add">添加
                                </button>
                                <span class="error" style="color: red;">${error}</span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="row">
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th width="20%">id</th>
                        <th width="55%">年级</th>
                        <th width="25%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageBean.list}" var="grade">
                        <tr>
                            <td>${grade.id}</td>
                            <td>${grade.grade}</td>
                            <td>
                                <button class="btn btn-danger btn-xs" onclick="deleteGrade(this);">删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <%--分页--%>
                <div class="pull-right">
                    <!--分页-->
                    <!-- 用于javascript提交，搜索内容 -->
                    <input type="hidden" id="search_content" value="${search}">
                    <script type="text/javascript">
                        function page(pageCode) {
                            var search = document.getElementById("search_content").value;
                            window.location.href = "${pageContext.request.contextPath}/admin/grade/list?pageCode=" + pageCode + "&search=" ${search};
                        }
                    </script>
                    <jsp:include page="../share/page.jsp"/>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="grade_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="grade/add" method="post" onsubmit="return addGrade(this);">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">添加年级</h4>
                </div>
                <div class="modal-body">
                    <table>
                        <tr>
                            <td>年级名称:</td>
                            <td><input type="text" name="grade"></td>
                        </tr>
                        <tr style="text-align: center;">
                            <td colspan="2"><span class="error" id="grade_add_error">&nbsp;</span>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
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
<script src="${pageContext.request.contextPath}/static/script/admin/grade.js"></script>
<script src="${pageContext.request.contextPath}/static/script/time.js"></script>
<script src="${pageContext.request.contextPath}/static/script/tips.js"></script>
</html>