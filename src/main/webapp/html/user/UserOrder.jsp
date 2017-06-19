<%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 2017/2/1
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>

    <link href="../../css/homepage.css" rel="stylesheet">
    <link href="../../css/css_reset.css" rel="stylesheet">
    <link href="../../css/common.css" rel="stylesheet">
    <link href="../../css/datetimepicker.min.css" rel="stylesheet">
    <link href="../../css/userorder.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body onclick="hideSearch(event)">

<div class="nav_bar">
    <div class="logo_div">
        <img src="../../image/logo_white.png">
    </div>
    <div class="sub_nav native">预定</div>
    <div class="sub_nav" onclick="window.location.href='MyWorld.jsp'">我的world</div>
    <div class="message_div" onclick="logout()">
        <i class="fa fa-sign-out"></i>
    </div>
</div>

<div class="search_div">
    <input class="input_text search_input" type="text" placeholder="按地区搜索hostel"
           onkeydown="keyDown(event)">
    <div class="btn search_btn" onclick="search()">搜索</div>
    <div class="key_btn key_selected" onclick="changeKey(0)">地区</div>
    <div class="key_btn" style="left: 58px;" onclick="changeKey(1)">店名</div>

    <div id="searchResult" class="result_div">
        <div id="noSearch">未搜索到相关客栈</div>
    </div>

    <div id="search_copy" class="each_search" style="display: none;">
        <span>客栈xxxxxxxx</span>
        <span class="location_div">南京市鼓楼区aaaa</span>
        <a style="display: none;"></a>
    </div>
</div>

<div class="introduce_div">
    <div class="each_location">
        <div class="location_name">北京</div>
        <div class="count_div"><span class="fa fa-map-marker"></span>&nbsp;52旅社</div>
        <div class="bg_each"><img src="../../image/BeiJing.png"></div>
        <div class="introduce_text">入住北京旅舍，感受天安门广场、长城及紫禁城的魅力</div>
    </div>

    <div class="each_location">
        <div class="location_name">香港</div>
        <div class="count_div"><span class="fa fa-map-marker"></span>&nbsp;52旅社</div>
        <div class="bg_each"><img src="../../image/HongKong.png"></div>
        <div class="introduce_text">住香港旅社，体验购物与美食、游乐场与自由港、潮流和传统的交融</div>
    </div>

    <div class="each_location">
        <div class="location_name">台北</div>
        <div class="count_div"><span class="fa fa-map-marker"></span>&nbsp;52旅社</div>
        <div class="bg_each"><img src="../../image/TaiBei.png"></div>
        <div class="introduce_text">住台北旅舍，访龙山寺古刹或畅享新北投温泉。</div>
    </div>

    <div class="each_location" style="border-right: none;">
        <div class="location_name">曼谷</div>
        <div class="count_div"><span class="fa fa-map-marker"></span>&nbsp;52旅社</div>
        <div class="bg_each"><img src="../../image/ManGu.png"></div>
        <div class="introduce_text">入住曼谷旅舍，感受"天使之城"、"佛教之都"的异域风情。</div>
    </div>
</div>

<div class="intro_title">消费越多、优惠越多</div>
<div class="lvl_div">
    <div class="each_level">
        <div class="circle_lvl">
            <img src="../../image/mem_lv_1.png">
        </div>
        <div class="rule_div">
            <span>大众会员</span><br>
        </div>
        <div class="detail_div">
            · 0≤消费<1,000元<br>
            · 1元=1积分<br>
        </div>
    </div>
    <div class="each_level" style="background-color: #f3c07f;">
        <div class="circle_lvl">
            <img src="../../image/mem_lv_2.png">
        </div>
        <div class="rule_div">
            <span>黄金会员</span><br>
        </div>
        <div class="detail_div">
            · 1,000≤消费<5,000元<br>
            · 1元=1.1积分<br>
        </div>
    </div>
    <div class="each_level" style="background-color: #bdbbcf;">
        <div class="circle_lvl">
            <img src="../../image/mem_lv_3.png">
        </div>
        <div class="rule_div">
            <span>铂金会员</span><br>
        </div>
        <div class="detail_div">
            · 5,000≤消费<20,000元<br>
            · 1元=1.2积分<br>
        </div>
    </div>
    <div class="each_level" style="background-color: #abafea;">
        <div class="circle_lvl">
            <img src="../../image/mem_lv_4.png">
        </div>
        <div class="rule_div">
            <span>钻石会员</span><br>
        </div>
        <div class="detail_div">
            · 20,000≤消费<100,000元<br>
            · 1元=1.3积分<br>
        </div>
    </div>
    <div class="each_level" style="background-color: #777777;">
        <div class="circle_lvl">
            <img src="../../image/mem_lv_5.png">
        </div>
        <div class="rule_div">
            <span>黑卡贵宾</span><br>
        </div>
        <div class="detail_div">
            · 100,000元≤消费<br>
            · 1元=1.4积分<br>
            · 先入住后付款
        </div>
    </div>
    <div class="each_level" style="background-color: #e5e8e9;">
        <div class="circle_lvl">
            <img src="../../image/mem_lv_more.png">
        </div>
        <div class="rule_div">
            <span style="font-size: 18px;">积分兑换</span><br>
        </div>
        <div class="detail_div">
            · 100积分=1元<br>
            · 只用于线上支付<br>
            · 不能够兑换现金
        </div>
    </div>
</div>

<%-- 客栈信息 --%>
<div id="hostelDetail" class="modal_bg" style="display: none;">
    <div class="modal" style="position: relative;">

        <div class="close_btn" onclick="$('#hostelDetail').hide()"><i class="fa fa-times"></i></div>

        <div class="block_div" style="margin-top: 40px;">
            <div class="hostel_img">
                <img style="width: 100%" src="../../image/HongKong.png">
            </div>

            <div class="info_div">
                <div><span>客栈名称</span></div>
                <div class="location_info">地址：<span>江苏省南京市鼓楼区</span></div>
                <div class="price_div">￥<span>200</span>元/天</div>
            </div>
        </div>

        <div class="block_div" style="margin-top: 15px;">
            <div class="left_part" style="margin-right: 24px;">
                <div class="textfield" style="margin-bottom: 0;">
                    <input id="checktime" class="input_text" type="text" placeholder="入住时间" readonly>
                </div>

                <div class="textfield" style="margin-bottom: 0;">
                    <input id="leavetime" class="input_text" type="text" placeholder="离店时间" readonly>
                </div>
            </div>

            <div class="left_part">
                <div class="textfield" style="margin-bottom: 0; line-height: 50px; font-size: 20px;">
                    预付金额：<span style="font-size: 24px;font-weight: 600;color: #f17350">￥100</span>
                </div>

                <div class="btn" onclick="makeOrder()">立即预定</div>
            </div>
        </div>

        <div class="remind_div">预订成功！订单号已发送到您的邮箱。<br><span>3</span>秒后刷新页面</div>
    </div>
    <a style="display: none"></a>
</div>

<div class="bottom_nav">@CopyRight howSure</div>

<script src="../../js/jquery.js"></script>
<script src="../../js/homepage.js"></script>
<script src="../../js/userorder.js"></script>
<script src="../../js/datetimepicker.js"></script>
<script>
    $("#checktime").datetimepicker({
        lang: 'ch',
        timepicker: true,
        format: 'Y-m-d H:i'
    });
    $("#leavetime").datetimepicker({
        lang: 'ch',
        timepicker: true,
        format: 'Y-m-d H:i'
    });
</script>
</body>
</html>
