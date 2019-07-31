package com.bysj.controller;

import java.io.File;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import com.bysj.utils.CommonUtils;
import com.bysj.utils.FileUtil;
import com.bysj.utils.MessageHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//文件图片处理类
@Controller
@RequestMapping("/file")
public class FileController {

    @Value("${customize.upload-path}")
    private String UPLOAD_PATH;  //"D:/upload_files/"

    @ResponseBody
    @PostMapping("/upload")
    public MessageHelper upload(@RequestParam("file") MultipartFile file) {
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
        return MessageHelper.ok(map);
    }

    @ResponseBody
    @PostMapping("/delete")
    public MessageHelper delete(@RequestBody Map<String, String> paramsMap) {
        File file = new File(UPLOAD_PATH + paramsMap.get("name"));
        if (file.exists()) {
            file.delete();
        }
        return MessageHelper.ok();
    }
}
