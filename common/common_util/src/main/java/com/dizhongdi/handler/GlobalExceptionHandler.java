package com.dizhongdi.handler;

import com.dizhongdi.exception.YyghException;
import com.dizhongdi.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:GlobalExceptionHandler
 * Package:com.dizhongdi.handler
 * Description:
 *
 * @Date: 2022/2/7 21:27
 * @Author:dizhongdi
 */
@ControllerAdvice
public class GlobalExceptionHandler {

/**
 * 全局异常处理类
 *
**/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();

        return Result.fail();
    }
    /**
     * 自定义异常处理方法
     * @param e
     * @return
     */

    @ExceptionHandler(YyghException.class)
    @ResponseBody
    public Result error(YyghException e){
        e.printStackTrace();

        return Result.build(e.getCode(),e.getMessage());
    }

}
