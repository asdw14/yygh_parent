package com.dizhongdi.yygh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dizhongdi.exception.YyghException;
import com.dizhongdi.result.ResultCodeEnum;
import com.dizhongdi.yygh.mapper.HospitalSetMapper;
import com.dizhongdi.yygh.model.hosp.HospitalSet;
import com.dizhongdi.yygh.service.HospitalSetService;
import com.dizhongdi.yygh.vo.order.SignInfoVo;
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

    //        获取传过来的医院编号，通过数据库查询签名
    @Override
    public String getSignKey(String hoscode) {
        QueryWrapper<HospitalSet> wrapper = new QueryWrapper<>();
        wrapper.eq("hoscode",hoscode);
        HospitalSet hospitalSet = hospitalSetMapper.selectOne(wrapper);
        return hospitalSet.getSignKey();
    }

    //获取医院签名信息
    @Override
    public SignInfoVo getSignInfoVo(String hoscode) {
        SignInfoVo signInfoVo = new SignInfoVo();
        HospitalSet hospitalSet = baseMapper.selectOne(new QueryWrapper<HospitalSet>().eq("hoscode", hoscode));
        if (hospitalSet==null){
            throw new YyghException(ResultCodeEnum.HOSPITAL_OPEN);
        }
        signInfoVo.setSignKey(hospitalSet.getSignKey());
        signInfoVo.setApiUrl(hospitalSet.getApiUrl());
        return signInfoVo;
    }
}

