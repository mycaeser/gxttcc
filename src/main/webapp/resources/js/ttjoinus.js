/**
 * 
 */
$(function(){
	var getAddressUrlot='/gxttcc/frontend/getaddressot';
	var otObj={};
	var otHtml='';
	$.getJSON(getAddressUrlot,function(data){
		otObj=data.addList;
		otObj.map(function(item,data){
			otHtml+='<div class="title">'+item.addName+'</div>地址:'+item.addContent+'('+item.addPhoneOwner+'：'+item.addPhoneNumber+')';
		});
		$('#cp-itemstt').html(otHtml);
	})
})