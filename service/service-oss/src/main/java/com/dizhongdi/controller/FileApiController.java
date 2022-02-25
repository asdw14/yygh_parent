package com.dizhongdi.controller;

import com.dizhongdi.result.Result;
import com.dizhongdi.yygh.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:FileApiController
 * Package:com.dizhongdi.controller
 * Description:
 *
 * @Date: 2022/2/21 22:43
 * @Author:dizhongdi
 */
@RestController
@RequestMapping("/api/oss/file")
public class FileApiController {
    @Autowired
    FileService fileService;

    //上传文件到阿里云oss
    @PostMapping("fileUpload")
    public Result fileUpload(MultipartFile file) {
        //获取上传文件
        String url = fileService.upload(file);
        //返回上传路径
        return Result.ok(url);
    }
}