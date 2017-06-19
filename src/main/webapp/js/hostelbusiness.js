/**
 * Created by L.H.S on 2017/3/5.
 */

window.onload = function () {
    getHostelInfo();
    changeCheck(0);
};

var CHECKOUT_SYB = 0;  // 登出前是否已查询：0，未查询；1，已查询
var ORDER_SYB = 0;  // 入住登记是否通过订单：0，否；1，是

function getHostelInfo() {

    $.ajax({
        type: "POST",
        url: "/hostel/getInfo",
        success: function (resp) {
            var infoSpans = $(".info_right").find("span");
            var noticeSpan = $(".notice_div").find("span");

            infoSpans[0].innerHTML = resp.name;
            infoSpans[1].innerHTML = resp.location;
            infoSpans[2].innerHTML = resp.description;
            infoSpans[3].innerHTML = resp.ownername;
            infoSpans[4].innerHTML = resp.phone;
            infoSpans[5].innerHTML = resp.email;

            noticeSpan[0].innerHTML = resp.notice;
        },
        error: function () {
            alert("获取信息失败");
        }
    });
}

function addCheck() {

    var copy = $("#checks").find(".each_check")[0];
    var div = $("<div class='each_check'></div>");
    div.html($(copy).html());

    $("#checks").append(div);

    return div;
}

function checkMember(checkbox) {
    if (checkbox.checked) {
        $("#card").show();
        $($("#card").parent("div").find("label")[1]).show();
        $("#member_input").slideDown();
    } else {
        $("#cash").attr("checked", true);
        $("#card").hide();
        $($("#card").parent("div").find("label")[1]).hide();
        $("#member_input").hide();
    }
}

function changeCheck(index) {

    if (index == 0) {
        $("#checkIn").show();
        $("#checkOut").hide();
    } else {
        $("#checkOut").show();
        $("#checkIn").hide();
    }

    var date = new Date();
    var now = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes();

    $("#checkInTime").val(now);
    $("#checkOutTime_out").val(now);

    $($(".sub_btn")[(index + 1) % 2]).removeClass("sub_btn_native");
    $($(".sub_btn")[index]).addClass("sub_btn_native");
}

// 修改信息
function modInfo() {

    var infoSpans = $(".info_right").find("span");
    var noticeSpan = $(".notice_div").find("span");

    var modInputs = $("#infoMod").find("input");
    var modArea = $("#infoMod").find("textarea");

    modInputs[0].value = infoSpans[3].innerHTML;  // 店主姓名
    modInputs[1].value = infoSpans[5].innerHTML;  // 联系邮箱
    modInputs[2].value = infoSpans[4].innerHTML;  // 联系电话
    modInputs[3].value = infoSpans[0].innerHTML;  // 店名

    modArea[0].value = infoSpans[1].innerHTML;  // 地址
    modArea[1].value = infoSpans[2].innerHTML;  // 描述
    modArea[2].value = noticeSpan[0].innerHTML;  // 公告

    $("#infoMod").fadeIn();

}

// 申请修改
function modApply() {

    var modInputs = $("#infoMod").find("input");
    var modArea = $("#infoMod").find("textarea");

    $.ajax({
        type: "POST",
        url: "/hostel/modApply",
        data: {
            applyer: modInputs[0].value,
            email: modInputs[1].value,
            phone: modInputs[2].value,
            hostelname: modInputs[3].value,
            location: modArea[0].value,
            description: modArea[1].value,
            notice: modArea[2].value,
            imgurl: "",
        },
        success: function () {

            $("#infoMod").find(".remind_div").show();
            var span = $("#infoMod").find(".remind_div").find("span")[0];
            self.setInterval(function () {
                var current = parseInt(span.innerHTML);
                span.innerHTML = current - 1;
            }, 1000);

            setTimeout("window.location.reload();", 3000);

        },
        error: function () {
            alert("修改失败");
        }
    });
}

function checkIn() {

    var inputs = $("#checkIn").find("input");
    var orderId = inputs[0].value;
    if (ORDER_SYB == 0 || orderId == "") {
        orderId = -1;
    }
    var roomId = inputs[1].value;
    var checktime = inputs[2].value;
    var leavetime = inputs[3].value;

    var staffs = [];
    var staffInputs = $("#checks").find("input");
    for (var i = 0; i < staffInputs.length; i++) {
        var eachStaff = [];
        eachStaff.push(staffInputs[i].value);
        i++;
        eachStaff.push(staffInputs[i].value);

        staffs.push(eachStaff.join("-"));
    }
    var checkinstaff = staffs.join(";");

    var payInputs = $("#payPart").find("input");
    var pay = payInputs[0].value;
    var ismember = false;
    var memberId = -1;
    if (payInputs[1].checked) {
        ismember = true;
        memberId = payInputs[4].value;
    }
    var paytype = "cash";
    if (payInputs[3].checked) {
        paytype = "card";
    }

    $.ajax({
        type: "POST",
        url: "/hostel/CheckIn",
        data: {
            roomId: roomId,
            checktime: checktime,
            leavetime: leavetime,
            checkinstaff: checkinstaff,
            pay: pay,
            ismember: ismember,
            paytype: paytype,
            memberId: memberId,
            orderId: orderId
        },
        success: function (resp) {
            if (resp.status == true) {
                location.reload();
            } else {
                alert(resp.info);
            }
        },
        error: function () {
            alert("登记失败");
        }
    });
}

function getCheckIn() {

    var inputs = $("#checkOut").find("input");
    var spans = $("#checkOut").find("span");
    var paySpans = $("#payPart_out").find("span");

    var roomId = inputs[0].value;

    if (roomId == "") {
        $(inputs[0]).focus();
        return;
    }

    $.ajax({
        type: "POST",
        url: "/hostel/getCheckIn",
        data: {
            roomId: roomId
        },
        success: function (result) {
            if (result.status == true) {
                var resp = result.object;
                CHECKOUT_SYB = 1;

                spans[0].innerHTML = resp.checktime;
                var checkOnes = (resp.checkinstaff).split(";");
                $("#checkOnes").find("span").remove();
                for (var i = 0; i < checkOnes.length; i++) {
                    var eachOne = checkOnes[i].split("-");
                    var span1 = $("<span></span>");
                    span1.html(eachOne[0]);
                    $("#checkOnes").append(span1);
                    var span2 = $("<span style='margin-left: 10px;'></span>");
                    span2.html(eachOne[1] + "<br>");
                    $("#checkOnes").append(span2);
                }

                paySpans[0].innerHTML = resp.pay;
                var note = "非会员，";
                if (resp.ismember) {
                    note = "会员，";
                }
                if (resp.paytype == "cash") {
                    note += "现金支付";
                } else {
                    note += "会员卡支付";
                }
                paySpans[1].innerHTML = note;

            } else {
                CHECKOUT_SYB = 0;
                alert(result.info);
            }
        },
        error: function () {
            alert("查询失败");
        }
    });
}

function checkOut() {

    if (CHECKOUT_SYB == 0) {
        alert("请先查询房间号");
        return;
    }

    var inputs = $("#checkOut").find("input");
    var paySpans = $("#payPart_out").find("span");

    var totalpay = parseFloat(paySpans[0].innerHTML) + parseFloat($("#payPart_out").find("input").val());

    $.ajax({
        type: "POST",
        url: "/hostel/CheckOut",
        data: {
            roomId: inputs[0].value,
            checkouttime: $("#checkOutTime_out").val(),
            totalpay: totalpay,
            payinfo: paySpans[1].innerHTML,
        },
        success: function () {
            location.reload();
        },
        error: function () {
            alert("登记失败");
        }
    });
}

function getTheOrder() {

    var orderId = $("#checkIn").find("input")[0].value;
    $.ajax({
        type: "POST",
        url: "/hostel/getTheOrder",
        data: {
            orderId: orderId
        },
        success: function (resp) {
            if (resp.status == true) {
                ORDER_SYB = 1;
                var orderInfo = resp.object;

                $("#checkOutTime").val(orderInfo.leavetime);

                var checkbox = $("#isMember");
                checkbox.attr("checked", true);
                checkbox.trigger("change");

                $("#payPart").find("input")[0].value = orderInfo.pay;
                $("#member_input").find("input")[0].value = orderInfo.userId;

            } else {
                ORDER_SYB = 0;
                alert(resp.info);
            }
        },
        error: function () {
            alert("获取订单失败");
        }
    });
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