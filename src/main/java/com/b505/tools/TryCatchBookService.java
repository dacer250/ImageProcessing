package com.b505.tools;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.b505.bean.Book;
import com.b505.dao.IBaseDao;
import com.b505.service.IBaseService;
import com.b505.service.IBookService;


@Component
public class TryCatchBookService {
	
	
	@Autowired
	private IBookService iBookService;
	
	public List<Book> getAll(){
		
		List<Book> list;
		
		try {
			list=iBookService.getAll();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		return list;
	  }
	
	public List<Book> getByPage(Integer currentPage,Integer pageSize){
		
		List<Book> list;
		
		try {
			list=iBookService.getByPage(currentPage, pageSize);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		return list;	
	}
	
	public Long totalCount(){
		
		Long totalCount;
		
		try {
			totalCount=iBookService.totalCount();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return totalCount;
	}
	
	/*public List<Book> fenyeBySearch(Integer currentPage, Integer pageSize,
			String propertyName, Object value) {
		
		List<Book> list;
		
		try {
			list=iBookService.fenyeBySearch(currentPage, pageSize, propertyName, value);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return list;
	}*/
	
	
	public List<Book> fenyeBySearch1(Integer currentPage, Integer pageSize,
			String propertyName, Object value) {
		List<Book> list;
		
		try {
			list=this.iBookService.fenyeBySearch(currentPage, pageSize, propertyName, value);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		return list;
	}
	
	
	public Long searchNumber(String propertyName, Object value){
		
		long number=this.iBookService.searchNumber(propertyName, value);
		
		return number;
	}
	
	
}
