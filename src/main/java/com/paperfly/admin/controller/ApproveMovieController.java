package com.paperfly.admin.controller;

import com.paperfly.admin.pojo.ApproveMove;
import com.paperfly.admin.service.AdminMovieService;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.admin.service.ApproveMoveService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 电影授权控制层
 * */
@Controller
public class ApproveMovieController {
    @Autowired
    HttpSession session;
    @Autowired
    ApproveMoveService approveMoveService;
    @Autowired
    AdminMovieService adminMovieService;
    /**
     * 通过传入的电影信息进行审批
     * 需要approveMIsPass，approveMNote，mId
     * */
    @PostMapping("/admin/approveMove")
    @ResponseBody
    @RequiresRoles("3")
    @Transactional
    public CommonResult approveMove(Integer [] approveMIsPass,String [] approveMNote, Integer [] mId ){
        List<ApproveMove> approveMoves=new ArrayList<>();
        for (int i=0;i<approveMIsPass.length;i++){
            ApproveMove approveMove=new ApproveMove();
            approveMove.setApproveMIsPass(approveMIsPass[i]+"");
            if(approveMNote[i].equals("")){
                approveMove.setApproveMNote("没有备注");
            }else {
                approveMove.setApproveMNote(approveMNote[i]);
            }
            approveMove.setMId(mId[i]);
            approveMove.setUserId((Integer) session.getAttribute("userId"));
            approveMove.setApproveMCreateTime(new Date());
            approveMoves.add(approveMove);
        }

        CommonResult commonResult = approveMoveService.approveMove(approveMoves);
        return commonResult;
    }

    @GetMapping("/admin/passMovie")
    @RequiresPermissions("3")
    @Transactional
    public String passMovie( Integer [] mId,String[] approveMNote, Map<String,Object> map){

        List<ApproveMove> approveMoves=new ArrayList<>();
        for (int i=0;i<mId.length;i++){
            ApproveMove approveMove=new ApproveMove();
            approveMove.setApproveMIsPass("1");
            approveMove.setApproveMNote("暂无");
            approveMove.setMId(mId[i]);
            approveMove.setUserId((Integer) session.getAttribute("userId"));
            approveMove.setApproveMCreateTime(new Date());
            approveMoves.add(approveMove);
        }
        CommonResult commonResult1 = approveMoveService.approveMove(approveMoves);

        //这个是为了返回页面时候，刷新数据
        CommonResult commonResult = adminMovieService.selectMovie("0", 0);
        commonResult.setMessage(commonResult.getMessage());
        map.put("msg",commonResult);
        return "admin/movieDetail";
    }

    @GetMapping("/admin/noPassMovie")
    @RequiresPermissions("3")
    public String noPassMovie( Integer [] mId,String[] approveMNote, Map<String,Object> map){
        List<ApproveMove> approveMoves=new ArrayList<>();
        for (int i=0;i<mId.length;i++){
            ApproveMove approveMove=new ApproveMove();
            approveMove.setApproveMIsPass("0");
            approveMove.setApproveMNote("暂无");
            approveMove.setMId(mId[i]);
            approveMove.setUserId((Integer) session.getAttribute("userId"));
            approveMove.setApproveMCreateTime(new Date());
            approveMoves.add(approveMove);
        }
        CommonResult commonResult1 = approveMoveService.approveMove(approveMoves);

        //这个是为了返回页面时候，刷新数据
        CommonResult commonResult = adminMovieService.selectMovie("1", 0);
        commonResult.setMessage(commonResult.getMessage());
        map.put("msg",commonResult);
        return "admin/movieDetail";
    }
}
