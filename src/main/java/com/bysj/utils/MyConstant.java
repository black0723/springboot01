package com.bysj.utils;

import java.util.*;

public class MyConstant {

    public static String UPLOAD_URL = "D:/upload";

    public static String USER_ADMIN = "学工处";
    public static String USER_MIDDLE = "辅导员";
    public static String USER_LOW = "学生";

    public static Map<String, Object> getDictionary() {
        Map<String, Object> map = new HashMap<>();
        map.put("SYS_ROLE", getRoles());
        map.put("SYS_NAME", "系统名称");
        return map;
    }

    public static Map<String, Object> getRoles() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("admin-login", new LinkedList<>(Arrays.asList(USER_ADMIN)));
        map.put("admin-register", new LinkedList<>(Arrays.asList(USER_ADMIN, USER_MIDDLE)));
        map.put("client-login", new LinkedList<>(Arrays.asList(USER_MIDDLE, USER_LOW)));
        map.put("client-register", new LinkedList<>(Arrays.asList(USER_MIDDLE, USER_LOW)));
        return map;
    }
}
