// 初始化获取原有文件
$(function() {
	// 初始化toastr控件，控件显示在左下位置
	toastr.options = {
		closeButton : false,// 是否显示关闭按钮
		debug : false,
		progressBar : true,// 是否显示进度条
		positionClass : "toast-bottom-left",// 位置
		onclick : null,
		showDuration : "300",// 显示动作（从无到有这个动作）持续的时间
		hideDuration : "1000",// 隐藏动作持续的时间
		timeOut : "5000",// 间隔的时间
		extendedTimeOut : "1000",
		showEasing : "swing",
		hideEasing : "linear",
		showMethod : "fadeIn",
		hideMethod : "fadeOut"
	};

	$.ajax({
		type : "post",
		url : "/jiong/fileUpload.servlet?method=getExsitFile",
		dataType : "json",
		success : function(data) {
			showPhotos(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			toastr.error('获取失败');
		}
	});
});

function showPhotos(jsonStr) {
	// 后台返回json字符串转换为json对象
	var jsonObj = eval(jsonStr);
	// 预览图片json数据组
	var preList = new Array();
	for (var i = 0; i < jsonObj.length; i++) {
		var fileInfo = jsonObj[i];
		// 此处指针对.txt判断，其余自行添加
		if (fileInfo.fileName.indexOf("txt") > 0) {
			// 非图片类型的展示
			preList[i] = "<div class='file-preview-other-frame'><div class='file-preview-other'><span class='file-icon-4x'><i class='fa fa-file-text-o text-info'></i></span></div></div>";
		} else {
			// 图片类型
			preList[i] = "<img src='" + fileInfo.path + "' class='file-preview-image' style='width:"+fileInfo.width+"px;height:"+fileInfo.height+"px;'>";
		}
	}
	var previewJson = preList;
	// 与上面 预览图片json数据组 对应的config数据
	var preConfigList = new Array();
	for (var i = 0; i < jsonObj.length; i++) {
		var fileInfo = jsonObj[i];
		var tjson = {
			caption : fileInfo.fileName, // 展示的文件名
			// 删除图片
			url : '/jiong/fileUpload.servlet?method=deletePic&fileId=' + fileInfo.fileId
		};
		preConfigList[i] = tjson;
	}
	// 具体参数自行查询
	$('#myfile').fileinput({
		language : 'zh', // 设置语言
		uploadUrl : '/jiong/fileUpload.servlet?method=upload',
		uploadAsync : true,
		showUpload : true,// 是否显示上传按钮
		showRemove : false,// 是否显示删除按钮
		showCaption : true,// 是否显示输入框
		showPreview : true,
		showCancel : true,
		dropZoneEnabled : true,
		maxFileCount : 10,
		initialPreviewShowDelete : true,
		msgFilesTooMany : "选择上传的文件数量 超过允许的最大数值！",
		initialPreview : previewJson,
		previewFileIcon : '<i class="fa fa-file"></i>',
		allowedPreviewTypes : [ 'image' ],
		previewFileIconSettings : {
			'docx' : '<i class="fa fa-file-word-o text-primary"></i>',
			'xlsx' : '<i class="fa fa-file-excel-o text-success"></i>',
			'pptx' : '<i class="fa fa-file-powerpoint-o text-danger"></i>',
			'pdf' : '<i class="fa fa-file-pdf-o text-danger"></i>',
			'zip' : '<i class="fa fa-file-archive-o text-muted"></i>',
			'sql' : '<i class="fa fa-file-word-o text-primary"></i>',
		},
		initialPreviewConfig : preConfigList
	});
}

$("#myfile").on("fileuploaded", function(event, data, previewId, index) {
	toastr.success('上传成功'); // 绿色
});