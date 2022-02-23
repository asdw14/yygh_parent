package com.dizhongdi.servicemsm.service;

/**
 * ClassName:MsmService
 * Package:com.dizhongdi.servicemsm.service
 * Description:
 *
 * @Date: 2022/2/19 22:03
 * @Author:dizhongdi
 */
public interface MsmService {
    boolean send(String phone,String code);
    String getCode();

    void sendEmail(String email, String code);
}
