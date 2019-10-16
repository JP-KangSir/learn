package com.learning.myproject.util;

import com.learning.myproject.annotation.SoftCopy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * @author kjp
 * Email : kangjinpeng@zhehekeji.com
 * @Date: 2019/3/13 0013
 * @Time: 17:36
 */
public class BeanUtils{

    private static final String LIST_BYTE="java.util.List<java.lang.Byte>";

    private static final String MAP_BYTE="java.lang.Byte>";

    private static final Byte BYTE_VALUE=0;

    private static final String LIST_INTEGER ="java.util.List<java.lang.Integer>";

    private static final String MAP_INTEGER ="java.lang.Integer>";

    private static final Integer INTEGER_VALUE=0;

    private static final String LIST_DOUBLE ="java.util.List<java.lang.Double>";

    private static final String MAP_DOUBLE ="java.lang.Double>";

    private static final Double DOUBLE_VALUE=0.00;

    private static final Logger log = LoggerFactory.getLogger(BeanUtils.class);


    //将数字为null的字段 设为0
    public static Object digitalCorrecting(Object o) {
        Class cl = o.getClass();
        Field[] fields = cl.getDeclaredFields();
        for(Field f:fields){
            try{
                f.setAccessible(true);
                if (f.getType().equals(Byte.class) && f.get(o)==null){
                    f.set(o,BYTE_VALUE);
                }
                if (f.getType().equals(Integer.class) && f.get(o)==null){
                    f.set(o,INTEGER_VALUE);
                }
                if (f.getType().equals(Double.class) && f.get(o)==null){
                    f.set(o,DOUBLE_VALUE);
                }
                if (f.getType().equals(List.class) && f.get(o)!=null){
                    List list= (List) f.get(o);
                    Type genericType = f.getGenericType();
                    if (genericType!=null){
                        String typeName = genericType.getTypeName();
                        list.forEach(e->{
                            if (typeName.equals(LIST_BYTE) && e==null ){
                                list.set(list.indexOf(e),BYTE_VALUE);
                            }else if (typeName.equals(LIST_INTEGER) && e==null ){
                                list.set(list.indexOf(e),INTEGER_VALUE);
                            }else if (typeName.equals(LIST_DOUBLE) && e==null){
                                list.set(list.indexOf(e),DOUBLE_VALUE);
                            }
                        });
                    }
                }
                if (f.getType().equals(Map.class) && f.get(o)!=null){
                    Map map= (Map) f.get(o);
                    Type genericType = f.getGenericType();
                    if (genericType!=null){
                        String typeName = genericType.getTypeName();
                        map.forEach((k,v)->{
                            if (typeName.contains(MAP_BYTE) && v==null){
                                map.put(k,BYTE_VALUE);
                            }
                            if (typeName.contains(MAP_INTEGER) && v==null){
                                map.put(k,INTEGER_VALUE);
                            }
                            if (typeName.contains(MAP_DOUBLE) && v==null){
                                map.put(k,DOUBLE_VALUE);
                            }
                        });
                    }
                }
            }catch (IllegalAccessException e){
                log.error(e.getMessage());
            }
        }
        return o;
    }


    /**
     *软拷贝 拷贝SoftCopy注解的字段
     * */
    public static void beanSoftCopy(Class oc,Object o,Class tc, Object t){
        customerBeanSoftCopy(oc,o,tc,t, SoftCopy.class);
    }

    /**
     *软拷贝 拷贝an注解的字段
     * */
    public static void customerBeanSoftCopy(Class oc,Object o,Class tc, Object t,Class an){
        Field[] fields1 = oc.getDeclaredFields();
        Field[] fields2 = tc.getDeclaredFields();
        for(Field f:fields1 ){
            if (f.getAnnotation(an)!=null){
                for (Field e:fields2){
                    if (f.getName().equals(e.getName())){
                        try {
                            f.setAccessible(true);
                            e.setAccessible(true);
                            f.set(o,e.get(t));
                        } catch (IllegalAccessException e1) {
                            log.error(e1.getMessage());
                        }
                    }
                }
            }
        }
    }

}
