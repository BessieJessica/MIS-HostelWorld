<%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 2017/1/18
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HomePage</title>

    <link href="../css/homepage.css" rel="stylesheet">
    <link href="../css/css_reset.css" rel="stylesheet">
    <link href="../css/common.css" rel="stylesheet">
    <link href="../css/datetimepicker.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>

<div id="initial" class="center_div">
    <div class="tab_div tab_selected" onclick="changeTab(0)">用户</div>
    <div class="tab_div" style="right: 20px; left: auto;" onclick="changeTab(1)">客栈</div>

    <div id="user_div" style="margin-top: 70px;">
        <div class="btn" onclick="$('#creditApply').fadeIn();">申请会员卡</div>

        <div class="btn" onclick="showLogin('#userLogin')">会员登录</div>
    </div>

    <div id="hostel_div" style="margin-top: 70px; display: none;">
        <div class="btn" onclick="$('#hostelApply').fadeIn()">开店申请</div>

        <div class="btn" onclick="$('#hostelReg').fadeIn()">客栈注册</div>

        <div class="btn" onclick="showLogin('#hostelLogin')">客栈登录</div>
    </div>
</div>

<div id="userLogin" class="center_div" style="display: none;">
    <div class="back_div" onclick="backInitial('#userLogin')">
        <span class="fa fa-angle-double-left"></span>&nbsp;返回
    </div>

    <div class="textfield" style="margin-top: 40px;">
        <input class="input_text" type="text" placeholder="请输入会员卡号">
    </div>

    <div class="textfield">
        <input class="input_text" type="password" placeholder="请输入密码">
    </div>

    <div class="btn" onclick="login('#userLogin')">登录</div>
</div>

<div id="hostelLogin" class="center_div" style="display: none;">
    <div class="back_div" onclick="backInitial('#hostelLogin')">
        <span class="fa fa-angle-double-left"></span>&nbsp;返回
    </div>

    <div class="textfield" style="margin-top: 40px;">
        <input class="input_text" type="text" placeholder="请输入客栈编号">
    </div>

    <div class="textfield">
        <input class="input_text" type="password" placeholder="请输入密码">
    </div>

    <div class="btn" onclick="login('#hostelLogin')">登录</div>
</div>

<%-- 会员卡申请 --%>
<div id="creditApply" class="modal_bg" style="display: none;">
    <div class="modal">

        <div class="step_div">
            <img src="../image/step_1.png">
        </div>

        <%-- 填写信息 --%>
        <div id="step1" class="block_div">

            <div class="left_part" style="margin-right: 15px;">

                <div class="textfield">
                    <input class="input_text" type="text" placeholder="姓名">
                </div>

                <div class="textfield">
                    <input class="input_text" type="text" placeholder="手机号">
                </div>

                <div class="textfield" style="margin-bottom: 0;">
                    <input id="birth" class="input_text" type="text" placeholder="出生日期" readonly>
                </div>

            </div>

            <div class="left_part">

                <div class="sex">
                    <span>性别: </span>
                    <input id="man" type="radio" name="sex" value="男"><label for="man">男</label>
                    <input id="woman" type="radio" name="sex" value="女"><label for="woman">女</label>
                </div>

                <div class="banks_div">
                    <div id="bank_copy" class="each_bank" style="display: none;">
                        <input class="input_text bank_text" type="text" placeholder="请输入银行卡号">
                        <i class="fa fa-check" style="right: -40px;" onclick="confirmBank()"></i>
                        <i class="fa fa-times" onclick="cancelBank()"></i>
                        <i class="fa fa-pencil" style="display: none;" onclick="modifyBank()"></i>
                    </div>

                    <div class="add_bank" onclick="$('#bank_copy').slideDown();">
                        <i class="fa fa-plus"></i>&nbsp;添加银行卡
                    </div>
                </div>
            </div>

            <div style="width: 230px; margin: 30px auto;">
                <div class="step_btn" style="margin-right: 20px;" onclick="$('#creditApply').hide()">退出</div>
                <div class="step_btn" style="background-color: #32af32" onclick="nextStep(1)">下一步</div>
            </div>
        </div>

        <%-- 获取会员卡号 --%>
        <div id="step2" style="display: none;">
            <div class="id_div" style="margin-top: 0;">
                恭喜您申请成功!<br>
                你的会员卡号为:&nbsp;<span></span>(登录编号)
            </div>

            <div class="textfield">
                <input class="input_text" type="password" placeholder="请设置密码">
            </div>

            <div class="textfield">
                <input class="input_text" type="password" placeholder="请确认密码">
            </div>

            <div class="note_div">注意:会员卡在充值至少1000元后才能激活使用，您可以选择立即充值，也可以在一年内激活</div>

            <div style="width: 230px; margin: 30px auto;">
                <div class="step_btn" style="margin-right: 20px;" onclick="setUserPwd(1)">
                    完成
                </div>
                <div class="step_btn" style="background-color: #32af32" onclick="nextStep(2)">下一步</div>
            </div>

            <div class="remind_div">请您记住会员卡号和密码，<span>3</span>秒后跳转到登录界面</div>
        </div>

        <%-- 激活 --%>
        <div id="step3" style="display: none;">

            <div class="id_div" style="margin-top: 10px;">
                你的会员卡号:&nbsp;<span></span>
            </div>

            <div class="tip_div">请输入充值金额</div>
            <div class="textfield" style="margin-top: -5px;">
                <input class="input_text" type="text" placeholder="大于等于1000">
            </div>

            <div class="tip_div">充值银行卡</div>
            <div class="banks_div" style="margin-top: -5px; border-bottom: none;">
                <div class="each_bank" style="padding-left: 5px; box-sizing: border-box;"></div>
            </div>

            <div style="width: 230px; margin: 30px auto;">
                <div class="step_btn" style="margin-right: 20px;" onclick="toLogin('#step3')">退出</div>
                <div class="step_btn" style="background-color: #32af32" onclick="nextStep(3)">充值</div>
            </div>

            <div class="remind_div">充值成功！<span>3</span>秒后跳转到登录界面</div>
        </div>

    </div>
</div>

<%-- 开店申请 --%>
<div id="hostelApply" class="modal_bg" style="display: none;">
    <div class="modal">

        <div class="block_div">
            <div class="left_part">
                <div class="textfield">
                    <input class="input_text" type="text" placeholder="申请人姓名">
                </div>

                <div class="textfield" style="margin-bottom: 0;">
                    <input class="input_text" type="text" placeholder="申请人邮箱">
                </div>

            </div>

            <div class="left_part">
                <div class="textfield">
                    <input class="input_text" type="text" placeholder="申请人手机号">
                </div>

                <div class="textfield" style="margin-bottom: 0;">
                    <input class="input_text" type="text" placeholder="申请人身份证号">
                </div>

            </div>
        </div>

        <div class="block_div">
            <div class="left_part">
                <div class="textfield" style="margin-bottom: 0;">
                    <input class="input_text" type="text" placeholder="店名">
                </div>
            </div>

            <div class="left_part">
                <div class="textfield" style="margin-bottom: 0;">
                    <textarea class="text_area" placeholder="店址(精确)"></textarea>
                </div>
            </div>
        </div>

        <div class="block_div" style="margin-top: 20px;">

            <div class="describe_div">
                <textarea class="text_area" style="width: 370px;height: 100px;"
                          placeholder="旅社描述（面积、房间数、环境等）"></textarea>
            </div>

            <div class="upload_div">
                <hr class="hr_h">
                <hr class="hr_v">
                <div>上传一张旅店照片</div>
            </div>
        </div>

        <div class="much_text">
            <span style="color: #484948; font-weight: 500;">请确保以上信息填写的真实性，</span>
            审核过程中如果有问题我们将会与您电话联系，最终如果审核通过我们将会向您预留的邮箱发送您的注册码。
            使用该注册码完成旅社的注册后，即加盟成功。
        </div>

        <div style="width: 230px; margin: 30px auto;">
            <div class="step_btn" style="margin-right: 20px;" onclick="$('#hostelApply').hide()">退出</div>
            <div class="step_btn" style="background-color: #32af32" onclick="hostelApply()">申请</div>
        </div>

        <div class="remind_div">已成功发送申请，请静候佳音，<span>3</span>秒后回到首页</div>
    </div>
</div>

<%-- 客栈注册 --%>
<div id="hostelReg" class="modal_bg" style="display: none;">
    <div class="modal" style="width: 430px; min-height: 0; margin-top: 120px;">
        <div id="regStep1">
            <div class="tip_div" style="margin-top: 30px;">请输入您的注册码</div>
            <div class="textfield" style="margin-top: 0;">
                <input class="input_text" type="text" placeholder="6位注册码">
            </div>

            <div style="width: 230px; margin: 30px auto;">
                <div class="step_btn" style="margin-right: 20px;" onclick="$('#hostelReg').hide()">退出</div>
                <div class="step_btn" style="background-color: #32af32" onclick="checkCode(1)">验证</div>
            </div>
        </div>

        <div id="regStep2" style="display: none;">
            <div class="id_div" style="margin-top: 0;">
                验证成功!<br>
                你的客栈编号为:&nbsp;<span></span>(登录编号)
            </div>

            <div class="tip_div" style="margin-top: 30px;">请设置登录密码</div>
            <div class="textfield">
                <input class="input_text" type="password" placeholder="请设置密码">
            </div>

            <div class="textfield">
                <input class="input_text" type="password" placeholder="请确认密码">
            </div>

            <div style="width: 110px; margin: 30px auto;">
                <div class="step_btn" style="background-color: #32af32" onclick="checkCode(2)">完成</div>
            </div>

            <div class="remind_div">设置成功！<span>3</span>秒后跳转到登录界面</div>
        </div>
    </div>
</div>

<script src="../js/jquery.js"></script>
<script src="../js/homepage.js"></script>
<script src="../js/datetimepicker.js"></script>
<script>
    $("#birth").datetimepicker({
        lang: 'ch',
        timepicker: false,
        format: 'Y-m-d'
    });
</script>
</body>
</html>
