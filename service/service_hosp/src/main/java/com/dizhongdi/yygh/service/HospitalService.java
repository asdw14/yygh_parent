package com.dizhongdi.yygh.service;

import com.dizhongdi.yygh.model.hosp.Hospital;
import com.dizhongdi.yygh.model.hosp.HospitalSet;

import java.util.Map;

/**
 * ClassName:HospitalService
 * Package:com.dizhongdi.yygh.service
 * Description:
 *
 * @Date: 2022/2/13 16:45
 * @Author:dizhongdi
 */

public interface HospitalService {
    /**
     * 上传医院信息
     * @param switchMap
     */
    void save(Map<String, Object> switchMap);

    Hospital getByHoscode(String hoscode);
}
