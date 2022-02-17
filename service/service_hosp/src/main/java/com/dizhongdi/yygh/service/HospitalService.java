package com.dizhongdi.yygh.service;

import com.dizhongdi.yygh.model.hosp.Hospital;
import com.dizhongdi.yygh.model.hosp.HospitalSet;
import com.dizhongdi.yygh.vo.hosp.HospitalQueryVo;
import org.springframework.data.domain.Page;

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

    /**
     * 分页查询
     * @param page 当前页码
     * @param limit 每页记录数
     * @param hospitalQueryVo 查询条件
     * @return
     */
    Page<Hospital> selectPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    /**
     * 更新上线状态
     */

    void updateStatus(String id, Integer status);

    /**
     * 医院详情
     * @param id
     * @return
    */
    Map<String, Object> showHospById(String id);
}
