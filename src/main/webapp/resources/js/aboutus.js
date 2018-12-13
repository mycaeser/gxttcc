/**
 * 
 */
$(function() {
	var getAboutAllUrl='/gxttcc/companygx/getaboutall';
	var imgUrlfix='../resources/';
	var aboutUs={};
	var culture={};
	var honorList={};
	var teamList={};
	$.getJSON(getAboutAllUrl,function(data){
		aboutUs=data.aboutUs;
		culture=data.culture;
		honorList=data.honorList;
		teamList=data.teamList;
	})
	var leftMenuId=getQueryString('a');
	if(leftMenuId!=null){
		if(leftMenuId==1){
			$('#about-menu-a').addClass('on');
			$('#location-a').html('企业简介');
		}
		if(leftMenuId==2){
			$('#about-menu-b').addClass('on');
			$('#location-a').html('企业文化');
		}
		if(leftMenuId==3){
			$('#about-menu-c').addClass('on');
			$('#location-a').html('管理团队');
		}
		if(leftMenuId==4){
			$('#about-menu-d').addClass('on');
			$('#location-a').html('合作伙伴');
		}
	}
	$('#about-menu-a').click(function(){
		removeClassOnAll();
		$('#about-menu-a').addClass('on');
		$('#location-a').html('企业简介');
		var viewHTML='<img src="'+imgUrlfix+aboutUs.aaa204+'" style="height:800px;">';
		$('.cm-content').html(aboutUs.aaa202+viewHTML);
	});
	$('#about-menu-b').click(function(){
		removeClassOnAll();
		$('#about-menu-b').addClass('on');
		$('#location-a').html('企业文化');
		var viewHTML='<div class="cm-content-title" >'+culture.aaa302+'</div>'+culture.aaa302+'<img src="'+imgUrlfix+culture.aaa309+'">'+culture.aaa304+'<img src="'+imgUrlfix+culture.aaa310+'">'+culture.aaa305+'<img src="'+imgUrlfix+culture.aaa311+'">';
		$('.cm-content').html(viewHTML);
	});
	$('#about-menu-c').click(function(){
		removeClassOnAll();
		$('#about-menu-c').addClass('on');
		$('#location-a').html('管理团队');
		var viewHTML='<div class="prodoct-list" > <div class="am-u-sm-6 am-u-md-6 am-u-lg-3"> <a > <img src="'+imgUrlfix+teamList[0].aaa403+'"> <span class="f-toe">'+teamList[0].aaa404+'</span></a></div><div class="am-u-sm-6 am-u-md-6"><p>'+teamList[0].aaa402+'</p></div></div>';
		viewHTML=viewHTML+'<div class="cm-page"> <ul id="pgmtt">';
		var tmph='';
		var i=0;
		teamList.map(function(item,data){
			if(i==0){
				tmph+='<li class="on"><a >'+item.aaa401+'</a></li>';
			}else{
				tmph+='<li ><a >'+item.aaa401+'</a></li>';
			}
			i++;
		});
		viewHTML=viewHTML+tmph+'</ul></div>';
		$('.cm-content').html(viewHTML);
		$('#pgmtt li').click(function(data){
			var whichid=$(this).text();
			var viewHTMLa='';
			tmph='';
			teamList.map(function(item,data){
				//tmph+='<li ><a >'+item.aaa401+'</a></li>';
				if(item.aaa401==whichid){
					viewHTMLa='<div class="prodoct-list" > <div class="am-u-sm-6 am-u-md-6 am-u-lg-3"> <a > <img src="'+imgUrlfix+item.aaa403+'"> <span class="f-toe">'+item.aaa404+'</span></a></div><div class="am-u-sm-6 am-u-md-6"><p>'+item.aaa402+'</p></div></div>';	
				}	
			});
			viewHTMLa=viewHTMLa+'<div class="cm-page"> <ul id="pgmtt">';
			viewHTMLa=viewHTMLa+'</ul></div>';
			//viewHTMLa=viewHTMLa+tmph+'</ul></div>';
			$('.cm-content').html(viewHTMLa);
		});
	});
	
	function removeClassOnAll(){
		$('#about-menu-a').removeClass('on');
		$('#about-menu-b').removeClass('on');
		$('#about-menu-c').removeClass('on');
		$('#about-menu-d').removeClass('on');
	}
})