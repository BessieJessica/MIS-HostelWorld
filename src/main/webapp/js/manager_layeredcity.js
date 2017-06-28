/**
 * Created by lois on 2017/6/27.
 */

function getlayeredCitySales(id) {

    var chart=echarts.init(document.getElementById(id));


    var option = {
        title: {
            top:'10px',
            text: '历年全国城市收益分析',
            subtext:'单位: 十万元',
            left:'center'
        },

        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data:['北京','上海','广州','杭州','南京','厦门','苏州','武汉','西安','成都']
        },
        series: [
            {
                name:'销售额  (单位:10万)',
                type:'pie',
                selectedMode: 'single',
                radius: [0, '35%'],

                label: {
                    normal: {
                        position: 'inner'
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data:[
                    {value:556, name:'一线城市', selected:true},
                    {value:348, name:'二线发达城市'},
                    {value:177, name:'二线中等城市'}
                ]
            },
            {
                name:'销售额',
                type:'pie',
                radius: ['41%', '55%'],

                data:[
                    {value:164, name:'北京'},
                    {value:125, name:'上海'},
                    {value:267, name:'广州'},
                    {value:75, name:'杭州'},
                    {value:164, name:'南京'},
                    {value:109, name:'厦门'},
                    {value:19, name:'苏州'},
                    {value:80, name:'武汉'},
                    {value:47, name:'西安'},
                    {value:31, name:'成都'}
                ]
            }
        ]
    };

    chart.setOption(option);
}