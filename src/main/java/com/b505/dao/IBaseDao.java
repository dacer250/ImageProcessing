package com.b505.dao;

import java.io.Serializable;
import java.util.List;

//�����interface��һ���ײ�ӿڣ����õײ�ķ����������impl��������ʵ�ֽӿ�
public interface IBaseDao<T> {
	
	//����д������ķ�����Ȼ����������ͨ���̳ж�����
	public void save(T entity); 
	public void delete(T entity);
	public void update(T entity);
	public void saveOrUpdate(T entity);
	//id��Ψһһ���б�ʶ��(Ҳ��Ψһ��ʶ)
	public T get(Serializable id);
	//�����ṩ�����ݻ�ȡĳ������
	public T get(String propertyName,Object value);
	public T get(String propertyName1 ,String propertyName2,Object value1,Object value2);
	//ʲô����(ȷ�����������ݿ���)
	public boolean findById(String propertyName,Object value);
	//��ջ������е�����
	public void fulsh();
	public void clear();
	//merge��������update��insert�Ľ�ϣ�����У��ͼ����£�û�о��½�
	public void saveByMerge(T entity);
	//��ҳʱ���ô˷����������������ݵ�������
	public Long totalCount();
	
	
	public List<T> getAll();
	//��ҳʱ��ȡ������ҳ������� currentPage-->��ǰҳ��
	public List<T> getByPage(Integer currentPage,Integer pageSize);
	//ģ������
	public List<T> select(String propertyName,Object value);
	public List<T> select(String propertyName1,String propertyName2,Object value1,Object value2);
	public List<T> getAll(String propertyName,Object value);
	public List<T> getAll(String propertyName1,String propertyName2,Object value1,Object value2);
	
	
	public List<T>  fenyeDemo(Integer currentPage,Integer pageSize,String propertyName,Object value);
	
	

}
