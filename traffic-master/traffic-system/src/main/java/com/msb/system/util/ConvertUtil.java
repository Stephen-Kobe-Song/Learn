package com.msb.system.util;

import com.msb.system.entity.RoleEntity;
import com.msb.system.entity.UserEntity;
import com.msb.system.info.RoleInfo;
import com.msb.system.info.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: traffic-master
 * @BelongsPackage: com.msb.system.util
 * @Author: song
 * @CreateTime: 2022-06-04  01:36
 * @Description: TODO
 * @Version: 1.0
 */

public class ConvertUtil {
    /**
     * @description: userEntity 转 userInfo
     * @author: song
     * @date: 2022/5/29 22:43
     * @param:
     * @return:
     **/
    public static UserEntity userInfoToUserEntity(UserInfo userInfo){
        UserEntity userEntity = new UserEntity();
        userEntity.setUid(userInfo.getUid());
        userEntity.setUname(userInfo.getUname());
        userEntity.setUpass(userInfo.getUpass());
        userEntity.setUaccount(userInfo.getUaccount());
        userEntity.setUmail(userInfo.getUmail());
        userEntity.setUphone(userInfo.getUphone());
        userEntity.setDesc(userInfo.getUdesc());
        userEntity.setT1(userInfo.getT1());
        userEntity.setUtime(userInfo.getUtime());

        if(null != userInfo.getRoles()){
            userEntity.setRoles(Role2List(userInfo.getRoles()));
        }

        return userEntity;
    }

    /**
     * @description: 将查询到的用户转成list，返回给前端
     * @author: song
     * @date: 2022/5/30 1:12
     * @param: [userInfos]
     * @return: java.util.List<com.msb.system.entity.UserEntity>
     **/
    public static List<UserEntity> userInfosConvertToUserEntitys(List<UserInfo> userInfos){
        List<UserEntity> list = new ArrayList<>();
        for (UserInfo u : userInfos){
            list.add(userInfoToUserEntity(u));
        }
        return list;
    }

    /**
     * @description: 前后端实体进行转换
     * @author: song
     * @date: 2022/5/29 21:47
     * @param: [userEntity]
     * @return: com.msb.system.info.UserInfo
     **/
    public static UserInfo UserEntityToUserInfo(UserEntity userEntity){
        UserInfo userInfo = new UserInfo();

        userInfo.setT1(userEntity.getT1());

        userInfo.setUaccount(userEntity.getUaccount());
        userInfo.setUdesc(userEntity.getDesc());

        userInfo.setUid(userEntity.getUid());
        userInfo.setUmail(userEntity.getUmail());
        userInfo.setUname(userEntity.getUname());
        userInfo.setUpass(userEntity.getUpass());
        userInfo.setUphone(userEntity.getUphone());

        return userInfo;
    }

    // 前后端实体转换
    public static  RoleInfo RoleEntity2RoleInfo(RoleEntity roleEntity){
        RoleInfo roleInfo = new RoleInfo();

        roleInfo.setRid(roleEntity.getRid());
        roleInfo.setRname(roleEntity.getRname());
        roleInfo.setRtype(roleEntity.getRtype());
        roleInfo.setRdesc(roleEntity.getRdesc());

        return roleInfo;
    }
    public static  RoleEntity RoleInfo2RoleEntity(RoleInfo roleInfo){

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRid(roleInfo.getRid());
        roleEntity.setRname(roleInfo.getRname());
        roleEntity.setRdesc(roleInfo.getRdesc());
        roleEntity.setRtype(roleInfo.getRtype());

        if (null != roleInfo.getUsers() && roleInfo.getUsers().size() > 0
                && roleInfo.getUsers().get(0).getRoles() == null){
            roleEntity.setUsers(userInfosConvertToUserEntitys(roleInfo.getUsers()));
        }
        return roleEntity;
    }
    public static List<RoleEntity> Role2List(List<RoleInfo> roleInfos){

        ArrayList<RoleEntity> list = new ArrayList<>();
        for (RoleInfo role : roleInfos){
            list.add(RoleInfo2RoleEntity(role));
        }
        return list;
    }



}
