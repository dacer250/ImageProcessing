package com.b505.dao;

import java.io.Serializable;
import java.util.List;

//这里的interface是一个底层接口，调用底层的方法，下面的impl用来具体实现接口
public interface IBaseDao<T> {
	
	//这里写操作表的方法，然后让其他的通过继承而调用
	public void save(T entity); 
	public void delete(T entity);
	public void update(T entity);
	public void saveOrUpdate(T entity);
	//id是唯一一个有标识的(也是唯一标识)
	public T get(Serializable id);
	//根据提供的数据获取某条数据
	public T get(String propertyName,Object value);
	public T get(String propertyName1 ,String propertyName2,Object value1,Object value2);
	//什么方法(确定存在在数据库中)
	public boolean findById(String propertyName,Object value);
	//清空缓存区中的数据
	public void fulsh();
	public void clear();
	//merge方法就是update和insert的结合，如果有，就检查更新，没有就新建
	public void saveByMerge(T entity);
	//分页时调用此方法，用来计算数据的总条数
	public Long totalCount();
	
	
	public List<T> getAll();
	//分页时获取到具体页面的数据 currentPage-->当前页码
	public List<T> getByPage(Integer currentPage,Integer pageSize);
	//模糊搜索
	public List<T> select(String propertyName,Object value);
	public List<T> select(String propertyName1,String propertyName2,Object value1,Object value2);
	public List<T> getAll(String propertyName,Object value);
	public List<T> getAll(String propertyName1,String propertyName2,Object value1,Object value2);
	
	
	public List<T>  fenyeDemo(Integer currentPage,Integer pageSize,String propertyName,Object value);
	
	

}
