package com.dizhongdi.yygh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dizhongdi.yygh.model.hosp.Department;
import com.dizhongdi.yygh.repository.DepartmentRepository;
import com.dizhongdi.yygh.service.DepartmentService;
import com.dizhongdi.yygh.vo.hosp.DepartmentQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * ClassName:DepartmentServiceImpl
 * Package:com.dizhongdi.yygh.service.impl
 * Description:
 *
 * @Date: 2022/2/13 22:41
 * @Author:dizhongdi
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public void save(Map<String, Object> paramMap) {
        Department department = JSONObject.parseObject(JSON.toJSONString(paramMap), Department.class);
        Department targetDepartment = departmentRepository.getDepartmentByHoscodeAndDepcode(department.getHoscode(), department.getHoscode());
        if (targetDepartment!=null){
            department.setCreateTime(targetDepartment.getCreateTime());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }else {
            department.setCreateTime(new Date());
            department.setUpdateTime(new Date());
            department.setIsDeleted(0);
            departmentRepository.save(department);
        }
    }

    @Override
    public Page<Department> selectPage(int page, int limit, DepartmentQueryVo departmentQueryVo) {
            Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
//0为第一页
            Pageable pageable = PageRequest.of(page-1, limit, sort);

            Department department = new Department();
            BeanUtils.copyProperties(departmentQueryVo, department);
            department.setIsDeleted(0);

//创建匹配器，即如何使用查询条件
            ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                    .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写

//创建实例
            Example<Department> example = Example.of(department, matcher);
            Page<Department> pages = departmentRepository.findAll(example, pageable);
            return pages;
    }

    /**
     * 删除科室
     * @param hoscode
     * @param depcode
     */

    @Override
    public void remove(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if (department!=null){
            departmentRepository.deleteById(department.getId());
        }
    }


}
