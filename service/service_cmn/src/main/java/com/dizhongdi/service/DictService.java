package com.dizhongdi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dizhongdi.mapper.DictMapper;
import com.dizhongdi.yygh.model.cmn.Dict;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * ClassName:DictService
 * Package:com.dizhongdi.service
 * Description:
 *
 * @Date: 2022/2/12 17:05
 * @Author:dizhongdi
 */
public interface DictService extends IService<Dict> {
    //根据数据id查询子数据列表
    List<Dict> findChlidData(Long id);

    void exportData(HttpServletResponse response);

    void improtData(MultipartFile file);

    /**
     * 根据上级编码与值获取数据字典名称
     * @param parentDictCode
     * @param value
     * @return
     */

    String getNameByParentDictCodeAndValue(String parentDictCode, String value);

    List<Dict> findByDictCode(String dictCode);
}
