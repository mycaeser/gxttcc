/**
 * 
 */
$(function(){
	var getAddressOtUrl='/gxttcc/frontend/getaddressot';//分公司
	var getAddressUrl='/gxttcc/frontend/getaddress?addid=';//主公司
	var outHtml1='<tr><th>编号</th><th>名称</th><th>地址</th><th>联系人</th><th>电话</th><th>编辑</th></tr><tr>';
	var outHtml2='';
	$.getJSON(getAddressOtUrl,function(data){
		var newsObj=data.addList;
		newsObj.map(function(item,data){
			outHtml2+='<tr><td>'+item.addId+'</td><td>'+item.addName+'</td><td>'+item.addContent+'</td><td>'+item.addPhoneOwner+'</td><td>'+item.addPhoneNumber+'</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/gxttcc/cps/brief?b=1&c='+item.aac101+'" >编辑</a></td></tr>';
		});//
		$('#hr-part').html('<a href="/gxttcc/addarticlemodel/addteam?a=1" >新增地址</a><br /><br />'+outHtml1+outHtml2);
	});
	var outHtml3='<tr><th>网站名称</th><th>备案号</th><th>邮箱</th><th>编辑</th></tr><tr>';
	var outHtml4='';
	
	$.getJSON(getAddressUrl+1,function(data){
		var newsObj=data.add;
		
		outHtml4='<tr><td>'+newsObj.addName+'</td><td>'+newsObj.addContent+'</td><td>'+newsObj.addEmail+'</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/gxttcc/cps/brief?b=1&c='+newsObj.aac101+'" >编辑</a></td></tr>';
		
		$('#bot-index').html(outHtml3+outHtml4);
	});
	var outHtml5='<tr><th>公司名</th><th>地址</th><th>联系人</th><th>电话</th><th>编辑</th></tr><tr>';
	var outHtml6='';
	$.getJSON(getAddressUrl+2,function(data){
		var newsObj1=data.add;
		outHtml6='<tr><td>'+newsObj1.addName+'</td><td>'+newsObj1.addContent+'</td><td>'+newsObj1.addPhoneOwner+'</td>><td>'+newsObj1.addPhoneNumber+'</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/gxttcc/cps/brief?b=1&c='+newsObj1.aac101+'" >编辑</a></td></tr>';
		//$('#bot-part').html(outHtml1+outHtml2);
		$.getJSON(getAddressUrl+3,function(data){
			var newsObj2=data.add;
			outHtml6+='<tr><td>'+newsObj2.addName+'</td><td>'+newsObj2.addContent+'</td><td>'+newsObj2.addPhoneOwner+'</td>><td>'+newsObj2.addPhoneNumber+'</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/gxttcc/cps/brief?b=1&c='+newsObj2.aac101+'" >编辑</a></td></tr>';
			$('#bot-part').html(outHtml5+outHtml6);
		});
	});
})