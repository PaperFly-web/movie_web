package com.paperfly.admin.controller;

import com.paperfly.admin.service.AdminMovieService;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;
import com.paperfly.system.pojo.View;
import com.paperfly.system.service.MoveService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * 这个控制层就只负责页面的跳转
 * */
@Controller
public class AdminMoveWebViewsController {

    @Autowired
    HttpSession session;

    @Autowired
    AdminMovieService adminMovieService;

    @Autowired
    MoveService moveService;

    @GetMapping("/admin/Administrator")
    @RequiresPermissions("3")
    public String toUploadMove(Map<String,Object> map) {
        map.put("msg",new CommonResult<>(200,"欢迎来到网站管理页面"));
        map.put("adminName",session.getAttribute("userName"));
        return "admin/Administrator";
    }

    @GetMapping("/admin/toMovieDetail")
    @RequiresPermissions("3")
    public String toMovieDetail(Map<String,Object> map) {
        CommonResult commonResult = adminMovieService.selectMovie("0", 0);
        commonResult.setMessage("欢迎来到授权电影管理页面");
        map.put("msg",commonResult);
        return "admin/movieDetail";
    }
    @GetMapping("/admin/toShowMove")
    @RequiresAuthentication
    public String toShowMove(Move move, Map<String ,Object> map){
        View view=new View();
        view.setMId(move.getMId());
        view.setUserId((Integer) session.getAttribute("userId"));
        view.setVCreateTime(new Date());
        //返回的公共类的data  就是你传过去的view
        CommonResult commonResult = moveService.updateMoveHot(view);
        map.put("move",move);
        map.put("msg",commonResult);
        return "admin/showMove";
    }

}
