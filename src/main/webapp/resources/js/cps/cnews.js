/**
 * 
 */
$(function(){
	var ma=getQueryString('a');
	var getNewUrl='/gxttcc/newstt/getnews?typeid=';
	if(ma!=null&&ma==1){//显示公司动态
		viewNewsById(1);	
	}
	if(ma!=null&&ma==2){//显示公司动态
		viewNewsById(2);	
	}
	if(ma!=null&&ma==3){//显示公司动态
		viewNewsById(3);	
	}
	if(ma!=null&&ma==4){//显示公司动态
		viewNewsById(4);	
	}
	function viewNewsById(id){
		var outHtml1='<table border="1"><tr><th>编号</th><th>标题</th><th>日期</th><th>操作</th></tr>';
		var outHtml2='';
		var outHtml3=' </table>';
		$.getJSON(getNewUrl+id,function(data){
			var newsObj=data.projectsList;
			newsObj.map(function(item,data){
				outHtml2+='<tr><td>'+item.aab101+'</td><td>'+item.aab102+'</td><td>'+item.aab112+'</td><td><a href="/gxttcc/cps/brief?a=4&b=2&c='+item.aab101+'" >删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/gxttcc/cps/brief?b=1&c='+item.aab101+'" >编辑</a></td></tr>';
			});//
			$('.ue-clear').html('<a href="/gxttcc/cps/caddnewsarticle?a='+id+'" >新增文章</a><br /><br />'+outHtml1+outHtml2+outHtml3);
		});
	}
})