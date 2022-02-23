package com.dizhongdi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dizhongdi.yygh.model.user.Patient;

import java.util.List;

/**
 * ClassName:PatientService
 * Package:com.dizhongdi.service
 * Description:
 *
 * @Date: 2022/2/22 21:59
 * @Author:dizhongdi
 */
public interface PatientService extends IService<Patient> {
    //获取就诊人列表
    List<Patient> findAllUserId(Long userId);

    //根据id获取就诊人信息
    Patient getPatientId(Long id);
}
