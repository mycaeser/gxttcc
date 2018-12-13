package com.caeser.gxttcc.dao;

import java.util.List;

import com.caeser.gxttcc.entity.News;

public interface NewsDao {
	/**
	 * 通过aac113查询主页显示的案例和招聘信息
	 * @return
	 */
	List<News> queryIndexBottomItems(int type);
}
