package com.dizhongdi.yygh.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dizhongdi.yygh.model.order.OrderInfo;
import com.dizhongdi.yygh.vo.order.OrderQueryVo;

import java.util.Map;

/**
 * ClassName:OrderService
 * Package:com.dizhongdi.yygh.service
 * Description:
 *
 * @Date: 2022/2/24 15:45
 * @Author:dizhongdi
 */
public interface OrderService extends IService<OrderInfo> {
    //保存订单
    Long saveOrder(String scheduleId, Long patientId);
    /**
     * 分页列表
     */
    IPage<OrderInfo> selectPage(Page<OrderInfo> pageParam, OrderQueryVo orderQueryVo);

    /**
     * 获取订单详情
     */
    OrderInfo getOrderInfo(String id);

    /**
     * 订单详情
     * @param orderId
     * @return
     */
    Map<String,Object> show(Long orderId);



}
