package com.dizhongdi.yygh.controller;

import com.dizhongdi.result.Result;
import com.dizhongdi.yygh.model.hosp.Hospital;
import com.dizhongdi.yygh.service.HospitalService;
import com.dizhongdi.yygh.vo.hosp.HospitalQueryVo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:HospitalController
 * Package:com.dizhongdi.yygh.controller
 * Description:
 *
 * @Date: 2022/2/15 22:44
 * @Author:dizhongdi
 */
@RestController
@CrossOrigin
@RequestMapping("/admin/hosp/hospital")
public class HospitalController {

    @Autowired
    HospitalService hospitalService;

    @ApiOperation(value = "获取分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Integer page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Integer limit,

            @ApiParam(name = "hospitalQueryVo", value = "查询对象", required = false)
                    HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> selectPage = hospitalService.selectPage(page, limit, hospitalQueryVo);
        return Result.ok(selectPage);
    }



    }
