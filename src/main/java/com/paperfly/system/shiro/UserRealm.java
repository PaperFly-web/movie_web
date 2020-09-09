package com.paperfly.system.shiro;


import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.User;
import com.paperfly.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        Subject currentUser = SecurityUtils.getSubject();
//        currentUser.getSession().setTimeout(-1000L);//设置用户永不过期
        User user = (User) currentUser.getPrincipal();//这个user使用过下面的方法(验证密码)传的user
        //给url绑定角色和授权
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission(user.getUserPerm());//把当前用户授权
        info.addRole(user.getUserRole());//给当前用户添加角色
        log.info("开始授权"+user);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = new User();
        //获取前台传来的数据
        user.setUserPhone(token.getUsername());
        user.setUserPwd(String.valueOf(token.getPassword()));
        //记录登录最后一次登录时间
        user.setUserLastLoginTime(new Date());
        log.info("config:认证:" + user);
        //获取从数据库查询出来的数据
        CommonResult login = userService.login(user);
        if (login.getCode()!=200) {
            //返回null就是登录失败
            session.setAttribute("loginMsg",login);
            return null;
        }
        //密码验证是Shiro做，不能把密码暴露出来
        return new SimpleAuthenticationInfo(login.getData(), ((User)login.getData()).getUserPwd(), "UserRealm");
    }
}
