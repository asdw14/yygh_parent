package com.dizhongdi.yygh.client;

import com.dizhongdi.yygh.vo.order.OrderCountQueryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * ClassName:OrderFeignClient
 * Package:com.dizhongdi.yygh.client
 * Description:
 *
 * @Date: 2022/2/25 21:03
 * @Author:dizhongdi
 */
@FeignClient(value = "service-order")
public interface OrderFeignClient {
    /**
     * 获取订单统计数据
     */
    @PostMapping("/admin/order/orderInfo/inner/getCountMap")
    Map<String, Object> getCountMap(@RequestBody OrderCountQueryVo orderCountQueryVo);
}
