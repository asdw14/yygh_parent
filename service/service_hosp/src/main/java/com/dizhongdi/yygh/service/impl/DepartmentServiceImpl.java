package com.dizhongdi.yygh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dizhongdi.yygh.model.hosp.Department;
import com.dizhongdi.yygh.repository.DepartmentRepository;
import com.dizhongdi.yygh.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
