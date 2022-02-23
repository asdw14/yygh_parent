package com.dizhongdi.yygh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dizhongdi.yygh.model.hosp.BookingRule;
import com.dizhongdi.yygh.model.hosp.Department;
import com.dizhongdi.yygh.model.hosp.Schedule;
import com.dizhongdi.yygh.repository.DepartmentRepository;
import com.dizhongdi.yygh.service.DepartmentService;
import com.dizhongdi.yygh.service.HospitalService;
import com.dizhongdi.yygh.vo.hosp.BookingScheduleRuleVo;
import com.dizhongdi.yygh.vo.hosp.DepartmentQueryVo;
import com.dizhongdi.yygh.vo.hosp.DepartmentVo;
import org.apache.poi.ss.formula.functions.T;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
//    上传科室信息
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
//    分页查询
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

    //根据医院编号，查询医院所有科室列表
    @Override
    public List<DepartmentVo> findDeptTree(String hoscode) {
        //创建list集合，用于最终数据封装
        List<DepartmentVo> result = new ArrayList<>();
        //根据医院编号，查询医院所有科室信息
        Department departmentQuery  = new Department();
        departmentQuery.setHoscode(hoscode);
        List<Department> departmentList = departmentRepository.findAll(Example.of(departmentQuery ));
        //根据大科室编号  bigcode 分组，获取每个大科室里面下级子科室
        //根据大科室编号  bigcode 分组，获取每个大科室里面下级子科室
        Map<String, List<Department>> deparmentMap =
                departmentList.stream().collect(Collectors.groupingBy(Department::getBigcode));
        //遍历map集合 deparmentMap
        for (Map.Entry<String,List<Department>> entry : deparmentMap.entrySet()){
            //大科室编号
            String bigCode = entry.getKey();
            //大科室编号对应的全局数据
            List<Department> departmentList1 = entry.getValue();
            //封装大科室
            DepartmentVo departmentVo = new DepartmentVo();
            departmentVo.setDepcode(bigCode);
            departmentVo.setDepname(departmentList1.get(0).getBigname());

            //封装小科室
            List<DepartmentVo> childern = new ArrayList<>();
            for (Department department: departmentList1) {
                DepartmentVo departmentVo2 = new DepartmentVo();
                departmentVo2.setDepcode(department.getDepcode());
                departmentVo2.setDepname(department.getDepname());
                //封装到list集合
                childern.add(departmentVo2);
            }
            //把小科室list集合放到大科室children里面
            departmentVo.setChildren(childern);
            //放到最终result里面
            result.add(departmentVo);

        }
        //返回
        return result;
    }

    //根据科室编号，和医院编号，查询科室名称
    @Override
    public String getDepName(String hoscode, String depcode) {
        Department department = departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode, depcode);
        if(department != null) {
            return department.getDepname();
        }
        return null;
    }

    @Override
    public Department getDepartment(String hoscode, String depcode) {
        return departmentRepository.getDepartmentByHoscodeAndDepcode(hoscode,depcode);
    }


}












