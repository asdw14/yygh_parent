package com.dizhongdi.yygh.repository;

import com.dizhongdi.yygh.model.hosp.Department;
import com.dizhongdi.yygh.model.hosp.Hospital;
import com.dizhongdi.yygh.model.hosp.HospitalSet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName:HospitalRepository
 * Package:com.dizhongdi.yygh.repository
 * Description:
 *
 * @Date: 2022/2/13 16:44
 * @Author:dizhongdi
 */
@Repository
public interface HospitalRepository extends MongoRepository<Hospital,String> {
    Hospital getHospitalByHoscode(String hoscode);

}
