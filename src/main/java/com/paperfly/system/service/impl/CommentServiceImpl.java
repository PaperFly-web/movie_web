package com.paperfly.system.service.impl;

import com.paperfly.system.mapper.CommentMapper;
import com.paperfly.system.pojo.Comment;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;
import com.paperfly.system.properties.CodeProperties;
import com.paperfly.system.properties.CommonlyUsed;
import com.paperfly.system.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public CommonResult addComment(Comment comment) {
        int insert = commentMapper.addComment(comment);
        if(insert>=1){
            return new CommonResult(CodeProperties.SuccessCode,"评论添加成功",comment);
        }else {
            return new CommonResult(CodeProperties.ErrorCode, "品论添加失败",comment);
        }

    }

    @Override
    public CommonResult selectComments(Move move) {
        move.setPage(move.getPage()*CommonlyUsed.COM_PAGE_NUM);
        List<Comment> comments = commentMapper.selectComments(move);
        if(comments.size()==CommonlyUsed.COM_PAGE_NUM){
            return new CommonResult(CodeProperties.SuccessCode,"查询电影成功",comments);
        }else if (comments.size()>0&&comments.size()<=CommonlyUsed.COM_PAGE_NUM){
            return new CommonResult(CodeProperties.HalfSuccessCode,"电影查询到了最后的数据了",comments);
        }else {
            return new CommonResult(CodeProperties.NotFoundCode,"没有查询到电影,查询失败");
        }
    }

    @Override
    public CommonResult deleteComment(Comment comment) {
        int result = commentMapper.deleteComment(comment);
        if(result>=1){
            return new CommonResult(CodeProperties.SuccessCode, CommonlyUsed.USER_REGISTER_SUCCESS,comment);
        }else {
            return new CommonResult(CodeProperties.ErrorCode, CommonlyUsed.USER_REGISTER_FAILED,comment);
        }
    }
}
