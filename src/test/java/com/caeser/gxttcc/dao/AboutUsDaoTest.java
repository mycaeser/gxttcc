package com.caeser.gxttcc.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.caeser.gxttcc.BaseTest;
import com.caeser.gxttcc.entity.AboutUs;

public class AboutUsDaoTest extends BaseTest{
	@Autowired
	private AboutUsDao aboutUsDao;
	
	@Test
	public void testQueryAboutUsDao() {
		AboutUs aboutUs=aboutUsDao.queryAboutUs();
		System.out.println("内容："+aboutUs.getAaa202());
	}
}
