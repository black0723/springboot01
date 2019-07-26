package com.bysj.controller;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import com.bysj.utils.CommonUtils;
import com.bysj.utils.FileUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//文件图片处理类
@Controller
@RequestMapping("/file")
public class FileController {

    @Value("${customize.upload-path}")
    private String UPLOAD_PATH;  //"D:/upload_files/"

    @ResponseBody
    @PostMapping("/upload")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        if (!file.isEmpty()) {
            String originalName = file.getOriginalFilename();
            String fileName = Instant.now().getEpochSecond() + CommonUtils.getExtension(originalName);
            map.put("originalName", originalName);
            try {
                FileUtil.uploadFile(file.getBytes(), UPLOAD_PATH, fileName);
                map.put("fileName", fileName);
                map.put("fileUrlSuffix", "/upload-files/" + fileName);
                map.put("status", 0);
            } catch (Exception e) {
                map.put("status", 1);
                map.put("error", e.getMessage());
                e.printStackTrace();
            }
        }
        return map;
    }
}
