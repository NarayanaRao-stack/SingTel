package com.test.utilities;

import java.util.HashMap;
import java.util.Map;

public class ScreenshotHelper {
	
	public static final ThreadLocal<Map<String, Object>> SCREEN_SHOTS = new ThreadLocal<Map<String, Object>>() {
		
		protected Map<String, Object> initialValue() {
			return new HashMap<String, Object>();
		}
	};
	
	public static Map<String, Object> getScreenshotsForTest() {
		return SCREEN_SHOTS.get();
		
	}
	
	public static void removeScreenshotsAfterTest(){
		 SCREEN_SHOTS.remove();
	}
	
	public static void add(String name, byte[] image) {
		SCREEN_SHOTS.get().put(name, image.clone());
	}

}
