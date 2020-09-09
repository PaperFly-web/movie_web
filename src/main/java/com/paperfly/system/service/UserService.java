package com.paperfly.system.service;

import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.User;

public interface UserService {


    CommonResult insert(User user);
    CommonResult login(User user);
    CommonResult modifyUserInfo(User user);
    CommonResult selectUserInfo(User user);
}
