/**
 * Created by L.H.S on 2017/2/1.
 */
var KEY = 0;

function changeKey(index) {
    KEY = index;
    var keyPlace = ["地区", "店名"];

    $(".key_btn")[index].className = "key_btn key_selected";
    $(".key_btn")[(index + 1) % 2].className = "key_btn";
    var placeholder = "按" + keyPlace[index] + "搜索hostel";
    $(".search_input").attr("placeholder", placeholder);
    $(".search_input").val("");
}

function keyDown(event) {
    if (event.keyCode == 13) {
        search()
    }
}

function search() {

    var keyword = $(".search_input").val().trim();
    if (keyword == "") return;

    var condition = ["location", "name"];
    $.ajax({
        type: "POST",
        url: "/user/Search",
        data: {
            key: keyword,
            condition: condition[KEY]
        },
        success: function (resp) {
            $("#searchResult").find(".each_search").remove();
            if (resp.length == 0) {
                $("#noSearch").show();
            } else {
                $("#noSearch").hide();
                var copy = $("#search_copy").html();
                for (var i = 0; i < resp.length; i++) {
                    var div = $("<div class='each_search'></div>");
                    div.html(copy);
                    var spans = div.find("span");
                    spans[0].innerHTML = resp[i].name;
                    spans[1].innerHTML = resp[i].location;
                    div.find("a")[0].innerHTML = resp[i].id;

                    div.click(function () {
                        showHostelDetail(this);
                    });

                    $("#searchResult").append(div);
                }
            }
            $("#searchResult").slideDown();
        },
        error: function () {
            alert("搜索失败");
        }
    });
}

function hideSearch(event) {
    var target = event.target;
    if (target.className && target.className == "btn search_btn") {
        return;
    }
    $("#searchResult").hide();
}

function showHostelDetail(node) {

    var hosId = $(node).find("a")[0].innerHTML;
    $.ajax({
        type: "POST",
        url: "/user/getHostelInfo",
        data: {
            hosId: hosId
        },
        success: function (resp) {

            $("#hostelDetail").hide()
            $("#hostelDetail").show();
            var modal = $("#hostelDetail").find(".modal");
            var w = modal.css("width");
            var h = modal.css("height");

            modal.css("width", "0");
            modal.css("min-height", "0");
            modal.css("height", "0");
            modal.css("top", "60%");
            modal.animate({
                width: w,
                height: h,
                top: 0
            }, 300);

            var spans = modal.find("span");
            spans[0].innerHTML = resp.name;
            spans[1].innerHTML = resp.location;

            $("#hostelDetail").find("a")[0].innerHTML = resp.id;
        },
        error: function () {
            alert("获取客栈信息失败");
        }
    });
}

function makeOrder() {

    var hosId = $("#hostelDetail").find("a")[0].innerHTML;
    var checktime =  $("#checktime").val();
    var leavetime = $("#leavetime").val();
    var pay = 100;

    $.ajax({
        type: "POST",
        url: "/user/makeOrder",
        data: {
            hosId: hosId,
            checktime: checktime,
            leavetime: leavetime,
            pay: pay
        },
        success: function () {
            reloadPage("#hostelDetail");
        },
        error: function () {
            alert("预定失败");
        }
    });
}

function reloadPage(id) {
    $(id).find(".remind_div").show();
    var span = $(id).find(".remind_div").find("span")[0];
    self.setInterval(function () {
        var current = parseInt(span.innerHTML);
        span.innerHTML = current - 1;
    }, 1000);

    setTimeout("window.location.reload();", 3000);
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