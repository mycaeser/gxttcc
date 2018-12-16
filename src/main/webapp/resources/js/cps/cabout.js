/**
 * 
 */
$(function(){
	var getBriefUrl='/gxttcc/frontend/getbriefintroduction';
	var updateBriefUrl='/gxttcc/cps/modifybrief';
	var getAboutUsUrl='/gxttcc/companygx/getaboutus';
	var getCultureUrl='/gxttcc/companygx/getculture';
	var imgUrlfix='../resources/';
	var inHtml='';
	var ma=getQueryString('a');
	if(ma!=null&&ma==1){
		$.getJSON(getBriefUrl,function(data){
			inHtml=data.briefIntroduction;
			$('#content').html(inHtml);
		});
		$('.cabtn').click(function(){
			var inputStr=$('#content').val();
			console.log(inputStr);
			if(inputStr.length>500){
				alert('超过长度限制');
			}else{
				$.ajax({
					url : updateBriefUrl,
					type : 'POST',
					data : {'briefStr':inputStr},
					success : function(data) {
						if (data.success) {
							alert('提交成功');
						} else {
							alert('提交失败');
						}
					}
				});
			}
		});
	}
	if(ma!=null&&ma==2){
		var imgUrl='';
		$.getJSON(getAboutUsUrl,function(data){
			inHtml=data.aboutUs.aaa202;
			imgUrl=data.aboutUs.aaa204;
			$('#content').html(inHtml);
			$('#ca-image').html('<img style="width:100px;height:150px;" src="'+imgUrlfix+imgUrl+'"><br /><div class="kv-item ue-clear"><label><span class="impInfo">*</span>图片</label><div class="kv-item-content file"><span class="text"></span><input type="file"><input type="button" class="button normal long2" value="新图片.."></div></div>');
		})
	}
	if(ma!=null&&ma==3){
		var imgUrl='';
		$.getJSON(getCultureUrl,function(data){
			inHtml=data.culture.aaa303;
			$('#ca-lb').html('');
			$('.kv-item-content').html('');
			$('#ca-other').html('<div class="kv-item ue-clear"><label><span class="impInfo">*</span>文章标题</label><div class="kv-item-content"><input type="text" placeholder="文章标题" value="'+data.culture.aaa302+'"></div><span class="kv-item-tip">标题字数限制在35个字符</span></div><div class="kv-item ue-clear time"><label><span class="impInfo">*</span>发布时间</label><div class="kv-item-content"><input type="text" placeholder="日期" value="2018-12-16"><i class="time-icon"></i></div></div><textarea id="content1" style="width:800px;height:240px;">'+data.culture.aaa303+'</textarea><textarea id="content1" style="width:800px;height:240px;">'+data.culture.aaa302+'</textarea><textarea id="content2" style="width:800px;height:240px;">'+data.culture.aaa304+'</textarea><textarea id="content2" style="width:800px;height:240px;">'+data.culture.aaa305+'</textarea>');
			$('#ca-image').html('<img style="width:250px;height:250px;" src="'+imgUrlfix+data.culture.aaa309+'"><br /><div class="kv-item ue-clear"><label><span class="impInfo">*</span>图片</label><div class="kv-item-content file"><span class="text"></span><input type="file"><input type="button" class="button normal long2" value="新图片.."></div></div><img style="width:250px;height:250px;" src="'+imgUrlfix+data.culture.aaa310+'"><br /><div class="kv-item ue-clear"><label><span class="impInfo">*</span>图片</label><div class="kv-item-content file"><span class="text"></span><input type="file"><input type="button" class="button normal long2" value="新图片.."></div></div></div><img style="width:250px;height:250px;" src="'+imgUrlfix+data.culture.aaa311+'"><br /><div class="kv-item ue-clear"><label><span class="impInfo">*</span>图片</label><div class="kv-item-content file"><span class="text"></span><input type="file"><input type="button" class="button normal long2" value="新图片.."></div></div>');
		})
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