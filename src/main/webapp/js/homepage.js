/**
 * Created by L.H.S on 2017/1/18.
 */

function changeTab(index) {

    if (index == 0) {
        $("#hostel_div").hide();
        $("#user_div").slideDown();

        $(".tab_div")[1].className = "tab_div";
        $(".tab_div")[0].className = "tab_div tab_selected";
    } else {
        $("#user_div").hide();
        $("#hostel_div").slideDown();

        $(".tab_div")[0].className = "tab_div";
        $(".tab_div")[1].className = "tab_div tab_selected";
    }

}

function showLogin(id) {
    $("#initial").hide();
    $(id).fadeIn();
}

function backInitial(id) {
    $(id).hide();
    $("#initial").fadeIn();
}

var err_lbl = document.createElement("div");
err_lbl.className = "err_lbl";

function login(id) {

    var index = 0;
    if (id == "#hostelLogin") {
        index = 1;
    } else if (id == "#managerLogin") {
        index = 2;
    }

    var errTip = ["请输入会员卡号", "请输入客栈编号", "请输入工作编号"];

    var inputDiv = $(id).find("input");
    if (inputDiv[0].value == "") {
        err_lbl.innerHTML = errTip[index];
        inputDiv[0].parentNode.appendChild(err_lbl);
        $(inputDiv[0]).focus(function () {
            $(err_lbl).remove();
        });
        return;
    }

    if (inputDiv[1].value == "") {
        err_lbl.innerHTML = "请输入密码";
        inputDiv[1].parentNode.appendChild(err_lbl);
        $(inputDiv[1]).focus(function () {
            $(err_lbl).remove();
        });
        return;
    }

    var userId = inputDiv[0].value;
    var pwd = inputDiv[1].value;

    // var href = ["user/UserOrder.jsp", "hostel/HostelBusiness.jsp", "Approval.jsp"];
    if (index == 0) {
        $.ajax({
            type: "POST",
            url: "/user/Login",
            data: {
                userId: userId,
                pwd: pwd
            },
            success: function (resp) {
                if (resp.status == true) {
                    location.href = "user/UserOrder.jsp";
                } else {
                    err_lbl.innerHTML = resp.info;
                    inputDiv[0].parentNode.appendChild(err_lbl);
                    $(inputDiv[0]).focus(function () {
                        $(err_lbl).remove();
                    });
                }
            },
            error: function () {
                alert("登录失败");
            }
        });
    } else if (index == 1) {
        // 客栈登录

        $.ajax({
            type: "POST",
            url: "/hostel/Login",
            data: {
                hosId: userId,
                pwd: pwd
            },
            success: function (resp) {
                if (resp.status == true) {
                    location.href = "hostel/HostelBusiness.jsp";
                } else {
                    err_lbl.innerHTML = resp.info;
                    inputDiv[0].parentNode.appendChild(err_lbl);
                    $(inputDiv[0]).focus(function () {
                        $(err_lbl).remove();
                    });
                }
            },
            error: function () {
                alert("登录失败");
            }
        });

    } else {

        $.ajax({
            type: "POST",
            url: "/myManager/Login",
            data: {
                managerId: userId,
                pwd: pwd
            },
            success: function (resp) {
                if (resp.status == true) {
                    location.href = "Approval.jsp"
                } else {
                    err_lbl.innerHTML = resp.info;
                    inputDiv[0].parentNode.appendChild(err_lbl);
                    $(inputDiv[0]).focus(function () {
                        $(err_lbl).remove();
                    });
                }
            }
        });
    }

}

function confirmBank() {

    var input = $("#bank_copy").find("input");

    if ($(input).val() == "") {
        $("#bank_copy").slideUp();
        $(".add_bank").show();
    } else {
        $(input).css("background-color", "transparent");
        $(input).attr("disabled", "disabled");
        var iTag = $("#bank_copy").find("i");
        $(iTag[0]).css("display", "none");
        $(iTag[1]).css("display", "none");
        $(iTag[2]).css("display", "");
        $(input)[0].className = "input_hover_no";

        $(".add_bank").hide();
    }

}

var oldVal = "";

function cancelBank() {

    var input = $("#bank_copy").find("input");

    if (oldVal == "") {
        $("#bank_copy").slideUp();
    } else {
        $(input).val(oldVal);
        $(input).css("background-color", "transparent");
        $(input).attr("disabled", "disabled");
        var iTag = $("#bank_copy").find("i");
        $(iTag[0]).css("display", "none");
        $(iTag[1]).css("display", "none");
        $(iTag[2]).css("display", "");
        $(input)[0].className = "input_hover_no";
        oldVal = "";
    }
}

function modifyBank() {

    var input = $("#bank_copy").find("input");

    $(input).css("background-color", "#fff");
    $(input).removeAttr("disabled");
    var iTag = $("#bank_copy").find("i");
    $(iTag[0]).css("display", "");
    $(iTag[1]).css("display", "");
    $(iTag[2]).css("display", "none");
    $(input)[0].className = "input_text bank_text";

    oldVal = $(input).val();
}

var creditId;
var BankCard;

// 注册会员卡——下一步
function nextStep(index) {

    if (index == 1) {
        // 填写信息后
        userRegister();

    } else if (index == 2) {
        // 注册会员卡后
        setUserPwd(0);

    } else if (index == 3) {
        // 充值后

        var balInput = $("#step3").find("input")[0];
        var balance = balInput.value;
        if (balance > 8000) {
            err_lbl.innerHTML = "余额不足";
            balInput.parentNode.appendChild(err_lbl);
            $(balInput).focus(function () {
                $(err_lbl).remove();
            });
            return;
        }
        $.ajax({
            type: "POST",
            url: "/user/setBalance",
            data: {
                balance: balance
            },
            success: function () {
                $($("#creditApply").find("img"))[0].src = "../image/step_4.png";
                toLogin("#step3");
            },
            error: function () {
                alert("充值失败");
            }
        });
    }
}

function userRegister() {

    var inputs = $("#step1").find("input");
    var username = $(inputs[0]).val();
    var phone = $(inputs[1]).val();
    var birth = $(inputs[2]).val();
    var sex = "女";
    if (inputs[3].checked == true) {
        sex = "男";
    }
    BankCard = $(inputs[5]).val();

    $.ajax({
        type: "POST",
        url: "/user/register",
        data: {
            password: "",
            username: username,
            phone: phone,
            birth: birth,
            sex: sex,
            bankcard: BankCard
        },
        success: function (result) {
            creditId = result;
            $($("#creditApply").find("img"))[0].src = "../image/step_2.png";
            $("#step1").hide();
            $("#step2").show();
            $("#step2")[0].getElementsByTagName("span")[0].innerHTML = creditId;
        },
        error: function () {
            alert("注册失败");
        }
    });
}

// 设置会员卡密码
// syb=0：下一步；syb=1：不激活
function setUserPwd(syb) {

    var pwd = $("#step2").find("input")[0];
    if (pwd.value == "") {
        err_lbl.innerHTML = "请设置密码";
        pwd.parentNode.appendChild(err_lbl);
        $(pwd).focus(function () {
            $(err_lbl).remove();
        });
        return;
    }

    var pwd2 = $("#step2").find("input")[1];
    if (pwd2.value !== pwd.value) {
        err_lbl.innerHTML = "两次密码不一致";
        pwd2.parentNode.appendChild(err_lbl);
        $(pwd2).focus(function () {
            $(err_lbl).remove();
        });
        return;
    }

    var password = pwd.value;
    $.ajax({
        type: "POST",
        url: "/user/setPwd",
        data: {
            pwd: password
        },
        success: function () {

            if (syb == 0) {
                $($("#creditApply").find("img"))[0].src = "../image/step_3.png";
                $("#step2").hide();
                $("#step3").show();
                $("#step3").find(".id_div").find("span")[0].innerHTML = creditId;
                $("#step3").find(".each_bank").html(BankCard)
            } else {
                toLogin("#step2");
            }

        },
        error: function () {
            alert("设置密码失败")
        }
    });
}

function toLogin(id) {
    $(id).find(".remind_div").show();
    var span = $(id).find(".remind_div").find("span")[0];
    self.setInterval(function () {
        var current = parseInt(span.innerHTML);
        span.innerHTML = current - 1;
    }, 1000);

    setTimeout("window.location.reload();", 3000);
}

// 申请开店
function hostelApply() {

    var inputs = $("#hostelApply").find("input");
    var areas = $("#hostelApply").find("textarea");

    var location = $(areas[0]).val();
    var description = $(areas[1]).val();

    $.ajax({
        type: "POST",
        url: "/hostel/apply",
        data: {
            applyer: inputs[0].value,
            email: inputs[1].value,
            phone: inputs[2].value,
            identity: inputs[3].value,
            hostelname: inputs[4].value,
            location: location,
            description: description,
            imgurl: ""
        },
        success: function () {
            toLogin("#hostelApply");
        },
        error: function () {
            alert("申请失败");
        }
    });
}

var hostelId;    // applyId
var regId;
// 客栈注册
function checkCode(index) {

    if (index == 1) {
        // 验证注册码
        var codeInput = $("#regStep1").find("input");

        $.ajax({
            type: "POST",
            url: "/hostel/checkCode",
            data: {
                checkcode: codeInput.val()
            },
            success: function (resp) {
                if (resp.status == true) {

                    hostelId = resp.object.applyId;
                    regId = resp.object.id;

                    $("#regStep1").hide();
                    $("#regStep2").show();
                    $("#regStep2").find(".id_div").find("span")[0].innerHTML = hostelId;
                } else {
                    err_lbl.innerHTML = resp.info;
                    codeInput[0].parentNode.appendChild(err_lbl);
                    codeInput.focus(function () {
                        $(err_lbl).remove();
                    });
                }
            },
            error: function () {
                alert("验证失败");
            }
        });

    } else {
        // 设置密码

        var pwd = $("#regStep2").find("input")[0];
        if (pwd.value == "") {
            err_lbl.innerHTML = "请设置密码";
            pwd.parentNode.appendChild(err_lbl);
            $(pwd).focus(function () {
                $(err_lbl).remove();
            });
            return;
        }

        var pwd2 = $("#regStep2").find("input")[1];
        if (pwd2.value !== pwd.value) {
            err_lbl.innerHTML = "两次密码不一致";
            pwd2.parentNode.appendChild(err_lbl);
            $(pwd2).focus(function () {
                $(err_lbl).remove();
            });
            return;
        }

        $.ajax({
            type: "POST",
            url: "/hostel/setPwd",
            data: {
                regId: regId,
                applyId: hostelId,
                pwd: pwd.value
            },
            success: function () {
                toLogin("#regStep2");
            },
            error: function () {
                alert("设置密码失败");
            }
        });
    }
}