package com.learning.myproject.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64String;

import java.security.SecureRandom;

/**
 * Created with IntelliJ IDEA.
 *
 * @author kjp
 * Email : kangjinpeng@zhehekeji.com
 */
public class Md5Utils {

    /**
     * 加密方式
     * */
    private static String type="MD5";

    /**
     * 迭代次数
     * */
    private static Integer Iterations=10;

    /*
    * 密码加密
    * */
    public static String encryption(String password,String salt){
        SimpleHash simpleHash = new SimpleHash(type, password, salt, Iterations);
        return simpleHash.toString();
    }

    public static String generateSalt(){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[15];
        random.nextBytes(bytes);
        String salt = encodeBase64String(bytes);
        return salt;
    }
}
