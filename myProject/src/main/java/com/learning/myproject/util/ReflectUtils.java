package com.learning.myproject.util;

import java.lang.reflect.Field;

public class ReflectUtils {

    public static Object getFieldValue(Object target,Class type,String fieldName) throws IllegalAccessException, NoSuchFieldException {
        Field field = null;
        if (type.getSimpleName().equals("Object") || target.getClass().getSimpleName().equals("Object")){
            throw new NoSuchFieldException();
        }
        if (type==null){
            try {
                field = target.getClass().getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                return getFieldValue(target,target.getClass().getSuperclass(),fieldName);
            }
        }else {
            try {
                field =type.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                return getFieldValue(target,type.getSuperclass(),fieldName);
            }
        }

        field.setAccessible(true);
       return field.get(target);
    }
}
