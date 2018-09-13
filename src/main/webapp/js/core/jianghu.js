/**
 * 判断一个对象是否为空
 * @param obj
 * @returns {Boolean} 为空返回true
 */
String.prototype.isEmpty = function(){
	if(this==null||this==""||this==undefined){
		return true;
	}else{
		return false;
	}
}

// TODO 封装AJAX
function getResult(){
	
}

/**
 * 字符串忽略大小写比较
 * @param str
 * @returns {Boolean}
 */
String.prototype.equalsIgnoreCase = function(str){
	//不区分大小写
	if(this.toLowerCase() == str.toLowerCase()){
	   return true; // 正确
	}else{
	   return false; // 错误
	}
}

String.prototype.trim = function() { 
	  return this.triml().trimr(); 
}

String.prototype.triml = function () {
	  return this.replace(/^[\s\n\t]+/g, "");
}

String.prototype.trimr = function () {
	  return this.replace(/[\s\n\t]+$/g, "");
}