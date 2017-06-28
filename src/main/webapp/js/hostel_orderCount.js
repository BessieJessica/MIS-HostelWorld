/**
 * Created by lois on 2017/6/28.
 */

function getOrderCount(id){


    var chart=echarts.init(document.getElementById(id));

    var option = {
        title:{
            text:'历史订单数分析图'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross',
                crossStyle: {
                    color: '#999'
                }
            }
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                magicType: {show: true, type: ['line', 'bar']},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:['订单数（千单）','平均房价（元）','会员数量（人）']
        },
        xAxis: [
            {
                type: 'category',
                data: ['2010','2011','2012','2013','2014','2015','2016'],
                axisPointer: {
                    type: 'shadow'
                }
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '会员数量(人)',
                min: 0,
                max: 10,
                interval: 1,
                axisLabel: {
                    formatter: '{value}000'
                }
            },
            {
                type: 'value',
                name: '平均房价',
                min: 0,
                max: 10,
                interval: 1,
                axisLabel: {
                    formatter: '{value}00元'
                }
            }
        ],
        series: [
            {
                name:'会员数量（人）',
                type:'bar',
                data:[0.8, 2, 3.4, 5.1, 7.9, 9.1, 9.8]
            },
            {
                name:'平均房价（元）',
                type:'bar',
                data:[2.7, 2.9, 3, 2.8, 3.1, 3.4, 2.95]
            },
            {
                name:'订单数（千单）',
                type:'line',
                yAxisIndex: 1,
                data:[1, 1.8, 3, 4.7, 7.8, 9.2, 10]
            }
        ]
    };

    chart.setOption(option);

}