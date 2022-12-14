package com.msb.api.code;
/**
 * @description: 系统管理模块的错误码！
 * 区间： 10000-15555
 * @author: song 
 * @date: 2022/5/26 16:45
 * @param: 
 * @return:
 **/
public interface SystemCode {
    // 系统通用成功状态码！
    String TRAFFIC_SYSTEM_SUCCESS = "000000";
    String TRAFFIC_SYSTEM_ERROR = "000001";
    // 用户管理 10000 - 10499
    String  SYSTEM_USER_ERROR_ADD_FAIL = "10000";
    String  SYSTEM_USER_ERROR_ADD_FAIL_PARAM_NULL = "10001";
    String  SYSTEM_USER_ERROR_ADD_FAIL_NAME_NULL = "10002";
    String  SYSTEM_USER_ERROR_ADD_FAIL_ACCOUNT_NULL = "10003";
    String  SYSTEM_USER_ERROR_ADD_FAIL_PASSWORD_NULL = "10004";
    String  SYSTEM_USER_ERROR_ADD_FAIL_PHONE_NULL = "10005";
    String  SYSTEM_USER_ERROR_ADD_FAIL_NAME_SIZE = "10006";
    String  SYSTEM_USER_ERROR_ADD_FAIL_ACCOUNT_SIZE = "10007";
    String  SYSTEM_USER_ERROR_ADD_FAIL_PASSWORD_SIZE = "10008";

    String SYSTEM_USER_DEL_FAIL_UID_NULL = "10030";

    String  SYSTEM_USER_ERROR_UPDATE_FAIL_PARAM_NULL = "10060";
    String  SYSTEM_USER_ERROR_UPDATE_FAIL_UID_NULL = "10061";
    String  SYSTEM_USER_ERROR_UPDATE_FAIL = "10062";

    String SYSTEM_USER_ERROR_FIND_FAIL = "10080";
    // 10500 - 10999 成功的提示
    int SYSTEM_USER_INFO_ADD = 10500;

    // 角色管理 11000 - 11999
    int SYSTEM_ROLE_ERROR_ADD_FAIL = 11000;

}
