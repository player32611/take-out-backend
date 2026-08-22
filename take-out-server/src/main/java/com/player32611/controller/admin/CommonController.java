package com.player32611.controller.admin;

import com.player32611.constant.MessageConstant;
import com.player32611.result.Result;
import com.player32611.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传请求: {}", file);

        try {
            String originalFilename = file.getOriginalFilename();
            String extension =  originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName =  UUID.randomUUID().toString() + extension;

            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e){
            log.error(MessageConstant.UPLOAD_FAILED);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
