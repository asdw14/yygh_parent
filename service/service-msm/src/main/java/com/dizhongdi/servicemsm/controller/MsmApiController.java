package com.dizhongdi.servicemsm.controller;

import com.dizhongdi.result.Result;
import com.dizhongdi.servicemsm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * ClassName:MsmApiController
 * Package:com.dizhongdi.servicemsm.controller
 * Description:
 *
 * @Date: 2022/2/19 22:01
 * @Author:dizhongdi
 */
@RestController
@RequestMapping("/api/msm")
public class MsmApiController {
    @Autowired
    MsmService msmService;
    @Autowired
    RedisTemplate redisTemplate;
    //发送手机验证码
    @GetMapping("send/{phone}")
    public Result sendCode(@PathVariable String phone) {
        //从redis获取验证码，如果获取获取到，返回ok
        // key 手机号  value 验证码
        String code = (String) redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) {
            return Result.ok();
        }
        //如果从redis获取不到，
        // 生成验证码，
        code = msmService.getCode();
        if (msmService.send(phone,code)){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return Result.ok();
        }else {
            return Result.fail();
        }
    }
}
