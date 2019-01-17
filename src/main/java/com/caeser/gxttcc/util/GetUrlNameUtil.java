package com.caeser.gxttcc.util;

import com.caeser.gxttcc.entity.ImgUrl;


public class GetUrlNameUtil {
	
	public static  String getUrlName(ImgUrl target) {
		String nm="";
		String str1="";
		String str2="";
		int tmpint=0;
		ImgUrl imgUrl=target;
		nm=imgUrl.getImgUrlName();
		nm=nm.substring(0,nm.lastIndexOf("."));
		for(int i=0;i<nm.length();i++){
			if(nm.charAt(i)>=48 && nm.charAt(i)<=57){
				str1+=nm.charAt(i);
			}else {
				str2+=nm.charAt(i);
			}
		}
		tmpint=Integer.parseInt(str1.toString());
		tmpint++;
		return str2+tmpint;
	}
	public static  String getUrlNameA(String target) {
		String nm="";
		String str1="";
		String str2="";
		int tmpint=0;
		nm=target;
		nm=nm.substring(0,nm.length());
		for(int i=0;i<nm.length();i++){
			if(nm.charAt(i)>=48 && nm.charAt(i)<=57){
				str1+=nm.charAt(i);
			}else {
				str2+=nm.charAt(i);
			}
		}
		tmpint=Integer.parseInt(str1.toString());
		tmpint++;
		return str2+tmpint;
	}
}
