package com.dizhongdi.yygh.cmn.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * ClassName:DictFeignClient
 * Package:com.dizhongdi.yygh.cmn.client
 * Description:
 *
 * @Date: 2022/2/16 17:15
 * @Author:dizhongdi
 */
/**
 * 数据字典API接口
 */

@FeignClient("service-cmn")
@Repository
public interface DictFeignClient {
    /**
     * 获取数据字典名称
     * @param parentDictCode
     * @param value
     * @return
     */
    @GetMapping(value = "/admin/cmn/dict/getName/{parentDictCode}/{value}")
    String getName(@PathVariable("parentDictCode") String parentDictCode, @PathVariable("value") String value);

    /**
     * 获取数据字典名称
     * @param value
     * @return
     */
    @GetMapping(value = "/admin/cmn/dict/getName/{value}")
    String getName(@PathVariable("value") String value);

}
