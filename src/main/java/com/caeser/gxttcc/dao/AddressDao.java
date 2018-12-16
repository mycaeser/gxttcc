package com.caeser.gxttcc.dao;

import java.util.List;

import com.caeser.gxttcc.entity.Address;

public interface AddressDao {
	/**
	 * 通过主键查询地址内容
	 * @param id
	 * @return
	 */
	Address queryAddByID(int id);
	/**
	 * 查询分公司各个地址内容
	 * @return
	 */
	List<Address> queryAddot();
}
