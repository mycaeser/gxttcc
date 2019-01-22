/**
 * 
 */
$(function() {
	//获得简介内容URL
	var getBriefIntroductionUrl='/gxttcc/frontend/getbriefintroduction';
	//获得主页tab四个图片URL
	var getTabUrl='/gxttcc/frontend/getindextabimg?tabid=';
	//获得主页tab四个标签内容标题、日期
	var getTabContent='/gxttcc/frontend/getindextabcontent?type=';
	var urlToresource='/';
	var getHonorUrl='/gxttcc/frontend/gethonor';
	var getBottomUrl='/gxttcc/frontend/getbottoma?bottomaid=';
	//荣誉资质页面标志
	var HONOR_PAGE_TAB=4;
	$.getJSON(getBriefIntroductionUrl,function(data){
		var briefIntroduction=data.briefIntroduction;
		$('.index-new-about').children('h1').html(briefIntroduction);
	});
	var urlToresource='/';
	var tabName='.int';
	$.getJSON(getTabUrl+1,function(data){
		var imgUrl=data.tabUrl;
		$('.int1').html('<img src="'+urlToresource+imgUrl+'" />');
	});
	$.getJSON(getTabUrl+2,function(data){
		var imgUrl=data.tabUrl;
		$('.int2').html('<img src="'+urlToresource+imgUrl+'" />');
	});
	$.getJSON(getTabUrl+3,function(data){
		var imgUrl=data.tabUrl;
		$('.int3').html('<img src="'+urlToresource+imgUrl+'" />');
	});
	$.getJSON(getTabUrl+4,function(data){
		var imgUrl=data.tabUrl;
		$('.int4').html('<img src="'+urlToresource+imgUrl+'" />');
	});
	var contentName=new Array('.int11','.int21','.int31','.int41');
	//公司动态
	$.getJSON(getTabContent+1,function(data){
		var viewhtml='';
		for(var i=0;i<data.projectsList.length;i++){
			var typeid=data.projectsList[i].aab101;
			var title=data.projectsList[i].aab102;
			var datet=data.projectsList[i].aab112;
			viewhtml=viewhtml+'<li><a href="/gxttcc/newstt/index?a=1&b='+
				typeid
				+'"><div class="am-u-sm-8 am-u-md-8 am-u-lg-8"><strong class="f-toe">'+
				title
				+'</strong></div><div class="am-u-sm-4 am-u-md-4 am-u-lg-4"><span>'+
				datet
				+'</span></div></a></li>';
		}
		$('.int11').children('ul').html(viewhtml);
	});
	//行业新闻
	$.getJSON(getTabContent+2,function(data){
		var viewhtml='';
		for(var i=0;i<data.projectsList.length;i++){
			var typeid=data.projectsList[i].aab101;
			var title=data.projectsList[i].aab102;
			var datet=data.projectsList[i].aab112;
			var ifview=data.projectsList[i].aab114;
			//如果aab114为0表示不显示在页面上
			if(ifview==0)
				continue;
			viewhtml=viewhtml+'<li><a href="/gxttcc/newstt/index?a=2&b='+
				typeid
				+'"><div class="am-u-sm-8 am-u-md-8 am-u-lg-8"><strong class="f-toe">'+
				title
				+'</strong></div><div class="am-u-sm-4 am-u-md-4 am-u-lg-4"><span>'+
				datet
				+'</span></div></a></li>';
		}
		$('.int21').children('ul').html(viewhtml);
	});
	//通信知识
	$.getJSON(getTabContent+3,function(data){
		var viewhtml='';
		for(var i=0;i<data.projectsList.length;i++){
			var typeid=data.projectsList[i].aab101;
			var title=data.projectsList[i].aab102;
			var datet=data.projectsList[i].aab112;
			var ifview=data.projectsList[i].aab114;
			//如果aab114为0表示不显示在页面上
			if(ifview==0)
				continue;
			viewhtml=viewhtml+'<li><a href="/gxttcc/newstt/index?a=3&b='+
				typeid
				+'"><div class="am-u-sm-8 am-u-md-8 am-u-lg-8"><strong class="f-toe">'+
				title
				+'</strong></div><div class="am-u-sm-4 am-u-md-4 am-u-lg-4"><span>'+
				datet
				+'</span></div></a></li>';
		}
		$('.int31').children('ul').html(viewhtml);
	});
	//企业公告
	$.getJSON(getTabContent+4,function(data){
		var viewhtml='';
		for(var i=0;i<data.projectsList.length;i++){
			var typeid=data.projectsList[i].aab101;
			var title=data.projectsList[i].aab102;
			var datet=data.projectsList[i].aab112;
			var ifview=data.projectsList[i].aab114;
			//如果aab114为0表示不显示在页面上
			if(ifview==0)
				continue;
			viewhtml=viewhtml+'<li><a href="/gxttcc/newstt/index?a=4&b='+
				typeid
				+'"><div class="am-u-sm-8 am-u-md-8 am-u-lg-8"><strong class="f-toe">'+
				title
				+'</strong></div><div class="am-u-sm-4 am-u-md-4 am-u-lg-4"><span>'+
				datet
				+'</span></div></a></li>';
		}
		$('.int41').children('ul').html(viewhtml);
	});
	//荣誉资质
	$.getJSON(getHonorUrl,function(data){
		var honorList=data.honorList;
		var viewHTML='';
		honorList.map(function(item,index){
			if(item.aaa506!=0)
				viewHTML+='<li data-thumb-alt="" style="width: 200px; margin-right: 5px; float: left; display: block;"><a href="/gxttcc/companygx/index?a='+HONOR_PAGE_TAB+'&c='+item.aaa501+'"><img src="'+urlToresource+item.aaa503+'" /><span class="f-toe">'+item.aaa502+'</span></a></li>';
		});
		$('.honortt').children('.am-viewport').children('ul').html(viewHTML);
	});
	//案例细读
	$.getJSON(getBottomUrl+1,function(data){
		var bottoma=data.newsList;
		var viewHTML='';
		bottoma.map(function(item,index){
			viewHTML+='<li><a href="/gxttcc/projectstt/index?a='+item.aac115+'&b='+item.aac101+'"><div class="am-u-sm-8 am-u-md-8 am-u-lg-8"><strong class="f-toe">'+
			item.aac102
			+'</strong></div><div class="am-u-sm-4 am-u-md-4 am-u-lg-4"><span>'+
			item.aac112
			+'</span></div></a></li>';
		});
		$('#botta').children('ul').html(viewHTML);
	});
	//阅读精选
	$.getJSON(getBottomUrl+2,function(data){
		var bottoma=data.newsList;
		var viewHTML='';
		bottoma.map(function(item,index){
			viewHTML+='<li><a href="/gxttcc/projectstt/hr?a=2&b='+item.aac101
			+'"><div class="am-u-sm-8 am-u-md-8 am-u-lg-8"><strong class="f-toe">'+
			item.aac102
			+'</strong></div><div class="am-u-sm-4 am-u-md-4 am-u-lg-4"><span>'+
			item.aac112
			+'</span></div></a></li>';
		});
		$('#bottb').children('ul').html(viewHTML);
	});
})