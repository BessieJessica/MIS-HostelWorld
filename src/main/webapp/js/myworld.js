/**
 * Created by L.H.S on 2017/2/2.
 */

var INIT = [1, 0, 0, 0];  // 用来判断是否第一次加载数据
var USER_INFO;  // 用来存储user对象
var USER_ID;

window.onload = function () {
    getUserInfo();
    initMyOrder();
};

// 子导航
var LAST_TAB = 0;
function changeTab(index) {

    if (index == LAST_TAB) {
        return;
    }

    var tabId = ["#myOrder", "#checkIn", "#bill", "#myAccount"];

    $(".inner_div")[LAST_TAB].className = "inner_div";
    $(".inner_div")[index].className = "inner_div sub_native";
    $(tabId[LAST_TAB]).hide();
    $(tabId[index]).slideDown(300);

    if (INIT[index] == 0) {
        if (index == 1) {
            initCheckIn();
        } else if (index == 2) {
            initBill();
        } else {
            initAccount();
        }
    }

    INIT[index] = 1;
    LAST_TAB = index;
}

// userInfo
function getUserInfo() {

    $.ajax({
        type: "POST",
        url: "/user/getUserInfo",
        success: function (resp) {
            if (resp.status == true) {
                USER_INFO = resp.object;
                USER_ID = resp.info;

                var spans = $(".info_div").find("span");
                $(spans[0]).html(USER_INFO.username);
                spans[1].innerHTML = USER_INFO.sex + "，" + USER_INFO.birth + "，" + USER_INFO.level;

                var levels = ["大众会员", "黄金会员", "铂金会员", "钻石会员", "黑卡贵宾"];
                var pos = levels.indexOf(USER_INFO.level) + 1;
                var imgsrc = "../../image/mem_lv_" + pos + ".png";
                $(".lvl_img_div").find("img").attr("src", imgsrc);
            }
        },
        error: function () {
            alert("获取用户信息失败")
        }
    });

}

// 我的预定
function initMyOrder() {

    $.ajax({
        type: "POST",
        url: "/order/getFutureOrder",
        success: function (resp) {
            setOrder(resp, $("#futureDiv"));
        },
        error: function () {
            alert("获取预定信息失败")
        }
    });

    $.ajax({
        type: "POST",
        url: "/order/getHistoryOrder",
        success: function (resp) {
            setOrder(resp, $("#historyDiv"));
            $("#historyDiv").hide();
        },
        error: function () {
            alert("获取历史预定信息失败")
        }
    });
}

function setOrder(data, parent) {

    var copy = $("#record_copy");

    for (var i = 0; i < data.length; i++) {
        var div = $("<div class='each_record'></div>");
        div.html(copy.html());

        var spans = div.find("span");
        spans[0].innerHTML = data[i].id;
        spans[1].innerHTML = data[i].hostelname;
        spans[2].innerHTML = data[i].location;
        spans[3].innerHTML = data[i].ordertime;
        spans[4].innerHTML = data[i].checkintime;
        spans[5].innerHTML = data[i].leavetime;
        spans[6].innerHTML = data[i].room;
        spans[7].innerHTML = data[i].pay;
        spans[8].innerHTML = data[i].phone;
        spans[9].innerHTML = data[i].username;

        if (parent.attr("id") == "historyDiv") {
            div.find(".record_btn").hide();
        } else {

            var cancelBtn = div.find(".record_btn")[0];
            $(cancelBtn).click(function () {
                cancelOrder(this.parentNode);
            });
        }

        parent.append(div);
    }
}

function cancelOrder(node) {

    var orderId = $(node).find("span")[0].innerHTML;

    $.ajax({
        type: "POST",
        url: "/order/cancelOrder",
        data: {
            orderId: orderId
        },
        success: function () {
            location.reload();
        },
        error: function () {
            alert("退订失败");
        }
    });
}

function changeMyOrder(index) {

    var tab = $("#myOrder").find(".sub_btn");
    tab[index].className = "sub_btn sub_btn_native";
    tab[(index + 1) % 2].className = "sub_btn";

    var parents = ["#futureDiv", "#historyDiv"];
    $(parents[(index + 1) % 2]).hide();
    $(parents[index]).slideDown(300);
}

function initCheckIn() {

    var parent = $("#checkIn");
    var copy = $("#checkIn_copy");

    $.ajax({
        type: "POST",
        url: "/order/getCheckIn",
        success: function (data) {

            for (var i = 0; i < data.length; i++) {
                var div = $("<div class='each_record'></div>");
                div.html(copy.html());

                var spans = div.find("span");
                spans[0].innerHTML = data[i].hostelname;
                spans[1].innerHTML = data[i].location;
                spans[2].innerHTML = data[i].checkintime;
                spans[3].innerHTML = data[i].leavetime;
                spans[4].innerHTML = data[i].room;
                spans[5].innerHTML = data[i].pay;
                spans[6].innerHTML = data[i].phone;
                spans[7].innerHTML = data[i].username;

                parent.append(div);
            }

        },
        error: function () {
            alert("获取入住信息失败");
        }
    });
}

function initBill() {
    var parent = $("#bill");
    var billCopy = $("#bill_copy");
    var itemCopy = $("#item_copy");

    $.ajax({
        type: "POST",
        url: "/bill/getBills",
        success: function (result) {

            var resp = result.object;
            for (var i = 0; i < resp.length; i++) {
                var billDiv = $("<div class='bill_div'></div>");
                billDiv.html(billCopy.html());

                var timeSpans = billDiv.find("span");
                timeSpans[0].innerHTML = resp[i][0].year;
                timeSpans[1].innerHTML = resp[i][0].month;

                var itemParent = billDiv.find(".items_div");
                for (var j = 0; j < resp[i].length; j++) {

                    var itemDiv = $("<div class='each_item'></div>");
                    itemDiv.html(itemCopy.html());

                    var itemSpans = itemDiv.find("div");
                    itemSpans[0].innerHTML = resp[i][j].time.substr(5);
                    itemSpans[1].innerHTML = 0 - resp[i][j].pay;
                    itemSpans[2].innerHTML = resp[i][j].hostel;

                    itemParent.append(itemDiv);
                }

                parent.append(billDiv);
            }

        },
        error: function () {
            alert("获取账单失败");
        }
    });
}

function initAccount() {

    var username = $("#myAccount").find("input")[0];
    username.value = USER_INFO.username;

    var sex = USER_INFO.sex;  ////
    var sexlbl = $("#myAccount").find("label");
    if (sex == "男") {
        $(sexlbl[0]).show();
    } else {
        $(sexlbl[1]).show();
    }

    var userId = $("#myAccount").find(".name_div").find("span")[0];
    userId.innerHTML = USER_ID;

    var info = $("#myAccount").find(".each_info");
    var birth = $(info[0]).find("input")[0];
    birth.value = USER_INFO.birth;
    var phone = $(info[0]).find("input")[1];
    phone.value = USER_INFO.phone;
    var mail = $(info[0]).find("input")[2];
    mail.value = USER_INFO.email;
    var credit = $(info[1]).find("input")[0];
    credit.value = USER_INFO.bankcard;

    var spans = $(info[2]).find("span");
    spans[0].innerHTML = USER_INFO.balance;
    spans[1].innerHTML = USER_INFO.point;
    spans[2].innerHTML = Math.floor(USER_INFO.point / 100);
    spans[3].innerHTML = USER_INFO.level;

    var levels = ["大众会员", "黄金会员", "铂金会员", "钻石会员", "黑卡贵宾"];
    var limits = [1001, 5000, 20000, 100000, 100000000];
    var pos = levels.indexOf(USER_INFO.level);

    spans[4].innerHTML = limits[pos] - USER_INFO.point;
    spans[5].innerHTML = levels[pos + 1];
}

var infoOld = ["", "", "", "", ""]; //用户名, 性别, 生日, 手机号, 邮箱

function modAccount() {
    $($(".mod_btn")[0]).hide();
    $(".mod_con").show();

    var inputs = $("#myAccount").find("input");
    var sexlbl = $("#myAccount").find("label");
    sexlbl.show();
    if ($(sexlbl[0]).css("display") != "none") {
        infoOld[1] = "男";
    } else {
        infoOld[1] = "女";
    }

    for (var i = 0; i < inputs.length - 1; i++) {
        if ($(inputs[i]).attr("type") == "radio") {
            $(inputs[i]).show();
            if ($(inputs[i]).val() == infoOld[1]) {
                $(inputs[i]).attr("checked", "checked");
            }
        } else {
            $(inputs[i]).addClass("mod_state");
            if (i == 3) {
                $(inputs[3]).removeAttr("disabled");
            } else {
                $(inputs[i]).removeAttr("readonly");
            }
            if (i > 1) {
                infoOld[i - 1] = $(inputs[i]).val();
            } else {
                infoOld[i] = $(inputs[i]).val();
            }
        }
    }
}

function confirmAccount() {
    var infoNew = [];

    var inputs = $("#myAccount").find("input");
    var sexlbl = $("#myAccount").find("label");

    for (var i = 0; i < inputs.length - 1; i++) {
        if ($(inputs[i]).attr("type") == "radio") {
            if ($(inputs[i]).attr("checked") == "checked") {
                infoNew[1] = $(inputs[i]).val();
            }
            $(inputs[i]).hide();
        } else {
            if (i > 1) {
                infoNew[i - 1] = $(inputs[i]).val();
                if (i == 3) {
                    $(inputs[i]).attr("disabled", "disabled")
                }
            } else {
                infoNew[i] = $(inputs[i]).val();
            }
            $(inputs[i]).removeClass("mod_state");
            $(inputs[i]).attr("readonly", "readonly");
        }
    }
    if (infoNew[1] == "男") {
        $(sexlbl[1]).hide();
    } else {
        $(sexlbl[0]).hide();
    }

    // 更新数据库
    $.ajax({
        type: "POST",
        url: "/user/updateInfo",
        data: {
            username: infoNew[0],
            sex: infoNew[1],
            birth: infoNew[2],
            phone: infoNew[3],
            email: infoNew[4]
        },
        success: function () {
            $($(".mod_btn")[0]).show();
            $(".mod_con").hide();
        },
        error: function () {
            alert("更新失败");
        }
    });


}

function cancelAccount() {
    var inputs = $("#myAccount").find("input");
    var sexlbl = $("#myAccount").find("label");

    for (var i = 0; i < inputs.length - 1; i++) {
        if ($(inputs[i]).attr("type") == "radio") {
            $(inputs[i]).hide();
        } else {
            if (i == 3) {
                $(inputs[i]).attr("disabled", "disabled")
            }
            if (i > 1) {
                $(inputs[i]).val(infoOld[i - 1]);
            } else {
                $(inputs[i]).val(infoOld[i]);
            }
            $(inputs[i]).removeClass("mod_state");
            $(inputs[i]).attr("readonly", "readonly");
        }
    }
    if (infoOld[1] == "男") {
        $(sexlbl[1]).hide();
    } else {
        $(sexlbl[0]).hide();
    }
    $($(".mod_btn")[0]).show();
    $(".mod_con").hide();
}

var creditOld = "";
function changeCredit() {

    var input = $($("#myAccount").find(".each_info")[1]).find("input");
    input.addClass("mod_state");
    input.removeAttr("readonly");
    creditOld = input.val();
    $($(".exchange_card")[0]).hide();
    $(".credit_con").show();
}

function confirmCredit() {
    var input = $($("#myAccount").find(".each_info")[1]).find("input");
    var creditNew = input.val();
    if (creditNew == "") {
        return;
    }

    input.removeClass("mod_state");
    input.attr("readonly", "readonly");

    creditOld = "";

    // 存储
    $.ajax({
        type: "POST",
        url: "/user/updateBankcard",
        data: {
            bankcard: creditNew
        },
        success: function () {
            $(".credit_con").hide();
            $($(".exchange_card")[0]).show();
        },
        error: function () {
            alert("换卡失败");
        }
    });
}

function cancelCredit() {
    var input = $($("#myAccount").find(".each_info")[1]).find("input");
    input.removeClass("mod_state");
    input.attr("readonly", "readonly");
    input.val(creditOld);
    creditOld = "";

    $(".credit_con").hide();
    $($(".exchange_card")[0]).show();
}

function logout() {
    $.ajax({
        type: "POST",
        url: "/user/Logout",
        data: {
            attr: "userId"
        },
        success: function () {
            location.href = "../HomePage.jsp"
        },
        error: function () {
            alert("登出失败");
        }
    });
}

function reCharge() {

    var charge = $("#rechargeDiv").find("input").val();
    if (charge == "") {
        $("#rechargeDiv").find("input").focus();
        return;
    }

    $.ajax({
        type: "POST",
        url: "/user/Recharge",
        data: {
            charge: charge
        },
        success: function () {
            location.reload();
        },
        error: function () {
            alert("充值失败");
        }
    });
}