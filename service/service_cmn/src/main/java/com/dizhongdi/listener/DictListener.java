package com.dizhongdi.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dizhongdi.mapper.DictMapper;
import com.dizhongdi.yygh.model.cmn.Dict;
import com.dizhongdi.yygh.vo.cmn.DictEeVo;
import org.springframework.beans.BeanUtils;

import java.util.Map;

/**
 * ClassName:DictListener
 * Package:com.dizhongdi.listener
 * Description:
 *
 * @Date: 2022/2/12 22:03
 * @Author:dizhongdi
 */
public class DictListener extends AnalysisEventListener<DictEeVo> {
    private DictMapper dictMapper;

    public DictListener() {
    }

    public DictListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }
    //一行一行读取
    @Override
    public void invoke(DictEeVo dictEeVo, AnalysisContext analysisContext) {
        Dict dict = new Dict();
        BeanUtils.copyProperties(dictEeVo,dict);
        dictMapper.insert(dict);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
