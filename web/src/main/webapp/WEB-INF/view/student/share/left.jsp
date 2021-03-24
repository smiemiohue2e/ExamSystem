<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-2 bootstrap-admin-col-left">
    <ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
        <li>
            <a href="${pageContext.request.contextPath}/student/index">
                <i class="glyphicon glyphicon-chevron-right"></i> 主页
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/student/exam/list">
                <i class="glyphicon glyphicon-chevron-right"></i> 参加考试
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/student/notes">
                <i class="glyphicon glyphicon-chevron-right"></i> 考试记录
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/">
                <i class="glyphicon glyphicon-chevron-right"></i> 关于我们
            </a>
        </li>
    </ul>
</div>