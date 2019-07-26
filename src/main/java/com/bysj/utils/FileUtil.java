package com.bysj.utils;

import java.io.File;
import java.io.FileOutputStream;

public class FileUtil {

    /**
     * 上传文件
     *
     * @param file     文件
     * @param saveDir  文件夹
     * @param fileName 文件名
     * @throws Exception
     */
    public static void uploadFile(byte[] file, String saveDir, String fileName) throws Exception {
        File targetFile = new File(saveDir);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(saveDir + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
}
