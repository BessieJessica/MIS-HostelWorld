/**
 * Created by lois on 2017/6/26.
 */
function getMap(id) {
    var chart=echarts.init(document.getElementById(id));

    var data = [
        {name: '北京', value: 164},
        {name: '上海', value: 124},
        {name: '广州', value: 267},
        {name: '杭州', value: 74},
        {name: '南京', value: 164},
        {name: '厦门', value: 109},
        {name: '苏州', value: 20},
        {name: '武汉', value: 79},
        {name: '西安', value: 47},
        {name: '成都', value: 30},
    ];
    var geoCoordMap = {
        '北京':[116.46,39.92],
        '上海':[121.48,31.22],
        '广州':[113.23,23.16],
        '杭州':[120.19,30.26],
        '南京':[118.78,32.04],
        '厦门':[118.1,24.46],
        '苏州':[120.62,31.32],
        '武汉':[114.31,30.52],
        '西安':[108.95,34.27],
        '成都':[104.06,30.67],
    };

    var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
            var geoCoord = geoCoordMap[data[i].name];
            if (geoCoord) {
                res.push({
                    name: data[i].name,
                    value: geoCoord.concat(data[i].value)
                });
            }
        }
        return res;
    };


   var option = {
        backgroundColor: '#404a59',
        title: {
            text: '2016全国城市收益额 单位（十万元）',
            // subtext: 'data from PM25.in',
            // sublink: 'http://www.pm25.in',
            left: 'center',
            textStyle: {
                color: '#fff'
            }
        },
        tooltip : {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            y: 'bottom',
            x:'right',
            data:['销售额'],
            textStyle: {
                color: '#fff'
            }
        },
        geo: {
            map: 'china',
            label: {
                emphasis: {
                    show: false
                }
            },
            roam: true,
            itemStyle: {
                normal: {
                    areaColor: '#323c48',
                    borderColor: '#111'
                },
                emphasis: {
                    areaColor: '#2a333d'
                }
            }
        },
        series : [
            {
                name: '销售额',
                type: 'scatter',
                coordinateSystem: 'geo',
                data: convertData(data),
                symbolSize: function (val) {
                    return val[2] / 10;
                },
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: false
                    },
                    emphasis: {
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#ddb926'
                    }
                }
            },
            {
                name: 'Top 5',
                type: 'effectScatter',
                coordinateSystem: 'geo',
                data: convertData(data.sort(function (a, b) {
                    return b.value - a.value;
                }).slice(0, 6)),
                symbolSize: function (val) {
                    return val[2] / 10;
                },
                showEffectOn: 'render',
                rippleEffect: {
                    brushType: 'stroke'
                },
                hoverAnimation: true,
                label: {
                    normal: {
                        formatter: '{b}',
                        position: 'right',
                        show: true
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#f4e925',
                        shadowBlur: 10,
                        shadowColor: '#333'
                    }
                },
                zlevel: 1
            }
        ]
    };

   chart.setOption(option);

}