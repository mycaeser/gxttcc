package com.caeser.gxttcc.dao;


import com.caeser.gxttcc.entity.LocalAuth;

public interface LocalAuthDao {
	/**
	 * 通过帐号和密码查询对应信息，登录用
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	LocalAuth queryLocalByUserNameAndPwd(String username,  String password);
}
