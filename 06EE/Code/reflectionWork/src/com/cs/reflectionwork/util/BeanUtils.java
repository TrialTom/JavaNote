package com.cs.reflectionwork.util;


import com.sun.xml.internal.ws.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author ：TrialCat
 * @description：TODO
 * @date ：2024/05/10 11:38
 */

public class BeanUtils {

    private final static String SET_PREFIX = "set";
    public static void toBean(Object obj, Map<String, String[]> parameterMap) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        // 获取字节码文件，使用反射
        Class<?> aClass = obj.getClass();
        for (String key : parameterMap.keySet()) {
            // 通过map的key值来获取obj中对应的成员变量
            Field declaredField = aClass.getDeclaredField(key);
            // 获取成员变量对应的类型
            Class<?> type = declaredField.getType();
            // 通过set拼接key，获取set方法
            Method method = aClass.getMethod(SET_PREFIX + StringUtils.capitalize(key), type);
            // 拿到key对应的value值
            String[] strings = parameterMap.get(key);
            // 获取成员变量的数据类型名
            String simpleName = type.getSimpleName();
            // 分批处理传入的数据
            if("String".equals(simpleName)){
                method.invoke(obj, strings[0]);
            }else if("Double".equals(simpleName)){
                method.invoke(obj, Double.parseDouble(strings[0]));
            }else if("String[]".equals(simpleName)){
                method.invoke(obj, (Object) strings);
            }else {
                // 当传入的数据不是以上的类型，那么将抛出异常
                throw  new IllegalArgumentException("不支持处理除了简单数据类型之外的类型");
            }
        }
    }
}
