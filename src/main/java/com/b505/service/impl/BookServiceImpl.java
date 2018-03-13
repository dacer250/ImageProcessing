package com.b505.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.b505.bean.Book;
import com.b505.dao.IBaseDao;
import com.b505.dao.IBookDao;
import com.b505.service.IBookService;

@Service
public class BookServiceImpl extends BaseServiceImpl<Book> implements IBookService{

	@Autowired
    @Qualifier("IBookDao")
	private IBookDao iBookDao;
	
	
	
	@Override
	public void setBaseDao(IBaseDao<Book> iBookDao) {
		// TODO Auto-generated method stub
		this.baseDao=iBookDao;
		this.iBookDao=(IBookDao) iBookDao;
	}



	@Override
	public long searchNumber(String propertyName, Object value) {
		// TODO Auto-generated method stub
		List<Book> list=this.iBookDao.select(propertyName, value);
		long number=list.size();
		if(number>0){
			return number;
		}
		return 0;
	}

	


	/*@Override
	public List<Book> fenyeBySearch(Integer currentPage, Integer pageSize,
			String propertyName, Object value) {
		// TODO Auto-generated method stub
		List<Book> list=this.iBookDao.fenyeBySearch(currentPage, pageSize, propertyName, value);
		return list;
	}*/

}
