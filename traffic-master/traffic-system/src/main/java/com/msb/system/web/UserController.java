package com.msb.system.web;

import com.msb.api.code.SystemCode;
import com.msb.api.commons.ResponseResult;
import com.msb.api.commons.ResponseResultFactory;
import com.msb.api.commons.SystemUtils;
import com.msb.system.entity.UserEntity;
import com.msb.system.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: traffic-master
 * @BelongsPackage: com.msb.system.web
 * @Author: song
 * @CreateTime: 2022-05-27  17:16
 * @Description: TODO
 * @Version: 1.0
 */

@RestController
@RequestMapping("/users")
public class UserController {
    // 我在那个类里面打印的日志
    final Logger logger = LoggerFactory.getLogger(UserController.class);
    final long UPDATE_USER_ZERO = 0;
    final int NAME_LENGTH =3;
    final int ACCOUNT_LENGTH = 6;
    final int PASS_LENGTH = 6;
    @Autowired
    UserService userService;


    /**
     * @description: 添加用户的请求
     * @author: song
     * @date: 2022/5/28 11:45
     * @param: [userEntity]
     * @return: com.msb.api.commons.ResponseResult
     **/
    @RequestMapping(value = "/addUser" ,method = RequestMethod.POST)
    public ResponseResult addUser(UserEntity userEntity){
        logger.info("system user addUser start " );
        // 参数为空
        if (SystemUtils.isNull(userEntity)){
            logger.error("system user addUser UserEntity is null");
            logger.info("param : " + userEntity);
            ResponseResult responseResult = ResponseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_NULL);
            logger.info("system user addUser return msg: " + responseResult);
            return responseResult;
        }
        // 用户名为空
        if (SystemUtils.isNullOrEmpty(userEntity.getUname())){
            logger.error("system user addUser UserName is null");
            logger.info("param : " + userEntity);
            ResponseResult responseResult = ResponseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_NAME_NULL);
            return responseResult;
        }
        // 账号为空
        if (SystemUtils.isNullOrEmpty(userEntity.getUaccount())){
            logger.error("system user addUser UserAccount is null");
            logger.info("param : " + userEntity);
            ResponseResult responseResult = ResponseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_ACCOUNT_NULL);
            logger.info("system user addUser return msg: " + responseResult);
            return responseResult;
        }
        // 密码为空
        if (SystemUtils.isNullOrEmpty(userEntity.getUpass())){
            logger.error("system user addUser UserPassword is null");
            logger.info("param : " + userEntity);
            ResponseResult responseResult = ResponseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PASSWORD_NULL);
            logger.info("system user addUser return msg: " + responseResult);
            return responseResult;
        }
        // 手机为空
        if (SystemUtils.isNullOrEmpty(userEntity.getUphone())){
            logger.error("system user addUser UserPhone is null");
            logger.info("param : " + userEntity);
            ResponseResult responseResult = ResponseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PHONE_NULL);
            logger.info("system user addUser return msg: " + responseResult);
            return responseResult;
        }
        // 用户名小于三个长度
        if (userEntity.getUname().trim().length() <NAME_LENGTH){
            logger.error("system user addUser UserName's length is small than three");
            logger.info("param : " + userEntity);
            ResponseResult responseResult = ResponseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_NAME_SIZE);
            logger.info("system user addUser return msg: " + responseResult);
            return responseResult;
        }

        // 账号长度
        if (userEntity.getUaccount().trim().length() <ACCOUNT_LENGTH){
            logger.error("system user addUser UserAccount's length is small than six");
            logger.info("param : " + userEntity);
            ResponseResult responseResult = ResponseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_ACCOUNT_SIZE);
            logger.info("system user addUser return msg: " + responseResult);
            return responseResult;
        }
        // 密码长度
        if (userEntity.getUpass().trim().length() <PASS_LENGTH){
            logger.error("system user addUser UserPassword's length is small than six");
            logger.info("param : " + userEntity);
            ResponseResult responseResult = ResponseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PASSWORD_SIZE);
            logger.info("system user addUser return msg: " + responseResult);
            return responseResult;
        }

        // 密码加密
        userEntity.setUpass(DigestUtils.md5DigestAsHex(userEntity.getUpass().getBytes()));
        boolean result = userService.addUser(userEntity);
        ResponseResult responseResult;
        if (result){
            responseResult = ResponseResultFactory.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS);
        }else {
            responseResult =ResponseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_ADD_FAIL_PARAM_NULL);
        }
        logger.info("system user addUser end, return : " +responseResult);
        return responseResult;

    }

    /**
     * @description: 删除用户的请求
     * @author: song
     * @date: 2022/5/28 11:46
     * @param: [uid = "1" 删除一个， uid = "1,2,3" 删除多个]
     * @return: com.msb.api.commons.ResponseResult
     **/
    @RequestMapping(value="/delUser", method = RequestMethod.POST)
    public ResponseResult delUser(String uid){
        logger.info("system user delUser start...");
        // 传过来的参数为空
        if (SystemUtils.isNullOrEmpty(uid)){
            logger.error("system user delUser uid is null");
            ResponseResult responseResult = ResponseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_DEL_FAIL_UID_NULL);
            logger.info("system user uid return msg: " +responseResult);
            return responseResult;
        }

        // 简答你的逻辑判断： 1：可以在这里判断 2：交个业务层去判断
        logger.info("system user delUser serviceImpl start...");
        boolean bl = userService.delUser(uid);
        logger.info("system user delUser UserService end: " +bl);

        ResponseResult responseResult;
        if (bl){
            logger.info("system user delUser success");
             responseResult = new ResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS);
        }else {
            logger.error("system user delUser fail!");
            responseResult = new ResponseResult(SystemCode.TRAFFIC_SYSTEM_ERROR);
        }
        logger.info("system user delUser end...");
        return responseResult;
    }

    /**
     * @description: 更新用户
     * @author: song
     * @date: 2022/5/29 20:41
     * @param: [userEntity]
     * @return: com.msb.api.commons.ResponseResult
     **/
    @RequestMapping(value = "/updUser",method = RequestMethod.POST)
    public ResponseResult updUser(UserEntity userEntity){
        logger.info("system user updUser start...");
        // 参数为空
        if (SystemUtils.isNull(userEntity)){
            logger.info("system user updUser UserEntity is null");
            ResponseResult responseResult = ResponseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_UPDATE_FAIL_PARAM_NULL);
            logger.info("system user updUser return msg : " + responseResult);
            return responseResult;
        }
        // 没有传id
        if (SystemUtils.isNull(userEntity.getUid()) || userEntity.getUid() ==UPDATE_USER_ZERO){
            logger.info("system user updUser uid is null");
            ResponseResult responseResult = ResponseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_UPDATE_FAIL_UID_NULL);
            logger.info("system user updUser  uid return msg : " +responseResult);
            return responseResult;
        }
        logger.info("system user updUser service start : " + userEntity);
        boolean bl = userService.updUser(userEntity);
        logger.info("system user updUser service end : " + bl);

        logger.info("system user updUser end...");
        return getResponseResult(bl,SystemCode.SYSTEM_USER_ERROR_UPDATE_FAIL);
    }

    /**
     * @description: 查询所有用户
     * @author: song
     * @date: 2022/5/29 22:18
     * @param: []
     * @return: com.msb.api.commons.ResponseResult
     **/
    @RequestMapping("/allUser")
    public ResponseResult<List<UserEntity>> findAllUser(){
        logger.info("system user findAllUser id start...");
        ResponseResult<List<UserEntity>> responseResult=null;
        List<UserEntity> allUser = userService.findAllUser();
        if (!allUser.isEmpty()){
            logger.info("system user findAllUser success...");
           responseResult = ResponseResultFactory.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS,
                    allUser);
        }else {
            logger.error("system user findAllUser is fail..." );
            responseResult =ResponseResultFactory.buildResponseResult(SystemCode.SYSTEM_USER_ERROR_FIND_FAIL,"查询失败！");
        }
        logger.info("system user findAllUser end..." + responseResult);
        return responseResult;

    }

    /**
     * @description: 根据条件查找
     * @author: song
     * @date: 2022/5/30 2:40
     * @param: [userEntity]
     * @return: com.msb.api.commons.ResponseResult<java.util.List<com.msb.system.entity.UserEntity>>
     **/
    @RequestMapping("/findUserByWhere")
    public ResponseResult<List<UserEntity>> findAllUserByWhere(UserEntity userEntity){

        List<UserEntity> allUserByWhere = userService.findAllUserByWhere(userEntity);
        ResponseResult<List<UserEntity>> responseResult = ResponseResultFactory.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS,
                allUserByWhere );
        return responseResult;
    }

    /**
     * @description: 根据时间进行查询
     * @author: song
     * @date: 2022/5/30 13:48
     * @param: [userEntity]
     * @return: com.msb.api.commons.ResponseResult<java.util.List<com.msb.system.entity.UserEntity>>
     **/
    @RequestMapping("/findUserByTime")
    public  ResponseResult<List<UserEntity>> findAllUserByTime(UserEntity userEntity){
        logger.info("system user findByTime is start...");
        ResponseResult<List<UserEntity>> responseResult =null;
        List<UserEntity> users = userService.findAllUsersByTime(userEntity.getStartTime(), userEntity.getEndTime());
        if (!users.isEmpty()){
            responseResult = ResponseResultFactory.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS,
                    users );
            logger.error("system user findUserByTime if success...");
        }else {
            responseResult = ResponseResultFactory.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_ERROR);
            logger.error("system user findUserByTime if fail...");
        }
        logger.info("system user findByTime is end...");
        return responseResult;
    }

    /**
     * @description: 通用的分页查询
     * @author: song
     * @date: 2022/5/30 13:55
     * @param: [userEntity]
     * @return: com.msb.api.commons.ResponseResult
     **/
    @RequestMapping("/queryUsers")
    public ResponseResult queryUsers(UserEntity userEntity){
        Map<String, Object> map = userService.queryUsers(userEntity);
        ResponseResult responseResult = ResponseResultFactory.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS,map);

        return responseResult;

    }



    /**
     * @description: 返回我们的结果视图
     * @author: song
     * @date: 2022/5/30 2:40
     * @param: [bl, code]
     * @return: com.msb.api.commons.ResponseResult
     **/
    private ResponseResult getResponseResult(boolean bl, String code) {
        ResponseResult responseResult;
        if(bl){
            responseResult = ResponseResultFactory.buildResponseResult();
        }else {
            responseResult = ResponseResultFactory.buildResponseResult(code);
            logger.info("system user getResponseResult fail : " + responseResult);
        }

        logger.info("system user getResponseResult , return : " + responseResult);
        return responseResult;
    }
}
