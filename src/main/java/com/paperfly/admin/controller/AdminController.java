package com.paperfly.admin.controller;

import com.paperfly.admin.service.AdminService;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.User;
import com.paperfly.system.properties.CommonlyUsed;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    AdminService adminService;

    @PostMapping("/admin/selectUsers")
    @RequiresPermissions("3")
    @ResponseBody
    public CommonResult selectUsers(User user){
        user.setPage(user.getPage()* CommonlyUsed.PAGE_NUM);
        CommonResult commonResult = adminService.selectUsers(user);
        return commonResult;
    }

    @PostMapping("/admin/updateUserPerm")
    @RequiresPermissions("3")
    @Transactional
    public String updateUserPerm(String[]  userPerm,Integer [] userId, Map<String,Object> map){
        List<User> users=new ArrayList<>();
        for (int i=0;i<userPerm.length;i++){
            User user=new User();
            user.setUserPerm(userPerm[i]);
            user.setUserId(userId[i]);
            users.add(user);
        }
        CommonResult commonResult = adminService.updateUserPerm(users);
        map.put("msg",commonResult);
        return "admin/Administrator";
    }
    @PostMapping("/admin/updateUserStatus")
    @RequiresPermissions("3")
    @Transactional
    public String  updateUserStatus(String[]  userStatus, Integer[] userId, Map<String,Object> map){
        List<User> users=new ArrayList<>();
        for (int i=0;i<userStatus.length;i++){
            User user=new User();
            user.setUserStatus(userStatus[i]);
            user.setUserId(userId[i]);
            users.add(user);
        }
        CommonResult commonResult = adminService.updateUserStatus(users);
        map.put("msg",commonResult);
        return "admin/Administrator";
    }
}
