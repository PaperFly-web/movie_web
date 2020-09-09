package com.paperfly.system.controller;

import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;
import com.paperfly.system.pojo.User;
import com.paperfly.system.pojo.View;
import com.paperfly.system.properties.CommonlyUsed;
import com.paperfly.system.service.MoveService;
import com.paperfly.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
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
public class MoveWebViewsController {

    @Autowired
    HttpSession session;

    @Autowired
    MoveService moveService;

    @Autowired
    UserService userService;

    @GetMapping(value = {"/","/index","/index.html"})
    public String toIndex(Map<String,Object> map){

        map.put("msg",new CommonResult<>(111,"主页跳转"));

        return "index";
    }
    @GetMapping("error/unauthorized")
    public String toUnauthorized(Map<String,Object> map) {
        map.put("msg",new CommonResult(444,"没有认证"));
        return "error/unauthorized";
    }

    @GetMapping("toUploadMove")
    @RequiresAuthentication
    public String toUploadMove(Map<String,Object> map) {
        map.put("msg",new CommonResult<>(200,"欢迎您上传电影"));
        return "movie/uploadMove";
    }

    /**
     * 观看一次电影就记录一次数据，并添加一次电影的浏览量
     * 需要参数
     *    mId,mFalseSavePath，mName
     * */
    @GetMapping("/toShowMove")
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
        return "movie/showMove";
    }

    @GetMapping("toWebIntroduce")
    public String toWebIntroduce() {
        return "webIntroduce/webIntroduce";
    }

    @GetMapping("/toLatestMovie")
    public String toLatestMovie(Move move,Map<String,Object> map){
        move.setPage(0);
        map.put("msg",moveService.selectMovesByTime(move));
        return "movie/latestMovie";
    }
    @GetMapping("/toHotMovie")
    public String toHotMovie(Move move,Map<String,Object> map){
        move.setPage(0);
        map.put("msg",moveService.selectMovesByHot(move));
        return "movie/hotMovie";
    }

    @GetMapping("/findMoveByKeyWords")
    public String findMoveByKeyWords(String keyWords,Integer page,Map<String,Object> map){
        page=page* CommonlyUsed.PAGE_NUM;
        CommonResult commonResult = moveService.findMoveByKeyWords(keyWords, page);
        map.put("keyWords",keyWords);
        map.put("msg",commonResult);
        return "movie/movieDetail";
    }

    @GetMapping("/toUserInfo")
    @RequiresAuthentication
    //功能:跳转用户信息界面，并且传值用户信息
    public String toUserInfo(Map<String,Object> map){
        User user=new User();
        user.setUserId((Integer) session.getAttribute("userId"));
        //查询用户信息给前台
        CommonResult commonResult = userService.selectUserInfo(user);
        map.put("user",commonResult);

        Move move=new Move();
        move.setPage(0);
        move.setUserId((Integer) session.getAttribute("userId"));
        CommonResult commonResult1 = moveService.selectMyselfMoves(move);
        map.put("movies",commonResult1);
        return "user/userInfo";
    }
}
