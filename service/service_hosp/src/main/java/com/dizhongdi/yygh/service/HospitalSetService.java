package com.dizhongdi.yygh.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dizhongdi.yygh.model.hosp.HospitalSet;

/**
 * ClassName:HospitalSetService
 * Package:com.dizhongdi.yygh.service
 * Description:
 *
 * @Date: 2022/2/6 17:15
 * @Author:dizhongdi
 */
public interface HospitalSetService extends IService<HospitalSet> {
    String getSignKey(String hoscode);

}

