package com.bysj.utils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyConstant {

	public static String SYS_NAME = "基于JSP的大学生医保报销系统";
	public static Map<String, Object> DICTIONARIES;
	public static String UPLOAD_URL = "D:/upload";
	
	public static String USER_ADMIN = "学工处";
	public static String USER_MIDDLE = "辅导员";
	public static String USER_LOW = "学生";
	

	public static Map<String, Object> getDictionary() {
		Map<String, Object> map = new HashMap<>();
		map.put("category", getCategorys());
		map.put("roles", getRoles());
		map.put("SYS_NAME", SYS_NAME);
		return map;
	}
	
	public static Map<String, Object> getRoles() {
		Map<String, Object> map = new LinkedHashMap<>();
		
		List<String> list1 = new LinkedList<>();
		list1.add(USER_ADMIN);
		map.put("admin-login", list1);
		
		List<String> list2 = new LinkedList<>();
		list2.add(USER_ADMIN);
		list2.add(USER_MIDDLE);
		map.put("admin-register", list2);
		
		List<String> list3 = new LinkedList<>();
		list3.add(USER_ADMIN);
		list3.add(USER_MIDDLE);
		map.put("admin-info", list3);
		
		List<String> list4 = new LinkedList<>();
		list4.add(USER_LOW);
		list4.add(USER_MIDDLE);
		map.put("client-login", list4);
		
		List<String> list5 = new LinkedList<>();
		list5.add(USER_LOW);
		list5.add(USER_MIDDLE);
		map.put("client-register", list5);
		
		return map;
	}
	
	public static Map<String, String> getCategorys() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("Java", "Java");
		return map;
	}
}
