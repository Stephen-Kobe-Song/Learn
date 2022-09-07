package com.itheima.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.itheima.reggie.common
 * @Author: song
 * @CreateTime: 2022-06-08  23:39
 * @Description: 全局异常处理
 * @Version: 1.0
 */

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /**
     * @description: 异常处理方法
     * @author: song
     * @date: 2022/6/8 23:42
     * @param: []
     * @return: com.itheima.reggie.common.R<java.lang.String>
     **/
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException e){
        log.error(e.getMessage());
        if (e.getMessage().contains("Duplicate entry")){
            String[] split = e.getMessage().split(" ");
            String msg = split[2] + "已存在！";
            return R.error(msg);
        }

        return R.error("未知错误，用户添加失败！");
    }

    @ExceptionHandler(CustomException.class)
    public R<String> exceptionHandler(CustomException e){
        log.error(e.getMessage());

        return R.error(e.getMessage());
    }
}
