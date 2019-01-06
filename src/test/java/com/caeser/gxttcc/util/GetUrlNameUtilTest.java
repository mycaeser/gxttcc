package com.caeser.gxttcc.util;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.caeser.gxttcc.BaseTest;
import com.caeser.gxttcc.dao.ImgUrlDao;
import com.caeser.gxttcc.entity.ImgUrl;

public class GetUrlNameUtilTest extends BaseTest{
	@Autowired
	private ImgUrlDao imgUrlDao;
	@Test
	public void testQueryUrlName() {
		ImgUrl testImgUrl=new ImgUrl();
		ImgUrl imgUrl=imgUrlDao.queryUrl();
		String tmp=GetUrlNameUtil.getUrlName(imgUrl);
		System.out.println(tmp);
		testImgUrl.setImgUrlName(tmp+".jpg");
		int effectedNum=imgUrlDao.updateUrlA(testImgUrl);
		System.out.println("num="+effectedNum);
	}
}
