/**
 * 
 */
$(function(){
	var pid=getQueryString('a');
	var getOnePjtItemUrl='/gxttcc/addprojectsarticle/getonepjtbyprimid?id=';
	var updateOnePjtUrl='/gxttcc/mod/modifypjtarticle';
	var fixedUrl='/';
	$.getJSON(getOnePjtItemUrl+pid,function(data){
		var pjItem=data.newsItem;
		$('#cl-title').attr("value", pjItem.aac102);
		$('#cl-date').attr("value", pjItem.aac112);
		$('#content1').html(pjItem.aac103);
		$('#content2').html(pjItem.aac104);
		$('#content3').html(pjItem.aac105);
		$('#view-img1').attr("src", fixedUrl+pjItem.aac109);
		$('#view-img2').attr("src", fixedUrl+pjItem.aac110);
		$('#view-img3').attr("src", fixedUrl+pjItem.aac111);
	});
	$('.btn-return').click(function(){
		self.location='/gxttcc/cps/cprojects?a=1';
	});
	$('.cabtn').click(function(){
		var apid=pid;
		var inputTitle=$('#cl-title').val();
		var inputDate=$('#cl-date').val();
		var inputContent1=$('#content1').val();
		var inputContent2=$('#content2').val();
		var inputContent3=$('#content3').val();
		var img1=$('#cl-imgf1')[0].files[0];
		var img2=$('#cl-imgf2')[0].files[0];
		var img3=$('#cl-imgf3')[0].files[0];
		if(inputTitle==null||inputDate==null||inputContent1==null||inputContent2==null||inputContent3==null||inputTitle==''||inputDate==''||inputContent1==''||inputContent2==''||inputContent3==''){
			alert('输入不能为空，请检查');
		}else{
			var formData = new FormData();
			formData.append('inputTitle',inputTitle);
			formData.append('inputContent1', inputContent1);
			formData.append('inputContent2', inputContent2);
			formData.append('inputContent3', inputContent3);
			formData.append('inputDate', inputDate);
			formData.append('img1',img1);
			formData.append('img2',img2);
			formData.append('img3',img3);
			formData.append('id',apid);
			$.ajax({
				url : updateOnePjtUrl,
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
	})
})