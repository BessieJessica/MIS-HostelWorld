/**
 * Created by L.H.S on 2017/3/7.
 */

window.onload = function () {
    getFinance();
    getRecords();
};

function getFinance() {

    $.ajax({
        type: "POST",
        url: "/hostel/getJoinDays",
        success: function (resp) {
            console.log(resp);
            var spans = $(".total_div").find("span");
            spans[0].innerHTML = resp;
        },
        error: function () {
            alert("获取信息失败");
        }
    });

    $.ajax({
        type: "POST",
        url: "/hostel/getHostelFinance",
        success: function (resp) {
            var datas = resp.data;
            datas[3] = 53193;
            getHostelBar("chartDiv", datas);
            getHostelBenefit("benefitChart");
            getOccupy("occupyChart");
            getOrderCount("orderChart");
            getMemberChart("memberChart");

            var spans = $(".total_div").find("span");
            spans[1].innerHTML = ((datas[0] * 0.8 + datas[1]) * 100).toFixed(2);
            spans[2].innerHTML = datas[3];

        },
        error: function () {
            alert("获取统计信息失败");
        }
    });
}

function getRecords() {

    $.ajax({
        type: "POST",
        url: "/hostel/getHostelRecords",
        success: function (resp) {
            var checkIns = resp[0];
            var checkouts = resp[1];
            var orders = resp[2];

            setCheckIn(checkIns);
            setCheckOut(checkouts);
            setOrders(orders);
        },
        error: function () {
            alert("获取记录失败");
        }
    })
}

function setCheckIn(checkIns) {

    var checkCopy = $("#item_copy").html();

    for (var i = 0; i < checkIns.length; i++) {
        var inDiv = $("<div class='each_item'></div>");
        inDiv.html(checkCopy);
        var spans = $(inDiv).find("div");
        spans[0].innerHTML = checkIns[i].checktime;

        var staff = checkIns[i].checkinstaff.split(";");
        spans[1].innerHTML = staff[0];
        spans[2].innerHTML = "房间号:" + checkIns[i].roomId;
        if (checkIns[i].ismember) {
            spans[3].innerHTML = "会员";
        }
        if (checkIns[i].orderId != -1) {
            spans[5].innerHTML = "订单号:" + checkIns[i].orderId;
        }
        if (checkIns[i].state == "valid") {
            spans[4].innerHTML = "尚未离店";
        }
        spans[6].innerHTML = "已支付:" + checkIns[i].pay;
        $("#checkInPart").append(inDiv);
    }
}

function setCheckOut(checkOuts) {
    var checkCopy = $("#item_copy").html();

    for (var i = 0; i < checkOuts.length; i++) {
        var outDiv = $("<div class='each_item'></div>");
        outDiv.html(checkCopy);
        var spans = $(outDiv).find("div");

        spans[0].innerHTML = checkOuts[i].checkouttime;
        if (checkOuts[i].checkinstaff != "") {
            var staff = checkOuts[i].checkinstaff.split(";");
            spans[1].innerHTML = staff[0];
        }

        spans[2].innerHTML = "房间号:" + checkOuts[i].roomId;
        if (checkOuts[i].userId != -1) {
            spans[3].innerHTML = "会员";
        }
        spans[4].innerHTML = "已离店";
        spans[6].innerHTML = "总支付:" + checkOuts[i].totalpay;
        $("#checkOutPart").append(outDiv);
    }
}

function setOrders(orders) {

    var orderCopy = $("#order_copy").html();

    for (var i = 0; i < orders.length; i++) {
        var orderDiv = $("<div class='each_item'></div>");
        orderDiv.html(orderCopy);
        var spans = $(orderDiv).find("div");

        spans[0].innerHTML = orders[i].ordertime;
        spans[1].innerHTML = orders[i].id;
        spans[2].innerHTML = orders[i].userId;
        if (orders[i].checkstate == "checked") {
            spans[3].innerHTML = "已入住";
        } else if (orders[i].checkstate == "notchecked") {
            spans[3].innerHTML = "尚未入住";
        } else {
            spans[3].innerHTML = "已取消";
        }
        spans[4].innerHTML = orders[i].checkintime;
        spans[5].innerHTML = orders[i].pay;

        $("#orderPart").append(orderDiv);
    }
}

function toggleSlide(id) {
    $(id).slideToggle();
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