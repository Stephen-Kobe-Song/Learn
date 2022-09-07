package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.itheima.reggie.controller
 * @Author: song
 * @CreateTime: 2022-06-12  13:57
 * @Description: 分类管理
 * @Version: 1.0
 */
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * @description: 新增菜品分类
     * @author: song
     * @date: 2022/6/12 17:41
     * @param: [category]
     * @return: com.itheima.reggie.common.R<java.lang.String>
     **/
    @PostMapping
    public R<String> save(@RequestBody Category category){

        log.info("category : {}",category);
        categoryService.save(category);

        return R.success("新增分类成功！");
    }

    /**
     * @description: 分页查询
     * @author: song
     * @date: 2022/6/12 19:18
     * @param: [page, pageSize]
     * @return: com.itheima.reggie.common.R<com.baomidou.mybatisplus.extension.plugins.pagination.Page>
     **/
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize){

        Page<Category> pageInfo = new Page<>(page,pageSize);
        // 构建条件构造器
        LambdaQueryWrapper<Category> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        // 进行分页查询
        categoryService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * @description: 分类删除！
     * @author: song
     * @date: 2022/6/12 19:19
     * @param: [ids]
     * @return: com.itheima.reggie.common.R<java.lang.String>
     **/
    @DeleteMapping
    public R<String> delete(Long ids){
        log.info("删除分类id为：{}" ,ids);
        categoryService.remove(ids);

        return R.success("分类信息删除成功！");
    }
    
    /**
     * @description: 根据id修改分类信息
     * @author: song 
     * @date: 2022/6/12 22:29
     * @param: [category]
     * @return: com.itheima.reggie.common.R<java.lang.String>
     **/
    @PutMapping
    public R<String> update(@RequestBody Category category){
        log.info("修改分类信息：{}",category);

        categoryService.updateById(category);

        return R.success("修改分类信息成功！");
    }

    /**
     * @description: 根据条件查询分类数据
     * @author: song
     * @date: 2022/6/19 10:15
     * @param: [category]
     * @return: com.itheima.reggie.common.R<java.util.List<com.itheima.reggie.entity.Category>>
     **/
    @GetMapping("/list")
    public R<List<Category>> list(Category category){
        // 构造查询条件
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(category.getType() != null,Category::getType,category.getType());
        lambdaQueryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);

        List<Category> list = categoryService.list(lambdaQueryWrapper);

        return R.success(list);
    }
}
