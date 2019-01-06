package com.caeser.gxttcc.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.caeser.gxttcc.BaseTest;
import com.caeser.gxttcc.entity.Team;

public class TeamDaoTest extends BaseTest{
	@Autowired
	private TeamDao teamDao;
	@Test
	public void testInsertTeamDao() {
		Team testTeam=new Team();
		testTeam.setAaa402("1");
		testTeam.setAaa403("1");
		testTeam.setAaa404("2019-1-6");
		int effectedNum=teamDao.insertTeam(testTeam);
		System.out.println("num="+effectedNum);
	}
}
