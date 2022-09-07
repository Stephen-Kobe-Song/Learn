package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.BaseContext;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.entity.*;
import com.itheima.reggie.mapper.OrderMapper;
import com.itheima.reggie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.itheima.reggie.service.impl
 * @Author: song
 * @CreateTime: 2022-07-12  17:02
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Transactional
    @Override
    public void submit(Orders orders) {
        // 获取当前用户id
        Long userId = BaseContext.getCurrentId();
        // 获取当前用户的购物车数据
        LambdaQueryWrapper<ShoppingCart> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);

        if (list == null || list.size() ==0){
            throw new CustomException("购物车为空，不能下单");
        }
        // 查询用户数据
        User user = userService.getById(userId);
        // 查询地址数据
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if (addressBook == null){
            throw new CustomException("地址为空，不能下单");
        }

        AtomicInteger amount = new AtomicInteger(0);
        long orderId = IdWorker.getId(); // 生成订单号

        List<OrderDetail> orderDetails =list.stream().map((item)->{
            OrderDetail orderDetail =new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setSetmealId(item.getSetmealId());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());// 金额乘以分数累加起来成为总价格！；
            return orderDetail;
        }).collect(Collectors.toList());

        orders.setNumber(String.valueOf(orderId));
        orders.setOrderTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(amount.get()));
        orders.setUserId(userId);
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress((addressBook.getProvinceName() == null ? "":addressBook.getProvinceName())
                +(addressBook.getCityName() == null?"":addressBook.getCityName())
                + (addressBook.getDistrictName() == null ?"":addressBook.getDistrictName())
                +(addressBook.getDetail() == null ?"":addressBook.getDetail()));
        // 向订单表插入一条数据
        this.save(orders);
        // 向订单明细表插入多条数据
        orderDetailService.saveBatch(orderDetails);
        // 清空购物车数据
        shoppingCartService.remove(queryWrapper);
    }
}
