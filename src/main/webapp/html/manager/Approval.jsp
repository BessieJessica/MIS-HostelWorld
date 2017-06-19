<%@ page import="org.springframework.http.HttpRequest" %><%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 2017/3/7
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>审批</title>

    <link href="../../css/css_reset.css" rel="stylesheet">
    <link href="../../css/common.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="../../css/myworld.css" rel="stylesheet">
    <link href="../../css/homepage.css" rel="stylesheet">
    <link href="../../css/manager.css" rel="stylesheet">
</head>

<body style="background: none;">

<div class="nav_bar">
    <div class="logo_div">
        <img src="../../image/logo_white.png">
    </div>
    <div class="sub_nav native">审批</div>
    <div class="sub_nav" onclick="window.location.href='ManagerFinance.jsp'">财务统计</div>
    <div class="message_div" onclick="logout()">
        <i class="fa fa-sign-out"></i>
    </div>
</div>

<div class="above_part">
    <div style="margin-top: 35px; margin-left: 65px;">
        <div class="portrait_div">
            <img class="portrait_img" src="../../image/portrait_img.svg">
        </div>

        <div class="info_div">
            <span style="font-size: 24px; color: #2b2b2b;"><%=session.getAttribute("managerName") %></span><br>
            <span>总经理</span>
        </div>
    </div>

    <div style="margin-top: 35px; margin-left: 65px;">
        <div class="sub_title">
            <div class="inner_div sub_native" style="width: 70px;" onclick="changeTab(0)">待审批</div>
        </div>
        <div class="sub_title">
            <div class="inner_div" style="width: 70px;" onclick="changeTab(1)">已审批</div>
        </div>
    </div>
</div>

<div class="below_part">

    <%-- 待审批 --%>
    <div id="toApprove" class="sub_div">
        <div id="approve_copy" class="approve_div" style="display: none;">
            <img class="envelope_img" src="../../image/envelope.png">
            <div class="apply_text">
                <span>开店申请</span><br>
                <span>2017-03-11</span>
                <a style="display: none;"></a>
            </div>
        </div>
    </div>

    <%-- 已审批 --%>
    <div id="hasApprove" class="sub_div">

    </div>
</div>

<div class="bottom_nav" style="margin-top: 0;">@CopyRight howSure</div>

<%-- 开店申请 --%>
<div id="hostelApply" class="modal_bg" style="display: none;">
    <div class="modal" style="position: relative;">

        <div class="close_btn" onclick="$('#hostelApply').hide()"><i class="fa fa-times"></i></div>

        <div class="block_div">
            <div class="left_part">
                <div class="textfield">
                    <input class="input_text" type="text" placeholder="" readonly>
                </div>

                <div class="textfield" style="margin-bottom: 0;">
                    <input class="input_text" style=" font-size: 16px;" type="text" placeholder="" readonly>
                </div>

            </div>

            <div class="left_part">
                <div class="textfield">
                    <input class="input_text" type="text" placeholder="" readonly>
                </div>

                <div class="textfield" style="margin-bottom: 0;">
                    <input class="input_text" style=" font-size: 16px;" type="text" placeholder="" readonly>
                </div>

            </div>
        </div>

        <div class="block_div">
            <div class="left_part">
                <div class="textfield" style="margin-bottom: 0;">
                    <input class="input_text" type="text" placeholder="" readonly>
                </div>
            </div>

            <div class="left_part">
                <div class="textfield" style="margin-bottom: 0;">
                    <textarea class="text_area" placeholder="" readonly></textarea>
                </div>
            </div>
        </div>

        <div class="block_div" style="margin-top: 20px;">

            <div class="describe_div" style="width: 370px;">
                <textarea class="text_area" style="width: 100%;height: 100px;"
                          placeholder="" readonly></textarea>
            </div>

            <div class="describe_div" style="width: 255px; display: none;">
                <textarea class="text_area" style="width: 255px;height: 100px;"
                          placeholder="" readonly></textarea>
            </div>

            <div class="upload_div" style="border: none;">
                <img style="width: 100%; height: 100px;" src="../../image/HongKong.png">
            </div>
        </div>

        <div style="width: 230px; margin: 30px auto;">
            <div class="step_btn" style="margin-right: 20px;" onclick="approve('inapprove')">拒绝</div>
            <div class="step_btn" style="background-color: #32af32" onclick="approve('approve')">通过</div>
        </div>

    </div>
    <a style="display: none;"></a>
</div>

<script src="../../js/jquery.js"></script>
<script src="../../js/approval.js"></script>
</body>
</html>
