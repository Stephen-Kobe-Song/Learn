package com.itheima.reggie.controller;

import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Orders;
import com.itheima.reggie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.itheima.reggie.controller
 * @Author: song
 * @CreateTime: 2022-07-12  17:07
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderControler {

    @Autowired
    private OrderService orderService;
    /**
     * @description: 用户下单
     * @author: song
     * @date: 2022/7/12 17:13
     * @param: [orders]
     * @return: com.itheima.reggie.common.R<java.lang.String>
     **/
    @PostMapping("/submit")
    public R<String> submit(Orders orders){

        orderService.submit(orders);
        return R.success("下单成功");
    }
}
