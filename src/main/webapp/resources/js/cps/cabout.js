/**
 * 
 */
$(function(){
	var getBriefUrl='/gxttcc/frontend/getbriefintroduction';
	var updateBriefUrl='/gxttcc/cps/modifybrief';
	var getAboutUsUrl='/gxttcc/companygx/getaboutus';
	var getCultureUrl='/gxttcc/companygx/getculture';
	var updateCultureUrl='/gxttcc/cps/modifyindexculture';
	var getTeamUrl='/gxttcc/companygx/getteamlist';
	var deleteTeamByIdUrl='/gxttcc/cps/deleteteambyid';
	var getTeamByIdUrl='/gxttcc/cps/getteambyid?id=';
	var updateTeamUrl='/gxttcc/cps/updateteambyid';
	var getHonorUrl='/gxttcc/companygx/gethonorlist';
	var deleteHonorUrl='/gxttcc/cps/deletehonorbyid';
	var getHonoryByIdUrl='/gxttcc/cps/gethonorbyid?id=';
	var updateHonorUrl='/gxttcc/cps/updatehonorbyid';
	var imgUrlfix='/';
	var inHtml='';
	var ma=getQueryString('a');
	if(ma!=null&&ma==1){
		$.getJSON(getBriefUrl,function(data){
			inHtml=data.briefIntroduction;
			$('.ue-clear').html('<label id="ca-lb"><span class="impInfo">*</span>首页简介</label><textarea placeholder="简介内容" name="content" id="content" style="width:800px;height:240px;">'+inHtml+'</textarea>');
		});
		$('.cabtn').click(function(){
			var inputStr=$('#content').val();
			if(inputStr.length>500){
				alert('超过长度限制500');
			}else{
				$.ajax({
					url : updateBriefUrl,
					type : 'POST',
					data : {'briefStr':inputStr},
					success : function(data) {
						alert('提交成功');
						self.location='/gxttcc/cps/brief?a=1';
					}
				});
			}
		});
	}
	if(ma!=null&&ma==2){//显示企业简介
		var imgUrl='';
		$.getJSON(getAboutUsUrl,function(data){
			inHtml=data.aboutUs.aaa202;
			imgUrl=data.aboutUs.aaa204;
			$('.ue-clear').html('<textarea name="content" id="content" style="width:535px;height:240px;">'+inHtml+'</textarea><table border="3"><tr><th>编号</th><th>图片</th><th>编辑</th></tr><tr><td>1</td><td><img style="width:100px;height:100px;" src="'+imgUrlfix+imgUrl+'"></td><td><input id="brief-imgf" type="file"></td></tr></table>');
		});
		$('.cabtn').click(function(){
			var inputStr=$('#content').val();
			if(inputStr.length>2000){
				alert('超过长度限制2000');
			}else{
				var formData = new FormData();
				var brImg = $('#brief-imgf')[0].files[0];
				formData.append('briefImgFile', brImg);
				formData.append('briefText',inputStr);
				$.ajax({
					url : updateBriefUrl+'a',
					type : 'POST',
					data : formData,
					contentType : false,
					processData : false,
					cache : false,
					success : function(data) {
							alert('修改成功');
							self.location='/gxttcc/cps/brief?a=2';
					}
				});
			}
		});
	}
	if(ma!=null&&ma==3){//显示企业文化
		var imgUrl='';
		$.getJSON(getCultureUrl,function(data){
			inHtml=data.culture.aaa303;
			$('.ue-clear').html('<p>标题</p><input id="cl-title" type="text" style="width:510px;" value="'+data.culture.aaa302+'"><p>日期</p><input type="text" style="width:510px" id="cl-date"  onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd\'})" value="'+data.culture.aaa312+'"/><br /><textarea id="content1" style="width:510px;height:240px;">'+data.culture.aaa303+'</textarea><br /><textarea id="content2" style="width:510px;height:240px;">'+data.culture.aaa304+'</textarea><br /><textarea id="content3" style="width:510px;height:240px;">'+data.culture.aaa305+'</textarea><table border="3"><tr><th>编号</th><th>图片</th><th>编辑</th></tr><tr><td>1</td><td><img style="width:100px;height:100px;" src="'+imgUrlfix+data.culture.aaa309+'"></td><td><input id="cl-imgf1" type="file"></td></tr><tr><td>2</td><td><img style="width:100px;height:100px;" src="'+imgUrlfix+data.culture.aaa310+'"></td><td><input id="cl-imgf2" type="file"></td></tr><tr><td>3</td><td><img style="width:100px;height:100px;" src="'+imgUrlfix+data.culture.aaa311+'"></td><td><input id="cl-imgf3" type="file"></td></tr></table>');
		});
		$('.cabtn').click(function(){
			var inputStr4=$('#cl-date').val();
			var inputTitle=$('#cl-title').val();
			var inputStr1=$('#content1').val();
			var inputStr2=$('#content2').val();
			var inputStr3=$('#content3').val();
			if(inputStr1.length>2000||inputStr2.length>2000||inputStr3.length>2000){
				alert('超过长度限制2000');
			}else{
				var formData = new FormData();
				var clImg1 = $('#cl-imgf1')[0].files[0];
				var clImg2 = $('#cl-imgf2')[0].files[0];
				var clImg3 = $('#cl-imgf3')[0].files[0];
				formData.append('inputTitle',inputTitle);
				formData.append('inputStr1', inputStr1);
				formData.append('inputStr2', inputStr2);
				formData.append('inputStr3', inputStr3);
				formData.append('inputStr4', inputStr4);
				formData.append('clImg1',clImg1);
				formData.append('clImg2',clImg2);
				formData.append('clImg3',clImg3);
				$.ajax({
					url : updateCultureUrl,
					type : 'POST',
					data : formData,
					contentType : false,
					processData : false,
					cache : false,
					success : function(data) {
							alert('修改成功');
							self.location='/gxttcc/cps/brief?a=3';
					}
				});
			}
		});
	}
	if(ma!=null&&ma==4){//显示团队管理
		var outHtml1='<table border="1"><tr><th>编号</th><th>标题</th><th>日期</th><th>操作</th></tr>';
		var outHtml2='';
		var outHtml3=' </table>';
		$.getJSON(getTeamUrl,function(data){
			var teamObj=data.teamList;
			teamObj.map(function(item,data){
				outHtml2+='<tr><td>'+item.aaa401+'</td><td>'+item.aaa402+'</td><td>'+item.aaa404+'</td><td><a href="/gxttcc/cps/brief?a=4&b=2&c='+item.aaa401+'" >删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/gxttcc/cps/brief?b=1&c='+item.aaa401+'" >编辑</a></td></tr>';
			});//a=4团队管理b=1编辑 b=2删除
			$('.ue-clear').html('<a href="/gxttcc/addarticlemodel/addteam?a=1" >新增文章</a><br /><br />'+outHtml1+outHtml2+outHtml3);
			$('.buttons').html('');
		});
		
	}
	var mb=getQueryString('b');
	var mc=getQueryString('c');
	if(mb!=null&&mc!=null){
		if(mb==2){//删除团队管理一项
			$.ajax({
				url : deleteTeamByIdUrl,
				type : 'POST',
				data : {'id':mc},
				success : function(data) {
						alert('修改成功');
						self.location='/gxttcc/cps/brief?a=4';
				}
			});
		}
		if(mb==1){//编辑团队管理一项
			$.getJSON(getTeamByIdUrl+mc,function(data){
				$('.ue-clear').html('<p>标题</p><textarea id="content1" style="width:510px;height:240px;">'+data.teamItem.aaa402+'</textarea><p>日期</p><input type="text" style="width:510px" id="team-date1"  onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd\'})" value="'+data.teamItem.aaa404+'"/><br /><br /><table border="3"><tr><th>编号</th><th>图片</th><th>编辑</th></tr><tr><td>1</td><td><img style="width:100px;height:100px;" src="'+imgUrlfix+data.teamItem.aaa403+'"></td><td><input id="team-imgf1" type="file"></td></tr></table>');
				$('.buttons').html('<input class="button cabtn" type="button" value="确认修改" />&nbsp;&nbsp;&nbsp;&nbsp;<input class="button btn-return error" type="button" value="返回"/>');
				$('.btn-return').click(function(){
					self.location='/gxttcc/cps/brief?a=4';
				});
				$('.cabtn').click(function(){
					var teamTitle=$('#content1').val();
					var teamDate=$('#team-date1').val();
					var teamFile = $('#team-imgf1')[0].files[0];
					var formData = new FormData();
					formData.append('teamId',mc);
					formData.append('teamTitle',teamTitle);
					formData.append('teamDate',teamDate);
					formData.append('teamFile',teamFile);
					$.ajax({
						url : updateTeamUrl,
						type : 'POST',
						data : formData,
						contentType : false,
						processData : false,
						cache : false,
						success : function(data) {
								alert('修改成功');
								self.location='/gxttcc/cps/brief?a=4';
						}
					});
				});
			})
		}
		if(mb==3){
			$.getJSON(getHonoryByIdUrl+mc,function(data){
				$('.ue-clear').html('<p>标题</p><input id="honor-title" type="text" style="width:510px;" value="'+data.honorItem.aaa502+'"><p>正文</p><textarea id="content1" style="width:510px;height:240px;">'+data.honorItem.aaa504+'</textarea><p>日期</p><input type="text" style="width:510px" id="honor-date1"  onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd\'})" value="'+data.honorItem.aaa505+'"/><br /><br /><table border="3"><tr><th>编号</th><th>图片</th><th>编辑</th></tr><tr><td>1</td><td><img style="width:100px;height:100px;" src="'+imgUrlfix+data.honorItem.aaa503+'"></td><td><input id="honor-imgf1" type="file"></td></tr></table>');
				$('.buttons').html('<input class="button cabtn" type="button" value="确认修改" />&nbsp;&nbsp;&nbsp;&nbsp;<input class="button btn-return error" type="button" value="返回"/>');
				$('.btn-return').click(function(){
					self.location='/gxttcc/cps/brief?a=5';
				});
				$('.cabtn').click(function(){
					var honorTitle=$('#honor-title').val();
					var honorText=$('#content1').val();
					var honorDate=$('#honor-date1').val();
					var honorFile = $('#honor-imgf1')[0].files[0];
					var formData = new FormData();
					formData.append('honorId',mc);
					formData.append('honorText',honorText);
					formData.append('honorTitle',honorTitle);
					formData.append('honorDate',honorDate);
					formData.append('honorFile',honorFile);
					$.ajax({
						url : updateHonorUrl,
						type : 'POST',
						data : formData,
						contentType : false,
						processData : false,
						cache : false,
						success : function(data) {
								alert('修改成功');
								self.location='/gxttcc/cps/brief?a=5';
						}
					});
				});
			})
		}
		if(mb==4){//删除一条荣誉资质
			$.ajax({
				url : deleteHonorUrl,
				type : 'POST',
				data : {'id':mc},
				success : function(data) {
						alert('修改成功');
						self.location='/gxttcc/cps/brief?a=5';
				}
			});
		}
	}
	if(ma!=null&&ma==5){//显示荣誉资质
		var outHtml1='<table border="1"><tr><th>编号</th><th>标题</th><th>日期</th><th>操作</th></tr>';
		var outHtml2='';
		var outHtml3=' </table>';
		$.getJSON(getHonorUrl,function(data){
			var honorObj=data.honorList;
			honorObj.map(function(item,data){
				outHtml2+='<tr><td>'+item.aaa501+'</td><td>'+item.aaa502+'</td><td>'+item.aaa505+'</td><td><a href="/gxttcc/cps/brief?a=5&b=4&c='+item.aaa501+'" >删除</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/gxttcc/cps/brief?b=3&c='+item.aaa501+'" >编辑</a></td></tr>';
			});//a=5荣誉资质b=3编辑 b=4删除
			$('.ue-clear').html('<a href="/gxttcc/addarticlemodel/addteam?a=2" >新增文章</a><br /><br />'+outHtml1+outHtml2+outHtml3);
			$('.buttons').html('');
		});	
	}
	if(ma!=null&&ma==6){//显示荣誉资质
		$('.ue-clear').html('<table border="1"><tr><th>编号</th><th>图片</th><th>编辑</th></tr><tr><td>1</td><td><img style="width:100px;height:100px;" src="'+imgUrlfix+'images/taitongjj08.jpg"></td><td><input id="org-img" type="file"></td></tr></table>');
	}
	if(ma!=null&&ma==7){//显示荣誉资质
		$('.ue-clear').html('<table border="1"><tr><th>编号</th><th>图片</th><th>编辑</th></tr><tr><td>1</td><td><img style="width:100px;height:100px;" src="'+imgUrlfix+'images/hezuo.jpg"></td><td><input id="org-img" type="file"></td></tr></table>');
	}
})
function getQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null) {
			return decodeURIComponent(r[2]);
		}
		return '';
	}