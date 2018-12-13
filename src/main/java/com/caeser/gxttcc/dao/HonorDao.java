package com.caeser.gxttcc.dao;

import java.util.List;

import com.caeser.gxttcc.entity.Honor;

public interface HonorDao {
	/**
	 * 查询首页显示的5个荣誉资质
	 * @return
	 */
	List<Honor> queryHonor();
	/**
	 * 查询全部
	 * @return
	 */
	List<Honor> queryHonorAll();
}
