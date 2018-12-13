package com.caeser.gxttcc.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.caeser.gxttcc.BaseTest;
import com.caeser.gxttcc.entity.Projects;

public class ProjectsDaoTest extends BaseTest{
	@Autowired
	private ProjectsDao projectsDao;
	
	@Test
	public void testqueryIndexTabContentByType() {
		List<Projects> projectsList=projectsDao.queryIndexTabContentByType(1);
		System.out.println(projectsList.size());
	}
}
