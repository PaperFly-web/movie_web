package com.paperfly.admin.controller;

import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;
import com.paperfly.admin.service.AdminMovieService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AdminMovieController {
    @Autowired
    AdminMovieService adminMovieService;

    @Autowired
    HttpSession session;

    //批量删除电影  通过电影ID
    @PostMapping("/admin/deleteMoves")
    @ResponseBody
    @RequiresPermissions("3")
    @Transactional
    public CommonResult deleteMoves(Integer [] mId){
        List<Move> moves=new ArrayList<>();
        /*通过传来的mId删除电影*/
        for (int i=0;i<mId.length;i++){
            Move move=new Move();
            move.setMId(mId[i]);
            moves.add(move);
        }
        CommonResult commonResult = adminMovieService.deleteMoves(moves);
        return commonResult;
    }
    /**
     * 查询网站的数据
     * */
    @PostMapping("/admin/selectMovieWebData")
    @ResponseBody
    @RequiresPermissions("3")
    public CommonResult selectMovieWebData(){
        CommonResult commonResult = adminMovieService.selectMovieWebData();
        return commonResult;
    }

    @PostMapping("/admin/selectMovie")
    @ResponseBody
    @RequiresPermissions("3")
    public CommonResult selectMovie(String isPass,Integer page){
        CommonResult commonResult = adminMovieService.selectMovie(isPass,page);
        return commonResult;
    }


}

