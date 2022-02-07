package com.dizhongdi.yygh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dizhongdi.yygh.mapper.HospitalSetMapper;
import com.dizhongdi.yygh.model.hosp.HospitalSet;
import com.dizhongdi.yygh.service.HospitalSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * ClassName:HospitalSetServiceImpl
 * Package:com.dizhongdi.yygh.service.impl
 * Description:
 *
 * @Date: 2022/2/6 19:46
 * @Author:dizhongdi
 */
@Service
public class HospitalSetServiceImpl extends ServiceImpl<HospitalSetMapper, HospitalSet> implements HospitalSetService{

    @Autowired
    private HospitalSetMapper hospitalSetMapper;


}

