/**
 * Created by lois on 2017/6/27.
 */

function  getOccupy(id) {

    var chart=echarts.init(document.getElementById(id));

   var option = {
       title: {
           text: '过去一周入住率分析图'
       },
       tooltip : {
           trigger: 'axis',
           axisPointer: {
               type: 'cross',
               label: {
                   backgroundColor: '#6a7985'
               }
           }
       },
       legend: {
           data:['大床房','单人间','标准间','总入住率','取消率']
       },
       toolbox: {
           feature: {
               saveAsImage: {}
           }
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
               boundaryGap : false,
               data : ['周一','周二','周三','周四','周五','周六','周日']
           }
       ],
       yAxis : [
           {
               type : 'value'
           }
       ],
       series : [
           {
               name:'取消率',
               type:'line',
               lineStyle: {
                   normal: {
                       width: 3,
                       shadowColor: 'rgba(0,0,0,0.4)',
                       shadowBlur: 10,
                       shadowOffsetY: 10
                   }
               },
               stack: '总量',
               areaStyle: {normal: {}},
               data:[1.2, 1.4, 1.5, 1.5, 1.2, 0.5, 0.8]
           },
           {
               name:'大床房',
               type:'line',
               stack: '总量',
               areaStyle: {normal: {}},
               data:[72, 75, 76, 70, 73, 97, 90]
           },
           {
               name:'单人间',
               type:'line',
               stack: '总量',
               areaStyle: {normal: {}},
               data:[72, 71, 73, 74, 71, 72, 71]
           },
           {
               name:'标准间',
               type:'line',
               stack: '总量',
               areaStyle: {normal: {}},
               data:[80, 82, 83, 81, 80, 82, 81]
           },
           {
               name:'总入住率',
               type:'line',
               stack: '总量',
               areaStyle: {normal: {}},
               data:[75, 77, 78, 75, 76, 92, 83]
           }


       ]
   };

   chart.setOption(option);

}