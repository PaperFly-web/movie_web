package com.paperfly.system.controller;

import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.View;
import com.paperfly.system.properties.CommonlyUsed;
import com.paperfly.system.service.ViewService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ViewController {
    @Autowired
    HttpSession session;

    @Autowired
    ViewService viewService;

    @PostMapping("/selectViewsHistory")
    @RequiresAuthentication
    @ResponseBody
    public CommonResult selectViewsHistory(View view){
        view.setUserId((Integer) session.getAttribute("userId"));
        CommonResult commonResult = viewService.selectViewHistory(view);
        return commonResult;
    }
}
