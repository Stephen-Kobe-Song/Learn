package com.msb.api.commons;

/**
 * @BelongsProject: traffic-master
 * @BelongsPackage: com.msb.api.commons
 * @Author: song
 * @CreateTime: 2022-05-27  19:00
 * @Description: TODO
 * @Version: 1.0
 */

public class SystemUtils {

    public static  boolean isNull(Object obj){
        return null == obj;
    }

    /**
     * @description:判断字符串是否为空字符串！
     * @author: song 
     * @date: 2022/5/27 23:52
     * @param: [str]
     * @return: true:表示为空！
     **/
    public static  boolean isNullOrEmpty(String str){
        return null == str || str.trim().equals("");
    }
}
