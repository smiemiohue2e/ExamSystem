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
<jsp:include page="share/head.jsp"/>
<div class="container">
    <div class="row">
        <jsp:include page="share/left.jsp"/>
        <div class="col-md-10">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default bootstrap-admin-no-table-panel">
                        <div class="panel-heading">
                            <div id="exam_details" class="text-muted bootstrap-admin-box-title">试卷：

                            </div>
                        </div>
                        <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                            <div class="col-lg-2 form-group">
                                <button type="button" class="btn btn-primary" data-toggle="modal" id="addSingleModal">
                                    添加单选题
                                </button>
                            </div>
                            <div class="col-lg-2 form-group">
                                <button type="button" class="btn btn-primary" id="addMultiModal" data-toggle="modal"
                                >添加多选题
                                </button>
                            </div>
                            <div class="col-lg-2 form-group">
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#judgeModal">添加判断题
                                </button>
                            </div>
                            <div class="col-lg-2 form-group">
                                <button type="button" class="btn btn-primary" data-toggle="modal"
                                        data-target="#question_bank">题库
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12" id="question-list">
                    <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>问题</th>
                            <th>类型</th>
                            <th>分数</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:choose>
                        <c:when test="${pageBean.list!=null&&pageBean.list.size()>0}">
                        <c:forEach items="${pageBean.list}" var="question">
                            <tr>
                                <td qid="${question.id}" type="${question.type}" hidden>${question.id}</td>
                                <td>${question.question}</td>
                                    <%--<c:if test="${question.type eq '1'}">--%>
                                    <%--<td>单选题</td>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${question.type eq '2'}">--%>
                                    <%--<td>多选题</td>--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${question.type eq '3'}">--%>
                                    <%--<td>判断题</td>--%>
                                    <%--</c:if>--%>
                                <td>${question.answerFacade}</td>
                                <td>${question.score}</td>
                                <td>
                                    <button name="show-edit-btn" class="btn btn-default btn-xs">编辑</button>
                                    <button name="show-rate-btn" class="btn btn-info btn-xs">正答率</button>
                                    <button name="delete-btn" class="btn btn-danger btn-xs">删除</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </c:when>
                            <c:otherwise>
                                <td>暂无数据</td>
                                <td>暂无数据</td>
                                <td>暂无数据</td>
                                <td>暂无数据</td>
                            </c:otherwise>
                        </c:choose>
                        </tbody>
                    </table>
                    <div class="pull-right">
                        <input type="hidden" id="search-content" value="${search}">
                        <!-- 题型 -->
                        <input type="hidden" id="question-type" value="${type}">
                        <script type="text/javascript">
                            function page(pageCode) {
                                var search = document.getElementById("search-content").value;
                                window.location.href = "${pageContext.request.contextPath}/teacher/exam/examManager/"+${examId}
                                    +"?pageCode="+ pageCode + "&search=" + search;
                            }
                        </script>
                        <jsp:include page="../share/page.jsp"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--------------------------------------添加单选题的模态框------------------------>
<form class="form-horizontal">   <!--保证样式水平不混乱-->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="singleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title">
                        添加新的选择题
                    </h4>
                </div>
                <div class="modal-body">
                    <!---------------------表单-------------------->
                    <div class="form-group">
                        <input type="hidden" id="examId" value="${examId}">
                        <input type="hidden" id="type">
                        <label class="col-sm-3 control-label">问题</label>
                        <div class="col-sm-7">
                            <input type="hidden" class="form-control" id="subjectId"
                                   value="">
                            <input type="text" class="form-control" id="choice_question" placeholder="请输入选择题问题">
                            <label class="control-label" for="choice_question" style="display:none;"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">选项A</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="optionA" placeholder="请输入选项A答案">
                            <label class="control-label" for="optionA" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">选项B</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="optionB" placeholder="请输入选项B答案">
                            <label class="control-label" for="optionB" style="display:none;"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">选项C</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="optionC" placeholder="请输入选项C答案">
                            <label class="control-label" for="optionC" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">选项D</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="optionD" placeholder="请输入选项D答案">
                            <label class="control-label" for="optionC" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">题目分数</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="point" placeholder="请输入题目分数">
                            <label class="control-label" style="display:none;"></label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">正确答案</label>
                        <div id="single_answer" class="col-sm-7">
                            <input type="radio" name="answer" value="0"/>A
                            <input type="radio" name="answer" value="1"/>B
                            <input type="radio" name="answer" value="2"/>C
                            <input type="radio" name="answer" value="3"/>D
                        </div>

                        <div id="multi_answer" class="col-sm-7">
                            <input type="checkbox" value="0"/>A
                            <input type="checkbox" value="1"/>B
                            <input type="checkbox" value="2"/>C
                            <input type="checkbox" value="3"/>D
                        </div>

                    </div>
                    <!---------------------表单-------------------->
                </div>
                <div class="modal-footer">
                    <span class="error" id="choice-error">&nbsp;</span>
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addMultiQuestion">
                        添加
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>
</form>


<!--------------------------------------添加判断题的模糊框------------------------>
<form class="form-horizontal">   <!--保证样式水平不混乱-->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="judgeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        添加新的判断题
                    </h4>
                </div>
                <div class="modal-body">

                    <!---------------------表单-------------------->
                    <div class="form-group">
                        <label class="col-sm-3 control-label">问题</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="judge_question" placeholder="请输入选择题问题">
                            <label class="control-label" for="judge_question" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">题目分数</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="judge_point" placeholder="请输入题目分数">
                            <label class="control-label" style="display:none;"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-3 control-label">正确答案</label>
                        <div class="col-sm-7">
                            <select class="form-control" id="judge_answer">
                                <option value="-1">请选择</option>
                                <option value="0">对</option>
                                <option value="1">错</option>
                            </select>
                            <label class="control-label" for="judge_answer" style="display: none;"></label>
                        </div>
                    </div>

                    <!---------------------表单-------------------->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary" id="addJudgeQuestion">
                        添加
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</form>

<!-- 正答率 -->
<div class="modal_window" id="rate-window" style="width: 200px;height: 100px;">
    <div class="modal_window_title" style="margin-bottom: 5px;">正答率:</div>
    <div id="rate" style="text-align: center;font-size: 18px;"></div>
    <div style="text-align: center;margin-top: 10px;">
        <button id="close-rate-btn" class="btn btn-default btn-xs">确定</button>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal fade form-horizontal" id="question_bank" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    题库
                </h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="col-sm-3 control-label">请选择</label>
                    <div class="col-sm-4">
                        <select class="form-control" id="question_type">
                            <option value="-1">请选择</option>
                            <option value="1">单选题</option>
                            <option value="2">多选题</option>
                            <option value="3">判断题</option>
                        </select>
                    </div>
                    <input id="btn_select_question" class="btn btn-primary" type="button" value="筛选">
                </div>
                <div class="form-group">
                    <ul id="bank-container"></ul>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button id="addQuestion" type="button" class="btn btn-primary">
                    添加
                </button>
            </div>
        </div>
    </div>
</div>


</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/script/time.js"></script>
<script src="${pageContext.request.contextPath}/static/script/tips.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/teacher/question_manage.js"></script>
</html>