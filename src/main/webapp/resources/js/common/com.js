/**
 * 
 */
// 根据数组的值返回数组下标
$(function() {
	var getAddressUrl='/gxttcc/frontend/getaddress?addid=';
	var addObj={};
	var addHtml='';
	var addEmail='';
	$.getJSON(getAddressUrl+1,function(data){
		addObj=data.add;
		addHtml+='<h2><span>'+addObj.addName+'</span><span>'+addObj.addContent+'</span></h2>';
		addEmail+='<h2><span>邮箱:'+addObj.addEmail+'</span></h2>';
		$.getJSON(getAddressUrl+2,function(data){
			addObj=data.add;
			addHtml+='<h2><span>'+addObj.addName+':'+addObj.addContent+':</span><span>'+addObj.addPhoneNumber+'</h2>';
			$.getJSON(getAddressUrl+3,function(data){
				addObj=data.add;
				addHtml+='<h2><span>'+addObj.addName+':'+addObj.addContent+':</span><span>'+addObj.addPhoneNumber+'</h2>';
				$('.footer').children('.cml-g').html(addHtml+addEmail);
			});
		});
		
	});
	
	
	
	
	
	
})
function vuleToID(arr, value) {
		for (var a = 0; a < arr.length; a++) {
			if (arr[a] == value) {
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
