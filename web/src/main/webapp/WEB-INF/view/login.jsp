<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName()
                    + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>用户登录</title>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css">
    <link rel="SHORTCUT ICON" href="${pageContext.request.contextPath}/static/images/icno.ico">
    <link rel="BOOKMARK" href="${pageContext.request.contextPath}/static/images/icno.ico">
    <script src="${pageContext.request.contextPath}/static/script/jquery-1.11.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/script/login.js"></script>
</head>
<body>
    <!--中间登录-->
    <form action="login/do" method="post" onsubmit="return check(this);">
            <div class="box">
                <h2>登录</h2>
                <div id="error_box"></div>
                <div class="input_box">
                    <input type="text" value="admin" placeholder="请输入账户名" name="username" />
                </div>
                <div class="input_box">
                    <input type="password" value="admin" placeholder="请输入密码" name="password"/>
                </div>
                <div class="input_box">
                    <input type="text" name="verify" placeholder="请输入验证码" id="yzm" value="xxxx" style="width: 248px;" />
                    <img src="${pageContext.request.contextPath}/kaptcha.jpg"  onclick="image(this);"
                         class="verify_image">
                </div>
                <div style="width: 200px;margin-left:20px;margin-bottom: 10px;">
                    <input id="radio_student" type="radio" name="role" value="1" checked>
                    <label for="radio_student">学生</label>
                    <input id="radio_teacher" type="radio" name="role" value="2">
                    <label for="radio_teacher">教师</label>
                    <input id="radio_manager" type="radio" name="role" value="3">
                    <label for="radio_manager">管理员</label>
                </div>
                <div class="input_box">
                    <button type="submit" >登录</button>
                    <span class="error" id="login_error">${error}</span>
                </div>
            </div>
    </form>
</body>
</html>