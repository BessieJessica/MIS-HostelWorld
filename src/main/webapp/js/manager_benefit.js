/**
 * Created by lois on 2017/6/28.
 */

function getHistoryMeanBenefit(id) {

    var chart=echarts.init(document.getElementById(id));

    var option = {
        title:{
            text:'会员发展分析图'
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
            data:['客栈数量','会员数量(万)','平均收益(百万)','平均房价(百元)']
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
                name: '客栈(家)/平均收益(百万)',
                min: 0,
                max: 50,
                interval: 5,
                axisLabel: {
                    formatter: '{value} 家'
                }
            },
            {
                type: 'value',
                name: '会员数量(万)',
                min: 0,
                max: 10,
                interval: 1,
                axisLabel: {
                    formatter: '{value} '
                }
            },

        ],
        series: [
            {
                name:'客栈数量',
                type:'bar',
                data:[5, 12, 19, 30, 36, 43, 49]
            },
            {
                name:'平均收益(百万)',
                type:'bar',
                data:[10, 18, 20, 27, 38, 48, 45]
            },
            {
                name:'会员数量(万)',
                type:'line',
                yAxisIndex: 1,
                data:[1, 1.8, 3, 4.7, 7.8, 9.2, 10]
            },
            {
                name:'平均房价(百元)',
                type:'bar',
                data:[2.7, 2.9, 3, 2.8, 3.1, 3.4, 2.95]
            },
        ]
    };

    chart.setOption(option);
}