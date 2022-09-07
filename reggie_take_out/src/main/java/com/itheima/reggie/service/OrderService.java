package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.Orders;
import com.mysql.cj.x.protobuf.MysqlxCrud;

/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.itheima.reggie.service
 * @Author: song
 * @CreateTime: 2022-07-12  17:01
 * @Description: TODO
 * @Version: 1.0
 */

public interface OrderService extends IService<Orders> {

    public void submit(Orders orders);
}
