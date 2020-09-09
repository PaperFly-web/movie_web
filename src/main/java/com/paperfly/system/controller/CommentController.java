package com.paperfly.system.controller;

import com.paperfly.system.pojo.Comment;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;
import com.paperfly.system.service.CommentService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    HttpSession session;
    @PostMapping("/addComment")
    @ResponseBody
    @RequiresAuthentication
    public CommonResult addComment(Comment comment){
        CommonResult commonResult = commentService.addComment(comment);
        return commonResult;
    }

    @PostMapping("/selectComments")
    @ResponseBody
    @RequiresAuthentication
    public CommonResult selectComments(Move move){
        CommonResult commonResult = commentService.selectComments(move);
        return commonResult;
    }

    @PostMapping("/deleteComment")
    @ResponseBody
    @RequiresAuthentication
    public CommonResult deletComment(Comment comment){
        comment.setUserId((Integer) session.getAttribute("userId"));
        CommonResult commonResult = commentService.deleteComment(comment);
        return commonResult;
    }
}
