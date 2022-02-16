package com.dizhongdi.yygh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dizhongdi.yygh.cmn.client.DictFeignClient;
import com.dizhongdi.yygh.enums.DictEnum;
import com.dizhongdi.yygh.model.hosp.Hospital;
import com.dizhongdi.yygh.model.hosp.HospitalSet;
import com.dizhongdi.yygh.repository.HospitalRepository;
import com.dizhongdi.yygh.service.HospitalService;
import com.dizhongdi.yygh.service.HospitalSetService;
import com.dizhongdi.yygh.vo.hosp.HospitalQueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
    @Autowired
    private DictFeignClient dictFeignClient;

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

    @Override
    public Page<Hospital> selectPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {

            Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
//0为第一页
            Pageable pageable = PageRequest.of(page-1, limit, sort);

            Hospital hospital = new Hospital();
            BeanUtils.copyProperties(hospitalQueryVo, hospital);

//创建匹配器，即如何使用查询条件
            ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                    .withIgnoreCase(true); //改变默认大小写忽略方式：忽略大小写

            //创建实例
            Example<Hospital> example = Example.of(hospital, matcher);
            Page<Hospital> pages = hospitalRepository.findAll(example, pageable);
            pages.getContent().stream().forEach(item -> {
                this.packHospital(item);
            });
            return pages;

        }

    @Override
    public void updateStatus(String id, Integer status) {
        if(status.intValue() == 0 || status.intValue() == 1) {
            Hospital hospital = hospitalRepository.findById(id).get();
            hospital.setStatus(status);
            hospital.setUpdateTime(new Date());
            hospitalRepository.save(hospital);
        }

    }

    /**
     * 封装数据
     * @param hospital
     * @return
     */

    private Hospital packHospital(Hospital hospital) {
        String hostypeString = dictFeignClient.getName(DictEnum.HOSTYPE.getDictCode(), hospital.getHostype());
//        省市区
        String provinceString = dictFeignClient.getName(hospital.getProvinceCode());
        String cityString = dictFeignClient.getName(hospital.getCityCode());
        String districtString = dictFeignClient.getName(hospital.getDistrictCode());

        hospital.getParam().put("hostypeString", hostypeString);
        hospital.getParam().put("fullAddress", provinceString + cityString + districtString + hospital.getAddress());
        return hospital;
    }
}
