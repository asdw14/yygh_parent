package com.dizhongdi.yygh.service;

import com.dizhongdi.yygh.model.hosp.Schedule;
import com.dizhongdi.yygh.vo.hosp.ScheduleQueryVo;
import org.springframework.data.domain.Page;

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
     * @param hashCode
     * @param hosScheduleId
     */

    void remove(String hoscode, String hosScheduleId);
}
