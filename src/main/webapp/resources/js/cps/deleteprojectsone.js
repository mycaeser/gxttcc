/**
 * 
 */
$(function(){
	var pid=getQueryString('id');
	var pimg1=getQueryString('img1');
	var pimg2=getQueryString('img2');
	var pimg3=getQueryString('img3');
	var deleteUrl='/gxttcc/addprojectsarticle/delpjtarticle?id='+pid+'&img1='+pimg1+'&img2='+pimg2+'&img3='+pimg3;
	$.getJSON(deleteUrl);
	self.location='/gxttcc/cps/cprojects?a=1';
})