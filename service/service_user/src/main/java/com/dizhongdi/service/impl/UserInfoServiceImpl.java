package com.dizhongdi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dizhongdi.exception.YyghException;
import com.dizhongdi.helper.JwtHelper;
import com.dizhongdi.mapper.UserInfoMapper;
import com.dizhongdi.result.ResultCodeEnum;
import com.dizhongdi.service.UserInfoService;
import com.dizhongdi.yygh.model.user.UserInfo;
import com.dizhongdi.yygh.vo.user.LoginVo;
import com.dizhongdi.yygh.vo.user.UserInfoQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public Map<String, Object> login(LoginVo loginVo) {
        String phone = loginVo.getPhone();
        String code = loginVo.getCode();
        //校验参数
        if (StringUtils.isEmpty(phone)||StringUtils.isEmpty(code)){
            throw new YyghException(ResultCodeEnum.PARAM_ERROR);
        }

        //校验校验验证码
        String mobleCode = (String) redisTemplate.opsForValue().get(phone);
        if(!code.equals(mobleCode)) {
            throw new YyghException(ResultCodeEnum.CODE_ERROR);
        }else {

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone",loginVo.getPhone());
        UserInfo userInfo = baseMapper.selectOne(wrapper);
        if (userInfo==null){    //第一次使用这个手机号登录
            //添加数据到数据库
            userInfo = new UserInfo();
            userInfo.setName("");
            userInfo.setPhone(loginVo.getPhone());
            userInfo.setStatus(1);
            baseMapper.insert(userInfo);
        }
        //判断验证码是否一致

        //校验是否被禁用
        if (userInfo.getStatus() == 0){
            throw new YyghException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        //不是第一次直接登录
        HashMap<String, Object> map = new HashMap<>();
        //返回登录用户名
        String name = userInfo.getName();
        if (StringUtils.isEmpty(name)){
            name = userInfo.getNickName();
        }
        if (StringUtils.isEmpty(name)){
            name = userInfo.getPhone();
        }
        map.put("name",name);

        //返回token信息
        String token = JwtHelper.createToken(userInfo.getId(), name);
        map.put("token",token);
        //返回登录信息
        return map;
    }
    }

    //根据openId查询用户信息
    @Override
    public UserInfo getByOpenid(String openId) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("openid",openId);
        return baseMapper.selectOne(wrapper);
    }
}
