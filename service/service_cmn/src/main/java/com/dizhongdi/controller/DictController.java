package com.dizhongdi.controller;

import com.dizhongdi.result.Result;
import com.dizhongdi.service.DictService;
import com.dizhongdi.yygh.model.cmn.Dict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName:DictController
 * Package:com.dizhongdi.controller
 * Description:
 *
 * @Date: 2022/2/12 17:21
 * @Author:dizhongdi
 */
@RestController
@Api(description = "数据字典接口")
@RequestMapping("/admin/cmn/dict")
@CrossOrigin
public class DictController {

    @Autowired
    DictService dictService;
    //根据数据id查询子数据列表
    @ApiOperation(value = "根据数据id查询子数据列表")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Dict> dicts = dictService.findChlidData(id);
        return Result.ok(dicts);
    }

    @ApiOperation(value="导出")
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response) {
        dictService.exportData(response);
    }

    @ApiOperation(value="导入")
    @PostMapping(value = "importData")
    public void exportData(MultipartFile file) {
        dictService.improtData(file);
    }

}
