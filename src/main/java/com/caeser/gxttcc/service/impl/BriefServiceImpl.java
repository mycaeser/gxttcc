package com.caeser.gxttcc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caeser.gxttcc.dao.BriefDao;
import com.caeser.gxttcc.entity.Brief;
import com.caeser.gxttcc.service.BriefService;

@Service
public class BriefServiceImpl implements BriefService{
	@Autowired
	private BriefDao briefDao;
	@Override
	public Brief queryBrief() {
		return briefDao.queryBrief();
	}
}
