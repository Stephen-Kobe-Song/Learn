package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.itheima.reggie.controller
 * @Author: song
 * @CreateTime: 2022-06-07  15:31
 * @Description: TODO
 * @Version: 1.0
 */

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // 员工登录
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        // 使用md5算法对数据进行加密操作
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
        // 根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        // 如果没有查询到则返回登陆失败
        if (null == emp){
            return R.error("登陆失败！");
        }
        // 密码比对
        if (!emp.getPassword().equals(password)){
            return R.error("登陆失败！");
        }
        // 查看员工状态是否已经禁用
        if (emp.getStatus()== 0){
            return R.error("账号已禁用！");
        }
        // 登陆成功，将员工id存入到session并返回登录成功结果
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }

    // 员工退出
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        // 清理session中保存的当前登陆员工的id
        request.getSession().removeAttribute("employee");
        return R.success("退出成功！");
    }

    // 员工添加
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        log.info("新增员工信息，员工信息：{}", employee.toString());
        // 设置初始密码，使用MD5算法进行加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        //employee.setCreateTime(LocalDateTime.now());
        //employee.setUpdateTime(LocalDateTime.now());
        //获得当前用户登录id
        //Long empId = (Long) request.getSession().getAttribute("employee");
        //employee.setCreateUser(empId);
        //employee.setUpdateUser(empId);
        try {
            employeeService.save(employee);
        }catch (Exception e){
            return R.error("添加失败！");
        }
        return R.success("新增员工成功！");
    }

    // 分页查询
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize,String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);

        // 构造分页构造器
        Page pageInfo = new Page(page,pageSize);
        // 条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper();
        // 添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name);
        // 添加排序条件
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        // 执行查询
        employeeService.page(pageInfo,queryWrapper);

        return R.success(pageInfo);
    }

    // 根据id修改员工信息
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        log.info(employee.toString());

        //employee.setUpdateTime(LocalDateTime.now());
        //employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        employeeService.updateById(employee);

        return R.success("员工信息修改成功！");
    }

    // 根据id查询员工信息
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        log.info("根据id查询员工信息...");
        Employee employee = employeeService.getById(id);
        if (null != employee){
            return R.success(employee);
        }
        return R.error("员工信息不存在！");
    }
}
