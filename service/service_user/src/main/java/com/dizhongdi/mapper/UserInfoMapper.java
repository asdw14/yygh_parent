package com.dizhongdi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dizhongdi.yygh.model.user.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * ClassName:UserInfoMapper
 * Package:com.dizhongdi.mapper
 * Description:
 *
 * @Date: 2022/2/19 16:42
 * @Author:dizhongdi
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
}
