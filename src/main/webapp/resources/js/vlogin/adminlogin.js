/**
 * 
 */
$(function(){
	$('#btn-login').click(function(){
		var loginAdminUrl='/gxttcc/local/logincheck';
		var userName=$('#userName').val();
		var password=$('#password').val();
		var formData=new FormData();
		formData.append('userName',userName);
		formData.append('password',password);
		$.ajax({
			url : loginAdminUrl,
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
					self.location='/gxttcc/cps/admin';
			}
		});
	})
})