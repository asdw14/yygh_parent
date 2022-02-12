package com.dizhongdi;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;

/**
 * ClassName:TestWriter
 * Package:com.dizhongdi
 * Description:
 *
 * @Date: 2022/2/12 20:55
 * @Author:dizhongdi
 */

public class TestWriter {
    public static void main(String[] args) {
        String path = "D:\\excel\\1.xlsx";
        ArrayList<UserData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new UserData( i,"dizhongdi"+i));
        }
        EasyExcel.write(path,UserData.class).sheet("用户数据").doWrite(list);
    }
}
