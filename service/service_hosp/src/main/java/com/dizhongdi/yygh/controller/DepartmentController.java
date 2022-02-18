package com.dizhongdi.yygh.controller;

import com.dizhongdi.result.Result;
import com.dizhongdi.yygh.service.DepartmentService;
import com.dizhongdi.yygh.vo.hosp.DepartmentVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName:DepartmentController
 * Package:com.dizhongdi.yygh.controller
 * Description:
 *
 * @Date: 2022/2/17 19:57
 * @Author:dizhongdi
 */
@RestController
@RequestMapping("/admin/hosp/department")
//@CrossOrigin
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;


    //根据医院编号，查询医院所有科室列表
    @ApiOperation(value = "查询医院所有科室列表")
    @GetMapping("getDeptList/{hoscode}")
    public Result getDeptList(@PathVariable String hoscode) {
        List<DepartmentVo> list =  departmentService.findDeptTree(hoscode);
        return Result.ok(list);
    }


}
