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
    <title>题目列表</title>
    <meta charset="UTF-8">
    <base href="<%=basePath%>">
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/static/images/icon.ico">
    <link rel="BOOKMARK" href="${pageContext.request.contextPath}/static/images/icon.ico">
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/head.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/list_main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/modal.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-admin-theme.css">

    <script type="text/javascript">
        //搜索
        function searchQuestion(form) {
            var value = $.trim(form.search.value);
            if (value !== "") {
                return true;
            }
            return false;
        }
    </script>
</head>
<body>
<jsp:include page="share/head.jsp" />
<div class="container">
    <div class="row">
        <jsp:include page="share/left.jsp"/>
        <div class="col-md-10">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default bootstrap-admin-no-table-panel">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">搜索</div>
                        </div>
                        <!--搜索框-->
                        <form action="teacher/question/${type == 'SINGLE' ? 'singles' : (type == 'MULTI' ? 'multis' : 'judges')}/1"
                              method="post" onsubmit="return searchQuestion(this);">
                            <div class="form-inline bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                                <div class="col-lg-4 form-group">
                                    <label class="control-label">题名</label>
                                    <input class="form-control" name="search" type="text">
                                </div>
                                <div class="form-group">
                                    <button type="submit" class="btn btn-primary">搜索
                                    </button>
                                    <button id="add-question-btn" class="btn btn-primary">添加题目</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12" id="question-list">
                    <table class="table table-hover table-bordered">

                    <c:choose>
                            <c:when test="${type == 'JUDGE'}">
                                <thead>
                                <tr>
                                    <th width="10%">id</th>
                                    <th width="40%">标题</th>
                                    <th width="10%">答案</th>
                                    <th width="20%">分值</th>
                                    <th width="20%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pageBean.list}" var="question">
                                    <tr>
                                        <td>${question.id}</td>
                                        <td>${question.title}</td>
                                        <td>${question.answerFacade}</td>
                                        <td>${question.point}</td>
                                        <td>
                                            <button name="show-edit-btn" class="btn btn-default btn-xs">编辑</button>
                                            <button name="show-rate-btn" class="btn btn-info btn-xs">正答率</button>
                                            <button name="delete-btn" class="btn btn-danger btn-xs">删除</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </c:when>
                            <c:otherwise>
                                <thead>
                                <tr>
                                    <th width="5%">id</th>
                                    <th width="25%">标题</th>
                                    <th width="10%">选项A</th>
                                    <th width="10%">选项B</th>
                                    <th width="10%">选项C</th>
                                    <th width="10%">选项D</th>
                                    <th width="5%">答案</th>
                                    <th width="5%">分值</th>
                                    <th width="20%">操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pageBean.list}" var="question">
                                    <tr>
                                        <td>${question.id}</td>
                                        <td>${question.title}</td>
                                        <td>${question.optiona}</td>
                                        <td>${question.optionb}</td>
                                        <td>${question.optionc}</td>
                                        <td>${question.optiond}</td>
                                        <td>${question.answerFacade}</td>
                                        <td>${question.point}</td>
                                        <td>
                                            <button name="show-edit-btn" class="btn btn-default btn-xs">编辑</button>
                                            <button name="show-rate-btn" class="btn btn-info btn-xs">正答率</button>
                                            <button name="delete-btn" class="btn btn-danger btn-xs">删除</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </c:otherwise>
                        </c:choose>
                    </table>
                    <div class="pull-right">
                        <input type="hidden" id="search-content" value="${search}">
                        <!-- 题型 -->
                        <input type="hidden" id="question-type" value="${type}">
                        <script type="text/javascript">
                            function page(pageCode) {
                                var search = document.getElementById("search-content").value;
                                window.location.href = "${pageContext.request.contextPath}/teacher/question/singles/"
                                    + pageCode + "?search=" + search;
                            }
                        </script>
                        <jsp:include page="../share/page.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 题目编辑 -->
<div class="modal fade" id="question-modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">题目编辑</h4>
            </div>
            <div id="question-edit" class="modal-body form-horizontal">
                <table style="margin: 0px auto 0px;width: 350px;">
                    <tr>
                        <td width="15%">题目:</td>
                        <td width="60%">
                            <input type="text" name="title" class="required"/>
                        </td>
                        <td width="25%">
                            <span class="error"></span>
                        </td>
                    </tr>
                    <c:if test="${type != 'JUDGE'}">
                        <tr>
                            <td>选项A:</td>
                            <td>
                                <input type="text" name="optionA" class="required"/>
                            </td>
                            <td>
                                <span class="error"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>选项B:</td>
                            <td>
                                <input type="text" name="optionB" class="required"/>
                            </td>
                            <td>
                                <span class="error"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>选项C:</td>
                            <td>
                                <input type="text" name="optionC" class="required"/>
                            </td>
                            <td>
                                <span class="error"></span>
                            </td>
                        </tr>
                        <tr>
                            <td>选项D:</td>
                            <td>
                                <input type="text" name="optionD" class="required"/>
                            </td>
                            <td>
                                <span class="error"></span>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td>答案:</td>
                        <td id="question-answer-container">
                            <c:choose>
                                <c:when test="${type == 'SINGLE'}">
                                    <input type="radio" name="answer" value="0"/>A
                                    <input type="radio" name="answer" value="1"/>B
                                    <input type="radio" name="answer" value="2"/>C
                                    <input type="radio" name="answer" value="3"/>D
                                </c:when>
                                <c:when test="${type == 'MULTI'}">
                                    <input type="checkbox" value="0"/>A
                                    <input type="checkbox" value="1"/>B
                                    <input type="checkbox" value="2"/>C
                                    <input type="checkbox" value="3"/>D
                                </c:when>
                                <c:otherwise>
                                    <input type="radio" name="anser" value="0"/>对
                                    <input type="radio" name="answer" value="1"/>错
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <tr>
                        <td>分值:</td>
                        <td>
                            <input type="text" name="point" class="number"/>
                        </td>
                        <td>
                            <span class="error"></span>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <span class="error" id="question-error">&nbsp;</span>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button id="question-save-btn" class="btn btn-primary">添加</button>
            </div>
        </div>
    </div>
</div>
<!-- 正答率 -->
<div class="modal_window" id="rate-window" style="width: 200px;height: 100px;">
    <div class="modal_window_title" style="margin-bottom: 5px;">正答率:</div>
    <div id="rate" style="text-align: center;font-size: 18px;"></div>
    <div style="text-align: center;margin-top: 10px;">
        <button id="close-rate-btn" class="btn btn-default btn-xs">确定</button>
    </div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/script/time.js"></script>
<script src="${pageContext.request.contextPath}/static/script/tips.js"></script>
<script src="/static/script/teacher/question.js"></script>
</html>