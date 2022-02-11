package com.dizhongdi.yygh.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ClassName:HospConfig
 * Package:com.dizhongdi.yygh.config
 * Description:
 *
 * @Date: 2022/2/11 19:24
 * @Author:dizhongdi
 */
@Configuration
public class HospConfig {
//    分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
