<%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 2017/1/18
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MemberLogin</title>
</head>

<link href="../../css/homepage.css" rel="stylesheet">
<link href="../../css/css_reset.css" rel="stylesheet">
<link href="../../css/common.css" rel="stylesheet">
<body>

<div id="managerLogin" class="center_div">

    <div class="textfield" style="margin-top: 40px;">
        <input class="input_text" type="text" placeholder="请输入工作编号">
    </div>

    <div class="textfield">
        <input class="input_text" type="password" placeholder="请输入密码">
    </div>

    <div class="btn" onclick="login('#managerLogin')">登录</div>
</div>

<script src="../../js/jquery.js"></script>
<script src="../../js/homepage.js"></script>
</body>
</html>
