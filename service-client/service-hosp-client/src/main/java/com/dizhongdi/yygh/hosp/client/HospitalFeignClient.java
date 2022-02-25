package com.dizhongdi.yygh.hosp.client;

import com.dizhongdi.yygh.vo.hosp.ScheduleOrderVo;
import com.dizhongdi.yygh.vo.order.SignInfoVo;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName:HospitalFeignClient
 * Package:com.dizhongdi.yygh.hosp.client
 * Description:
 *
 * @Date: 2022/2/24 16:24
 * @Author:dizhongdi
 */
@FeignClient("service-hosp")
@Repository
public interface HospitalFeignClient {
    //    获取医院签名信息
    @GetMapping("/admin/hosp/hospitalSet/inner/getSignInfoVo/{hoscode}")
    SignInfoVo getSignInfoVo(@PathVariable("hoscode") String hoscode);

    //根据排班id获取预约下单数据
    @GetMapping("/admin/hosp/schedule/inner/getScheduleOrderVo/{scheduleId}")
    ScheduleOrderVo getScheduleOrderVo(@PathVariable("scheduleId") String scheduleId);
}
