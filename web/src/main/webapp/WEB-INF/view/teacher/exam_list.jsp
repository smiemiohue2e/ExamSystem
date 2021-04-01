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
    <title>试卷</title>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/static/images/icon.ico">
    <link rel="BOOKMARK" href="${pageContext.request.contextPath}/static/images/icon.ico">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-admin-theme.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/head.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/list_main.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/script/teacher/exam_list.js"></script>
    <script src="${pageContext.request.contextPath}/static/script/time.js"></script>
    <script src="${pageContext.request.contextPath}/static/script/tips.js"></script>
    <style type="text/css">
        .exam-table {
            width: 100%;
            height: 50% !important;
        }
    </style>
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
                <div class="col-lg-12">
                    <div class="panel panel-default bootstrap-admin-no-table-panel">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">查询</div>
                        </div>
                        <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                            <form class="form-horizontal"
                                  action="${pageContext.request.contextPath}/teacher/subjectManageAction_querySubject.action"
                                  method="post">
                                <div class="col-lg-5 form-group">
                                    <label class="col-lg-4 control-label" for="query_ano">试卷名称</label>
                                    <div class="col-lg-8">
                                        <input class="form-control" type="text" id="subjectName" name="subjectName">
                                        <label class="control-label" for="subjectName" style="display: none;"></label>
                                    </div>
                                </div>
                                <div class="col-lg-2 form-group">
                                    <button type="submit" class="btn btn-primary" id="btn_query" onclick="query()">查询
                                    </button>
                                    <button type="button" class="btn btn-primary" id="btn_add" data-toggle="modal"
                                            data-target="#addModal">添加
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div id="exam-list">
                    <table class="table table-hover table-bordered">
                        <thead>
                        <tr>
                            <th width="10%">id</th>
                            <th width="20%">标题</th>
                            <th width="20%">适用班级</th>
                            <th width="20%">状态</th>
                            <th width="10%">切换状态</th>
                            <th width="25%">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pageBean.list}" var="exam">
                            <tr>
                                <td>${exam.id}</td>
                                <td>${exam.title}</td>
                                <td>
                                    <button name="show-clazz-btn"
                                            class="btn btn-primary btn-xs">显示
                                    </button>
                                </td>
                                <c:choose>

                                    <c:when test="${exam.fkStatus == 1}">
                                        <td>未初始化</td>
                                        <td>
                                            无操作项
                                        </td>
                                    </c:when>
                                    <c:when test="${exam.fkStatus == 2}">
                                        <td>尚未运行</td>
                                        <td>
                                            <button class="btn btn-success btn-xs"
                                                    name="show-run-time-btn">开始运行
                                            </button>
                                        </td>
                                    </c:when>
                                    <c:when test="${exam.fkStatus == 3}">
                                        <td>正在运行</td>
                                        <td>
                                            <button class="btn btn-danger btn-xs" name="stop-run-btn">立即停止</button>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>运行结束(<a href="teacher/exam/statistics/${exam.id}">成绩统计</a>)
                                        </td>
                                        <td>
                                            <button class="btn btn-success btn-xs"
                                                    name="show-run-time-btn">重新运行
                                            </button>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    <!-- 保存试卷的时间限制 -->
                                    <input type="hidden" name="limit" value="${exam.timelimit}"/>
                                    <button class="btn btn-info btn-xs" name="show-edit-btn">编辑</button>
                                    <button class="btn btn-danger btn-xs" name="delete-exam-btn">删除</button>
                                    <button name="btn_questions" class="btn btn-danger btn-xs">
                                        <a href="/teacher/exam/examManager/${exam.id}">试题</a>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <div class="pull-right">
                        <!-- 用于javascript提交，搜索内容 -->
                        <script type="text/javascript">
                            function page(pageCode) {
                                window.location.href = "${pageContext.request.contextPath}/teacher/exam/list?pageCode="
                                    + pageCode;
                            }
                        </script>
                        <jsp:include page="../share/page.jsp"></jsp:include>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!--------------------添加试卷的模糊框------------------------>
<!-- 模态框（Modal） -->
<div class="modal fade form-horizontal" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    添加新的试卷
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="col-sm-3 control-label">试卷名称</label>
                    <div class="col-sm-7">
                        <input type="text" name="title" class="form-control" id="addSubjectName"
                               placeholder="请输入试卷名称">
                        <label class="control-label" for="addSubjectName" style="display:none;"></label>
                    </div>
                </div>
                <%--<div class="form-group">--%>
                <%--<label class="col-sm-3 control-label">专业</label>--%>
                <%--<div class="col-sm-7">--%>
                <%--<select class="form-control" id="addCourse">--%>
                <%--<option value="-1">请选择</option>--%>
                <%--</select>--%>
                <%--<label class="control-label" for="addCourse" style="display: none;"></label>--%>
                <%--</div>--%>
                <%--</div>--%>
                <div class="form-group">
                    <label class="col-sm-3 control-label">考试时间</label>
                    <div class="col-sm-7">
                        <input type="text" name="timelimit" class="form-control" id="addSubjectTime"
                               placeholder="请输入考试时间">
                        <label class="control-label" for="addSubjectTime" style="display:none;"></label>
                    </div>
                </div>
                <%--<div class="form-group">--%>
                <%--<label class="col-sm-3 control-label">选择题分数</label>--%>
                <%--<div class="col-sm-7">--%>
                <%--<input type="text" class="form-control" id="addChoiceScore" placeholder="请输入选择题的分值">--%>
                <%--<label class="control-label" for="addChoiceScore" style="display:none;"></label>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="form-group">--%>
                <%--<label class="col-sm-3 control-label">判断题分数</label>--%>
                <%--<div class="col-sm-7">--%>
                <%--<input type="text" class="form-control" id="addJudgeScore" placeholder="请输入判断题的分值">--%>
                <%--<label class="control-label" for="addJudgeScore" style="display:none;"></label>--%>
                <%--</div>--%>
                <%--</div>--%>
                <!---------------------表单-------------------->
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" id="addSubject">
                    添加
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<!-- 适用班级的显示&编辑 -->
<div class="modal modal_window teacher_clazz_window form-control" id="clazz-show">
    <div class="modal_window_title">
        适用班级: <img src="${pageContext.request.contextPath}/static/images/error.png" id="close-clazz-btn">
    </div>
    <div
            style="text-align: center; height: 100px; overflow-y: scroll; overflow-x: hidden;">
        <ul style="list-style: none; padding: 0px; margin: 0px;"
            id="exam-list-container"></ul>
    </div>
    <div
            style="text-align: center; border-top: 1px solid #E2E2E2; margin-top: 5px;">
        <button id="remove-clazz-btn">移除所选</button>
    </div>
    <div class="modal_window_title">添加班级:</div>
    <div>
        年级:<select id="grade_select"></select> 专业:<select id="major_select">
        <option>专业...</option>
    </select>
        班级:<select id="clazz_select">
        <option>班级...</option>
    </select>
    </div>
    <div class="error" style="text-align: center;" id="clazz_error">
        &nbsp;
    </div>
    <div style="text-align: center;">
        <button id="clazz-add-btn">添加</button>
        <button id="clazz-save-btn">保存</button>
    </div>
</div>
<!--设置运行天数-->
<div class="modal_window teacher_window form-control" id="run-time-set">
    <!--标题-->
    <div class="modal_window_title">
        运行天数: <img src="${pageContext.request.contextPath}/static/images/error.png" id="close-run-time-btn">
    </div>
    <table>
        <tr>
            <td>运行天数:</td>
            <td><input type="text" name="id"></td>
        </tr>
        <tr style="text-align: center;">
            <td colspan="2"><span class="error" id="run-time-error">&nbsp;</span>
            </td>
        </tr>
        <tr style="text-align: center;">
            <td colspan="2">
                <button id="run-time-save-btn">保存</button>
            </td>
        </tr>
    </table>
</div>
<!-- 试卷编辑 -->
<div class="modal_window teacher_clazz_window form-control " id="exam-edit" style="height: 150px;width: 400px;">
    <div class="modal_window_title" style="margin-bottom: 5px;">
        试卷编辑: <img src="${pageContext.request.contextPath}/static/images/error.png" id="close-edit-btn">
    </div>
    <hr style="margin-top: 5px;margin-bottom: 5px;"/>
    <div>
        <table class="exam-table">
            <tr>
                <td width="20%">试卷题目:</td>
                <td width="60%">
                    <input type="text" id="title-value"/>
                </td>
                <td width="20">
                    <span class="error"></span>
                </td>
            </tr>
            <tr>
                <td>时间限制:</td>
                <td>
                    <input type="text" id="limit-value"/>
                </td>
                <td>
                    <span class="error"></span>
                </td>
            </tr>
        </table>
    </div>
    <div style="text-align: center;">
        <button id="exam-save-btn">保存</button>
    </div>
</div>
</body>
</html>