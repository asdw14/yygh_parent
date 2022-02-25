package com.dizhongdi.yygh.user.client;

import com.dizhongdi.yygh.model.user.Patient;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName:PatientFeignClient
 * Package:com.dizhongdi.yygh.user.client
 * Description:
 *
 * @Date: 2022/2/24 15:54
 * @Author:dizhongdi
 */
@FeignClient(value = "service-user")
@Repository
public interface PatientFeignClient {
    //获取就诊人
    @GetMapping("/api/user/patient/inner/get/{id}")
    Patient getPatient(@PathVariable("id") Long id);

}
