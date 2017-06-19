<%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 2017/2/2
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>MyWorld</title>

    <link href="../../css/css_reset.css" rel="stylesheet">
    <link href="../../css/common.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <link href="../../css/myworld.css" rel="stylesheet">
    <link href="../../css/datetimepicker.min.css" rel="stylesheet">
</head>
<body>

<div class="nav_bar">
    <div class="logo_div">
        <img src="../../image/logo_white.png">
    </div>
    <div class="sub_nav" onclick="window.location.href='UserOrder.jsp'">预定</div>
    <div class="sub_nav native">我的world</div>
    <div class="message_div" onclick="logout()">
        <i class="fa fa-sign-out"></i>
    </div>
</div>

<div class="above_part">
    <div style="margin-top: 35px; margin-left: 65px;">
        <div class="portrait_div">
            <img class="portrait_img" src="../../image/portrait_img.svg">
            <div class="lvl_img_div">
                <img style="width: 100%" src="../../image/mem_lv_1.png">
            </div>
        </div>

        <div class="info_div">
            <span style="font-size: 24px; color: #2b2b2b;"></span><br>
            <span>男, 20, 大众会员</span>
        </div>
    </div>

    <div style="margin-top: 35px; margin-left: 65px;">
        <div class="sub_title">
            <div class="inner_div sub_native" onclick="changeTab(0)">我的预定</div>
        </div>
        <div class="sub_title">
            <div class="inner_div" onclick="changeTab(1)">入住信息</div>
        </div>
        <div class="sub_title" style="width: 52px;">
            <div class="inner_div" style="width: 42px;" onclick="changeTab(2)">账单</div>
        </div>
        <div class="sub_title">
            <div class="inner_div" onclick="changeTab(3)">我的账户</div>
        </div>
    </div>
</div>

<div class="below_part">

    <%-- 我的预定 --%>
    <div id="myOrder" class="sub_div">
        <div>
            <div class="sub_btn sub_btn_native" onclick="changeMyOrder(0)">尚未入住</div>
            <div class="sub_btn" onclick="changeMyOrder(1)">历史记录</div>
        </div>

        <div id="futureDiv">
            <div id="record_copy" class="each_record" style="display: none;">
                <div class="tip_con">订单号：<span>1234567</span></div>
                <div>
                    <div class="content" style="font-size: 20px; margin-bottom: -1px; margin-right: 20px;">
                        <span>南京紫峰大厦xxx青年旅社</span>
                    </div>
                    <div class="tip_con"><span>江苏省南京市鼓楼区</span></div>
                    <div class="tip_con"><span>2017-1-20</span>预定</div>
                    <div class="tip_con">入住时间：<span class="content">2017-2-18 14:00</span></div>
                    <div class="tip_con">退房时间：<span class="content">2017-2-19 14:00</span></div>
                </div>
                <div>
                    <div class="tip_con" style="margin-right: 35px;">房间信息：<span class="content">单人间标准房</span></div>
                    <div class="tip_con" style="margin-right: 35px;">支付金额：<span class="content">600</span>元</div>
                    <div class="tip_con" style="margin-right: 35px;">预留手机号：<span class="content">15250997793</span>
                    </div>
                    <div class="tip_con">预订人：<span class="content">李昊朔</span></div>
                </div>
                <div class="record_btn">退订</div>
            </div>
        </div>

        <div id="historyDiv"></div>
    </div>

    <%-- 入住信息 --%>
    <div id="checkIn" class="sub_div">

        <div id="checkIn_copy" class="each_record" style="display: none;">
            <div>
                <div class="content" style="font-size: 20px; margin-bottom: -1px; margin-right: 20px;">
                    <span>南京紫峰大厦xxx青年旅社</span>
                </div>
                <div class="tip_con"><span>江苏省南京市鼓楼区</span></div>
                <div class="tip_con">入住时间：<span class="content">2017-2-18 14:00</span></div>
                <div class="tip_con">退房时间：<span class="content">2017-2-19 14:00</span></div>
            </div>
            <div>
                <div class="tip_con" style="margin-right: 35px;">房间信息：<span class="content">单人间标准房</span></div>
                <div class="tip_con" style="margin-right: 35px;">支付金额：<span class="content">600</span>元</div>
                <div class="tip_con" style="margin-right: 35px;">预留手机号：<span class="content">15250997793</span>
                </div>
                <div class="tip_con">预订人：<span class="content">李昊朔</span></div>
            </div>
        </div>

    </div>

    <%-- 账单 --%>
    <div id="bill" class="sub_div bill_small_screen" style="display: none;">

        <div id="bill_copy" class="bill_div" style="display: none;">
            <div style="width: 30px; position: absolute; left: 5px;">
                <img src="../../image/nail.png" style="width: 100%">
            </div>
            <div class="month_div"><span>2017</span> 年 <span>1</span> 月</div>

            <div class="items_div"></div>
        </div>

        <div id="item_copy" class="each_item" style="display: none;">
            <div style="left: 0;">02-01</div>
            <div style="font-weight: 400; color: #2b2b2b; left: 50px;">-600.0</div>
            <div style="right: 0;">南京紫峰大厦青年旅社</div>
        </div>

    </div>

    <%-- 我的账户 --%>
    <div id="myAccount" class="sub_div"  style="display: none;">
        <%-- 头像部分 --%>
        <div class="head_info">
            <div class="photo_change">
                <img class="portrait_img" src="../../image/portrait_img.svg">
                <div class="fa fa-camera camera_btn"></div>
            </div>

            <div class="name_div">
                <div style="display: inline-block; vertical-align: middle;">
                    <input class="mod_field" style="font-size: 24px; width: 120px;" type="text" value="howSure" readonly>
                </div>
                <div style="display: inline-block; vertical-align: middle;">
                    <input style="display: none;" id="man" type="radio" name="sex" value="男">
                    <label for="man" style="display: none;">男</label>
                    <input style="display: none;" id="woman" type="radio" name="sex" value="女">
                    <label style="display: none;" for="woman">女</label>
                </div>

                <div style="margin-left: 5px;">
                    会员卡号：<span>1234567</span>
                </div>

            </div>

            <div class="mod_btn" onclick="modAccount()">修改</div>
            <div class="mod_con" style="display: none;">
                <div class="mod_btn" onclick="confirmAccount()">确认</div>
                <div class="mod_btn" style="margin-left: 0;" onclick="cancelAccount()">取消</div>
            </div>
        </div>

        <%-- 信息部分 --%>
        <div>
            <div class="each_info">
                <div>出生日期&nbsp;
                    <input id="birth" class="mod_field" type="text" value="1996-12-15" disabled>
                </div>
                <div style="margin-top: 10px;">手机号&nbsp;&nbsp;&nbsp;&nbsp;
                    <input class="mod_field" type="text" value="15250997793" readonly>
                </div>
                <div style="margin-top: 10px;">邮箱&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input class="mod_field" type="text" value="794637366@qq.com" readonly style="font-size: 16px;">
                </div>
            </div>

            <div class="each_info">
                <div>我的银行卡</div>
                <div style="font-size: 18px;">
                    <i class="fa fa-credit-card"></i>
                    <input class="mod_field" type="text" value="622222222222222" readonly>
                </div>
                <div class="exchange_card" onclick="changeCredit();">
                    <i class="fa fa-exchange"></i>&nbsp;更换银行卡
                </div>
                <div class="credit_con">
                    <div class="exchange_card" onclick="confirmCredit()"><i class="fa fa-check"></i></div>
                    <div class="exchange_card" onclick="cancelCredit()"><i class="fa fa-times"></i></div>
                </div>

            </div>

            <div class="each_info">
                <div style="margin-top: -10px;">
                    账户余额：<span class="span_em">1000</span>元
                    <i class="recharge" onclick="$('#rechargeDiv').slideDown()">充值</i>
                </div>

                <div style="margin-top: -5px;">当前积分：<span class="span_em">1600</span></div>
                <div class="note_div">·可抵用 <span>16</span> 元</div>

                <div style="margin-top: 10px;">会员等级：<span class="span_em">大众会员</span></div>
                <div class="note_div">·还需消费 <span>400</span> 元可升级为 <span>黄金会员</span></div>
            </div>
        </div>
    </div>
</div>

<div id="rechargeDiv" class="charge_div">
    <div style="margin: 30px 50px;">
        <input class="mod_field mod_state" type="text" placeholder="请输入充值金额">
    </div>
    <div style="width: 170px; margin: 10px auto;">
        <div class="step_btn" style="margin-right: 20px; background-color: #32af32" onclick="reCharge()">充值</div>
        <div class="step_btn" onclick="$('#rechargeDiv').slideUp()">取消</div>
    </div>
</div>

<div class="bottom_nav" style="margin-top: 0;">@CopyRight howSure</div>

<script src="../../js/jquery.js"></script>
<script src="../../js/myworld.js"></script>
<script src="../../js/datetimepicker.js"></script>
<script>
    $("#birth").datetimepicker({
        lang: 'ch',
        timepicker: false,
        format: 'Y-m-d'
    });
</script>
</body>
</html>
