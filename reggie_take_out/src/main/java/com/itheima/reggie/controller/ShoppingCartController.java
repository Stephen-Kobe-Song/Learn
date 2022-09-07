package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.BaseContext;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.ShoppingCart;
import com.itheima.reggie.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.itheima.reggie.controller
 * @Author: song
 * @CreateTime: 2022-07-11  17:06
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * @description: 添加购物车
     * @author: song
     * @date: 2022/7/11 17:09
     * @param: [shoppingCart]                                                  
     * @return: com.itheima.reggie.common.R<com.itheima.reggie.entity.ShoppingCart>
     **/
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        log.info("ShoppingCart:{}",shoppingCart);
        // 设置用户id，指定当前是哪个用户的购物车
        Long id = BaseContext.getCurrentId();
        shoppingCart.setUserId(id);
        // 查询当前菜品或者套餐是否存在购物车
        Long dishId = shoppingCart.getDishId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,id);
        if (dishId != null){
            queryWrapper.eq(ShoppingCart::getDishId,dishId);
        }else{
            queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getDishId());
        }
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);
        // 如果已经存在，在原来数量基础上加一
        if (cartServiceOne != null){
            cartServiceOne.setNumber(cartServiceOne.getNumber() +1);
            shoppingCartService.updateById(cartServiceOne);
        }else {
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cartServiceOne = shoppingCart;
        }
        // 如果不存在，再添加到购物车，数量默认为1
        return R.success(cartServiceOne);
    }
    @PutMapping("/sub")
    public R sub(Long id){
        ShoppingCart shoppingCart = shoppingCartService.getById(id);
        if (shoppingCart.getNumber() > 1){
            shoppingCart.setNumber(shoppingCart.getNumber() -1);
        }else {
            return R.error("数量为1，不支持减少操作！");
        }
        shoppingCartService.updateById(shoppingCart);
        return R.success(shoppingCart);
    }

    /**
     * @description: 查看购物车
     * @author: song
     * @date: 2022/7/12 16:26
     * @param: []
     * @return: com.itheima.reggie.common.R<java.util.List<com.itheima.reggie.entity.ShoppingCart>>
     **/
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
        log.info("查看购物车");
        LambdaQueryWrapper<ShoppingCart> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);

        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);

        return R.success(list);
    }
    /**
     * @description: 清空购物车
     * @author: song
     * @date: 2022/7/12 16:34
     * @param: []
     * @return: com.itheima.reggie.common.R<java.lang.String>
     **/
    @DeleteMapping("/clean")
    public R<String> clean(){
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,BaseContext.getCurrentId());

        shoppingCartService.remove(queryWrapper);

        return R.success("清除购物车成功");
    }
}
