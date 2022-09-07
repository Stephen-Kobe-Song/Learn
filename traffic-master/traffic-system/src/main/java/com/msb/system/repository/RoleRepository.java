package com.msb.system.repository;

import com.msb.system.info.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


/**
 * @BelongsProject: traffic-master
 * @BelongsPackage: com.msb.system.repository
 * @Author: song
 * @CreateTime: 2022-06-03  23:43
 * @Description: TODO
 * @Version: 1.0
 */

public interface RoleRepository extends JpaRepository<RoleInfo,Long>, JpaSpecificationExecutor<RoleInfo> {

}
