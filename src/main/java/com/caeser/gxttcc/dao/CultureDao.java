package com.caeser.gxttcc.dao;

import com.caeser.gxttcc.entity.Culture;

public interface CultureDao {
	
	/**
	 * 返回企业文化内容
	 * @return
	 */
	Culture queryCulture();
	/**
	 * 修改企业文化
	 * @return
	 */
	int updateCulture(Culture culture);
}
