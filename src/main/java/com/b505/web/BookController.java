package com.b505.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jgroups.tests.bla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.b505.bean.Book;
import com.b505.dao.IBookDao;
import com.b505.json.JsonAnalyze;
import com.b505.tools.TryCatchBookService;

@Controller
public class BookController {
	
	@Autowired
	private TryCatchBookService tryCatchBookService;
	@Autowired
	private JsonAnalyze jsonAnalyze;
	@Autowired
	private IBookDao IBookDao;
	
	
	//获取所有书
	@RequestMapping("/getAllBook.do")
	@ResponseBody
	public List<Book> getAllBook(HttpServletRequest request){
		
		List<Book> list;
		
		list=tryCatchBookService.getAll();
		
		if(list==null){
			return null; 
		}
		return list;
	}
	
	//分页
	@RequestMapping("/fenye.do")
	@ResponseBody
	public List<Book> fenye(@RequestBody String requestBody,HttpServletRequest request) throws IOException{
		
		Map<String, Object> map=jsonAnalyze.json2Map(requestBody);
		Integer currentPage= (Integer) map.get("currentPage");
		
		System.out.println("@@@@@@@@@@"+currentPage);
		
		
		List<Book> list;
		list=tryCatchBookService.getByPage(currentPage, 3);//������Ը�ҳ����ʾ���ݵ�����������ȡһ�������ö���ȡ����������list<>
		
		System.out.println(list+"@@@@@@@@@@@");
		
		if(list==null){
			return null;
		}
		return list;
		
	}
	
	
	
	//搜索分页功能
	@RequestMapping("fenyeBySearch.do")
	@ResponseBody
	public List<Book> fenyeBySearch(@RequestBody String requestJsonBody,HttpServletRequest request) throws IOException{
		
		Map<String, Object> map=jsonAnalyze.json2Map(requestJsonBody);
		String search=String.valueOf(map.get("search"));
		Integer currentPage= (Integer) map.get("currentPage");
		System.out.println(search);
		
		List<Book> list;
		Long total;
		total=tryCatchBookService.searchNumber("info", search);
		System.out.println(total);
		
		list=tryCatchBookService.fenyeBySearch1(currentPage, 2, "info", search);
		System.out.println(list);
		Map<String, Object> map1 = null;
		
		//map1.put("total", total);
		//map1.put("list", list);
		
		//System.out.println(list);
		//System.out.println(map1);
		
	   return list;	
	}
	
	
	@RequestMapping("/number.do")
	@ResponseBody
	public Long number(@RequestBody String requestJsonBody) throws IOException{
		
		Map<String, Object> map=jsonAnalyze.json2Map(requestJsonBody);
		String search=String.valueOf(map.get("search"));
		
		Long total;
		total=tryCatchBookService.searchNumber("info", search);
		
		return total;
	}
	
	
    @RequestMapping("/total.do")
    @ResponseBody
    public Long total(){
    	
    	Long total;
    	total=tryCatchBookService.totalCount();
    	
    	if(total==null){
    		return null;
    	}
    	return total;
    	
    }
	
	
	
}
