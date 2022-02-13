package com.dizhongdi.yygh.controller.api;

import com.dizhongdi.exception.YyghException;
import com.dizhongdi.result.Result;
import com.dizhongdi.result.ResultCodeEnum;
import com.dizhongdi.service_util.helper.HttpRequestHelper;
import com.dizhongdi.service_util.utils.MD5;
import com.dizhongdi.yygh.model.hosp.HospitalSet;
import com.dizhongdi.yygh.service.DepartmentService;
import com.dizhongdi.yygh.service.HospitalService;
import com.dizhongdi.yygh.service.HospitalSetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * ClassName:ApiController
 * Package:com.dizhongdi.yygh.controller.api
 * Description:
 *
 * @Date: 2022/2/13 16:46
 * @Author:dizhongdi
 */
@Api(tags = "医院管理API接口")
@RequestMapping("/api/hosp")
@RestController
public class ApiController {
    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private HospitalSetService hospitalSetService;
    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "上传医院")
    @PostMapping("saveHospital")
    public Result saveHospital(HttpServletRequest request) {
        Map<String, Object> switchMap = HttpRequestHelper.switchMap(request.getParameterMap());

//        获取系统传递来的签名，签名通过MD5加密
        String sign = (String) switchMap.get("sign");
//        获取传过来的医院编号，通过数据库查询签名
        String hoscode = (String) switchMap.get("hoscode");
        String SignKey = hospitalSetService.getSignKey(hoscode);

//        把数据库获取到的Sign进行MD5加密
        String SignKeyMD5 = MD5.encrypt(SignKey);
        String logoData = (String) switchMap.get("logoData");
        //传输过程中“+”转换为了“ ”，因此我们要转换回来
        logoData = logoData.replaceAll(" ", "+");
        System.out.println(logoData);
        switchMap.put("logoData", logoData);
//        判断签名是否一致
        if (!sign.equals(SignKeyMD5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        hospitalService.save(switchMap);

        return Result.ok();
    }

    @ApiOperation(value = "获取医院信息")
    @PostMapping("hospital/show")
    public Result hospital(HttpServletRequest request) {
        Map<String, Object> switchMap = HttpRequestHelper.switchMap(request.getParameterMap());

//        获取系统传递来的签名，签名通过MD5加密
        String sign = (String) switchMap.get("sign");
//        获取传过来的医院编号，通过数据库查询签名
        String hoscode = (String) switchMap.get("hoscode");
        String SignKey = hospitalSetService.getSignKey(hoscode);

//        把数据库获取到的Sign进行MD5加密
        String SignKeyMD5 = MD5.encrypt(SignKey);
//        判断签名是否一致
        if (!sign.equals(SignKeyMD5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        return Result.ok(hospitalService.getByHoscode(hoscode));
    }

    @ApiOperation(value = "上传科室")
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request) {
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
//        获取系统传递来的签名，签名通过MD5加密
        String sign = (String) paramMap.get("sign");
//        获取传过来的医院编号，通过数据库查询签名
        String hoscode = (String) paramMap.get("hoscode");
        String SignKey = hospitalSetService.getSignKey(hoscode);
//        把数据库获取到的Sign进行MD5加密
        String SignKeyMD5 = MD5.encrypt(SignKey);
//        判断签名是否一致
        if (!sign.equals(SignKeyMD5)) {
            throw new YyghException(ResultCodeEnum.SIGN_ERROR);
        }
        departmentService.save(paramMap);
        return Result.ok();
    }
}