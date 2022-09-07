package com.msb.api.commons;
import com.msb.api.code.SystemCode;
/**
 * @BelongsProject: traffic-master
 * @BelongsPackage: com.msb.api.commons
 * @Author: song
 * @CreateTime: 2022-05-27  18:21
 * @Description: TODO
 * @Version: 1.0
 */

public class ResponseResultFactory<T> {
    public static ResponseResult buildResponseResult(){
        /**
         * @description:构建一个通用的成功的返回结果！
         * @author: song 
         * @date: 2022/5/27 18:30
         * @param: []
         * @return: com.msb.api.commons.ResponseResult
         **/
        return  new ResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS);
    }

    public static ResponseResult buildResponseResult(String code){
        return  new ResponseResult(code);
    }

    public static ResponseResult buildResponseResult(String resultCode,String resultMsg){
        return  new ResponseResult(resultCode,resultMsg);
    }

    public static <T> ResponseResult buildResponseResult(String resultCode, T obj){
        return  new ResponseResult(resultCode,obj);
    }
    public static <T> ResponseResult buildResponseResult(String resultCode,String resultMsg, T obj){
        return  new ResponseResult(resultCode,resultMsg,obj);
    }
}
