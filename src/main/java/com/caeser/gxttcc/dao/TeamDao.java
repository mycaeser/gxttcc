package com.caeser.gxttcc.dao;

import java.util.List;

import com.caeser.gxttcc.entity.Team;

public interface TeamDao {
	/**
	 * 返回某一条团队管理数据
	 * @return
	 */
	Team queryTeamById(int id);
	/**
	 * 返回全部团队管理数据
	 * @return
	 */
	List<Team> queryTeam();
	/**
	 * 删除某条团队管理数据
	 * @param id
	 * @return
	 */
	int deleteTeamById(int id);
	/**
	 * 更新某条团队管理
	 * @param team
	 * @return
	 */
	int updateTeam(Team team);
	/**
	 * 插入一条管理团队数据
	 * @param team
	 * @return
	 */
	int insertTeam(Team team);
}
