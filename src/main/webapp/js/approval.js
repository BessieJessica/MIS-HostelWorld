/**
 * Created by L.H.S on 2017/3/7.
 */

var TO_APPROVE;  // 存储待审批
var HAS_APPROVE;  // 存储已审批

window.onload = function () {
    getApprove();
};

function changeTab(index) {

    if (index == 0) {
        $("#toApprove").show();
        $("#hasApprove").hide();
    } else {
        $("#toApprove").hide();
        $("#hasApprove").show();
    }

    $($(".inner_div")[index]).addClass("sub_native");
    $($(".inner_div")[(index + 1) % 2]).removeClass("sub_native");
}

function getApprove() {

    var approve_copy = $("#approve_copy");
    // 待审批
    $.ajax({
        type: "POST",
        url: "/myManager/getToApprove",
        success: function (resp) {
            TO_APPROVE = resp;

            for (var i = 0; i < resp.length; i++) {
                var div = $("<div class='approve_div'></div>");
                div.html(approve_copy.html());

                if (resp[i].applytype == "modify") {
                    $(div.find("span")[0]).html("修改信息申请");
                }

                $(div.find("span")[1]).html(resp[i].applytime);
                $(div.find("a")[0]).html(i);

                div.click(function () {
                    showDetail(this, 0);
                });

                $("#toApprove").append(div);
            }
        },
        error: function () {
            alert("获取待审批失败");
        }
    });

    // 已审批
    $.ajax({
        type: "POST",
        url: "/myManager/getHistoryApprove",
        success: function (resp) {
            HAS_APPROVE = resp;

            for (var i = 0; i < resp.length; i++) {
                var div = $("<div class='approve_div' style='width: 150px;'></div>");
                div.html(approve_copy.html());
                $(div.find("span")[1]).html(resp[i].applytime);
                $(div.find("a")[0]).html(i);

                if (resp[i].applytype == "modify") {
                    $(div.find("span")[0]).html("修改信息申请");
                }

                var img = $("<img class='syb_img' src='../../image/agree.png'>");
                if (resp[i].approvalstate == "inapprove") {
                    img.attr("src", "../../image/refuse.png");
                }
                div.append(img);

                div.click(function () {
                    showDetail(this, 1);
                });

                $("#hasApprove").append(div);
            }
            $("#hasApprove").hide();
        },
        error: function () {
            alert("获取已审批失败");
        }
    });
}

// 0，未审批；1，已审批
function showDetail(node, syb) {

    $("#hostelApply").fadeIn();
    $("#hostelApply").find(".step_btn").show();

    var index = $(node).find("a").html();
    var data = TO_APPROVE[index];
    if (syb == 1) {
        data = HAS_APPROVE[index];
        $("#hostelApply").find(".step_btn").hide();
    }

    var inputs = $("#hostelApply").find("input");
    var areas = $("#hostelApply").find("textarea");

    inputs[0].value = "申请人：" + data.applyer;
    inputs[1].value = "邮箱：" + data.email;
    inputs[2].value = "手机：" + data.phone;
    inputs[3].value = "身份证：" + data.identity;
    inputs[4].value = "店名：" + data.hostelname;

    areas[0].value = "店址：" + data.location;
    areas[1].value = "描述：" + data.description;

    var uploadDiv = $(".upload_div");
    var describeDiv = $(".describe_div");
    if (data.applytype == "modify") {
        $(describeDiv[0]).css("width", "255px");
        $(describeDiv[1]).show();
        areas[2].value = "公告：" + data.notice;
        uploadDiv.hide();
    } else {
        $(describeDiv[0]).css("width", "370px");
        $(describeDiv[1]).hide();
        uploadDiv.show();
    }

    $("#hostelApply").find("a").html(data.id);
}

function approve(state) {

    var applyId = $("#hostelApply").find("a").html();
    $("#hostelApply").hide()

    $.ajax({
        type: "POST",
        url: "/myManager/Approve",
        data: {
            applyId: applyId,
            state: state
        },
        success: function () {
            location.reload();
        },
        error: function () {
            alert("审批失败");
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
            location.href = "ManagerLogin.jsp"
        },
        error: function () {
            alert("登出失败");
        }
    });
}