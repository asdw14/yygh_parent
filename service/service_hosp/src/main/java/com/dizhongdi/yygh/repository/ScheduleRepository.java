package com.dizhongdi.yygh.repository;

import com.dizhongdi.yygh.model.hosp.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

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
}
