package com.msb.system.repository;

import com.msb.system.info.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @BelongsProject: traffic-master
 * @BelongsPackage: com.msb.system.repostitory
 * @Author: song
 * @CreateTime: 2022-05-27  17:30
 * @Description: 我们负责数据查找的接口
 * @Version: 1.0
 */

public interface UserRepository extends JpaRepository<UserInfo,Long>, JpaSpecificationExecutor<UserInfo> {
    // 批量更新
    @Modifying
    @Transactional
    @Query("update UserInfo set ustatus = 1 where uid in (?1)")
    public int  updates(Collection<Long> ids);

    /**
     * @description:根据条件查询信息
     * @author: song
     * @date: 2022/5/30 1:46
     * @param:
     * @return:
     **/
    @Query("select ui from UserInfo ui where ui.uphone = ?1 ")
    public List<UserInfo> findUsers(String phone);

    public List<UserInfo> findAllByUtimeBetween(Date t1, Date t2);

}
