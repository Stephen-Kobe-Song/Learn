package com.itheima.reggie.common;

/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.itheima.reggie.common
 * @Author: song
 * @CreateTime: 2022-06-12  18:56
 * @Description: 自定义业务异常
 * @Version: 1.0
 */

public class CustomException extends  RuntimeException{
    public CustomException(String msg){
        super(msg);
    }
}
