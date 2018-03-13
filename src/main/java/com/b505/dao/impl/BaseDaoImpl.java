package com.b505.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.Resource;















import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.b505.dao.IBaseDao;

public class BaseDaoImpl<T> implements IBaseDao<T> {
	
	private Class<T> entityClass;
	@Resource
	private SessionFactory sessionFactory;
	@SuppressWarnings("unchecked")//���߱���������ָ���ľ��棬�����ڱ�����ɺ���־�����Ϣ
	//�����࣬�ô���ʹ��Ƶ���ķ�������ʼ��Ҫʵ�������󣬵��ú�ֻ���һ��ʵ�������У��Ѿ�Ӧ������������з�����//�������ʾ��Ǳ���ʹ������Ե�ɻ�����������Ӧ�õĵط�
	public BaseDaoImpl(){
		ParameterizedType type=(ParameterizedType) getClass().getGenericSuperclass();//��ȡʵ�����е����ֱ�ӳ���ķ�������
		entityClass=(Class<T>) type.getActualTypeArguments()[0];//��ȡ�������͵�ʵ�����Ͳ�������ȡ����һ��������ֵ
	}
	
	
	//������һ������(һ���߳�)�е���getCurrentSession()�Ͷ����ThreadLocal��ȡ�������Session���������ͱ�����һ������(һ���߳�)��ʹ�õĶ���ͬһ��Session�����ˣ����Ա���������������ԣ�������һ���Ժ����ݰ�ȫ�Եȡ�
	//�ܶ���֮�����Ǳ�֤���е�session����ͬһ��(�߳�����)
	public Session getCurrentSession(){
		
		Session session=null;
		System.out.println("这是BaseDao的session");
		try {
			session=this.sessionFactory.getCurrentSession();
		} catch (Exception e) {
			// TODO: handle exception
            //e.printStackTrace();
            System.out.println(e);
		}
		return session;
	}
	
	
	

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
	   System.out.println(entity+"huoji.save");
	   getCurrentSession().save(entity);
		
	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub
		System.out.println(entity+"huoji.delete");
		getCurrentSession().delete(entity);
		
	}


	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		getCurrentSession().update(entity);
	}


	@Override
	public void saveOrUpdate(T entity) {
		// TODO Auto-generated method stub
		getCurrentSession().saveOrUpdate(entity);
	}


	@SuppressWarnings("unchecked")
	@Override
	public T get(Serializable id) {
		// TODO Auto-generated method stub
		return (T) getCurrentSession().get(entityClass, id);
	}


	@Override
	public T get(String propertyName, Object value) {
		// TODO Auto-generated method stub
		System.out.println(entityClass.getName()+"@@@@@@@@@@@@@@@@@@@@@@@@@");
		//hql���,���ڲ���������Ǹ���ģ����ϲ���ʱ����tets1��
		//from com.b505.bean.Hero  model where model.name = :name(��һ������ѡȡ ĳ�� ����������XX�� ��������)
		//�ǵ�hql���֮�����пո��
		String hql="from "+entityClass.getName()+"  model where model."+propertyName+" = :name";
		//ִ��hql���
		Query query=getCurrentSession().createQuery(hql);
		System.out.println(query+"@@@@@@@@@@");
		query.setParameter("name", value);    //���name��hql������nameҪ����һ�� 
		System.out.println("232323"+query);
		
		//query.list�󣬾ͻ����������ȫ����ȫ
		@SuppressWarnings("unchecked")
		List<T> list=query.list();
		System.out.println(list+"query LISt()��");
	    if(list.size()>0){
	    	return list.get(0);
	    }
		return null;
	}


	@Override
	public T get(String propertyName1, String propertyName2, Object value1,
			Object value2) {
		// TODO Auto-generated method stub
		
		String hql="from "+entityClass.getName()+" as model where model."+propertyName1+" = :name"
				   +" and model."+propertyName2+" = :pd";
		Query query=getCurrentSession().createQuery(hql);
		query.setParameter("name", value1);
		query.setParameter("pd", value2);
		
		@SuppressWarnings("unchecked")
		List<T> list=query.list();
		 
		if(list.size()>0){
			return list.get(0);
		}
		
		
		return null;
	}


	@Override
	public boolean findById(String propertyName, Object value) {
		// TODO Auto-generated method stub
		//���������get()�������еĻ�����true
		T object=get(propertyName, value);
		return (object!=null);
	}


	@Override
	public void fulsh() {
		// TODO Auto-generated method stub
		getCurrentSession().flush();
	}


	@Override
	public void clear() {
		// TODO Auto-generated method stub
		getCurrentSession().clear();
	}


	@Override
	public void saveByMerge(T entity) {
		// TODO Auto-generated method stub
		getCurrentSession().merge(entity);
	}


	@Override
	public Long totalCount() {
		// TODO Auto-generated method stub
		//��test2��
		String hql="select Count (model) from "+entityClass.getName()+" as model";
		//ҳ������
		Long total=(Long) getCurrentSession().createQuery(hql).uniqueResult(); //����Ψһ���
		return total;
	}


	@Override
	public List<T> getAll() {
		// TODO Auto-generated method stub
		//�����������ݶ��ó���
		String hql="from "+entityClass.getName();
		Query query=getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<T> list=query.list();
		if(list.size()>0){
			return list;
		}
		
		return null;
	}


	@Override
	public List<T> getByPage(Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		//����
		String hql="select model from "+entityClass.getName()+" as model";
		
	    @SuppressWarnings("unchecked")
		List<T> list=getCurrentSession().createQuery(hql).setFirstResult((currentPage-1)*pageSize)
		             .setMaxResults(pageSize).list();  //ǰ������ôӵڼ�ҳ��ʼ�������������ȡҳ��
		return list;
	}


	@Override
	public List<T> select(String propertyName, Object value) {
		// TODO Auto-generated method stub
		//ģ������(��like�Ķ���ģ������)��ʲô��������Ļ�,Ĭ�ϻ�ȡȫ�����ݣ���2���Ѵ�2 �Ķ�����������,һ�����һ��
		String hql="from "+entityClass.getName()+" model where model."+propertyName+" like '%"+value+"%'";
		System.out.println(hql+"huoji.select@@@@@@@@@@@@@@");
		Query query=getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<T> list=query.list();
		if(list.size()>0){
			return list;
		}
		return null;
	}


	@Override
	public List<T> select(String propertyName1, String propertyName2,
			Object value1, Object value2) {
		// TODO Auto-generated method stub
		String hql="from "+entityClass.getName()+" as model where model."+propertyName1+" like '%"+value1+"%'";
		       hql+=" and model."+propertyName2+"like '%"+value2+"%' ";
		Query query=getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<T> list=query.list();
		if(list.size()>0){
			return list;
		}
		       
		return null;
	}

    
	@Override
	public List<T> getAll(String propertyName, Object value) {
		// TODO Auto-generated method stub
		//��select��ģ������һ��������
		String hql="from "+entityClass.getName()+" model where model."+propertyName+" like '%"+value+"%' ";
		Query query=getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<T> list=query.list();
		if(list.size()>0){
			return list;
		}
		return null;
	}


	@Override
	public List<T> getAll(String propertyName1, String propertyName2,
			Object value1, Object value2) {
		// TODO Auto-generated method stub
		//������Ϣ��ȡĳ���ض������ݣ���ȡ�ļ���ʱ��Ҫ�����
		String hql="from "+entityClass.getName()+" model where model."+propertyName1+" = :name1";
		       hql+=" and model."+propertyName2+" = :name2";
		       
		Query query=getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<T> list=query.setParameter("name1", value1).setParameter("name2", value2).list();//��������ԣ��Ƚ�һ��������select������
	    if(list.size()>0){
	    	return list;
	    }	
       
		return null;
	}


	
	@Override
	public List<T> fenyeDemo(Integer currentPage, Integer pageSize,
			String propertyName, Object value) {
		// TODO Auto-generated method stub
		String hql="from "+entityClass.getName()+" model where model."+propertyName+" like '%"+value+"%'";
		@SuppressWarnings("unchecked")
		List<T> list=getCurrentSession().createQuery(hql).setFirstResult((currentPage-1)*pageSize)
	             .setMaxResults(pageSize).list();
		System.out.println("��������@@@@@@@");
		
		if(list.size()>0){
			return list;
		}
		
		return null;
	}
	
	
	
	
	

}
