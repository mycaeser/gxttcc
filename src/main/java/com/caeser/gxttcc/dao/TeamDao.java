package com.caeser.gxttcc.dao;

import java.util.List;

import com.caeser.gxttcc.entity.Team;

public interface TeamDao {
	/**
	 * 返回全部团队管理数据
	 * @return
	 */
	List<Team> queryTeam();
}
