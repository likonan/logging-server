package com.swpu.logging.logging.service;


import com.swpu.logging.logging.dto.LoginUser;
import com.swpu.logging.logging.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author sdx2009
 * @since 2024-12-07
 */
public interface UserService extends IService<User> {
    User login(LoginUser loginuser);

    String getVcode();

    boolean checkVcode(String vocode);
    String getPasswordByusername(String username);

}
