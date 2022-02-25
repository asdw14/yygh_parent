package com.dizhongdi.controller.api;

import com.dizhongdi.result.Result;
import com.dizhongdi.yygh.service.UserInfoService;
import com.dizhongdi.utils.AuthContextHolder;
import com.dizhongdi.yygh.model.user.UserInfo;
import com.dizhongdi.yygh.vo.user.LoginVo;
import com.dizhongdi.yygh.vo.user.UserAuthVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

    @ApiOperation(value = "用户登录")
    @PostMapping("login")
    public Result login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
//        loginVo.setIp(IpUtil.getIpAddr(request));
        Map<String, Object> info = userInfoService.login(loginVo);
        return Result.ok(info);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("loginEmail")
    public Result loginEmail(@RequestBody LoginVo loginVo, HttpServletRequest request) {
//        loginVo.setIp(IpUtil.getIpAddr(request));
        Map<String, Object> info = userInfoService.loginEmail(loginVo);
        return Result.ok(info);
    }



    //用户认证接口
    @PostMapping("auth/userAuth")
    public Result userAuth(@RequestBody UserAuthVo userAuthVo, HttpServletRequest request) {
        //传递两个参数，第一个参数用户id，第二个参数认证数据vo对象
        userInfoService.userAuth(AuthContextHolder.getUserId(request),userAuthVo);
        return Result.ok();
    }

    //通过id获取用户信息接口
    @GetMapping("auth/getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
        UserInfo userInfo = userInfoService.getById(AuthContextHolder.getUserId(request));
        return Result.ok(userInfo);
    }

    }
