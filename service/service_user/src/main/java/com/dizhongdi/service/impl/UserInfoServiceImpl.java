package com.dizhongdi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dizhongdi.mapper.UserInfoMapper;
import com.dizhongdi.service.UserInfoService;
import com.dizhongdi.yygh.model.user.UserInfo;
import org.springframework.stereotype.Service;

/**
 * ClassName:UserInfoServiceImpl
 * Package:com.dizhongdi.service.impl
 * Description:
 *
 * @Date: 2022/2/19 16:45
 * @Author:dizhongdi
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
