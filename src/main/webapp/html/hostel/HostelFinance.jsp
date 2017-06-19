<%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 2017/3/7
  Time: 20:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>财务统计</title>

    <link href="../../css/css_reset.css" rel="stylesheet">
    <link href="../../css/common.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="../../css/manager.css" rel="stylesheet">
</head>
<body>

<div class="nav_bar">
    <div class="logo_div">
        <img src="../../image/logo_white.png">
    </div>
    <div class="sub_nav" onclick="window.location.href='HostelBusiness.jsp'">业务</div>
    <div class="sub_nav native">财务统计</div>
    <div class="message_div" onclick="logout()">
        <i class="fa fa-sign-out"></i>
    </div>
</div>

<div class="total_div">
    <div class="each_total">
        <img src="../../image/time.svg">
        <div>
            已加盟<span>15</span>天
        </div>
    </div>
    <div class="each_total">
        <img src="../../image/sales.svg">
        <div>
            总经营额<span>20000</span>元
        </div>
    </div>
    <div class="each_total">
        <img src="../../image/trades.svg">
        <div>
            总入住<span>200</span>人次
        </div>
    </div>
</div>

<%-- 图表 --%>
<div id="chartDiv" class="chart_div" style="width: 60%; height: 300px;"></div>

<%-- 记录 --%>
<div id="recordDiv" class="record_div">

    <div>
        <div class="quote_div" onclick="toggleSlide('#checkInPart')">入店记录 <i class="fa fa-angle-down"></i></div>
        <div id="checkInPart" class="each_record" style="display: block">
            <div id="item_copy" class="each_item" style="display: none;">
                <div style="width: 150px; margin-left: 0;"></div>
                <div style="width: 150px;"></div>
                <div style="width: 90px;"></div>
                <div style="width: 60px;">非会员</div>
                <div style="width: 70px;">已离店</div>
                <div style="width: 125px; margin-left: 30px;"></div>
                <div style="float: right; width: 100px;"></div>
            </div>
        </div>
    </div>

    <div>
        <div class="quote_div" onclick="toggleSlide('#checkOutPart')">离店记录 <i class="fa fa-angle-down"></i></div>
        <div id="checkOutPart" class="each_record">

        </div>
    </div>

    <div>
        <div class="quote_div" onclick="toggleSlide('#orderPart')">订单记录 <i class="fa fa-angle-down"></i></div>
        <div id="orderPart" class="each_record">
            <div id="order_copy" class="each_item">
                <div style="width: 150px; margin-left: 0;">订单时间</div>
                <div style="width: 130px;">订单号</div>
                <div style="width: 130px;">会员号</div>
                <div style="width: 70px;">入住状态</div>
                <div style="width: 150px; margin-left: 30px;">(预计)入住时间</div>
                <div style="float: right; width: 100px;">已支付金额</div>
            </div>
        </div>
    </div>
</div>


<div class="bottom_nav" style="margin-top: 0;">@CopyRight howSure</div>

<script src="../../js/jquery.js"></script>
<script src="../../js/hostelfinance.js"></script>
<script src="../../js/chart.js"></script>
<script src="../../js/echarts.min.js"></script>

</body>
</html>
