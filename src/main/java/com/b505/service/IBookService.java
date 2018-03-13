package com.b505.service;

import java.util.List;

import com.b505.bean.Book;

public interface IBookService extends IBaseService<Book>{

	/*public List<Book> fenyeBySearch(Integer currentPage, Integer pageSize,String propertyName, Object value);*/

   public long searchNumber(String propertyName, Object value);
}
