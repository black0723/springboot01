package com.bysj.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * map 转 model
 *
 * @param <T>
 */
public class Map2ModelUtils<T> {

    public T toModel(Class<?> classzz, Map<String, Object> map) {
        try {
            if (map == null || map.size() == 0)
                return null;
            T object = (T) Class.forName(classzz.getName()).newInstance();
            // Class c = object.getClass();
            // Field[] fields = c.getDeclaredFields();// 取得所有类成员变量
            List<Field> fields = getAllFields(classzz);
            for (Field f : fields) {
                String name = f.getName();
                if (map.get(name) == null)
                    continue;
                f.setAccessible(true);
                String type = f.getType().getName();
                // System.out.println(f.getName());
                // System.out.println(f.getType().getName()); // java.lang.String
                // System.out.println(f.getType().getSimpleName()); // String
                String valueType = getType(map.get(name));
                switch (type) {
                    case "java.util.Date":
                        f.set(object, CommonUtils.toDate(map.get(name) == null ? null : map.get(name).toString()));
                        break;
                    case "java.lang.Integer":
                        if (valueType.equals("java.lang.Integer"))
                            f.set(object, map.get(name));
                        else
                            f.set(object, Integer.valueOf(map.get(name).toString()));
                        break;
                    case "java.lang.Long":
                    case "java.lang.Boolean":
                    case "java.lang.Double":
                        f.set(object, map.get(name));
                        break;
                    default:
                        // java.lang.String
                        f.set(object, map.get(name));
                        break;
                }
            }
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<T> toList(Class<?> classzz, List<Map<String, Object>> list) {
        if (list == null || list.size() == 0)
            return null;
        List<T> list2 = new ArrayList<>();
        for (Map<String, Object> map : list) {
            T t = toModel(classzz, map);
            list2.add(t);
        }
        return list2;
    }

    public List<Field> getAllFields(Class<?> classzz) {
        List<Field> fieldList = new ArrayList<>();
        // 不获取object的属性
        while (classzz != Object.class) {
            fieldList.addAll(Arrays.asList(classzz.getDeclaredFields()));
            // 得到父类,然后赋给自己
            classzz = classzz.getSuperclass();
        }
        return fieldList;
    }

    public String getType(Object o) { // 获取变量类型方法
        return o.getClass().toString(); // 使用int类型的getClass()方法
    }

}
