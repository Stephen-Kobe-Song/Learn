package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.User;
import com.itheima.reggie.service.UserService;
import com.itheima.reggie.utils.SMSUtils;
import com.itheima.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: reggie_take_out
 * @BelongsPackage: com.itheima.reggie.controller
 * @Author: song
 * @CreateTime: 2022-07-10  16:23
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @description: 发送手机验证码并保存到Session里面
     * @author: song
     * @date: 2022/7/10 17:07
     * @param: [user]
     * @return: com.itheima.reggie.common.R<java.lang.String>
     **/
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        // 获取手机号
        String phone = user.getPhone();
        if (StringUtils.isNotEmpty(phone)){
            // 生成随机的四位验证码
            String  code = ValidateCodeUtils.generateValidateCode(4).toString();
            log.info(code);
            // 调用阿里云提供的短信服务API完成发送短信
            SMSUtils.sendMessage("外卖系统"," ",phone,code);
            /* // 将生成的验证码保存起到Session
            session.setAttribute(phone,code);*/

            // 将生成的验证码保存到redis中并设置过期时间
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);

            return R.success("手机验证码发送成功！");
        }

        return R.error("短信发送失败！");

    }
    /**
     * @description: 移动端用户登录
     * @author: song
     * @date: 2022/7/10 17:18
     * @param: [user, session]
     * @return: com.itheima.reggie.common.R<java.lang.String>
     **/
    @PostMapping("/login")
    public R<User> login(@RequestBody Map user, HttpSession session){
        log.info("map:{}",user);
        // 从map里面获取手机号和验证码
        String phone = user.get("phone").toString();
        String code = user.get("code").toString();
        /*// 从Session中获取保存的验证码并进行比对
        Object codeInSession = session.getAttribute(phone);*/
        // 从redis中取出验证码
        Object codeInSession = redisTemplate.opsForValue().get(phone);

        if (codeInSession != null && codeInSession.equals(code)){
            // 登陆成功
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,phone);

            User thisUser = userService.getOne(queryWrapper);
            if (thisUser == null){
                // 新用户，自动注册
                thisUser = new User();
                thisUser.setPhone(phone);

                userService.save(thisUser);
            }
            session.setAttribute("user",thisUser.getId());
            // 如果用户登陆成功，就是删除redis里面的验证码信息
            redisTemplate.delete(phone);
            return R.success(thisUser);
        }
        // 登录不成功！
        return R.error("登录失败！");

    }
}
