package com.b505.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.b505.bean.Hero;
import com.b505.dao.IBaseDao;
import com.b505.dao.IHeroDao;
import com.b505.service.IHeroService;

@Service
public class HeroServiceImpl extends BaseServiceImpl<Hero> implements IHeroService{
	
	@Autowired
	@Qualifier("IHeroDao")
	private IHeroDao iHeroDao;

	@Override
	public void setBaseDao(IBaseDao<Hero> iHeroDao) {
		// TODO Auto-generated method stub
		this.baseDao=iHeroDao;
		this.iHeroDao=(IHeroDao) iHeroDao;
		
	}

}
