/**
 * 
 */
$(function(){
	var getProjectsTypeUrl='/gxttcc/projectstt/getprojectstype';
	var optHtml='';
	$.getJSON(getProjectsTypeUrl,function(data){
			var typeObj=data.projectsTypeList;
			typeObj.map(function(item,data){
				optHtml+='<option value="'+item.aac301+'">'+item.aac302+'</option>';
			});
			$('#cselect').html(optHtml);
		});
})