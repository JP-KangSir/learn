package com.learning.myproject.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

/**
 * 文件工具类
 *
 * @author kjp
 * Email : kangjinpeng@zhehekeji.com
 * @date 2018/7/2
 */
public class FileUtils {

    /**
     * 保存图片
     *
     * @param file    需要保存的图片
     * @param baseDir 基础地址
     * @return 保存完的文件的路径+文件名
     */
    public static String multipartFileUpload(MultipartFile file, String baseDir, String imgBaseDir) {
        String fileName;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd/");
            baseDir = baseDir + format.format(Calendar.getInstance().getTime());
            File dir = new File(baseDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            fileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            fileName = UUID.randomUUID() + fileName;
            File serverFile = new File(baseDir + fileName);
            file.transferTo(serverFile);
        } catch (Exception e) {
            return "";
        }
        String pic = baseDir + fileName;
        return imgBaseDir+pic.substring(pic.indexOf("/static"));
    }
}
