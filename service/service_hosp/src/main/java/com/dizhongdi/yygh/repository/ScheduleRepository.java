package com.dizhongdi.yygh.repository;

import com.dizhongdi.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * ClassName:ScheduleRepository
 * Package:com.dizhongdi.yygh.repository
 * Description:
 *
 * @Date: 2022/2/14 22:39
 * @Author:dizhongdi
 */
@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {
    Schedule getScheduleByHoscodeAndHosScheduleId(String hoscode, String hosScheduleId);

    //根据医院编号 、科室编号和工作日期，查询排班详细信息
    List<Schedule> findScheduleByHoscodeAndDepcodeAndWorkDate(String hoscode, String depcode, Date toDate);

}
