package com.dizhongdi;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.analysis.ExcelReadExecutor;

/**
 * ClassName:TestRead
 * Package:com.dizhongdi
 * Description:
 *
 * @Date: 2022/2/12 21:02
 * @Author:dizhongdi
 */
public class TestRead {
    public static void main(String[] args) {
        String path = "D:\\excel\\1.xlsx";
        EasyExcel.read(path,UserData.class,new ExcelListener()).sheet().doRead();
    }
}
