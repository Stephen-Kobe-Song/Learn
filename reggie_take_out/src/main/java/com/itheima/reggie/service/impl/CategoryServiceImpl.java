package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.entity.Dish;
import com.itheima.reggie.entity.Setmeal;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.itheima.reggie.service.impl
 * @Author: song
 * @CreateTime: 2022-06-12  13:55
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
   @Autowired
   private DishService dishService;

   @Autowired
   private SetmealService setmealService;
    /**
     * @description: 根据id删除分类，删除之前需要进行判断是否关联其他表文件
     * @author: song 
     * @date: 2022/6/12 18:44
     * @param: [id]
     * @return: void
     **/
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<Dish>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int countOne = dishService.count(dishLambdaQueryWrapper);
        // 查询当前分类是否关联了菜品，如果已经关联。抛出一个异常。
        if (countOne > 0){
            throw new CustomException("当前分类下关联菜品，不能删除！");
        }

        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int countTwo = setmealService.count(setmealLambdaQueryWrapper);

        // 查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常。
        if (countTwo > 0){
            throw new CustomException("当前分类下关联套餐，不能删除！");
        }

        // 正常删除分类
        super.removeById(id);
    }
}
