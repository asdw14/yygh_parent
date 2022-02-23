package com.dizhongdi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dizhongdi.exception.YyghException;
import com.dizhongdi.helper.JwtHelper;
import com.dizhongdi.mapper.UserInfoMapper;
import com.dizhongdi.result.ResultCodeEnum;
import com.dizhongdi.service.PatientService;
import com.dizhongdi.service.UserInfoService;
import com.dizhongdi.yygh.enums.AuthStatusEnum;
import com.dizhongdi.yygh.model.user.Patient;
import com.dizhongdi.yygh.model.user.UserInfo;
import com.dizhongdi.yygh.vo.user.LoginVo;
import com.dizhongdi.yygh.vo.user.UserAuthVo;
import com.dizhongdi.yygh.vo.user.UserInfoQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
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
    @Autowired
    PatientService patientService;

    //手机登录
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

    //用户认证
    @Override
    public void userAuth(Long userId, UserAuthVo userAuthVo) {
        //根据用户id查询用户信息
        UserInfo userInfo = baseMapper.selectById(userId);
        //设置认证信息
        //认证人姓名
        userInfo.setName(userAuthVo.getName());
        //其他认证信息
        userInfo.setCertificatesType(userAuthVo.getCertificatesType());
        userInfo.setCertificatesNo(userAuthVo.getCertificatesNo());
        userInfo.setCertificatesUrl(userAuthVo.getCertificatesUrl());
        userInfo.setAuthStatus(AuthStatusEnum.AUTH_RUN.getStatus());
        baseMapper.updateById(userInfo);
    }

//邮箱登录
    @Override
    public Map<String, Object> loginEmail(LoginVo loginVo) {
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
            wrapper.eq("phone", loginVo.getPhone());
            UserInfo userInfo = baseMapper.selectOne(wrapper);
            if (userInfo == null) {    //第一次使用这个手机号登录
                //添加数据到数据库
                userInfo = new UserInfo();
                userInfo.setName("");
                userInfo.setPhone(loginVo.getPhone());
                userInfo.setStatus(1);
                baseMapper.insert(userInfo);
            }

            //校验是否被禁用
            if (userInfo.getStatus() == 0) {
                throw new YyghException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
            }

            //不是第一次直接登录
            HashMap<String, Object> map = new HashMap<>();
            //返回登录用户名
            String name = userInfo.getName();
            if (StringUtils.isEmpty(name)) {
                name = userInfo.getNickName();
            }
            if (StringUtils.isEmpty(name)) {
                name = userInfo.getPhone();
            }
            map.put("name", name);

            //返回token信息
            String token = JwtHelper.createToken(userInfo.getId(), name);
            map.put("token", token);
            redisTemplate.delete(phone);
            //返回登录信息
            return map;
        }
    }

    //用户列表（条件查询带分页）
    @Override
    public Page<UserInfo> selectPage(Page<UserInfo> pageParam, UserInfoQueryVo userInfoQueryVo) {
        //UserInfoQueryVo获取条件值
        String name = userInfoQueryVo.getKeyword(); //用户名称
        Integer status = userInfoQueryVo.getStatus();//用户状态
        Integer authStatus = userInfoQueryVo.getAuthStatus(); //认证状态
        String createTimeBegin = userInfoQueryVo.getCreateTimeBegin(); //开始时间
        String createTimeEnd = userInfoQueryVo.getCreateTimeEnd(); //结束时间
        //对条件值进行非空判断
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(name)) {
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(status)) {
            wrapper.eq("status",status);
        }
        if(!StringUtils.isEmpty(authStatus)) {
            wrapper.eq("auth_status",authStatus);
        }
        if(!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge("create_time",createTimeBegin);
        }
        if(!StringUtils.isEmpty(createTimeEnd)) {
            wrapper.le("create_time",createTimeEnd);
        }

        Page<UserInfo> pages = baseMapper.selectPage(pageParam, wrapper);
        pages.getRecords().stream().forEach(item -> {
            this.packageUserInfo(item);
        });
        return pages;
    }

//     用户锁定 0：锁定 1：正常
    @Override
    public void lock(Long userId, Integer status) {
        if (status == 0 || status == 1 ){
            UserInfo userInfo = baseMapper.selectById(userId);
            userInfo.setStatus(status);
            baseMapper.updateById(userInfo);
        }
    }

    //用户详情
    @Override
    public Map<String, Object> show(Long userId) {
        HashMap<String, Object> map = new HashMap<>();
        //根据userid查询用户信息
        UserInfo userInfo = baseMapper.selectById(userId);
        map.put("userInfo",this.packageUserInfo(userInfo));
        //根据userid查询就诊人信息
        List<Patient> patients = patientService.findAllUserId(userId);
        map.put("patientList",patients);
        return map;
    }

    //认证审批  2通过  -1不通过
    @Override
    public void approval(Long userId, Integer authStatus) {
        if (authStatus.intValue()==2 || authStatus.intValue()==-1){
            UserInfo userInfo = baseMapper.selectById(userId);
            userInfo.setAuthStatus(authStatus);
            baseMapper.updateById(userInfo);
        }

    }

    //编号变成对应值封装
    private UserInfo packageUserInfo(UserInfo userInfo) {
        //处理认证状态编码
        userInfo.getParam().put("authStatusString",AuthStatusEnum.getStatusNameByStatus(userInfo.getAuthStatus()));
        //处理用户状态 0  1
        String statusString = userInfo.getStatus().intValue() ==0 ? "锁定" : "正常";
        userInfo.getParam().put("statusString",statusString);
        return userInfo;
    }
}
