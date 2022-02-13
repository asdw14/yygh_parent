package com.dizhongdi.yygh.repository;

import com.dizhongdi.yygh.model.hosp.Department;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * ClassName:DepartmentRepository
 * Package:com.dizhongdi.yygh.repository
 * Description:
 *
 * @Date: 2022/2/13 22:39
 * @Author:dizhongdi
 */
public interface DepartmentRepository extends MongoRepository<Department,String> {
    Department getDepartmentByHoscodeAndDepcode(String hoscode, String depcode);


}
