package com.msb.system.service;

import com.msb.system.entity.RoleEntity;

import java.util.List;

public interface RoleService {


    /**
     * @description: 添加角色
     * @author: song
     * @date: 2022/6/3 23:52
     * @param: [roleEntity]
     * @return: boolean
     **/
    public boolean addRole(RoleEntity roleEntity);

    public List<RoleEntity> queryRoles();
}
