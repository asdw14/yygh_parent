package com.dizhongdi;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * ClassName:ExcelListener
 * Package:com.dizhongdi
 * Description:
 *
 * @Date: 2022/2/12 21:05
 * @Author:dizhongdi
 */
public class ExcelListener extends AnalysisEventListener<UserData> {
    //一行一行读取Excel数据，从第二行读，第一行是表头
    @Override
    public void invoke(UserData userData, AnalysisContext analysisContext) {
        System.out.println(userData);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println(headMap);
    }

    //    读取之后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
