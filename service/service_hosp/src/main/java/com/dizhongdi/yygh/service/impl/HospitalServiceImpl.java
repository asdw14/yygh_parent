package com.dizhongdi.yygh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dizhongdi.yygh.model.hosp.Hospital;
import com.dizhongdi.yygh.model.hosp.HospitalSet;
import com.dizhongdi.yygh.repository.HospitalRepository;
import com.dizhongdi.yygh.service.HospitalService;
import com.dizhongdi.yygh.service.HospitalSetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * ClassName:HospitalServiceImpl
 * Package:com.dizhongdi.yygh.service.impl
 * Description:
 *
 * @Date: 2022/2/13 16:45
 * @Author:dizhongdi
 */
@Service
@Slf4j
public class HospitalServiceImpl implements HospitalService {
    @Autowired
    private HospitalRepository hospitalRepository;

    @Override
    public void save(Map<String, Object> switchMap) {
        log.info(JSONObject.toJSONString(switchMap));
        Hospital hospital = JSON.parseObject(JSON.toJSONString(switchMap), Hospital.class);

        //判断是否存在
        Hospital tagerHospital = hospitalRepository.getHospitalByHoscode(hospital.getHoscode());
        if (tagerHospital!=null){
            hospital.setStatus(tagerHospital.getStatus());
            hospital.setIsDeleted(0);
            hospital.setCreateTime(hospital.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospitalRepository.save(hospital);
        }else {
            //0：未上线 1：已上线
            hospital.setStatus(0);
            hospital.setIsDeleted(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospitalRepository.save(hospital);
        }
    }

    @Override
    public Hospital getByHoscode(String hoscode) {
        return hospitalRepository.getHospitalByHoscode(hoscode);
    }
}
