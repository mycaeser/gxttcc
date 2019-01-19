package com.caeser.gxttcc.dao;

import java.util.List;

import com.caeser.gxttcc.entity.News;
import com.caeser.gxttcc.entity.ProjectsType;

public interface ProjectsTypeDao {
	/**
	 * 查询全部工程类型
	 * @return
	 */
	List<ProjectsType> queryProjectsType();
	/**
	 * 新增一篇工程文章
	 * @param news
	 * @return
	 */
	int insertOneProjects(News news);
	/**
	 * 删除一篇工程文章
	 * @param id
	 * @return
	 */
	int deleteOneProjectsById(int id);
}
