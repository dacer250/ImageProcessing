package com.b505.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.b505.bean.Book;
import com.b505.dao.IBookDao;

@Repository(value="IBookDao")
public class BookDaoImpl extends BaseDaoImpl<Book> implements IBookDao{

	private Class<Book> entityClass;

	

	
	/*@SuppressWarnings("unchecked")
	@Override
	public List<Book> fenyeBySearch(Integer currentPage, Integer pageSize,
			String propertyName, Object value) {
		// TODO Auto-generated method stub
		//String hql="select model from "+entityClass.getName()+" model where model."+propertyName+" like '%"+value+"%' as model";
		System.out.println(entityClass.getName());
		String hql="from "+entityClass.getName()+" model where model."+propertyName+" like '%"+value+"%'";
		System.out.println(hql);
		List list=getCurrentSession().createQuery(hql).setFirstResult((currentPage-1)*pageSize)
	             .setMaxResults(pageSize).list();
		
		System.out.println("@@@@@@@@@@"+list);
		
		return (List<Book>) list;
		
	}*/
	
	


	
	
	//里面可以写特有的方法，现在我不需要  implements可以理解为具体实现
	
	

}
