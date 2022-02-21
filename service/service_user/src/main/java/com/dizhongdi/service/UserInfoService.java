package com.dizhongdi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dizhongdi.yygh.model.user.UserInfo;
import com.dizhongdi.yygh.vo.user.LoginVo;

import java.util.Map;

/**
 * ClassName:UserInfoService
 * Package:com.dizhongdi.service
 * Description:
 *
 * @Date: 2022/2/19 16:44
 * @Author:dizhongdi
 */
public interface UserInfoService extends IService<UserInfo> {
    Map<String, Object> login(LoginVo loginVo);

    //根据openId查询用户信息
    UserInfo getByOpenid(String openId);

}
