package com.dizhongdi.servicemsm.service;

import com.dizhongdi.yygh.vo.msm.MsmVo;

/**
 * ClassName:MsmService
 * Package:com.dizhongdi.servicemsm.service
 * Description:
 *
 * @Date: 2022/2/19 22:03
 * @Author:dizhongdi
 */
public interface MsmService {
//    手机验证码
    boolean send(String phone,String code);
    String getCode();

    //邮箱验证码
    void sendEmail(String email, String code);

//    rabbitMQ验证码
    boolean send(MsmVo msmVo);
}
