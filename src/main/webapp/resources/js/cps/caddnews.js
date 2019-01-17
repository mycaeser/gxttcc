/**
 * 
 */
$(function(){
	///gxttcc/cps/viewparta?a=1
	var ma=getQueryString('a');
	if(ma!=null){
		$('.btn-return').click(function(){//给返回按钮加上ID
			self.location='/gxttcc/cps/viewparta?a='+ma;
		});
		$('.cabtn').click(function(){//给确认按钮加上事件
			var inputTitle=$('#cl-title').val();
			var inputDate=$('#cl-date').val();
			var inputParta=$('#content1').val();
			var inputPartb=$('#content2').val();
			var inputPartc=$('#content3').val();
			var clImg1 = $('#cl-imgf1')[0].files[0];
			var clImg2 = $('#cl-imgf2')[0].files[0];
			var clImg3 = $('#cl-imgf3')[0].files[0];
			if(inputTitle==null||inputDate==null||clImg1==null||clImg2==null||clImg3==null){
				//标题、图片如果有未填写的
				alert('请输入指定内容再提交！');
			}else{
				//
			}
		});
		
	}
	
})