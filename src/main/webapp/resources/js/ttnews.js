/**
 * 
 */
$(function(){
	var getNewUrl='/gxttcc/newstt/getnews?typeid=';
	var MAX_COUNT_ITEM=8;
	var imgUrlfix='../resources/';
	var MENU_TAB_A=1;
	var MENU_TAB_B=2;
	var MENU_TAB_C=3;
	var MENU_TAB_D=4;
	var getMenuA={};
	var getMenuB={};
	var getMenuC={};
	var getMenuD={};
	$.getJSON(getNewUrl+1,function(data){
		getMenuA=data.projectsList;
	});
	$.getJSON(getNewUrl+2,function(data){
		getMenuB=data.projectsList;
	});
	$.getJSON(getNewUrl+3,function(data){
		getMenuC=data.projectsList;
	});
	$.getJSON(getNewUrl+4,function(data){
		getMenuD=data.projectsList;
	});
	$('#about-menu-a').click(function(){
		removeClassOnAll();
		$('#about-menu-a').addClass('on');
		$('#location-c').html('公司动态');
		var viewHTML='<ul class="cm-new-list-ul">';
		var viewHTMLm='';
		var viewHTMLpage='<div class="cm-page"><ul class="honor-detail-tt">';
		var pageTMP='';
		var i=0;
		getMenuA.map(function(item,data){
			if(i<MAX_COUNT_ITEM){
				viewHTMLm+='<li><a href="?a=1&b='+item.aab101+'"><span>'+item.aab102+'</span><i>'+item.aab112+'</i></a></li>';	
			}
			i++;
		});
		var count=getMenuA.length;
		var pageNum=Math.ceil(count/MAX_COUNT_ITEM);
		for(var a=0;a<pageNum;a++){
			if(a==0){
				pageTMP+='<li class="on"><a href="?a=1&pg=1">1</a></li>';
			}else{
				var ia=a+1;
				pageTMP+='<li><a href="?a=1&pg='+ia+'">'+ia+'</a></li>';
			}
		}
		$('.cm-content').html(viewHTML+viewHTMLm+'</ul>'+viewHTMLpage+pageTMP+'</ul></div>');
	});
	
	$('#about-menu-b').click(function(){
		removeClassOnAll();
		$('#about-menu-b').addClass('on');
		$('#location-c').html('行业新闻');
		var viewHTML='<ul class="cm-new-list-ul">';
		var viewHTMLm='';
		var viewHTMLpage='<div class="cm-page"><ul class="honor-detail-tt">';
		var pageTMP='';
		var i=0;
		getMenuB.map(function(item,data){
			if(i<MAX_COUNT_ITEM){
				viewHTMLm+='<li><a href="?a=2&b='+item.aab101+'"><span>'+item.aab102+'</span><i>'+item.aab112+'</i></a></li>';	
			}
			i++;
		});
		var count=getMenuB.length;
		var pageNum=Math.ceil(count/MAX_COUNT_ITEM);
		for(var a=0;a<pageNum;a++){
			if(a==0){
				pageTMP+='<li class="on"><a href="?a=2&pg=1">1</a></li>';
			}else{
				var ia=a+1;
				pageTMP+='<li><a href="?a=2&pg='+ia+'">'+ia+'</a></li>';
			}
		}
		$('.cm-content').html(viewHTML+viewHTMLm+'</ul>'+viewHTMLpage+pageTMP+'</ul></div>');
	});
	
	$('#about-menu-c').click(function(){
		removeClassOnAll();
		$('#about-menu-c').addClass('on');
		$('#location-c').html('通信知识');
		var viewHTML='<ul class="cm-new-list-ul">';
		var viewHTMLm='';
		var viewHTMLpage='<div class="cm-page"><ul class="honor-detail-tt">';
		var pageTMP='';
		var i=0;
		getMenuC.map(function(item,data){
			if(i<MAX_COUNT_ITEM){
				viewHTMLm+='<li><a href="?a=3&b='+item.aab101+'"><span>'+item.aab102+'</span><i>'+item.aab112+'</i></a></li>';	
			}
			i++;
		});
		var count=getMenuC.length;
		var pageNum=Math.ceil(count/MAX_COUNT_ITEM);
		for(var a=0;a<pageNum;a++){
			if(a==0){
				pageTMP+='<li class="on"><a href="?a=3&pg=1">1</a></li>';
			}else{
				var ia=a+1;
				pageTMP+='<li><a href="?a=3&pg='+ia+'">'+ia+'</a></li>';
			}
		}
		$('.cm-content').html(viewHTML+viewHTMLm+'</ul>'+viewHTMLpage+pageTMP+'</ul></div>');
	});
	
	$('#about-menu-d').click(function(){
		removeClassOnAll();
		$('#about-menu-d').addClass('on');
		$('#location-c').html('企业公告');
		var viewHTML='<ul class="cm-new-list-ul">';
		var viewHTMLm='';
		var viewHTMLpage='<div class="cm-page"><ul class="honor-detail-tt">';
		var pageTMP='';
		var i=0;
		getMenuD.map(function(item,data){
			if(i<MAX_COUNT_ITEM){
				viewHTMLm+='<li><a href="?a=4&b='+item.aab101+'"><span>'+item.aab102+'</span><i>'+item.aab112+'</i></a></li>';	
			}
			i++;
		});
		var count=getMenuD.length;
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
	var ma=getQueryString('a');
	var mb=getQueryString('b');
	if(ma==MENU_TAB_A&&mb!=null){
		//点击公司动态详情
		var viewHTMLc='';
		var getMenuA1={};
		$.getJSON(getNewUrl+1,function(data){
			getMenuA1=data.projectsList;
			$('#about-menu-a').addClass('on');
			$('#location-c').html('公司动态');
			getMenuA1.map(function(item,data){
				if(item.aab101==mb){
					var pre=Math.floor(mb)-1;
					var nex=Math.floor(mb)+1;
					var pageHTMLc='';
					var pageCount=Math.ceil(item.length/MAX_COUNT_ITEM);
					if(pre<1){
						pageHTMLc='<div class="cm-page"><ul><li class="item"><a href="?a='+MENU_TAB_A+'&b=1">上一页</a></li>';
					}else{
						pageHTMLc='<div class="cm-page"><ul><li class="item"><a href="?a='+MENU_TAB_A+'&b='+pre+'">下一页</a></li>';
					}
					if(nex>pageCount){
						pageHTMLc+='<li class="item"><a href="?a='+MENU_TAB_A+'&b='+pageCount+'">上一页</a></li>';
					}else{
						pageHTMLc+='<li class="item"><a href="?a='+MENU_TAB_A+'&b='+nex+'">下一页</a></li>';
					}
					//console.log(viewHTMLc);
					viewHTMLc=viewHTMLc+'<div class="cm-content-title">'+item.aab102+'</div><p align="right">'+item.aab112+'</p><br />'+item.aab103+'<img src="'+imgUrlfix+item.aab109+'"><br /><br /><img src="'+imgUrlfix+item.aab110+'"><br /><br />'+item.aab104+'<img src="'+imgUrlfix+item.aab111+'"><br />'+pageHTMLc+'</ul></div>';
					$('.cm-content').html(viewHTMLc);
				}
			});
		});
	}
	if(ma==MENU_TAB_B&&mb!=null){
		//点击行业新闻详情
		var viewHTMLc='';
		var getMenuB1={};
		$.getJSON(getNewUrl+MENU_TAB_B,function(data){
			getMenuB1=data.projectsList;
			$('#about-menu-b').addClass('on');
			$('#location-c').html('行业新闻');
			getMenuB1.map(function(item,data){
				if(item.aab101==mb){
					var pre=Math.floor(mb)-1;
					var nex=Math.floor(mb)+1;
					var pageHTMLc='';
					var pageCount=Math.ceil(item.length/MAX_COUNT_ITEM);
					if(pre<1){
						pageHTMLc='<div class="cm-page"><ul><li class="item"><a href="?a='+MENU_TAB_B+'&b=1">上一页</a></li>';
					}else{
						pageHTMLc='<div class="cm-page"><ul><li class="item"><a href="?a='+MENU_TAB_B+'&b='+pre+'">下一页</a></li>';
					}
					if(nex>pageCount){
						pageHTMLc+='<li class="item"><a href="?a='+MENU_TAB_B+'&b='+pageCount+'">上一页</a></li>';
					}else{
						pageHTMLc+='<li class="item"><a href="?a='+MENU_TAB_B+'&b='+nex+'">下一页</a></li>';
					}
					//console.log(viewHTMLc);
					viewHTMLc=viewHTMLc+'<div class="cm-content-title">'+item.aab102+'</div><p align="right">'+item.aab112+'</p><br />'+item.aab103+'<img src="'+imgUrlfix+item.aab109+'"><br /><br /><img src="'+imgUrlfix+item.aab110+'"><br /><br />'+item.aab104+'<img src="'+imgUrlfix+item.aab111+'"><br />'+pageHTMLc+'</ul></div>';
					$('.cm-content').html(viewHTMLc);
				}
			});
		});
	}
	if(ma==MENU_TAB_C&&mb!=null){
		//点击通信知识详情
		var viewHTMLc='';
		var getMenuC1={};
		$.getJSON(getNewUrl+MENU_TAB_C,function(data){
			getMenuC1=data.projectsList;
			$('#about-menu-c').addClass('on');
			$('#location-c').html('通信知识');
			getMenuC1.map(function(item,data){
				if(item.aab101==mb){
					var pre=Math.floor(mb)-1;
					var nex=Math.floor(mb)+1;
					var pageHTMLc='';
					var pageCount=Math.ceil(item.length/MAX_COUNT_ITEM);
					if(pre<1){
						pageHTMLc='<div class="cm-page"><ul><li class="item"><a href="?a='+MENU_TAB_C+'&b=1">上一页</a></li>';
					}else{
						pageHTMLc='<div class="cm-page"><ul><li class="item"><a href="?a='+MENU_TAB_C+'&b='+pre+'">下一页</a></li>';
					}
					if(nex>pageCount){
						pageHTMLc+='<li class="item"><a href="?a='+MENU_TAB_C+'&b='+pageCount+'">上一页</a></li>';
					}else{
						pageHTMLc+='<li class="item"><a href="?a='+MENU_TAB_C+'&b='+nex+'">下一页</a></li>';
					}
					//console.log(viewHTMLc);
					viewHTMLc=viewHTMLc+'<div class="cm-content-title">'+item.aab102+'</div><p align="right">'+item.aab112+'</p><br />'+item.aab103+'<img src="'+imgUrlfix+item.aab109+'"><br /><br /><img src="'+imgUrlfix+item.aab110+'"><br /><br />'+item.aab104+'<img src="'+imgUrlfix+item.aab111+'"><br />'+pageHTMLc+'</ul></div>';
					$('.cm-content').html(viewHTMLc);
				}
			});
		});
	}
	if(ma==MENU_TAB_D&&mb!=null){
		//点击企业公告详情
		var viewHTMLc='';
		var getMenuD1={};
		$.getJSON(getNewUrl+MENU_TAB_D,function(data){
			getMenuD1=data.projectsList;
			$('#about-menu-d').addClass('on');
			$('#location-c').html('企业公告');
			getMenuD1.map(function(item,data){
				if(item.aab101==mb){
					var pre=Math.floor(mb)-1;
					var nex=Math.floor(mb)+1;
					var pageHTMLc='';
					var pageCount=Math.ceil(item.length/MAX_COUNT_ITEM);
					if(pre<1){
						pageHTMLc='<div class="cm-page"><ul><li class="item"><a href="?a='+MENU_TAB_D+'&b=1">上一页</a></li>';
					}else{
						pageHTMLc='<div class="cm-page"><ul><li class="item"><a href="?a='+MENU_TAB_D+'&b='+pre+'">下一页</a></li>';
					}
					if(nex>pageCount){
						pageHTMLc+='<li class="item"><a href="?a='+MENU_TAB_D+'&b='+pageCount+'">上一页</a></li>';
					}else{
						pageHTMLc+='<li class="item"><a href="?a='+MENU_TAB_D+'&b='+nex+'">下一页</a></li>';
					}
					viewHTMLc=viewHTMLc+'<div class="cm-content-title">'+item.aab102+'</div><p align="right">'+item.aab112+'</p><br />'+item.aab103+'<img src="'+imgUrlfix+item.aab109+'"><br /><br /><img src="'+imgUrlfix+item.aab110+'"><br /><br />'+item.aab104+'<img src="'+imgUrlfix+item.aab111+'"><br />'+pageHTMLc+'</ul></div>';
					$('.cm-content').html(viewHTMLc);
				}
			});
		});
	}
	function removeClassOnAll(){
		$('#about-menu-a').removeClass('on');
		$('#about-menu-b').removeClass('on');
		$('#about-menu-c').removeClass('on');
		$('#about-menu-d').removeClass('on');
	}
})