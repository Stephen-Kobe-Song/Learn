package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.ShoppingCart;
import com.itheima.reggie.mapper.ShoppingCartMapper;
import com.itheima.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.itheima.reggie.service.impl
 * @Author: song
 * @CreateTime: 2022-07-11  17:05
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
