package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.mapper.EmployeeMapper;
import com.itheima.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.itheima.reggie.service.impl
 * @Author: song
 * @CreateTime: 2022-06-07  15:28
 * @Description: TODO
 * @Version: 1.0
 */

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
