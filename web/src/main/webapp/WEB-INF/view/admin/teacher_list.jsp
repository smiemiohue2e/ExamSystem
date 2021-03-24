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
    <title>教师管理</title>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/static/images/icon.ico">
    <link rel="BOOKMARK" href="${pageContext.request.contextPath}/static/images/icon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/head.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-admin-theme.css">

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/list_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/modal.css">
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
                    <form class="form-inline" action="${pageContext.request.contextPath}/admin/teacher/list"
                          method="post">
                        <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                            <div class="col-lg-4 form-group">
                                <label class="control-label">工号</label>
                                <input type="text" class="form-control" name="id">
                            </div>
                            <div class="col-lg-4 form-group">
                                <label class="control-label">姓名</label>
                                <input class="form-control" type="text" name="name">
                            </div>
                            <div class="col-lg-4 form-group">
                                <button type="submit" class="btn btn-primary" id="btn_query"
                                        onclick="query()">查询
                                </button>
                                <button type="button" class="btn btn-primary" id="btn_add"
                                        data-toggle="modal" data-target="#teacher_add">添加教师
                                </button>
                            </div>

                        </div>
                    </form>
                </div>
            </div>

            <div class="row">
                <table class="table table-hover table-bordered">
                    <thead>
                    <tr>
                        <th width="30%">教职工号</th>
                        <th width="40%">姓名</th>
                        <th width="30%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageBean.list}" var="teacher">
                        <tr>
                            <td>${teacher.id}</td>
                            <td>${teacher.name}</td>
                            <td>
                                <button class="btn btn-default btn-xs" onclick="toggleTeacherEdit(true,this);">编辑
                                </button>
                                <button class="btn btn-default btn-xs" onclick="toggleClazzEdit(true,this);">
                                    设置所教班级
                                </button>
                                <button class="btn btn-danger btn-xs" onclick="deleteTeacher(this);">删除</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class="pull-right">
                    <!-- 用于javascript提交，搜索内容 -->
                    <input type="hidden" id="search_content" value="${name}">
                    <script type="text/javascript">
                        function page(pageCode) {
                            var search = document.getElementById("search_content").value;
                            window.location.href = "${pageContext.request.contextPath}/admin/teacher/list?pn=" + pageCode + "&search=" + search;
                        }
                    </script>
                    <jsp:include page="../share/page.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>

    <%--教师添加--%>
    <div class="modal fade" id="teacher_add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <form action="admin/teacher/add" method="post" onsubmit="return addTeacher(this);">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">添加教师</h4>
                    </div>
                    <div class="modal-body form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">教职工号</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="id"
                                       placeholder="请输入教职工号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">教师姓名</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="name"
                                       placeholder="请输入教职工号">
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <span class="error" id="teacher_add_error">&nbsp;</span>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary">添加</button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <!-- 教师修改(编辑) -->
    <div class="modal fade" id="teacher_edit" tabindex="-1" role="dialog">
        <form action="" method="post" onsubmit="return editTeacher(this);">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <!--标题-->
                    <div class="modal-header">
                        编辑教师
                    </div>

                    <div class="modal-body form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">教职工号</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" name="id" id="teacher_edit_id"
                                       readonly="readonly" placeholder="请输入教职工号">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">教师姓名</label>
                            <div class="col-sm-6">
                                <input type="text" id="teacher_edit_name" class="form-control" name="name"
                                       placeholder="请输入教职工号">
                            </div>
                        </div>

                    </div>

                    <div class="modal-footer">
                        <span class="error" id="teacher_edit_error">&nbsp;</span>
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="submit" class="btn btn-primary">更新</button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <!-- 所教班级设置界面 -->
    <div class="modal fade" id="teacher_clazz_edit" tabindex="-1" role="dialog">

        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <!--标题-->
                <div class="modal-header">
                    所教班级
                </div>

                <div class="modal-body">
                    <div>
                        年级:<select id="grade_select" onchange="changeMajor(this);"></select>
                        专业:<select id="major_select" onchange="changeClazz(this);">
                        <option>专业...</option>
                    </select>
                        班级:<select id="clazz_select">
                        <option>班级...</option>
                    </select>
                        <button class="btn btn-default" onclick="addClazz();">添加</button>
                    </div>

                    <div class="error" style="text-align: center;" id="clazz_error">
                        &nbsp;
                    </div>

                    <div style="text-align: center;height: 100px;overflow-y: scroll;overflow-x: hidden;">
                        <ul style="list-style: none;padding: 0px;margin: 0px;" id="clazz_list"></ul>
                    </div>


                </div>
                <div class="modal-footer">
                    <button class="btn btn-danger" onclick="removeClazz();">移除所选</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary" onclick="save();">保存</button>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/script/admin/teacher.js"></script>
<script src="${pageContext.request.contextPath}/static/script/time.js"></script>
<script src="${pageContext.request.contextPath}/static/script/tips.js"></script>
</html>