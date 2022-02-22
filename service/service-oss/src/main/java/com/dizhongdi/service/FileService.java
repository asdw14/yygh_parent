package com.dizhongdi.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName:FileService
 * Package:com.dizhongdi.service
 * Description:
 *
 * @Date: 2022/2/21 22:42
 * @Author:dizhongdi
 */
public interface FileService {
    //上传文件到阿里云oss
    String upload(MultipartFile file);

}
