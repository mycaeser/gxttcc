/**
 * 
 */
$(function() {
	var getAboutAllUrl='/gxttcc/companygx/getaboutall';
	var getHonorListUrl='/gxttcc/companygx/gethonorlist';
	var getTeamListUrl='/gxttcc/companygx/getteamlist';
	var getCulture='/gxttcc/companygx/getculture';
	var imgUrlfix='/';
	var MAX_COUNT_ITEM=8;
	var aboutUs={};
	var culture={};
	var honorList={};
	var teamList={};
	$.getJSON(getAboutAllUrl,function(data){
		aboutUs=data.aboutUs;
		culture=data.culture;
		honorList=data.honorList;
		teamList=data.teamList;
	});
	$('#about-menu-a').click();
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
		var j=1;
		teamList.map(function(item,data){
			if(i==0){
				tmph+='<li class="on"><a >'+j+'</a></li>';
			}else{
				tmph+='<li ><a href="?a=3&b='+item.aaa401+'">'+j+'</a></li>';
			}
			i++;
			j++;
		});
		viewHTML=viewHTML+tmph+'</ul></div>';
		$('.cm-content').html(viewHTML);
		$('#pgmtt li').click(function(data){
			var whichid=$(this).text();
			var viewHTMLa='';
			tmph='';
			teamList.map(function(item,data){
				if(item.aaa401==whichid){
					viewHTMLa='<div class="prodoct-list" > <div class="am-u-sm-6 am-u-md-6 am-u-lg-3"> <a > <img src="'+imgUrlfix+item.aaa403+'"> <span class="f-toe">'+item.aaa404+'</span></a></div><div class="am-u-sm-6 am-u-md-6"><p>'+item.aaa402+'</p></div></div>';	
				}	
			});
			viewHTMLa=viewHTMLa+'<div class="cm-page"> <ul id="pgmtt">';
			viewHTMLa=viewHTMLa+'</ul></div>';
			$('.cm-content').html(viewHTMLa);
		});
	});
	var ma=getQueryString('a');
	var mb=getQueryString('b');
	
	if(ma==3&&mb!=null){
		var tmph1='';
		var viewHTMLb='';
		var teamList1={};
		$.getJSON(getTeamListUrl,function(data){
			teamList1=data.teamList;
			teamList1.map(function(item,data){
				if(item.aaa401==mb){
					tmph1+='<li class="on"><a href="?a=3&b='+item.aaa401+'">'+item.aaa401+'</a></li>';
					viewHTMLb='<div class="prodoct-list" > <div class="am-u-sm-6 am-u-md-6 am-u-lg-3"> <a > <img src="'+imgUrlfix+item.aaa403+'"> <span class="f-toe">'+item.aaa404+'</span></a></div><div class="am-u-sm-6 am-u-md-6"><p>'+item.aaa402+'</p></div></div>';	
				}else{
					tmph1+='<li ><a href="?a=3&b='+item.aaa401+'">'+item.aaa401+'</a></li>';
				}	
			});
			viewHTMLb=viewHTMLb+'<div class="cm-page"> <ul id="pgmtt">';
			viewHTMLb=viewHTMLb+tmph1+'</ul></div>';
			$('.cm-content').html(viewHTMLb);
		});
		
	};
	$('#about-menu-d').click(function(){
		removeClassOnAll();
		$('#about-menu-d').addClass('on');
		$('#location-a').html('荣誉资质');
		//var viewHTML='<img src="'+imgUrlfix+aboutUs.aaa204+'" style="height:800px;">';
		//$('.cm-content').html(aboutUs.aaa202+viewHTML);
		var viewHTML='<ul class="cm-new-list-ul">';
		var viewHTMLm='';
		var viewHTMLpage='<div class="cm-page"><ul class="honor-detail-tt">';
		var pageTMP='';
		var i=0;
		honorList.map(function(item,data){
			if(i<MAX_COUNT_ITEM){
				viewHTMLm+='<li><a href="?a=4&c='+item.aaa501+'"><span>'+item.aaa502+'</span><i>'+item.aaa505+'</i></a></li>';	
			}
			i++;
		});
		var count=honorList.length;
		var pageNum=Math.ceil(count/MAX_COUNT_ITEM);
		for(var a=0;a<pageNum;a++){
			if(a==0){
				pageTMP+='<li class="on"><a href="?a=4&pg=1">1</a></li>';
			}else{
				var ia=a+1;
				pageTMP+='<li><a href="?a=4&pg='+ia+'">'+ia+'</a></li>';
			}
		}
		$('.cm-content').html(viewHTML+viewHTMLm+'</ul>'+viewHTMLpage+pageTMP+'</ul></div>');
	});
	ma=getQueryString('a');
	var mc=getQueryString('c');
	if(ma==4&&mc!=null){
		//点击荣誉资质详情
		var viewHTMLc='';
		var honorList1={};
		var getIDarr=new Array();
		var IDi=0;
		$.getJSON(getHonorListUrl,function(data){
			honorList1=data.honorList;
			honorList1.map(function(item,date){
				getIDarr[IDi]=item.aaa501;
				IDi++;
			});
			IDi=0;
			honorList1.map(function(item,data){
				if(item.aaa501==mc){
					var pre=vuleToID(getIDarr,mc)-1;
					var nex=vuleToID(getIDarr,mc)+1;
					var pageHTMLc='';
					var pageCount=Math.ceil(item.length/MAX_COUNT_ITEM);
					if(pre<0){
						pageHTMLc='<div class="cm-page"><ul><li class="disabled"><a >上一页</a></li>';
					}else{
						pageHTMLc='<div class="cm-page"><ul><li class="item"><a href="?a=4&c='+getIDarr[pre]+'">上一页</a></li>';
					}
					if(nex>getIDarr.length-1){
						pageHTMLc+='<li class="disabled"><a >下一页</a></li>';
					}else{
						pageHTMLc+='<li class="item"><a href="?a=4&c='+getIDarr[nex]+'">下一页</a></li>';
					}
					viewHTMLc=viewHTMLc+'<div class="cm-content-title">'+item.aaa502+'</div><img src="'+imgUrlfix+item.aaa503+'">'+item.aaa504+'<br />'+item.aaa505+pageHTMLc+'</ul></div>';
					$('.cm-content').html(viewHTMLc);
				}
			});
		});
	}
	var md=getQueryString('pg');
	if(ma==4&&md!=null&&md!=''){
		var viewHTMLd='';
		var honorList2={};
		var i=1;
		var startPage=(Math.floor(md)-1)*MAX_COUNT_ITEM+1;//计算当前页码的开始ID
		var finalPage=Math.floor(md)*MAX_COUNT_ITEM;//计算当前页码的结束ID
		var viewHTML='<ul class="cm-new-list-ul">';
		var viewHTMLm='';
		var viewHTMLpage='<div class="cm-page"><ul class="honor-detail-tt">';
		var pageTMP='';
		var count=0;
		$.getJSON(getHonorListUrl,function(data){
			honorList2=data.honorList;
			count=honorList2.length;
			honorList2.map(function(item,data){
				if(i>startPage-1&&i<finalPage+1){//输出当前页码的条目
					viewHTMLm+='<li><a href="?a=4&c='+item.aaa501+'"><span>'+item.aaa502+'</span><i>'+item.aaa505+'</i></a></li>';	
				}
				i++;
			});
			var pageNum=Math.ceil(count/MAX_COUNT_ITEM);//计算总页数
			for(var a=0;a<pageNum;a++){
				var ia=a+1;
				if(ia==md){//当前加标签class on
					pageTMP+='<li class="on"><a href="?a=4&pg='+ia+'">'+ia+'</a></li>';
				}else{
					pageTMP+='<li><a href="?a=4&pg='+ia+'">'+ia+'</a></li>';
				}
			}
			$('.cm-content').html(viewHTML+viewHTMLm+'</ul>'+viewHTMLpage+pageTMP+'</ul></div>');
		});	
	}
	$('#about-menu-e').click(function(){
		removeClassOnAll();
		$('#about-menu-e').addClass('on');
		$('#location-a').html('组织架构');
		var viewHTML='<img src="'+imgUrlfix+'images/taitongjj08.png">';
		$('.cm-content').html(viewHTML);
	});
	$('#about-menu-f').click(function(){
		removeClassOnAll();
		$('#about-menu-f').addClass('on');
		$('#location-a').html('合作伙伴');
		var viewHTML='<img src="'+imgUrlfix+'images/hezuo.png">';
		$('.cm-content').html(viewHTML);
	});
	function removeClassOnAll(){
		$('#about-menu-a').removeClass('on');
		$('#about-menu-b').removeClass('on');
		$('#about-menu-c').removeClass('on');
		$('#about-menu-d').removeClass('on');
		$('#about-menu-e').removeClass('on');
		$('#about-menu-f').removeClass('on');
	}
	var mt=getQueryString('t');
	if(mt==1){
		removeClassOnAll();
		var aboutUs1={};
		$.getJSON(getAboutAllUrl,function(data){
			aboutUs1=data.aboutUs;
			$('#about-menu-a').addClass('on');
			$('#location-a').html('企业简介');
			var viewHTML='<img src="'+imgUrlfix+aboutUs1.aaa204+'" style="height:800px;">';
			$('.cm-content').html(aboutUs1.aaa202+viewHTML);
		});
		
	}
	if(mt==2){
		removeClassOnAll();
		$('#about-menu-e').addClass('on');
		$('#location-a').html('组织架构');
		var viewHTML='<img src="'+imgUrlfix+'images/taitongjj08.png">';
		$('.cm-content').html(viewHTML);
	}
})