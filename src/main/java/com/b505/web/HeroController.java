package com.b505.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.mahout.cf.taste.common.TasteException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.b505.bean.Hero;
import com.b505.json.JsonAnalyze;
import com.b505.service.IHeroService;
import com.b505.tools.ImageFilters;
import com.b505.tools.LoadPicture;
import com.b505.tools.TryCatchHeroService;
import com.b505.tools.UserCF;


@Controller

public class HeroController {
	
	
	@Autowired
	private TryCatchHeroService tryCatchHeroService;
	@Autowired
	private JsonAnalyze jsonAnalyze;
	@Autowired
	private IHeroService iHeroService;
	@Autowired
	private UserCF userCF;
	@Autowired
	private ImageFilters imageFilters;
	@Autowired
	private LoadPicture loadPicture;
	
	
	//дһ���ӿڣ����������ύ����������
	@SuppressWarnings("static-access")
	@RequestMapping("/saveHero.do")
	@ResponseBody
	public boolean saveHero(@RequestBody String requestbody,HttpServletRequest request) throws IOException, TasteException{
	    Map<String,Object> map=jsonAnalyze.json2Map(requestbody);
	    String id= String.valueOf(map.get("id"));
	    String name=String.valueOf(map.get("name"));
	    String info=String.valueOf(map.get("info"));
	    System.out.println(id+"@@@@@@@@@"+name+"@@@@@@@"+info+"@@@@@@@");
	    
	    Hero hero=new Hero();
	    hero.setId(id);
	    hero.setName(name);
	    hero.setInfo(info);
	    
	    System.out.println("快到推荐系统了");
	    
	    userCF.CAT();
	    
	    if(tryCatchHeroService.saveHero(hero)){
	    	System.out.println("����ɹ�");
	    	return true;
	    }
	    	
		return false;
	}
	
	
	//��дһ���ӿ�,����ɾ������
	@RequestMapping("/deleteHero.do")
	@ResponseBody
	public boolean deleteHero(@RequestBody String requestBody,HttpServletRequest request) throws IOException{
		Map<String, Object> map=jsonAnalyze.json2Map(requestBody);
		
		String id=String.valueOf(map.get("id"));
		
		Hero hero=new Hero();
		
		hero.setId(id);
		
		if(tryCatchHeroService.deleteHero(hero)){
			return true;
		}
	
		return false;
		
	}
	
	
	//��дһ���ӿڣ�������ȡ���ݣ�һ������)--->>>һ�����ݻ�ȡ
	@RequestMapping("/get1.do")
	@ResponseBody
	public Hero get(@RequestBody String requestBody,HttpServletRequest request) throws IOException{
		
		Map<String, Object> map=jsonAnalyze.json2Map(requestBody);
		String name=String.valueOf(map.get("name"));
		System.out.println(name+"@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		Hero list;
		
		list=tryCatchHeroService.get("name", name);
		System.out.println(list+"233333333");
		
		if(list!=null){
			return list;
		}
		
		return null;
	}
	
	
	//��дһ����ȡ���ݵĽӿ�---->>>���������ض�ֵ��ȡ���ݵ�
	@RequestMapping("/get2.do")
	@ResponseBody
	public Hero get2(@RequestBody String requsetBody, HttpServletRequest request) throws IOException{
		
		Map<String, Object> map=jsonAnalyze.json2Map(requsetBody);
		String name=String.valueOf(map.get("name"));
		String id=String.valueOf(map.get("id"));
		Hero list;
		
		try {
			list=tryCatchHeroService.get("name", "id", name, id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return list;
		
	}
	
	//��дһ������id��ȡ���ݵĽӿ�
	@RequestMapping("/getById.do")
	@ResponseBody
	public Hero getById(@RequestBody String requestBody,HttpServletRequest request) throws IOException{
		Map<String, Object> map=jsonAnalyze.json2Map(requestBody);
		String id=String.valueOf(map.get("id"));
		Hero list;
		
		try {
			list=tryCatchHeroService.get(id);
			System.out.println(list+"@@@@@@@@@@@@@@@@@@@@@@@@");
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return list;
		
	}
	
	//��дһ���ӿ�,�������»��޸�ԭ�������ݣ�û�еĻ���ӣ��еĻ������޸ģ�
	@RequestMapping("/saveByMerge.do")
	@ResponseBody
	public boolean saveByMerge(@RequestBody String requestBody,HttpServletRequest request) throws IOException{
		Map<String, Object> map=jsonAnalyze.json2Map(requestBody);
		String id=String.valueOf(map.get("id"));
		String name=String.valueOf(map.get("name"));
		String info=String.valueOf(map.get("info"));
		
		Hero hero =new Hero();;	
		hero.setId(id);
		hero.setName(name);
		hero.setInfo(info);

		if(tryCatchHeroService.saveByMerge(hero)){
			return true;
		}
		
		return false;	
	}
	
	//������ȡȫ�����ݵ�
	@RequestMapping("/getAll.do")
	@ResponseBody
	public List<Hero> getAll(HttpServletRequest request){
		
		List<Hero> list;
		list=tryCatchHeroService.getAll();
		
		if(list==null){
			return null;
		}
		return list;
	}
	
	//����select��ȡ���ݵ�(ģ������)
	@RequestMapping("/select.do")
	@ResponseBody
	public List<Hero> select(@RequestBody String requsetBody,HttpServletRequest request) throws IOException{
		Map<String, Object> map=jsonAnalyze.json2Map(requsetBody);
		String id=String.valueOf(map.get("id"));
		
		List<Hero> list;
		list=tryCatchHeroService.select("id", id);
		
		if(list==null){
			return null;
		}
		return list;
	}
	
	/**
	 * 
	 * @param �õ�����Ϣ��ȡ��Ҫ�����ݣ�ģ��������
	 * @return ������Ҫ����������
	 * @throws IOException
	 * @author b505
	 */
	//�����ݻ�ȡgetAll(),�������ģ������һ��
	@RequestMapping("getAll_1.do")
	@ResponseBody
	public List<Hero> getAll_1(@RequestBody String requestBody,HttpServletRequest request) throws IOException{
		
		Map<String, Object> map=jsonAnalyze.json2Map(requestBody);
		String id=String.valueOf(map.get("id"));
		
		List<Hero> list;
		list=tryCatchHeroService.getAll("id",id);
		
		if(list==null){
			return null;
		}
		return list;
 	}
	
	/**
	 * @author b505
	 * @param  ��������Ϣ��ȡ����Ҫ�����������
	 * @return list
	 * @throws IOException
	 */
	@RequestMapping("/getAll_2.do")
	@ResponseBody
	public List<Hero> getAll_2(@RequestBody String requestBody,HttpServletRequest request) throws IOException{
		Map<String, Object> map=jsonAnalyze.json2Map(requestBody);
		String id=String.valueOf(map.get("id"));
		String name=String.valueOf(map.get("name"));
		
		List<Hero> list;
		list=tryCatchHeroService.getAll("id", "name", id, name);
		System.out.println(list+"@@@@@@@@@@@@@");
		
		
		if(list==null){
			return null;
		}
		return list;
	}
	
	
	
	

}
