/**
 * 
 */
$(function(){
	var getProjectsTypeUrl='/gxttcc/projectstt/getprojectstype';
	var optHtml='';
	var addProjectsUrl='/gxttcc/addprojectsarticle/addpjtnewarticle';
	$.getJSON(getProjectsTypeUrl,function(data){
			var typeObj=data.projectsTypeList;
			typeObj.map(function(item,data){
				optHtml+='<option value="'+item.aac301+'">'+item.aac302+'</option>';
			});
			$('#cselect').html(optHtml);
	});
	var changedTypeId=1;
	$("#cselect").change(function () {  
		var typeId = $(this).children('option:selected').val();
		changedTypeId=typeId;
    });
	$('.cabtn').click(function(){
		var typeId=changedTypeId;
		var inputTitle=$('#cl-title').val();
		var inputDate=$('#cl-date').val();
		var inputParta=$('#content1').val();
		var inputPartb=$('#content2').val();
		var inputPartc=$('#content3').val();
		var clImg1 = $('#cl-imgf1')[0].files[0];
		var clImg2 = $('#cl-imgf2')[0].files[0];
		var clImg3 = $('#cl-imgf3')[0].files[0];
		if(inputTitle==null||inputDate==null||inputParta==null||inputPartb==null||inputPartc==null||clImg1==null||clImg2==null||clImg3==null){
			alert('输入不能为空，请检查');
		}else{
			var formData = new FormData();
			formData.append('typeId',typeId);
			formData.append('inputTitle',inputTitle);
			formData.append('inputDate', inputDate);
			formData.append('inputParta', inputParta);
			formData.append('inputPartb', inputPartb);
			formData.append('inputPartc', inputPartc);
			formData.append('clImg1',clImg1);
			formData.append('clImg2',clImg2);
			formData.append('clImg3',clImg3);
			$.ajax({
				url : addProjectsUrl,
				type : 'POST',
				data : formData,
				contentType : false,
				processData : false,
				cache : false,
				success : function(data) {
						alert('修改成功');
						self.location='/gxttcc/cps/cprojects?a=1';
				}
			});
		}
	});
})