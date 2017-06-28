/**
 * Created by lois on 2017/6/28.
 */
function getMemberChart(id) {

    var chart=echarts.init(document.getElementById(id));

    var option = {
        title:{
            text:'入住会员数分析图'
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data:['25岁以下','25-40岁','40岁以上','男','女']
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis : [
            {
                type : 'category',
                data : ['2010','2011','2012','2013','2014','2015','2016']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [

            {
                name:'25岁以下',
                type:'bar',
                stack: '广告',
                data:[301, 1191, 1779, 2500, 2483, 3979, 4583]
            },
            {
                name:'25-40岁',
                type:'bar',
                stack: '广告',
                data:[405, 1587, 2369, 3388, 3379, 5407, 5328]
            },
            {
                name:'40岁以上',
                type:'bar',
                stack: '广告',
                data:[294, 1191, 1782, 2436, 2394, 3827, 3729]
            },
            {
                name:'男',
                type:'bar',
                data:[567, 2010, 2941, 4121, 4021, 6738, 6493],
                markLine : {
                    lineStyle: {
                        normal: {
                            type: 'dashed'
                        }
                    },
                    data : [
                        [{type : 'min'}, {type : 'max'}]
                    ]
                }
            },
            {
                name:'女',
                type:'bar',
                data:[433, 1959, 2937, 4130, 4080, 6823, 6638],
                markLine : {
                    lineStyle: {
                        normal: {
                            type: 'dashed'
                        }
                    },
                    data : [
                        [{type : 'min'}, {type : 'max'}]
                    ]
                }
            },

        ]
    };

    chart.setOption(option);

}