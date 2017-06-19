<%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 2017/3/2
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>业务</title>

    <link href="../../css/css_reset.css" rel="stylesheet">
    <link href="../../css/homepage.css" rel="stylesheet">
    <link href="../../css/common.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="../../css/datetimepicker.min.css" rel="stylesheet">
    <link href="../../css/myworld.css" rel="stylesheet">
    <link href="../../css/hostelbusiness.css" rel="stylesheet">
</head>
<body style="background-image: none;">

<div class="nav_bar">
    <div class="logo_div">
        <img src="../../image/logo_white.png">
    </div>
    <div class="sub_nav native">业务</div>
    <div class="sub_nav" onclick="window.location.href='HostelFinance.jsp'">财务统计</div>
    <div class="message_div" onclick="logout()">
        <i class="fa fa-sign-out"></i>
    </div>
</div>

<%-- 旅店信息 --%>
<div class="hostel_info_div">

    <div class="hostel_img"><img src="../../image/HongKong.png"></div>
    <div class="info_right">
        <div>
            <span style="font-size: 24px;display: inline-block;vertical-align: bottom;"></span>
            <div class="mod_btn" onclick="modInfo()">修改</div>
        </div>

        <div>
            <div class="inner_each_part">
                <div><i class="fa fa-map-marker"></i> &nbsp;<span></span></div>
                <div style="margin-top: 15px;">
                    客栈描述：
                    <span style="font-size: 14px;"></span>
                </div>
            </div>

            <div class="inner_each_part">
                <div>店主姓名：<span></span></div>
                <div style="margin-top: 15px;">联系电话：<span></span></div>
                <div style="margin-top: 15px;">联系邮箱：<span></span></div>
            </div>
        </div>
    </div>

    <div class="notice_div">
        <div style="display: inline-block; vertical-align: top;"><img src="../../image/notice.svg">本店计划：</div>
        <div style="display: inline-block; vertical-align: top;">
            <span></span>
        </div>
    </div>

    <div style="margin-top: 30px;">
        <div class="sub_btn sub_btn_native" onclick="changeCheck(0)">入住登记</div>
        <div class="sub_btn" onclick="changeCheck(1)">离店登记</div>
    </div>
</div>

<%-- 登记部分 --%>
<div id="checkIn" class="checkIn_part">

    <div class="sub_div">

        <div style="padding-top: 30px;">
            <div style="color: #979797; padding-left: 15px; display: inline-block">
                若您已预订，请输入订单号
                <input class="mod_field mod_state" type="text" placeholder="订单号">
            </div>

            <div class="check_btn inline_style" style="width: 70px;" onclick="getTheOrder()">查询</div>
        </div>

        <div class="each_info">
            <div>
                <input class="mod_field mod_state" type="text" placeholder="房间号">
            </div>

            <div>
                <input id="checkInTime" class="mod_field mod_state" type="text" placeholder="入住时间" readonly>
            </div>

            <div>
                <input id="checkOutTime" class="mod_field mod_state" type="text" placeholder="预计退房时间" readonly>
            </div>
        </div>

        <div class="each_info">
            <div>入住人员登记</div>

            <div id="checks">
                <div class="each_check">
                    <input style="width: 80px;" class="mod_field mod_state" type="text" placeholder="姓名">
                    <input style="width: 160px;" class="mod_field mod_state" type="text" placeholder="联系方式">
                </div>
            </div>

            <div class="exchange_card" style="position: relative; top: -25px;" onclick="addCheck();">
                <i class="fa fa-plus-circle"></i>&nbsp;增加人员
            </div>


        </div>

        <div id="payPart" class="each_info">
            <div>
                <input class="mod_field mod_state" type="text" placeholder="押金金额">
            </div>
            <div style="margin-top: -25px;">
                会员支付：<input id="isMember" type="checkbox" style="margin-right: 5px;" onchange="checkMember(this)">
                <label for="isMember">会员</label>
            </div>
            <div style="margin-top: -35px;">
                支付方式：
                <input id="cash" name="pay" type="radio" checked style="top: 0;left: -5px;"><label for="cash">现金</label>
                <input id="card" name="pay" type="radio" style="top: 0;margin-left: 5px; display: none;">
                <label for="card" style="display: none;">会员卡</label>
            </div>

            <div id="member_input">
                <input class="mod_field mod_state" type="text" placeholder="会员卡号">
            </div>

            <div class="check_btn" onclick="checkIn()">完成</div>
        </div>
    </div>
</div>

<div id="checkOut" class="checkIn_part" style="display: none;">

    <div class="sub_div">
        <div class="each_info">
            <div>
                <input style="width: 130px;" class="mod_field mod_state inline_style" type="text" placeholder="房间号">
                <div class="check_btn inline_style" style="width: 70px;" onclick="getCheckIn()">查询</div>
            </div>

            <div style="margin-top: -35px;">
                入住时间：<span></span>
            </div>

            <div style="margin-top: -15px;">
                <input id="checkOutTime_out" class="mod_field mod_state" type="text" placeholder="退房时间" readonly>
            </div>
        </div>

        <div class="each_info">
            <div>入住人员：</div>
            <div id="checkOnes" class="check_ones">
                <%--<span>姓名</span><span style="margin-left: 10px;">联系方式</span><br>--%>
            </div>
        </div>

        <div id="payPart_out" class="each_info">
            <div>已付押金：<span style="color: #2b2b2b"></span>
                &nbsp;<span style="color: #2b2b2b;font-size: 14px;"></span>
            </div>
            <div style="margin-top: -15px;">
                <input class="mod_field mod_state" placeholder="应付金额">
            </div>
            <div class="check_btn" onclick="checkOut()">完成</div>
        </div>

    </div>
</div>

<div class="bottom_nav" style="margin-top: 0;">@CopyRight howSure</div>

<%-- 修改客栈信息 --%>
<div id="infoMod" class="modal_bg" style="display: none; position: fixed;">
    <div class="modal">

        <div class="block_div">
            <div class="left_part">
                <div class="textfield">
                    <input class="input_text" type="text" placeholder="店主姓名">
                </div>

                <div class="textfield" style="margin-bottom: 0;">
                    <input class="input_text" type="text" placeholder="联系邮箱">
                </div>

            </div>

            <div class="left_part">
                <div class="textfield">
                    <input class="input_text" type="text" placeholder="联系电话">
                </div>

                <div class="textfield" style="margin-bottom: 0;">
                    <textarea class="text_area" placeholder="店址(精确)"></textarea>
                </div>

            </div>

            <div class="left_part">
                <div class="textfield" style="margin-bottom: 0;">
                    <input class="input_text" type="text" placeholder="店名">
                </div>

                <textarea class="text_area" style="height: 100px; margin-top: 20px;"
                          placeholder="旅社描述（面积、房间数、环境等）"></textarea>
            </div>

            <div class="left_part" style="margin-top: 20px;">
                <textarea class="text_area" style="height: 170px;"
                          placeholder="本店计划"></textarea>
            </div>
        </div>

        <div class="much_text" style="margin-top: 15px;">
            <span style="color: #484948; font-weight: 500;">请确保以上信息填写的真实性，</span>
            审核过程中如果有问题我们将会与您电话联系，最终如果审核通过，我们将会为您更新信息。
        </div>

        <div style="width: 230px; margin: 30px auto;">
            <div class="step_btn" style="margin-right: 20px;" onclick="$('#infoMod').hide()">退出</div>
            <div class="step_btn" style="background-color: #32af32" onclick="modApply()">申请修改</div>
        </div>

        <div class="remind_div">已成功发送申请，请静候佳音，<span>3</span>秒后返回页面</div>
    </div>
</div>

<script src="../../js/jquery.js"></script>
<script src="../../js/datetimepicker.js"></script>
<script src="../../js/hostelbusiness.js"></script>
<script>
    $("#checkInTime").datetimepicker({
        lang: 'ch',
        timepicker: true,
        format: 'Y-m-d H:i'
    });
    $("#checkOutTime").datetimepicker({
        lang: 'ch',
        timepicker: true,
        format: 'Y-m-d H:i'
    });
    $("#checkOutTime_out").datetimepicker({
        lang: 'ch',
        timepicker: true,
        format: 'Y-m-d H:i'
    });
</script>
</body>
</html>
