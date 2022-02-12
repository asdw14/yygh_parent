package com.dizhongdi;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName:UserData
 * Package:com.dizhongdi
 * Description:
 *
 * @Date: 2022/2/12 20:54
 * @Author:dizhongdi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    @ExcelProperty(value = "用户编号",index = 0)
    private Integer uid;
    @ExcelProperty(value = "用户姓名",index = 1)
    private String name;
}
