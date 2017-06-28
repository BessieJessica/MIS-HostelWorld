/**
 * Created by lois on 2017/6/27.
 */

function getHostelBenefit(id){

    var chart=echarts.init(document.getElementById(id));

    var option = {
        title: {
            top: '50px',
            text: '历史总收益分析图',
            subtext:'单位: 十万元',
            left: 'center',
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            x: 'center',
            data:['线上销售额(季度)','线下销售额(季度)','标准间','大床房','单人间','线上销售额','线下销售额',
                '会员','非会员']
        },
        radar: [
            {
                indicator: [
                    {text: '第一季度', max: 10},
                    {text: '第二季度', max: 10},
                    {text: '第三季度', max: 10},
                    {text: '第四季度', max: 10}
                ],
                center: ['25%','40%'],
                radius: 100
            },
            {
                indicator: [
                    {text: '第一季度', max: 100},
                    {text: '第二季度', max: 100},
                    {text: '第三季度', max: 100},
                    {text: '第四季度', max: 100},
                ],
                radius: 80,
                center: ['50%','60%'],
            },
            {
                indicator: (function (){
                    var res = [];
                    for (var i = 1; i <= 12; i++) {
                        res.push({text:i+'月',max:100});
                    }
                    return res;
                })(),
                center: ['75%','40%'],
                radius: 80
            }
        ],
        series: [
            {
                type: 'radar',
                tooltip: {
                    trigger: 'item'
                },
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data: [
                    {
                        value: [3,9,4,7],
                        name: '线上销售额(季度)'
                    },
                    {
                        value: [7,5,8,9],
                        name: '线下销售额(季度)'
                    }
                ]
            },
            {
                type: 'radar',
                tooltip: {
                    trigger: 'item'
                },
                radarIndex: 1,
                data: [
                    {
                        value: [85, 89, 87, 98],
                        name: '标准间'
                    },
                    {
                        value: [97, 99, 96, 97],
                        name: '大床房'
                    },
                    {
                        value: [57, 73, 65, 82],
                        name: '单人间'
                    }
                ]
            },
            {
                type: 'radar',
                tooltip: {
                    trigger: 'item'
                },
                radarIndex: 2,
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data: [
                    {
                        name: '线上销售额',
                        value: [66, 59, 70, 54, 77, 40, 90, 82, 48, 88, 60, 23],
                    },
                    {
                        name:'线下销售额',
                        value:[60, 49, 70, 23, 56, 46, 56, 62, 32, 60, 34, 33]
                    },
                    {
                        name:'会员',
                        value:[80, 69, 90, 69, 96, 46, 76, 82, 52, 80, 54, 48]
                    },
                    {
                        name:'非会员',
                        value:[46, 39, 50, 8, 36, 46, 36, 42, 12, 40, 14, 16]
                    }
                ]
            }
        ]
    };

    chart.setOption(option);
}