package com.itheima.reggie.common;

/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.itheima.reggie.common
 * @Author: song
 * @CreateTime: 2022-06-09  16:25
 * @Description: 基于ThreadLocal封装工具类，用户保存和获取当前登录的用户id
 * @Version: 1.0
 */

public class BaseContext {
    private static  ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }

}
