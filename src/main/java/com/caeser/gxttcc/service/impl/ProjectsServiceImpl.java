package com.caeser.gxttcc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caeser.gxttcc.dao.ProjectsDao;
import com.caeser.gxttcc.entity.Projects;
import com.caeser.gxttcc.service.ProjectsService;

@Service
public class ProjectsServiceImpl implements ProjectsService{
	@Autowired
	private ProjectsDao projectsDao;
	
	public Projects queryIndexTabImgById(int id) {
		Projects projects=projectsDao.queryIndexTabImgById(id);
		return projects;
	}
	
	public List<Projects> queryIndexTabContentByType(int type){
		List<Projects> projectsList=projectsDao.queryIndexTabContentByType(type);
		return projectsList;
	}
}
