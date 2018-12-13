package com.caeser.gxttcc.dao;

import java.util.List;

import com.caeser.gxttcc.entity.Projects;

public interface ProjectsDao {
	/**
	 * 查询首页四个小标签 公司动态、行业新闻、通信知识、企业公告
	 * @return
	 */
	Projects queryIndexTabImgById(int id);
	/**
	 * 查询首页四个小标签里的内容标题 日期
	 * @param id
	 * @return
	 */
	List<Projects> queryIndexTabContentByType(int type);
}
