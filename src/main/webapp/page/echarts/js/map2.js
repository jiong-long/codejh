/**
 * 地点选择
 */
function createECharts(){
    // 路径配置
    require.config({
        paths: {
            echarts: '/jiong/js/ECharts/dist'
        }
    });
    
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/map' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var myChart = ec.init(document.getElementById('main')); 
            $.ajax({
            	type:'post',
            	url:'eCharts_getAlldata.do',
            	datatype:'json',
            	error:function(msg){
            		alert("查询出错！！！");
            	},
            	success:function(data){
	        		var option = {
	    				tooltip : {
	    					trigger: 'item',
	    					formatter: '{b}'
	    				},
	    				series : [
				          {
				        	  clickable:true,
				        	  name: '中国',
				        	  type: 'map',
				        	  mapType: 'china',
				        	  selectedMode : 'multiple',
				        	  itemStyle:{
				        		  normal:{label:{show:true}},
				        		  emphasis:{label:{show:true}}
				        	  },
				        	  data:data
				          }
				       ]
	        		};
	        		// 为echarts对象加载数据 
	        		myChart.setOption(option); 
            	}
            });
            var ecConfig = require('echarts/config');
            myChart.on(ecConfig.EVENT.CLICK,ecConsole);
            function ecConsole(params){
            	if(params.type=='click'){
            		$.ajax({
                    	type:'post',
                    	url:'eCharts_saveData.do',
                    	data:'key='+params.data.name,
                    	datatype:'json'
            		});
            	}
            }
        }
    );
}