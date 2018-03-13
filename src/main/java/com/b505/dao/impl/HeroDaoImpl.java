package com.b505.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.b505.bean.Hero;
import com.b505.dao.IHeroDao;

//用于标注数据访问层组件
@Repository(value="IHeroDao")
public  class HeroDaoImpl extends BaseDaoImpl<Hero> implements IHeroDao{
	
	//下面可以写该bean独有的方法（具体是操作数据库的各种方法），当有需要时，就会调用，继承BaseDao里的方法也可以用
	@Resource
	private SessionFactory sessionFactory;
	@Override
	public Session getCurrentSession(){
		
		Session session=null;
		try {
			session=this.sessionFactory.getCurrentSession();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		System.out.println(session);
		return session;
	
		
	}
	
	
	
	

}
