package com.b505.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.b505.bean.Hero;
import com.b505.dao.IHeroDao;

//���ڱ�ע���ݷ��ʲ����
@Repository(value="IHeroDao")
public  class HeroDaoImpl extends BaseDaoImpl<Hero> implements IHeroDao{
	
	//�������д��bean���еķ����������ǲ������ݿ�ĸ��ַ�������������Ҫʱ���ͻ���ã��̳�BaseDao��ķ���Ҳ������
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
