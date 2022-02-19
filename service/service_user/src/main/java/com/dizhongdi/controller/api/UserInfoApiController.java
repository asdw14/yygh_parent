package com.dizhongdi.controller.api;

import com.dizhongdi.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:UserInfoApiController
 * Package:com.dizhongdi.controller.api
 * Description:
 *
 * @Date: 2022/2/19 16:47
 * @Author:dizhongdi
 */
@RestController
@RequestMapping("/api/user")
public class UserInfoApiController {
    @Autowired
    private UserInfoService userInfoService;




}
