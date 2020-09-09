package com.paperfly.admin.service;

import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.User;

import java.util.List;

public interface AdminService {
    CommonResult selectUsers(User user);
    CommonResult updateUserPerm(List<User> users);
    CommonResult updateUserStatus(List<User> users);
}
