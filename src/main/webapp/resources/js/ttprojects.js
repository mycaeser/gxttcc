/**
 * 
 */
$(function(){
	var getProjectsUrl='/gxttcc/projectstt/getprojects?typeid=';
	var MAX_COUNT_ITEM=8;
	var imgUrlfix='../resources/';
	var htmlLabel=['#about-menu-a','#about-menu-b','#about-menu-c','#about-menu-d','#about-menu-e','#about-menu-f','#about-menu-g','#about-menu-h'];
	var getMenuA={};
	var getMenuB={};
	var getMenuC={};
	var ma=getQueryString('a');
	var mb=getQueryString('b');
	var md=getQueryString('pg');
	for(var i=0;i<htmlLabel.length;i++){
		aboutMenuType(htmlLabel[i],i+1);
		detailType(htmlLabel[i],'案例细读',i+1);
		goPage(i+1);
	}
	function removeClassOnAll(){
		for(var j=0;j<htmlLabel.length;j++){
			$(htmlLabel[j]).removeClass('on');
		}
	}
	function aboutMenuType(clickType,whichID){
		$(clickType).click(function(){
			$.getJSON(getProjectsUrl+whichID,function(data){
				getMenuA=data.newsList;
				removeClassOnAll();
				$(clickType).addClass('on');
				var viewHTML='<ul class="cm-new-list-ul">';
				var viewHTMLm='';
				var viewHTMLpage='<div class="cm-page"><ul class="honor-detail-tt">';
				var pageTMP='';
				var i=0;
				getMenuA.map(function(item,data){
					if(i<MAX_COUNT_ITEM){
						viewHTMLm+='<li><a href="?a='+whichID+'&b='+item.aac101+'"><span>'+item.aac102+'</span><i>'+item.aac112+'</i></a></li>';	
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
						pageTMP+='<li><a href="?a='+whichID+'&pg='+ia+'">'+ia+'</a></li>';
					}
				}
				$('.cm-content').html(viewHTML+viewHTMLm+'</ul>'+viewHTMLpage+pageTMP+'</ul></div>');
			});
		});
	}
	function detailType(classOnStr,localStr,whichID){
		if(ma==whichID&&mb!=null){
			var viewHTMLc='';
			var getIDarr=new Array();
			var IDi=0;
			$.getJSON(getProjectsUrl+whichID,function(data){
				getMenuB=data.newsList;
				$(classOnStr).addClass('on');
				$('#location-c').html(localStr);
				getMenuB.map(function(item,date){
					getIDarr[IDi]=item.aac101;
					IDi++;
				});
				IDi=0;
				getMenuB.map(function(item,data){
					if(item.aac101==mb){
						var pre=vuleToID(getIDarr,mb)-1;
						var nex=vuleToID(getIDarr,mb)+1;
						var pageHTMLc='';
						var pageCount=Math.ceil(item.length/MAX_COUNT_ITEM);
						if(pre<0){
							pageHTMLc='<div class="cm-page"><ul><li class="disabled"><a>上一页</a></li>';
						}else{
							pageHTMLc='<div class="cm-page"><ul><li class="item"><a href="?a='+whichID+'&b='+getIDarr[pre]+'">上一页</a></li>';
						}
						if(nex>getIDarr.length-1){
							pageHTMLc+='<li class="disabled"><a>下一页</a></li>';
						}else{
							pageHTMLc+='<li class="item"><a href="?a='+whichID+'&b='+getIDarr[nex]+'">下一页</a></li>';
						}
						viewHTMLc=viewHTMLc+'<div class="cm-content-title">'+item.aac102+'</div><p align="right">'+item.aac112+'</p><br />'+item.aac103+'<img src="'+imgUrlfix+item.aac109+'"><br />'+item.aac103+'<br /><img src="'+imgUrlfix+item.aac110+'"><br /><br />'+item.aac105+'<img src="'+imgUrlfix+item.aac111+'"><br />'+pageHTMLc+'</ul></div>';
						$('.cm-content').html(viewHTMLc);
					}
				});
			});
		}
	}
	function goPage(whichID){
		if(ma==whichID&&md!=null&&md!=''){
			var viewHTMLd='';
			var i=1;
			var startPage=(Math.floor(md)-1)*MAX_COUNT_ITEM+1;//计算当前页码的开始ID
			var finalPage=Math.floor(md)*MAX_COUNT_ITEM;//计算当前页码的结束ID
			var viewHTML='<ul class="cm-new-list-ul">';
			var viewHTMLm='';
			var viewHTMLpage='<div class="cm-page"><ul class="honor-detail-tt">';
			var pageTMP='';
			var count=0;
			$.getJSON(getProjectsUrl+whichID,function(data){
				getMenuC=data.newsList;
				count=getMenuC.length;
				getMenuC.map(function(item,data){
					if(i>startPage-1&&i<finalPage+1){//输出当前页码的条目
						viewHTMLm+='<li><a href="?a='+whichID+'&b='+item.aac101+'"><span>'+item.aac102+'</span><i>'+item.aac112+'</i></a></li>';	
					}
					i++;
				});
				var pageNum=Math.ceil(count/MAX_COUNT_ITEM);//计算总页数
				for(var a=0;a<pageNum;a++){
					var ia=a+1;
					if(ia==md){//当前加标签class on
						pageTMP+='<li class="on"><a href="?a='+whichID+'&pg='+ia+'">'+ia+'</a></li>';
					}else{
						pageTMP+='<li><a href="?a='+whichID+'&pg='+ia+'">'+ia+'</a></li>';
					}
				}
				$('.cm-content').html(viewHTML+viewHTMLm+'</ul>'+viewHTMLpage+pageTMP+'</ul></div>');
			});	
		}
	}
})