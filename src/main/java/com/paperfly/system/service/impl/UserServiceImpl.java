package com.paperfly.system.service.impl;

import com.paperfly.system.mapper.UserMapper;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.User;
import com.paperfly.system.properties.CodeProperties;
import com.paperfly.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    CommonResult commonResult=null;

    @Override
    public CommonResult insert(User user) {
        String rexPhone="^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";
        if (!user.getUserPhone().matches(rexPhone)){
            return new CommonResult(CodeProperties.ErrorCode,"您输入的不是手机号");
        }
        int insert = userMapper.insert(user);
        if(insert>=1){
            commonResult=new CommonResult(CodeProperties.SuccessCode, "注册成功,赶快去登录吧",user);
        }else {
            commonResult=new CommonResult(CodeProperties.ErrorCode, "注册失败,手机号已被注册",user);
        }
        return commonResult;
    }

    @Override
    public CommonResult login(User user) {

        String rexPhone="^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$";
        if (!user.getUserPhone().matches(rexPhone)){
            return new CommonResult(CodeProperties.ErrorCode,"您输入的不是手机号");
        }
        User login = userMapper.login(user);
        if(login!=null){
            if(login.getUserStatus().equals("1")){
                return new CommonResult(CodeProperties.SuccessCode,"登录成功",login);
            }else{
                return new CommonResult(CodeProperties.ErrorCode,"您的账号被冻结",user);
            }
        }else {
            return new CommonResult(CodeProperties.NotFoundCode,"手机号或密码不正确",user);
        }

    }

    @Override
    public CommonResult modifyUserInfo(User user) {
        int i = userMapper.modifyUserInfo(user);
        if(i>0){
            return new CommonResult(CodeProperties.SuccessCode,"修改用户信息成功");
        }else {
            return new CommonResult(CodeProperties.ErrorCode,"修改用户信息失败");
        }
    }

    @Override
    public CommonResult selectUserInfo(User user) {
        User user1 = userMapper.selectUserInfo(user);
        if(user1!=null){
            return new CommonResult(CodeProperties.SuccessCode,"查询用户成功",user1);
        }else {
            return new CommonResult(CodeProperties.NotFoundCode,"没有查到此用户",user);
        }
    }


}
