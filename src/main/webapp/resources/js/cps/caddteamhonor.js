/**
 * 添加管理团队和荣誉资质
 */
$(function(){
	var ma=getQueryString('a');
	var addTeamUrl='/gxttcc/addarticlemodel/addteam';
	var addHonorUrl='/gxttcc/addarticlemodel/addhonor';
	if(ma!=null&&ma==1){
		//管理团队
		$('.ue-clear').html('<p>标题</p><input id="cl-title" type="text" style="width:510px;" value=""><p>日期</p><input type="text" style="width:510px" id="cl-date"  onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd\'})" value=""/><br /><table border="3"><tr><th>编号</th><th>图片</th><th>编辑</th></tr><tr><td>1</td><td><img style="width:100px;height:100px;" src=""></td><td><input id="cl-imgf1" type="file"></td></tr></table><div class="buttons"><input class="button cabtn" type="button" value="确认添加" />&nbsp;&nbsp;<input class="button btn-return" type="button" value="返回"/></div>');
		$('.btn-return').click(function(){
			self.location='/gxttcc/cps/brief?a=4';
		});
		$('.cabtn').click(function(){
			//点击确认添加
			var inputTitle=$('#cl-title').val();
			var inputDate=$('#cl-date').val();
			var teamImg = $('#cl-imgf1')[0].files[0];
			var formData = new FormData();
			formData.append('inputTitle',inputTitle);
			formData.append('inputDate',inputDate);
			formData.append('teamImg',teamImg);
			$.ajax({
				url : addTeamUrl,
				type : 'POST',
				data : formData,
				contentType : false,
				processData : false,
				cache : false,
				success : function(data) {
						alert('添加成功');
						self.location='/gxttcc/cps/brief?a=4';
				}
			});
		});
	}
	if(ma!=null&&ma==2){
		//荣誉资质
		$('.ue-clear').html('<p>标题</p><input id="cl-title" type="text" style="width:510px;" value=""><p>日期</p><input type="text" style="width:510px" id="cl-date"  onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd\'})" value=""/><br />正文<br /><textarea id="content1" style="width:510px;height:240px;"></textarea><table border="3"><tr><th>编号</th><th>图片</th><th>编辑</th></tr><tr><td>1</td><td><img style="width:100px;height:100px;" src=""></td><td><input id="cl-imgf1" type="file"></td></tr></table><div class="buttons"><input class="button cabtn" type="button" value="确认添加" />&nbsp;&nbsp;<input class="button btn-return" type="button" value="返回"/></div>');
		$('.btn-return').click(function(){
			self.location='/gxttcc/cps/brief?a=5';
		});
		$('.cabtn').click(function(){
			//点击确认添加
			var inputTitle=$('#cl-title').val();
			var inputDate=$('#cl-date').val();
			var honorImg = $('#cl-imgf1')[0].files[0];
			var inputText=$('#content1').val();
			var formData = new FormData();
			formData.append('inputTitle',inputTitle);
			formData.append('inputDate',inputDate);
			formData.append('inputText',inputText);
			formData.append('honorImg',honorImg);
			$.ajax({
				url : addHonorUrl,
				type : 'POST',
				data : formData,
				contentType : false,
				processData : false,
				cache : false,
				success : function(data) {
						alert('添加成功');
						self.location='/gxttcc/cps/brief?a=5';
				}
			});
		});
	}
})