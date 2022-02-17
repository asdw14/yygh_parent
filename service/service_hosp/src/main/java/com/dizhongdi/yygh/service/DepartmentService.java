package com.dizhongdi.yygh.service;

import com.dizhongdi.yygh.model.hosp.Department;
import com.dizhongdi.yygh.vo.hosp.DepartmentQueryVo;
import com.dizhongdi.yygh.vo.hosp.DepartmentVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * ClassName:DepartmentService
 * Package:com.dizhongdi.yygh.service
 * Description:
 *
 * @Date: 2022/2/13 22:41
 * @Author:dizhongdi
 */
public interface DepartmentService {

    /**
     * 上传科室信息
     * @param paramMap
     */
    void save(Map<String, Object> paramMap);
    /**
     * 分页查询
     * @param page 当前页码
     * @param limit 每页记录数
     * @param departmentQueryVo 查询条件
     * @return
     */

    Page<Department> selectPage(int page, int limit, DepartmentQueryVo departmentQueryVo);


    void remove(String hoscode, String depcode);

    //根据医院编号，查询医院所有科室列表
    List<DepartmentVo> findDeptTree(String hoscode);
    //根据科室编号，和医院编号，查询科室名称
    String getDepName(String hoscode, String depcode);

}
