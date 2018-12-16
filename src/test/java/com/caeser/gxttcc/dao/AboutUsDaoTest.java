package com.caeser.gxttcc.dao;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.caeser.gxttcc.BaseTest;
import com.caeser.gxttcc.entity.AboutUs;
import com.caeser.gxttcc.entity.Brief;

public class AboutUsDaoTest extends BaseTest{
	@Autowired
	private AboutUsDao aboutUsDao;
	@Autowired
	private BriefDao briefDao;
	
	@Test
	@Ignore
	public void testQueryAboutUsDao() {
		AboutUs aboutUs=aboutUsDao.queryAboutUs();
		System.out.println("内容："+aboutUs.getAaa202());
	}
	@Test
	public void testUpdaeBriefDao() {
		Brief brief=new Brief();
		brief.setAaa102("1");
		int effectedNum=briefDao.updateBrief(brief);
		System.out.println(effectedNum);
	}
}
