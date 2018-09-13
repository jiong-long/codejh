//'use strict';
(function($) {
	$(function() {
		$.mockjax({
			url : '/orgchart/initdata',
			responseTime : 1000,
			contentType : 'application/json',
			responseText : {
				'id' : '1',
				'name' : 'Lao Lao',
				'title' : 'general manager',
				'children' : [ {
					'id' : '2',
					'name' : 'Bo Miao',
					'title' : 'department manager',
					'className' : 'middle-level',
					'children' : [ {
						'id' : '3',
						'name' : 'Li Jing',
						'title' : 'senior engineer',
						'className' : 'product-dept'
					}, {
						'id' : '4',
						'name' : 'Li Xin',
						'title' : 'senior engineer',
						'className' : 'product-dept',
						'children' : [ {
							'id' : '5',
							'name' : 'To To',
							'title' : 'engineer',
							'className' : 'pipeline1'
						}, {
							'id' : '6',
							'name' : 'Fei Fei',
							'title' : 'engineer',
							'className' : 'pipeline1'
						}, {
							'id' : '7',
							'name' : 'Xuan Xuan',
							'title' : 'engineer',
							'className' : 'pipeline1'
						} ]
					} ]
				}, {
					'id' : '8',
					'name' : 'Su Miao',
					'title' : 'department manager',
					'className' : 'middle-level',
					'children' : [ {
						'id' : '9',
						'name' : 'Pang Pang',
						'title' : 'senior engineer',
						'className' : 'rd-dept'
					}, {
						'id' : '10',
						'name' : 'Hei Hei',
						'title' : 'senior engineer',
						'className' : 'rd-dept',
						'children' : [ {
							'id' : '11',
							'name' : 'Xiang Xiang',
							'title' : 'UE engineer',
							'className' : 'frontend1'
						}, {
							'id' : '12',
							'name' : 'Dan Dan',
							'title' : 'engineer',
							'className' : 'frontend1'
						}, {
							'id' : '13',
							'name' : 'Zai Zai',
							'title' : 'engineer',
							'className' : 'frontend1'
						} ]
					} ]
				} ]
			}
		});
		$('#chart-container').orgchart({
			'data' : '/orgchart/initdata',
			'depth' : 4,
			'nodeContent' : 'title',
			// 当方向显示为r2l，需要简单调整一下样式，图形距离左边框太近
			'direction' : 'b2t',
			'createNode' : function($node, data) {
				// 点击事件
				$node.on('click', function() {
					alert(data.name);
				});
			},
			'pan' : true,
			'zoom' : false
		});
		// 不启用上下左右的箭头
		$('.orgchart').addClass('noncollapsable');
		// 启动上下左右的箭头
		// $('.orgchart').removeClass('noncollapsable');
	});
})(jQuery);