package com.b505.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unchecked")
public class JsonAnalyze {
	
	
 private ObjectMapper objectMapper = new ObjectMapper();
	 
	 
	 //�Ѵӿͻ��˵õ���JSON����ת����Map
	 public Map<String, Object> json2Map(String json) throws IOException
	 {
		 Map<String,Object> map2 = new HashMap<String,Object>();	
		 try 
		 {
			 Map<String,Object> map1 = objectMapper.readValue(json, Map.class);
			 Set<String> key = map1.keySet();
			 Iterator<String> iter = key.iterator();
			 while (iter.hasNext()) 
			 {				
				String str = iter.next();
			    map2.put(str, map1.get(str));
			 }
			 return map2;
		 } 
		 catch (JsonParseException e)
		 {
			   e.printStackTrace();
		  } 
		 catch (JsonMappingException e) 
		 {
			   e.printStackTrace();
		 } 
		 catch (IOException e) 
		 {
			   e.printStackTrace();
		 }
		 return map2;
		 
	 }
	 
	 //��JOSN����ת��ΪList����
	 public <T> List<T> json2List(String json) throws IOException
	 {
		 List<T> list1 = new ArrayList<T>(); 
		 try 
		 {
			   List<LinkedHashMap<String, Object>> list = objectMapper.readValue(json, List.class);
			   list1 = (List<T>) list;
		  } 
		  catch (JsonParseException e)
		  {
			 e.printStackTrace();
		  } 
		  catch (JsonMappingException e)
		  {
			 e.printStackTrace();
		   } 
		  catch (IOException e) 
		  {
			 e.printStackTrace();
		   }
		 return list1;
	 }
	 
	 //��jsonת����Object
	 public Object json2Object(String json)throws IOException
	 {
		 Object object = new Object();
		 try
		 {
			 object = objectMapper.readValue(json,Object.class);
		 }
		 catch (JsonParseException e)
		  {
			 e.printStackTrace();
		  } 
		  catch (JsonMappingException e)
		  {
			 e.printStackTrace();
		   } 
		  catch (IOException e) 
		  {
			 e.printStackTrace();
		   }
		 return object;
		 
	 }
	 
	 //��Map����ת����JSON����
	 
	 
	 public String map2Json(Map<String,Object> map) throws IOException
	 {
		 String json = new String();
		 try 
		 {
			 json = objectMapper.writeValueAsString(map);
			 return json;
		 }
		 catch (IOException e) 
		 {
			  e.printStackTrace();
		 }
		 
		 return json;
	 }
	 
	//��List<Map>����ת����JSON����
	 
	 
		 public String Listmap2Json(List<Map<String, String>> maps) throws IOException
		 {
			 String json = new String();
			 try 
			 {
				 json = objectMapper.writeValueAsString(maps);
				 return json;
			 }
			 catch (IOException e) 
			 {
				  e.printStackTrace();
			 }
			 
			 return json;
		 }
	 
	 //�������ݿ�ȡ�õ�List���͵�����ת����JSON����
	 public <T> String list2Json(List<T> list) throws IOException
	 {
		 String json = new String();
		 try
		 {
			 json = objectMapper.writeValueAsString(list);
		 }
		 catch(IOException e)
		 {
			 e.printStackTrace();
		 }
		 return json;
	 }
	 
	 //Objectת��Ϊjson
	 public String string2Json(String str)throws IOException
	 {
		 String json = new String();
		 try 
		 {
			 json = objectMapper.writeValueAsString(str);
			 return json;
		 }
		 catch (IOException e) 
		 {
			  e.printStackTrace();
		 }
		 
		 return json;
	 }

	 //��objectת��Ϊjson
	 public String object2Json(Object obj) throws IOException
	 {
		 String json = new String();
		 try
		 {
			 json = objectMapper.writeValueAsString(obj);
		 }
		 catch(IOException e)
		 {
			 e.printStackTrace();
		 }
		 return json;
	 }
	 
	

}
