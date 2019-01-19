/**
 * 
 */
$(function(){
	var ma=getQueryString('a');
	var getProjectsUrl='/gxttcc/projectstt/getprojects?typeid=';
	var getProjectsTypeUrl='/gxttcc/projectstt/getprojectstype';
	if(ma!=null&&ma==1){//显示弱电工程
		var optHtml='';
		$.getJSON(getProjectsTypeUrl,function(data){
			var typeObj=data.projectsTypeList;
			typeObj.map(function(item,data){
				optHtml+='<option value="'+item.aac301+'">'+item.aac302+'</option>';
			});
			$('#cselect').html(optHtml);
		});
		viewNewsById(1);
		$("#cselect").change(function () {  
			var typeId = $(this).children('option:selected').val();
			viewNewsById(typeId);
        });  
	}
	
	function viewNewsById(id){
		var outHtml1='<table border="1"><tr><th>编号</th><th>标题</th><th>分类</th><th>日期</th><th>操作</th></tr>';
		var outHtml2='';
		var outHtml3=' </table>';
		$.getJSON(getProjectsUrl+id,function(data){
			var newsObj=data.newsList;
			newsObj.map(function(item,data){
				outHtml2+='<tr><td>'+item.aac101+'</td><td>'+item.aac102+'</td><td>'+item.aac116+'</td><td>'+item.aac112+'</td><td><a onclick="return confirm(\'请确认删除\');" href="/gxttcc/cps/brief?a=4&b=2&c='+item.aac101+'" >删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/gxttcc/cps/brief?b=1&c='+item.aac101+'" >编辑</a></td></tr>';
			});//
			$('.ue-clear').html(outHtml1+outHtml2+outHtml3);
		});
		$('#add-atl').html('<a href="/gxttcc/addprojectsarticle/index" >新增文章</a>')
	}
	function getQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) {
			return decodeURIComponent(r[2]);
		}
		return '';
	}
})