package com.msb.system.service;

import com.msb.system.entity.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;


public interface UserService {
    // 添加用户！
    public boolean addUser(UserEntity userEntity);
    // 删除用户
    public  boolean delUser(String uid);
    // 修改用户
    public boolean updUser(UserEntity userEntity);
    // 查询所有用户
    public List<UserEntity> findAllUser();
    // 条件查询
    public List<UserEntity> findAllUserByWhere(UserEntity userEntity);
    // 根据时间查询
    public List<UserEntity> findAllUsersByTime(String t1,String t2);
    // 通用用户查询，分页查询 包括条件查询
    public Map<String,Object> queryUsers(UserEntity userEntity);
}