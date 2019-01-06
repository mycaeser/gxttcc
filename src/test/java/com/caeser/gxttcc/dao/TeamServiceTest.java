package com.caeser.gxttcc.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.caeser.gxttcc.BaseTest;
import com.caeser.gxttcc.entity.Team;
import com.caeser.gxttcc.service.TeamService;

public class TeamServiceTest extends BaseTest{
	@Autowired
	private TeamService teamService;
	@Test
	public void testInsertTeamDao() {
		Team testTeam=new Team();
		testTeam.setAaa402("tst");
		testTeam.setAaa404("2019-1-6");
		teamService.insertTeam(testTeam);
	}
}
