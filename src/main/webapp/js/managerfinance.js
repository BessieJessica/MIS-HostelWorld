/**
 * Created by L.H.S on 2017/3/15.
 */

window.onload = function () {
    getBarChart();
};

function getBarChart() {

    $.ajax({
        type: "POST",
        url: "/myManager/getTotalFinance",
        success: function (resp) {
            var hostels = resp.hostelNames;
            var datas = resp.sales;

            var spans = $(".total_div").find("span");
            spans[0].innerHTML = hostels.length;

            spans[1].innerHTML = (getSum(datas[0]) + getSum(datas[1])) * 100;
            spans[2].innerHTML = getSum(datas[3]);

                getManagerBar("chartDiv", resp);
            getMap("countryMap");
            getlayeredCitySales("layeredCity");

        },
        error: function () {
            alert("获取财务统计失败");
        }
    });
}

function getSum(array) {

    var sum = 0;
    for (var i = 0; i < array.length; i++) {
        sum += parseInt(array[i]);
    }
    return sum;
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