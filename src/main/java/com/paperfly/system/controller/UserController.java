package com.paperfly.system.controller;

import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;
import com.paperfly.system.pojo.User;
import com.paperfly.system.properties.CodeProperties;
import com.paperfly.system.service.MoveService;
import com.paperfly.system.service.UserService;
import com.paperfly.system.utils.MD5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * 用户的登录和注册都在这里
 * */
@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;

    @Autowired
    MoveService moveService;

    @PostMapping("/regist")
    @ResponseBody
    @Transactional
    public CommonResult registUser(User user){

        user.setUserPwd(MD5Utils.getMD5(user.getUserPwd()));
        user.setUserCreateTime(new Date());
        user.setUserPerm("0");
        user.setUserRole("0");
        CommonResult commonResult = userService.insert(user);
        return commonResult;
    }
    @PostMapping("/login")
    public String  login(User user, Map<String,Object> map ){
        //记录最后一次登录时间
        user.setUserLastLoginTime(new Date());
        user.setUserPwd(MD5Utils.getMD5(user.getUserPwd()));
        //然后获取到当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //把密码和用户交给Shiro
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserPhone(), user.getUserPwd());//true代表记住我
        try {
            currentUser.login(token);
            user = (User) currentUser.getPrincipal();
            /**
             * 如果能走到这一步就说明登录成功，然后字在获取到用户的信息，存入会话的共享数据中
             * */
            session.setAttribute("phone", user.getUserPhone());
            session.setAttribute("userId",user.getUserId());
            session.setAttribute("userName",user.getUserName());
            map.put("msg", new CommonResult(CodeProperties.SuccessCode,"登录成功"));
            return "redirect:index";
        } catch (UnknownAccountException e) {
            CommonResult commonResult = (CommonResult) session.getAttribute("loginMsg");
            map.put("msg", commonResult);
            return "index";
        } catch (IncorrectCredentialsException ice) {
            CommonResult commonResult=new CommonResult(CodeProperties.NotFoundCode,"手机号或者密码不正确");
            map.put("msg", commonResult);
            return "index";
        } catch (Exception e) {
            CommonResult commonResult=new CommonResult(CodeProperties.ErrorCode,"登录失败");
            map.put("msg", commonResult);
            return "index";
        }

    }

    @PostMapping("/modifyUserInfo")
    @RequiresAuthentication
    @Transactional//功能:修改用户信息
    public String modifyUserInfo(User user,Map<String,Object> map){
        //对密码进行加密
        user.setUserPwd(MD5Utils.getMD5(user.getUserPwd()));
        user.setUserId((Integer) session.getAttribute("userId"));
        CommonResult commonResult = userService.modifyUserInfo(user);
        CommonResult userId = userService.selectUserInfo(user);
        map.put("msg",commonResult);
        map.put("user",userId);

        Move move=new Move();
        move.setPage(0);
        move.setUserId((Integer) session.getAttribute("userId"));
        CommonResult commonResult1 = moveService.selectMyselfMoves(move);
        map.put("movies",commonResult1);
        return "user/userInfo";
    }
}
