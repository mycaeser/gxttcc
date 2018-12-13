function getWordsCnt(str){ 
    var n = 0;  
    for(var i=0;i<str1.length;i++){  
         var ch = str1.charCodeAt(i);  
         if(ch > 255){ // 中文字符集  
             n+=2;  
         }else{  
             n++;  
         }  
    }  
    return n;  
}
function CountChar(str){
	var str1=str.textareacp1.value; 
	alert("当前汉字数量"+getWordsCnt(str1));
}