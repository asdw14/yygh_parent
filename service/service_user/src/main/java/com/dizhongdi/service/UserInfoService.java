package com.dizhongdi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dizhongdi.yygh.model.user.UserInfo;
import com.dizhongdi.yygh.vo.user.LoginVo;
import com.dizhongdi.yygh.vo.user.UserAuthVo;
import com.dizhongdi.yygh.vo.user.UserInfoQueryVo;

import java.util.List;
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

    //用户认证
    void userAuth(Long userId, UserAuthVo userAuthVo);

    Map<String, Object> loginEmail(LoginVo loginVo);

    //用户列表（条件查询带分页）
    Page<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo);
    //     用户锁定 0：锁定 1：正常
    void lock(Long userId, Integer status);

    /**
     * 详情
     * @param userId
     * @return
     */
    Map<String, Object> show(Long userId);

    /**
     * 认证审批
     * @param userId
     * @param authStatus 2：通过 -1：不通过
     */
    void approval(Long userId, Integer authStatus);


}
