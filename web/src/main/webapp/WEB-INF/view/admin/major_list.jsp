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
    <title>专业管理</title>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/${pageContext.request.contextPath}/static/images/icon.ico">
    <link rel="BOOKMARK" href="${pageContext.request.contextPath}/${pageContext.request.contextPath}/static/images/icon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-admin-theme.css">


</head>
<body>
<!--头部-->
<jsp:include page="share/head.jsp" />

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

                    <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                        <form class="form-inline"
                              action="${pageContext.request.contextPath}/admin/major/list" method="post"
                              onsubmit="return search(this);">
                            <div class="col-lg-4 form-group">
                                <label class="control-label">专业名</label>
                                <input class="form-control" name="search" type="text">
                            </div>
                            <div class="col-lg-4 form-group">
                                <button type="submit" class="btn btn-primary">搜索</button>
                                <button type="button" class="btn btn-primary" onclick="toggleMajorAdd(true);">添加专业
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
                        <th width="25%">id</th>
                        <th width="50%">专业名称</th>
                        <th width="25%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageBean.list}" var="major">
                        <tr>
                            <td>${major.id}</td>
                            <td>${major.name}</td>
                            <td>
                                <button class="btn btn-default btn-xs" onclick="toggleMajorEdit(true, this);">编辑
                                </button>
                                <button class="btn btn-danger btn-xs" onclick="deleteMajor(this);">删除</button>
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
                            window.location.href = "${pageContext.request.contextPath}/admin/major/list?pageCode=" + pageCode + "&search=${search}" ;
                        }
                    </script>
                    <jsp:include page="../share/page.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade" id="major_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

    <form method="post" onsubmit="return addMajor(this)">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">添加专业</h4>
                </div>
                <div class="modal-body form-inline">
                    <div class="form-group">
                        专业名称:
                        <input class="form-control" type="text" name="major">
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="error" id="major_add_error">&nbsp;</span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">提交</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </form>

</div>

<div class="modal fade" id="major_edit" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form method="post" onsubmit="return editMajor(this);">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">编辑专业</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="id" id="major_edit_id">
                    <table>
                        <tr>
                            <td>专业名称:</td>
                            <td><input type="text" class="form-control" id="major_edit_major" name="major"></td>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <span class="error" id="major_edit_error">&nbsp;</span>
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
<script src="${pageContext.request.contextPath}/static/script/admin/major.js"></script>
<script src="${pageContext.request.contextPath}/static/script/time.js"></script>
<script src="${pageContext.request.contextPath}/static/script/tips.js"></script>
</html>