package com.b505.tools;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.b505.bean.Hero;
import com.b505.service.IHeroService;

@Component
public class TryCatchHeroService {
	
	
	@Autowired
	private IHeroService iHeroService;
	
	public boolean saveHero(Hero hero){
		
		try {
			iHeroService.save(hero);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
	}
	
	public boolean deleteHero(Hero hero){
		
		
		try {
			iHeroService.delete(hero);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		return true;
	}
	
	public boolean update(Hero hero){
		
		try {
			iHeroService.update(hero);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		   return true;
	}
	
	
	
	public Hero get(String propertyName,Object value){
		Hero list;
		try {
			list=iHeroService.get(propertyName, value);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		return list;
		
	}
	
	public Hero get(String propertyName1,String propertyName2,Object value1,Object value2){
		Hero list;
		try {
		    list=iHeroService.get(propertyName1, propertyName2, value1, value2);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		
		return list;
		
	}
	
	public Hero get(Serializable id){
		Hero list;
		try {
			list=iHeroService.get(id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return list;
	}
	
	public boolean saveByMerge(Hero hero){
		
		try {
			iHeroService.saveByMerge(hero);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public List<Hero> getAll(){
		
		List<Hero> list;
		try {
		   list=iHeroService.getAll();	
			
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return list;
		
	} 
	
	public List<Hero> select(String propertyName,Object value){
		
		List<Hero> list;
		try {
			list=iHeroService.select(propertyName, value);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return list;
	}
	
	public List<Hero> getAll(String propertyName,Object value){
		
		List<Hero> list;
		
		try {
			list=iHeroService.getAll(propertyName, value);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return list;
	}

  
	public List<Hero> getAll(String propertyName1,String propertyName2,Object value1,Object value2){
		
		List<Hero> list;
		
		try {
			list=iHeroService.getAll(propertyName1, value1, propertyName2, value2);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return list;
	}
	
	
}
