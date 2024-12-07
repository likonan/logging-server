package com.swpu.logging.logging.controller;


import com.alibaba.fastjson.JSONObject;
import com.swpu.logging.common.result.Result;
import com.swpu.logging.config.JwtComponet;

import com.swpu.logging.logging.dto.LoginUser;
import com.swpu.logging.logging.entity.User;
import com.swpu.logging.logging.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author sdx2009
 * @since 2024-12-07
 */
@RestController
@RequestMapping("/emp")
public class UserController {
    @Resource
    UserService userService;
    @Autowired
    JwtComponet jwtComponet;
    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginUser loginuser){
        //验证图片验证码
        //如果验证码正确不处理如果错误直接返回错误结果
        boolean result = userService.checkVcode(loginuser.getVcode());
        if (!result){
            return new Result<>().error("验证码错误");
        }
        else {
            //将前端发送的数据发给service处理
            User user = userService.login(loginuser);
            //获取结果并封装，返回给前端
            if (ObjectUtils.isEmpty(user)) {
                return new Result<>().error("用户名或密码错误");

            } else {
                //如果登录成功生成token
                String token = jwtComponet.sign(user.getUsername(), user.getPassword());
                //封装返回结果
                JSONObject obj=new JSONObject();
                obj.put("token",token);
                obj.put("userId",user.getId());
                return new Result<>().success("登录成功").put(obj);
            }
        }
    }
    @GetMapping("/vcode")
    public Result<?> getVcode() {
        String code= userService.getVcode();
        return new Result<>().success().put(code);

    }
    @GetMapping("/info")
    public Result<?> getUserInfo(Integer userId){

        User user=userService.getById(userId);
        JSONObject obj=new JSONObject();
        if (ObjectUtils.isEmpty(user)){
            return new Result<>().error();
        }
        else {
            obj.put("username",user.getUsername());
        }

        return  new Result<>().success().put(obj);

    }

}
