package com.msb.system.service.impl;

import com.msb.api.commons.SystemUtils;
import com.msb.system.entity.UserEntity;
import com.msb.system.info.RoleInfo;
import com.msb.system.info.UserInfo;
import com.msb.system.repository.RoleRepository;
import com.msb.system.repository.UserRepository;
import com.msb.system.service.UserService;
import com.msb.system.util.ConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @BelongsProject: traffic-master
 * @BelongsPackage: com.msb.system.service
 * @Author: song
 * @CreateTime: 2022-05-27  17:17
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    final int ZERO = 0;
    final int status = 1;
    final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository repository;

    /**
     * @description: 添加用户
     * @author: song
     * @date: 2022/5/29 21:46
     * @param: [userEntity]
     * @return: boolean
     **/
    public boolean addUser(UserEntity userEntity){
        UserInfo user = null;
        logger.info("system user Service addUser start");
        try {
            UserInfo userInfo = ConvertUtil.UserEntityToUserInfo(userEntity);
            // 设定角色信息
            if (!SystemUtils.isNullOrEmpty(userEntity.getRolesStr())){
                String[] split = userEntity.getRolesStr().split(",");
                List<RoleInfo> roleInfos = new ArrayList<>();
                for (String ids : split){
                    roleInfos.add(repository.findById(Long.parseLong(ids)).get());
                }
                // 设置关系，添加这个用户，它有哪些角色。把数据保存到第三方表；
                userInfo.setRoles(roleInfos);
            }

            user = userRepository.save(userInfo);
        }catch (Exception e){
            logger.error("system user service addUser fail" + e);
            return false;
        }

        if (!SystemUtils.isNull(user) && user.getUid() != ZERO){
            logger.info("system user service addUser success");
            return true;
        }
        logger.error("system user service addUser fail");
        return false;
    }

    /**
     * @description: 删除用户
     * @author: song
     * @date: 2022/5/29 21:46
     * @param: [uid]
     * @return: boolean
     **/
    @Override
    public boolean delUser(String uid) {
        logger.info("system user delUser Service is Start...");

        String[] ids = uid.split(",");
        if (SystemUtils.isNull(ids) || ids.length== 0){
            return false;
        }
        if (ids.length ==1){
            // 更新一条记录，先查询再更新！
            logger.info("system user service addUser  one people");
            try{
                Long id = Long.parseLong(ids[0]);
                UserInfo userInfo = userRepository.findById(id).get();
                userInfo.setUstatus(status);
                UserInfo user = userRepository.save(userInfo);
                if (!SystemUtils.isNull(user) && user.getUid() != ZERO){
                    logger.info("system user service addUser success");
                    return true;
                }
                logger.error("system user service addUser fail");
                return false;
            }catch (Exception e){
                logger.error("system user service delUser fail" + e);
                return false;
            }

        }else {
            // 删除多条记录
            logger.info("system user service addUser more than one people");
            try {
                Set<Long> sets = new HashSet<>();
                for (String id : ids){
                    sets.add(Long.parseLong(id));
                }
                int updates = userRepository.updates(sets);
                if (updates > ZERO){
                    logger.info("system user service addUser success");
                    return true;
                }
                return false;
            }catch (Exception e){
                logger.error("system user service addUser fail");
                return false;
            }

        }

    }

    /**
     * @description: 修改用户
     * @author: song
     * @date: 2022/5/29 21:22
     * @param: [userEntity]
     * @return: boolean
     **/
    @Override
    public boolean updUser(UserEntity userEntity) {
        logger.info("system service  updUser is start...");
        // 1:数据库查询记录
        UserInfo userInfo = userRepository.findById(userEntity.getUid()).get();
        // 2：根据前端传过来的信息进行修改

        if (!SystemUtils.isNullOrEmpty(userEntity.getUname())){

            userInfo.setUname(userEntity.getUname());
        }
        if (!SystemUtils.isNullOrEmpty(userEntity.getUphone())){
            userInfo.setUphone(userEntity.getUphone());
        }
        if (!SystemUtils.isNullOrEmpty(userEntity.getUmail())){
            userInfo.setUmail(userEntity.getUmail());
        }
        if (!SystemUtils.isNullOrEmpty(userEntity.getUpass())){
            userInfo.setUpass(DigestUtils.md5DigestAsHex(userEntity.getUpass().getBytes()));
        }
        if (!SystemUtils.isNullOrEmpty(userEntity.getUaccount())){
            userInfo.setUaccount(userEntity.getUaccount());
        }
        if (!SystemUtils.isNullOrEmpty(userEntity.getDesc())){
            userInfo.setUpass(userEntity.getDesc());
        }
        if (!SystemUtils.isNullOrEmpty(userEntity.getT1())){
            userInfo.setT1(userEntity.getT1());
        }
        UserInfo info =null;
        try {
            info = userRepository.save(userInfo);
        }catch (Exception e){
            logger.error("system service updUser id fail..." + e);
            return false;
        }
        if (info.getUid() != ZERO){
            logger.info("system service  updUser is success...");
            return true;
        }
        logger.error("system user service updUser fail");
        return false;
    }
    
    /**
     * @description: 查询所有的用户
     * @author: song 
     * @date: 2022/5/29 22:40
     * @param: []
     * @return: java.util.List<com.msb.system.entity.UserEntity>
     **/
    @Override
    public List<UserEntity> findAllUser() {
        logger.info("system service findAllUser is start...");
        List<UserInfo> allInfo = userRepository.findAll();
        logger.info("system service findAllUser is end...");
        return ConvertUtil.userInfosConvertToUserEntitys(allInfo);
    }

    /**
     * @description:根据条件查询用户
     * @author: song
     * @date: 2022/5/30 2:37
     * @param: [userEntity]
     * @return: java.util.List<com.msb.system.entity.UserEntity>
     **/
    public List<UserEntity> findAllUserByWhere(UserEntity userEntity){

        // 将前端传过来的实体类转换成数据库对应的实体类
        UserInfo userInfo = ConvertUtil.UserEntityToUserInfo(userEntity);

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("uname",m->m.contains())
                .withMatcher("uphone",m->m.endsWith())
                .withIgnorePaths("uid")
                .withIgnorePaths("ustatus");
        // 根据实体类去构建查询条件
        Example example = Example.of(userInfo,matcher);


        // 根据咱们的条件去查询数据
        List<UserInfo> all = userRepository.findAll(example);
        return ConvertUtil.userInfosConvertToUserEntitys(all);

    }


    /**
     * @description: 根据时间查询
     * @author: song
     * @date: 2022/5/30 13:16
     * @param: [t1, t2]
     * @return: java.util.List<com.msb.system.entity.UserEntity>
     **/
    public List<UserEntity> findAllUsersByTime(String t1,String t2){
        logger.info("system user service findAllUserByTime is start...");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<UserInfo> allByUtimeBetween = null;
        try {
            allByUtimeBetween = userRepository.findAllByUtimeBetween(sdf.parse(t1), sdf.parse(t2));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        logger.info("system user service findAllUserByTime is end...");
        return ConvertUtil.userInfosConvertToUserEntitys(allByUtimeBetween);
    }

    /**
     * @description: 分页查询、条件查询
     * @author: song
     * @date: 2022/5/30 14:04
     * @param: [userEntity]
     * @return: java.util.Map<java.lang.String,java.lang.Object>
     **/
    @Override
    public Map<String, Object> queryUsers(UserEntity userEntity) {

        Pageable of = PageRequest.of(userEntity.getCurrentPage(), userEntity.getPageSize());

        if (!SystemUtils.isNullOrEmpty(userEntity.getSort())){
            String[] sorts = null;
            sorts =userEntity.getSort().split(",");
            if ("ASC".equals(userEntity.getSortType())){
                of = PageRequest.of(userEntity.getCurrentPage(), userEntity.getPageSize(), Sort.Direction.ASC,sorts);
            }else {
                of = PageRequest.of(userEntity.getCurrentPage(), userEntity.getPageSize(), Sort.Direction.DESC,sorts);
            }
        }
        // 条件查询
        Specification<UserInfo> spec = new Specification<UserInfo>() {
            @Override
            public Predicate toPredicate(Root<UserInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 自定义条件对象
                Predicate predicate = criteriaBuilder.conjunction();

                // 根据邮箱查询
                if (!SystemUtils.isNullOrEmpty(userEntity.getUmail())){
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("umail"), userEntity.getUmail()));
                }
                // 根据手机模糊查询
                if (!SystemUtils.isNullOrEmpty(userEntity.getUphone())){
                    predicate.getExpressions().add(criteriaBuilder.like(root.get("uphone").as(String.class),
                            "%"+userEntity.getUphone() + "%"));
                }
                // 起止时间
                if (!SystemUtils.isNullOrEmpty(userEntity.getStartTime())){
                    predicate.getExpressions().add(
                            criteriaBuilder.greaterThanOrEqualTo(root.get("utime").as(String.class),userEntity.getStartTime())
                    );
                }
                // 终止时间
                if (!SystemUtils.isNullOrEmpty(userEntity.getEndTime())){
                    predicate.getExpressions().add(
                            criteriaBuilder.lessThanOrEqualTo(root.get("utime").as(String.class),userEntity.getEndTime())
                    );
                }

                return predicate;
            }
        };
        // 根据指定的分页和条件查询当前页的内容
        Page<UserInfo> page = userRepository.findAll(spec,of);

        List<UserInfo> content = page.getContent();
        List<UserEntity> users = ConvertUtil.userInfosConvertToUserEntitys(content);

        HashMap<String , Object> map = new HashMap<>();
        map.put("users",users) ;// 查询到的列表信息
        map.put("totalPage", page.getTotalPages()); // 总页数
        map.put("currentPage", userEntity.getCurrentPage());
        map.put("pageSize",userEntity.getPageSize());
        return map;
    }



}



