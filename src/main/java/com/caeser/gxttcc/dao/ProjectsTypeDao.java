package com.caeser.gxttcc.dao;

import java.util.List;

import com.caeser.gxttcc.entity.ProjectsType;

public interface ProjectsTypeDao {
	/**
	 * 查询全部工程类型
	 * @return
	 */
	List<ProjectsType> queryProjectsType();
}
