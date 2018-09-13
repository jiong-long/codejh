$(function() {
	// 初始化上传控件
	var path = "/jiong/fileUpload.servlet?method=upload";
	initFileInput("myfile", path);

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
});

/**
 * 初始化控件
 * 
 * @param ctrlName
 *            ID
 * @param uploadUrl
 *            上传路径
 */
function initFileInput(ctrlName, uploadUrl) {
	var control = $('#' + ctrlName);
	control.fileinput({
		language : 'zh', // 设置语言
		uploadUrl : uploadUrl, // 上传地址
		showUpload : true, // 是否显示上传按钮
		showRemove : true,
		dropZoneEnabled : false, // 是否显示拖拽区域
		showCaption : true,// 是否显示标题
		allowedPreviewTypes : [ 'image' ],
		allowedFileTypes : [ 'image' ],
		allowedFileExtensions : [ 'jpg', 'png', 'jpeg' ],// 支持的扩展名
		maxFileSize : 2000, // 允许的大小
		maxFileCount : 5, // 最大上传数量
		enctype : 'multipart/form-data'
	});
}

$("#myfile").on("fileuploaded", function(event, data, previewId, index) {
	toastr.success('上传成功'); // 绿色
	// toastr.info('上传成功');// 深蓝色
	// toastr.error('上传成功'); // 红色
	// toastr.warning('上传成功'); //橙色
});

$('#myfile').on('fileerror', function(event, data, msg) {
	alert(2);
});

$('#myfile').on('filebatchuploaderror', function(event, data, msg) {
	alert(1);
});

$("#myfile").on("filebatchuploadsuccess", function(event, data, previewId, index) {
	alert(3);
});