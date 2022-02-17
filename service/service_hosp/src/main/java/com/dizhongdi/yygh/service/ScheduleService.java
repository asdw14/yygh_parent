package com.dizhongdi.yygh.service;

import com.dizhongdi.yygh.model.hosp.Schedule;
import com.dizhongdi.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName:ScheduleService
 * Package:com.dizhongdi.yygh.service
 * Description:
 *
 * @Date: 2022/2/14 22:40
 * @Author:dizhongdi
 */
public interface ScheduleService {
    /**
     * 上传排班信息
     * @param switchMap
     */

    void save(Map<String, Object> switchMap);

    /**
     * 分页查询
     * @param page 当前页码
     * @param limit 每页记录数
     * @param scheduleQueryVo 查询条件
     * @return
     */

    Page<Schedule> selectPage(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    /**
     * 删除科室
     * @param hoscode
     * @param hosScheduleId
     */
    void remove(String hoscode, String hosScheduleId);

    //根据医院编号 和 科室编号 ，查询排班规则数据
    Map<String, Object> getRuleSchedule(long page, long limit,String hoscode, String depcode);

    //根据医院编号 、科室编号和工作日期，查询排班详细信息
    List<Schedule> getDetailSchedule(String hoscode, String depcode, String workDate);

}
