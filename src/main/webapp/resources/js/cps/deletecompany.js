/**
 * 
 */
$(function(){
	var cpyid=getQueryString('id');
	var img1=getQueryString('img1');
	var img2=getQueryString('img2');
	var img3=getQueryString('img3');
	var type=getQueryString('type');
	var deleteUrl='/gxttcc/cps/deletecompanybyid?id='+cpyid+'&img1='+img1+'&img2='+img2+'&img3='+img3;
	$.getJSON(deleteUrl);
	self.location='/gxttcc/cps/viewparta?a='+type;
})