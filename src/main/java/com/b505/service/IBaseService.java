package com.b505.service;

import java.io.Serializable;
import java.util.List;

public interface IBaseService <T> {
	
	public void save(T entity);
	public void delete(T entity);
	public void update(T entity);
	public T  get(Serializable id);
	public void fulsh();
	public void clear();
	public T get(String propertyName,Object value);
	public T get(String propertyName1,String propertyName2 , Object value1,Object value2);
	public boolean findById(String propertyName, Object value);
	public void saveByMerge(T entity);
	public Long totalCount();
	public void saveOrUpdate(T entity);	
	
	
	public List<T> getAll();
	public List<T> getByPage(Integer currentPage, Integer pageSize);
	public List<T> select(String propertyName, Object value);
	public List<T> select(String propertyName1,String propertyName2 , Object value1,Object value2);
	public List<T> getAll(String propertyName, Object value);
	public List<T> getAll(String propertyName1, Object value1,String propertyName2, Object value2);
	public List<T> fenyeBySearch(Integer currentPage, Integer pageSize, String propertyName,
			Object value);

}
