/**
 * 
 */
$(function(){
	var getHrInfoUrl='/gxttcc/projectstt/gethrinfo?typeid=2';
	var outHtml1='<table border="1"><tr><th>编号</th><th>标题</th><th>日期</th><th>操作</th></tr>';
	var outHtml2='';
	var outHtml3=' </table>';
	$.getJSON(getHrInfoUrl,function(data){
		var newsObj=data.newsList;
		newsObj.map(function(item,data){
			outHtml2+='<tr><td>'+item.aac101+'</td><td>'+item.aac102+'</td><td>'+item.aac112+'</td><td><a onclick="return confirm(\'请确认删除\');" href="/gxttcc/cps/brief?a=4&b=2&c='+item.aac101+'" >删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/gxttcc/cps/brief?b=1&c='+item.aac101+'" >编辑</a></td></tr>';
		});//
		$('.ue-clear').html('<a href="/gxttcc/addarticlemodel/addteam?a=1" >新增文章</a><br /><br />'+outHtml1+outHtml2+outHtml3);
	});
})