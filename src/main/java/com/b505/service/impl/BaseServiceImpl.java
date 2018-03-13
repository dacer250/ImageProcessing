package com.b505.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.b505.dao.IBaseDao;
import com.b505.service.IBaseService;

public abstract  class BaseServiceImpl<T> implements IBaseService<T>{

	@Autowired
	protected IBaseDao<T> baseDao;
	
	public abstract void setBaseDao(IBaseDao<T> baseDao);
	
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		System.out.println("µ×²ã±£´æ---service²ã");
		baseDao.save(entity);
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		System.out.println("ÕâÊÇservice²ã");
		baseDao.delete(entity);
		
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		baseDao.update(entity);
	}

	@Override
	public T get(Serializable id) {
		// TODO Auto-generated method stub
		return baseDao.get(id);
	}

	@Override
	public void fulsh() {
		// TODO Auto-generated method stub
		baseDao.fulsh();
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		baseDao.clear();
		
	}

	@Override
	public T get(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return baseDao.get(propertyName, value);
	}

	@Override
	public T get(String propertyName1, String propertyName2, Object value1,
			Object value2) {
		// TODO Auto-generated method stub
		return baseDao.get(propertyName1, propertyName2, value1, value2);
	}

	@Override
	public boolean findById(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return baseDao.findById(propertyName, value);
	}

	@Override
	public void saveByMerge(T entity) {
		// TODO Auto-generated method stub
		baseDao.saveByMerge(entity);
	}

	@Override
	public Long totalCount() {
		// TODO Auto-generated method stub
		return baseDao.totalCount();
	}

	@Override
	public void saveOrUpdate(T entity) {
		// TODO Auto-generated method stub
		baseDao.saveOrUpdate(entity);
		
	}

	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		return baseDao.getAll();
	}

	@Override
	public List<T> getByPage(Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		return baseDao.getByPage(currentPage, pageSize);
	}

	@Override
	public List<T> select(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return baseDao.select(propertyName, value);
	}

	@Override
	public List<T> select(String propertyName1, String propertyName2,
			Object value1, Object value2) {
		// TODO Auto-generated method stub
		return baseDao.select(propertyName1, propertyName2, value1, value2);
	}

	@Override
	public List<T> getAll(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return baseDao.getAll(propertyName, value);
	}

	@Override
	public List<T> getAll(String propertyName1, Object value1,
			String propertyName2, Object value2) {
		// TODO Auto-generated method stub
		return baseDao.getAll(propertyName1, propertyName2, value1, value2);
	}
	
	@Override
	public List<T> fenyeBySearch(Integer currentPage, Integer pageSize, String propertyName,
			Object value){
		
		return baseDao.fenyeDemo(currentPage, pageSize, propertyName, value);
	}
	
	

}
