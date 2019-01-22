/**
 * 
 */
$(function(){
	$('.exit').click(function(){
		$.getJSON('/gxttcc/local/logout');
		
		window.setTimeout(function(){self.location='/gxttcc/vlogin/login';}, 800);
		
	})
})