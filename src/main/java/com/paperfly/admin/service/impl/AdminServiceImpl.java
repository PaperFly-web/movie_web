package com.paperfly.admin.service.impl;

import com.paperfly.admin.mapper.AdminMapper;
import com.paperfly.admin.service.AdminService;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.User;
import com.paperfly.system.properties.CodeProperties;
import com.paperfly.system.properties.CommonlyUsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public CommonResult selectUsers(User user) {
        List<User> users = adminMapper.selectUsers(user);
        if(users.size()== CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.SuccessCode,"查询用户成功",users);
        }else if (users.size()>0&&users.size()<=CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.HalfSuccessCode,"用户查询到了最后的数据了",users);
        }else {
            return new CommonResult(CodeProperties.NotFoundCode,"没有查询到用户,查询失败");
        }

    }

    @Override
    public CommonResult updateUserPerm(List<User> users) {
        int i = adminMapper.updateUserPerm(users);
        if(i>0){
            return new CommonResult(CodeProperties.SuccessCode,"修改用户信息成功");
        }else {
            return new CommonResult(CodeProperties.ErrorCode,"修改用户信息失败");
        }
    }

    @Override
    public CommonResult updateUserStatus(List<User> users) {
        int i = adminMapper.updateUserStatus(users);
        if(i>0){
            return new CommonResult(CodeProperties.SuccessCode,"修改用户信息成功");
        }else {
            return new CommonResult(CodeProperties.ErrorCode,"修改用户信息失败");
        }
    }
}
