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
	/**
	 * 根据ID删除一个荣誉资质
	 * @param id
	 * @return
	 */
	int deleteHonerById(int id);
	/**
	 * 根据ID查询一条荣誉资质
	 * @param id
	 * @return
	 */
	Honor queryHonorById(int id);
	/**
	 * 根据ID更新一条荣誉资质
	 * @param honor
	 * @return
	 */
	int updateHonor(Honor honor);
}
