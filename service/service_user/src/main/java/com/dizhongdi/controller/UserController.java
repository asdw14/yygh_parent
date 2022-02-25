package com.dizhongdi.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dizhongdi.result.Result;
import com.dizhongdi.yygh.service.UserInfoService;
import com.dizhongdi.yygh.model.user.UserInfo;
import com.dizhongdi.yygh.vo.user.UserInfoQueryVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName:UserController
 * Package:com.dizhongdi.controller
 * Description:
 *
 * @Date: 2022/2/23 16:58
 * @Author:dizhongdi
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    UserInfoService userInfoService;
    //用户列表（条件查询带分页）
    @GetMapping("{page}/{limit}")
    public Result list(@PathVariable Long page, @PathVariable Long limit, UserInfoQueryVo userInfoQueryVo) {
        Page<UserInfo> pageParam = new Page<>(page, limit);
        Page<UserInfo> pageModel = userInfoService.selectPage(pageParam,userInfoQueryVo);
        return Result.ok(pageModel);
    }
    @ApiOperation(value = "锁定")
    @GetMapping("lock/{userId}/{status}")
    public Result lock(
            @PathVariable("userId") Long userId,
            @PathVariable("status") Integer status){
        userInfoService.lock(userId, status);
        return Result.ok();
    }
    //用户详情
    @GetMapping("show/{userId}")
    public Result show(@PathVariable("userId") Long userId){
        Map<String, Object> show = userInfoService.show(userId);
        return Result.ok(show);
    }
    //认证审批
    @GetMapping("approval/{userId}/{authStatus}")
    public Result approval(@PathVariable Long userId,@PathVariable Integer authStatus) {
        userInfoService.approval(userId,authStatus);
        return Result.ok();
    }

}
