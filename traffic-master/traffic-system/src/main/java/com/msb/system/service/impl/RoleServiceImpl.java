package com.msb.system.service.impl;

import com.msb.system.entity.RoleEntity;

import com.msb.system.info.RoleInfo;
import com.msb.system.repository.RoleRepository;
import com.msb.system.service.RoleService;
import com.msb.system.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @BelongsProject: traffic-master
 * @BelongsPackage: com.msb.system.service.impl
 * @Author: song
 * @CreateTime: 2022-06-03  23:47
 * @Description: TODO
 * @Version: 1.0
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository repository;
    /**
     * @description: 添加角色
     * @author: song
     * @date: 2022/6/4 0:47
     * @param: [roleEntity]
     * @return: boolean
     **/
    @Override
    public boolean addRole(RoleEntity roleEntity) {

        RoleInfo roleInfo = repository.save(ConvertUtil.RoleEntity2RoleInfo(roleEntity));

        return null != roleInfo && roleInfo.getRid() != 0;
    }

    /**
     * @description: 查询所有角色
     * @author: song
     * @date: 2022/6/4 0:47
     * @param: []
     * @return: java.util.List<com.msb.system.entity.RoleEntity>
     **/
    @Override
    public List<RoleEntity> queryRoles() {

        List<RoleInfo> roles = repository.findAll();
        return ConvertUtil.Role2List(roles);
    }


}
