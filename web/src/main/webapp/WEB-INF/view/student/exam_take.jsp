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
	<title>参加考试</title>
	<meta charset="UTF-8">
	<base href="<%=basePath%>">
	<link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/static/images/icon.ico">
	<link rel="BOOKMARK" href="${pageContext.request.contextPath}/static/images/icon.ico">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css"
		  href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-theme.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap-admin-theme.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/student/exam_take.css">
	<style>
		.col-center-block {
			float: none;
			display: block;
			margin-left: auto;
			margin-right: auto;
		}
	</style>
</head>
<body>
<!--头部-->
<jsp:include page="share/head.jsp"/>
<div class="container">
	<div class="row myCenter">
		<div class="col-md-12">
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default bootstrap-admin-no-table-panel">
						<div class="panel-heading">
							<div class="text-muted bootstrap-admin-box-title">试卷信息</div>
						</div>
						<div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
							<div class="col-lg-12 form-group">
								<label class="col-lg-6 control-label"><h4><strong><%--${exam.title}--%></strong></h4></label>
								<label class="col-lg-6 control-label"><i>(卷面总分:<%--${exam.points}--%>)</i></label>
							</div>
							<div class="col-lg-3 form-group">
								<label class="col-lg-6 control-label">课程:</label>
								<label class="col-lg-6 control-label"><i>占位符</i></label>
							</div>

							<div class="col-lg-3 form-group">
								<label class="col-lg-6 control-label">| 考试时间:</label>
								<label class="col-lg-6 control-label"><i><%--${exam.limit}--%>分钟</i></label>
							</div>

							<div class="col-lg-3 form-group">
								<label class="col-lg-6 control-label">| 考生姓名:</label>
								<label class="col-lg-6 control-label"><i><%--${student.name}--%></i> </label>
							</div>

							<div class="col-lg-3  form-group">
								<label class="col-lg-6 control-label">| 剩余时间:</label>
								<label class="col-lg-6 control-label" id="count-time"><%--${exam.limit}--%>:00</label>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-lg-12 col-center-block">
				<!-- 时间限制以及倒计时 -->
				<input type="hidden" id="time-limit" value="${exam.limit}"/>
				<!-- 此试卷的id -->
				<input type="hidden" id="exam-id" value="${eid}"/>
				<input type="hidden" id="context-path" value="<%=basePath %>"/>
				<%--<div class="time">--%>
				<%--时间限制:&nbsp;&nbsp;<span class="time-limit">${exam.limit}</span>分钟--%>
				<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;剩余时间:&nbsp;&nbsp;<span id="count-time">${exam.limit}:00</span>--%>
				<%--</div>--%>
				<hr>
				<div id="single-container">
					<!-- 题型标识 -->
					<div class="title">
						一、单选题　(共${exam.singlePoints}分)
					</div>
					<c:forEach items="${exam.singleQuestions}" var="question" varStatus="status">
						<div class="question">
							<!-- 保存题目id -->
							<input type="hidden" name="question-id" value="${question.id}"/>
							<div class="question-title">
									${status.index + 1}.&nbsp;&nbsp;${question.title} (${question.point}分)
							</div>
							<ul class="question-option">
								<li>A.&nbsp;&nbsp;${question.optiona}</li>
								<li>B.&nbsp;&nbsp;${question.optionb}</li>
								<li>C.&nbsp;&nbsp;${question.optionc}</li>
								<li>D.&nbsp;&nbsp;${question.optiond}</li>
							</ul>
							<div class="question-answer">
								答案:
								<input type="radio" name="single-${status.index}" value="0"/>A
								<input type="radio" name="single-${status.index}" value="1"/>B
								<input type="radio" name="single-${status.index}" value="2"/>C
								<input type="radio" name="single-${status.index}" value="3"/>D
							</div>
						</div>
					</c:forEach>
				</div>
				<hr>
				<!-- 多选题 -->
				<div id="multi-container">
					<div class="title">
						二、多选题　(共${exam.multiPoints}分)
					</div>
					<c:forEach items="${exam.multiQuestions}" var="question" varStatus="status">
						<div class="question">
							<input type="hidden" name="question-id" value="${question.id}"/>
							<div class="question-title">
									${status.index + 1}.&nbsp;&nbsp;${question.title} (${question.point}分)
							</div>
							<ul class="question-option">
								<li>A.&nbsp;&nbsp;${question.optiona}</li>
								<li>B.&nbsp;&nbsp;${question.optionb}</li>
								<li>C.&nbsp;&nbsp;${question.optionc}</li>
								<li>D.&nbsp;&nbsp;${question.optiond}</li>
							</ul>
							<div class="question-answer">
								答案:
								<input type="checkbox" value="0"/>A
								<input type="checkbox" value="1"/>B
								<input type="checkbox" value="2"/>C
								<input type="checkbox" value="3"/>D
							</div>
						</div>
					</c:forEach>
				</div>
				<hr>
				<!-- 判断题 -->
				<div id="judge-container">
					<div class="title">
						三、判断题　(共${exam.judgePoints}分)
					</div>
					<c:forEach items="${exam.judgeQuestions}" var="question" varStatus="status">
						<div class="question">
							<input type="hidden" name="question-id" value="${question.id}"/>
							<div class="question-title">
									${status.index + 1}.&nbsp;&nbsp;${question.title} (${question.point}分)
							</div>
							<div class="question-answer">
								答案:
								<input type="radio" name="judge-${status.index}" value="0"/>对
								<input type="radio" name="judge-${status.index}" value="1"/>错
							</div>
						</div>
					</c:forEach>
				</div>
				<hr/>
				<div style="text-align: center;margin-bottom: 20px;">
					<button id="submit-btn">提交</button>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/static/script/time.js"></script>
<script src="${pageContext.request.contextPath}/static/script/tips.js"></script>
<script src="${pageContext.request.contextPath}/static/script/student/exam_take.js"></script>
</html>