<!DOCTYPE HTML>
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
    <title>学生管理</title>
    <base href="<%=basePath%>">
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/static/images/icon.ico">
    <link rel="BOOKMARK" href="${pageContext.request.contextPath}/static/images/icon.ico">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/head.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/list_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/modal.css">
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
                    <form class="form-inline" action="admin/student/list" method="post"
                          onsubmit="return searchStudent(this);">
                        <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                            <div class="col-lg-4 form-group">
                                <label class="control-label">姓名</label>
                                <input class="form-control" name="search" type="text">
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary">搜索
                                </button>
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        onclick="toggleStudentAdd(true);">添加学生
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
                        <th width="15%">学号</th>
                        <th width="30">学生姓名</th>
                        <th width="40%">班级</th>
                        <th width="15%">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${pageBean.list}" var="student">
                        <tr>
                            <td>${student.id}</td>
                            <td>${student.name}</td>
                            <td>${student.grade.grade}级${student.major.name}${student.clazz.cno}班</td>
                            <td>
                                <button class="btn btn-default btn-xs" onclick="toggleStudentEdit(true, this)">编辑
                                </button>
                                <button class="btn btn-danger btn-xs" onclick="deleteStudent(this)">删除</button>
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
                            window.location.href = "${pageContext.request.contextPath}/admin/student/list?pn=" + pageCode + "&search=" + search;
                        }
                    </script>
                    <jsp:include page="../share/page.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>
</div>


<%--添加学生--%>
<div class="modal fade" id="student_add" tabindex="-1" role="dialog">
    <form action="admin/student/add" method="post" onsubmit="return addStudent(this);">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">添加学生</h4>
                </div>
                <div class="modal-body form-horizontal">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">学号</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="id" placeholder="请输入学号">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">年级</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="grade" id="grade_select_add"
                                    onchange="changeMajor(this, true);">
                                <option value="0">年级...</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">专业</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="major_select_add" name="major"
                                    onchange="changeClazz(this, true);">
                                <option value="0">专业...</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">班级</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="clazz_select_add" name="clazz">
                                <option value="0">班级...</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">学生姓名</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" name="student">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span class="error" id="student_add_error">&nbsp;</span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">添加</button>
                </div>
            </div>
        </div>
    </form>
</div>

<!--学生修改-->
<div class="modal fade" id="student_edit" tabindex="-1" role="dialog">
    <form action="" id="student_edit_form" method="post" onsubmit="return editStudent(this);">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    编辑学生
                </div>

                <div class="modal-body form-horizontal">
                    <!--提交记录id-->
                    <input type="hidden" name="id" id="student_edit_id">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">年级</label>
                        <div class="col-sm-6">
                            <select class="form-control" name="grade" id="grade_select_edit"
                                    onchange="changeMajor(this, false);">
                                <option value="0">年级...</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">专业</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="major_select_edit" name="major"
                                    onchange="changeClazz(this, false);">
                                <option value="0">专业...</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">班级</label>
                        <div class="col-sm-6">
                            <select class="form-control" id="clazz_select_edit" name="clazz">
                                <option value="0">班级...</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">学生姓名</label>
                        <div class="col-sm-6">
                            <input class="form-control" type="text" name="student" id="student_edit_name">
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <span class="error" id="student_edit_error">&nbsp;</span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary">更新</button>
                </div>

            </div>
        </div>
    </form>
</div>
</body>


<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/script/admin/student.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/script/time.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/static/script/tips.js" charset="utf-8"></script>
</html>