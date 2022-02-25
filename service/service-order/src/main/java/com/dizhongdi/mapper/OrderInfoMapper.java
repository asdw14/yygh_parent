package com.dizhongdi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dizhongdi.yygh.model.order.OrderInfo;
import com.dizhongdi.yygh.vo.order.OrderCountQueryVo;
import com.dizhongdi.yygh.vo.order.OrderCountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName:OrderInfoMapper
 * Package:com.dizhongdi
 * Description:
 *
 * @Date: 2022/2/24 15:46
 * @Author:dizhongdi
 */
@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
    List<OrderCountVo> selectOrderCount(@Param("vo") OrderCountQueryVo orderCountQueryVo);
}
