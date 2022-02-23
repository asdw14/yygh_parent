package com.dizhongdi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dizhongdi.yygh.model.user.Patient;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:PatientMapper
 * Package:com.dizhongdi.mapper
 * Description:
 *
 * @Date: 2022/2/22 21:56
 * @Author:dizhongdi
 */
@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
}
