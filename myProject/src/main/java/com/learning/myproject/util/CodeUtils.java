package com.learning.myproject.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 *
 * @author kjp
 * Email : kangjinpeng@zhehekeji.com
 * @Desc: 验证码工具类
 */
public class CodeUtils {

    public static Map<String ,Integer>   verification=new HashMap<>();

    public static boolean verify(String username,Integer code){
        Integer cCode = verification.get(username);
        if (cCode==null){
            return false;
        }else if (cCode.equals(code)){
            return true;
        }else {
            return false;
        }
    }

    public static void remove(String username){
        verification.remove(username);
    }

    public static void saveCode(String username,Integer code){
        verification.put(username,code);
    }

    public static Integer generaterCode(){
        int flag = new Random().nextInt(999999);
        if (flag < 100000)
        {
            flag += 100000;
        }
        return flag;
    }

}
