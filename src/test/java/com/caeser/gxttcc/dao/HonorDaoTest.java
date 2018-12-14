package com.caeser.gxttcc.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.caeser.gxttcc.BaseTest;
import com.caeser.gxttcc.entity.Honor;

public class HonorDaoTest extends BaseTest{
	@Autowired
	private HonorDao honorService;
	
	@Test
	public void testQueryHonor() {
		List<Honor> honorList=honorService.queryHonor();
		System.out.println(honorList);
	}
	
}
