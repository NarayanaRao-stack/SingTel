package com.test.utilities;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ReadfieldsRef {
	
	Object entity;
	Map<Object, String> map;
	
	public ReadfieldsRef(Object someOtherClass){
		super();
		this.entity = someOtherClass;
	}
	
	public Map<Object, String> getValueOf() {
		Field[] fields = entity.getClass().getDeclaredFields();
		map = new HashMap<Object, String>();
		for(Field f : fields) {
			try{
				f.setAccessible(true);
				map.put(f.get(entity).toString(), f.getName());
			}catch (NullPointerException np){
				
			}catch(IllegalAccessException ia){
				
			}catch(IllegalArgumentException ae){
				
			}
		}
		return map;
	}
	

}
