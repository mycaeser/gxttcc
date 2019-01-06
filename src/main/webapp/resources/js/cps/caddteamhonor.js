/**
 * 添加管理团队和荣誉资质
 */
$(function(){
	var ma=getQueryString('a');
	if(ma!=null&&ma==1){
		//管理团队
		$('.ue-clear').html('<p>标题</p><input id="cl-title" type="text" style="width:510px;" value=""><p>日期</p><input type="text" style="width:510px" id="cl-date"  onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd\'})" value=""/><br /><table border="3"><tr><th>编号</th><th>图片</th><th>编辑</th></tr><tr><td>1</td><td><img style="width:100px;height:100px;" src=""></td><td><input id="cl-imgf1" type="file"></td></tr></table><div class="buttons"><input class="button cabtn" type="button" value="确认添加" />&nbsp;&nbsp;<input class="button btn-return" type="button" value="返回"/></div>');
		$('.btn-return').click(function(){
			self.location='/gxttcc/cps/brief?a=4';
		});
	}
	if(ma!=null&&ma==2){
		//荣誉资质
		$('.ue-clear').html('<p>标题</p><input id="cl-title" type="text" style="width:510px;" value=""><p>日期</p><input type="text" style="width:510px" id="cl-date"  onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd\'})" value=""/><br />正文<br /><textarea id="content1" style="width:510px;height:240px;"></textarea><table border="3"><tr><th>编号</th><th>图片</th><th>编辑</th></tr><tr><td>1</td><td><img style="width:100px;height:100px;" src=""></td><td><input id="cl-imgf1" type="file"></td></tr></table><div class="buttons"><input class="button cabtn" type="button" value="确认添加" />&nbsp;&nbsp;<input class="button btn-return" type="button" value="返回"/></div>');
		$('.btn-return').click(function(){
			self.location='/gxttcc/cps/brief?a=5';
		});
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