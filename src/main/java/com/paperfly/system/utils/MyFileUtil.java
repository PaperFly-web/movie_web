package com.paperfly.system.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class MyFileUtil {
    public static void uploadFile(String  filePath, MultipartFile file) {

        // 文件对象
        File dest = new File(filePath);
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 将上传文件写到服务器上指定的文件
            file.transferTo(dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getExtName(String filePath){
        String fileType=filePath.substring(filePath.lastIndexOf("."));
        return fileType;
    }


}
