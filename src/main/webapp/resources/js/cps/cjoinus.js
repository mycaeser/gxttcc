/**
 * 
 */
$(function(){
	var getAddressOtUrl='/gxttcc/frontend/getaddressot';//分公司
	var getAddressUrl='/gxttcc/frontend/getaddress?addid=';//主公司
	var outHtml1='<tr><th>编号</th><th>名称</th><th>地址</th><th>联系人</th><th>电话</th><th>编辑</th></tr><tr>';
	var outHtml2='';
	$.getJSON(getAddressOtUrl,function(data){
		var newsObj=data.addList;
		var numa=1;
		newsObj.map(function(item,data){
			outHtml2+='<tr><td>'+numa+'</td><td>'+item.addName+'</td><td>'+item.addContent+'</td><td>'+item.addPhoneOwner+'</td><td>'+item.addPhoneNumber+'</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="changeToInput('+numa+')" >编辑</a></td></tr>';
			numa++;
		});//
		$('#hr-part').html('<a href="/gxttcc/addarticlemodel/addteam?a=1" >新增地址</a><br /><br />'+outHtml1+outHtml2);
		
	});
	var outHtml3='<tr><th>网站名称</th><th>备案号</th><th>邮箱</th><th>编辑</th></tr><tr>';
	var outHtml4='';
	
	$.getJSON(getAddressUrl+1,function(data){
		var newsObj=data.add;
		
		outHtml4='<tr><td>'+newsObj.addName+'</td><td>'+newsObj.addContent+'</td><td>'+newsObj.addEmail+'</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="changeToInputA(1)" >编辑</a></td></tr>';
		
		$('#bot-index').html(outHtml3+outHtml4);
	});
	var outHtml5='<tr><th>公司名</th><th>地址</th><th>联系人</th><th>电话</th><th>编辑</th></tr><tr>';
	var outHtml6='';
	$.getJSON(getAddressUrl+2,function(data){
		var newsObj1=data.add;
		outHtml6='<tr><td>'+newsObj1.addName+'</td><td>'+newsObj1.addContent+'</td><td>'+newsObj1.addPhoneOwner+'</td>><td>'+newsObj1.addPhoneNumber+'</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="changeToInputA(2)" >编辑</a></td></tr>';
		//$('#bot-part').html(outHtml1+outHtml2);
		$.getJSON(getAddressUrl+3,function(data){
			var newsObj2=data.add;
			outHtml6+='<tr><td>'+newsObj2.addName+'</td><td>'+newsObj2.addContent+'</td><td>'+newsObj2.addPhoneOwner+'</td>><td>'+newsObj2.addPhoneNumber+'</td><td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="#" onclick="changeToInputA(3)" >编辑</a></td></tr>';
			$('#bot-part').html(outHtml5+outHtml6);
		});
	});
	
})
function changeToInput(id){
	id=id+1;
	var ROW_NUM=4;
			var oneRow=document.getElementById('hr-part').rows[id].cells;
			var b=(id-2)*ROW_NUM+1;
			var updateAddressUrl='/gxttcc/addresscc/updateadress';
			for(var a=0;a<oneRow.length-2;a++){
				var inputHtmlLength='80px';
				if((a+1)==2){
					inputHtmlLength='230px';
				}
				oneRow[a+1].innerHTML='<input value="'+oneRow[a+1].innerHTML+'" style="width:'+inputHtmlLength+'" id="upTableRow'+(b++)+'" type="text">';
			}
			oneRow[oneRow.length-1].innerHTML='<a href="#" id="finish'+id+'" >完成</a>';
			document.getElementById('finish'+id).onclick=function(){
				var fixNum=(id-2)*4+1;
				var formData=new FormData();
				for(var d=1;d<5;d++){
					var tmpC=document.getElementById('upTableRow'+fixNum).value;
					formData.append('inputC'+d,tmpC);
					fixNum++;
				}
				formData.append('id',id+2);//当前分公司地址前面还有三条信息
				$.ajax({
					url : updateAddressUrl,
					type : 'POST',
					data : formData,
					contentType : false,
					processData : false,
					cache : false,
					success : function(data) {
						self.location='/gxttcc/cps/joinusadmin';
					}
				});
			   }
		}
function changeToInputA(id){
	var oneRow={};
	var updateAddressUrl='/gxttcc/addresscc/updateadress';
	if(id==1){
		oneRow=document.getElementById('bot-index').rows[id+1].cells;
		for(var i=0;i<3;i++){
			oneRow[i].innerHTML='<input value="'+oneRow[i].innerHTML+'" style="width:120px" id="botindex'+i+'" type="text">';
		}
		oneRow[oneRow.length-1].innerHTML='<a href="#" id="finisha'+id+'" >完成</a>';
		document.getElementById('finisha'+id).onclick=function(){
			var formData=new FormData();
			var tmpC0=document.getElementById('botindex0').value;
			var tmpC1=document.getElementById('botindex1').value;
			var tmpC2=document.getElementById('botindex2').value;
			formData.append('inputC1',tmpC0);
			formData.append('inputC2',tmpC1);
			formData.append('inputC3',tmpC2);
			formData.append('id',id);//当前分公司地址前面还有三条信息
			$.ajax({
				url : updateAddressUrl,
				type : 'POST',
				data : formData,
				contentType : false,
				processData : false,
				cache : false,
				success : function(data) {
					self.location='/gxttcc/cps/joinusadmin';
				}
			});
		}
	}else{
		if(id==2){
			oneRow=document.getElementById('bot-part').rows[id].cells;
			for(var i=0;i<4;i++){
				oneRow[i].innerHTML='<input value="'+oneRow[i].innerHTML+'" style="width:80px" id="botparta'+i+'" type="text">';
			}
			oneRow[oneRow.length-1].innerHTML='<a href="#" id="finish'+id+'" >完成</a>';
		}
		if(id==3){
			oneRow=document.getElementById('bot-part').rows[id].cells;
			for(var i=0;i<4;i++){
				oneRow[i].innerHTML='<input value="'+oneRow[i].innerHTML+'" style="width:80px" id="botpartb'+i+'" type="text">';
			}
			oneRow[oneRow.length-1].innerHTML='<a href="#" id="finish'+id+'" >完成</a>';
		}
		document.getElementById('finish'+id).onclick=function(){
			var formData=new FormData();
			for(var j=0;j<4;j++){
				var tmpC={};
				if(id==2){
					tmpC=document.getElementById('botparta'+j).value;
				}else{
					tmpC=document.getElementById('botpartb'+j).value;
				}
				formData.append('inputC'+(j+1),tmpC);
			}
			formData.append('id',id);//
			$.ajax({
				url : updateAddressUrl,
				type : 'POST',
				data : formData,
				contentType : false,
				processData : false,
				cache : false,
				success : function(data) {
					self.location='/gxttcc/cps/joinusadmin';
				}
			});
		}
	}
	
	
	
}