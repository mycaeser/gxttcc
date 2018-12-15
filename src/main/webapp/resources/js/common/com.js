/**
 * 
 */
//根据数组的值返回数组下标
	function vuleToID(arr,value){
		for(var a=0;a<arr.length;a++){
			if(arr[a]==value){
				return a;
			}
		}
		return -1;
	}
function getQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) {
		return decodeURIComponent(r[2]);
	}
	return '';
}