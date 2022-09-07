package com.msb.system.web;

import com.msb.api.code.SystemCode;
import com.msb.api.commons.ResponseResult;
import com.msb.api.commons.ResponseResultFactory;
import com.msb.system.entity.RoleEntity;
import com.msb.system.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @BelongsProject: traffic-master
 * @BelongsPackage: com.msb.system.web
 * @Author: song
 * @CreateTime: 2022-06-03  23:42
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/roles")
public class RoleController {

    final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    RoleService roleService;

    @RequestMapping("/addRole")
    public ResponseResult addRole(RoleEntity roleEntity){

        boolean b = roleService.addRole(roleEntity);
        return getResponseResult(b, SystemCode.SYSTEM_USER_ERROR_ADD_FAIL);
    }

    @RequestMapping("/queryAllRole")
    public ResponseResult queryAllRole(){

        List<RoleEntity> roleEntities = roleService.queryRoles();
        return ResponseResultFactory.buildResponseResult(SystemCode.TRAFFIC_SYSTEM_SUCCESS,"添加成功！",roleEntities);

    }


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
