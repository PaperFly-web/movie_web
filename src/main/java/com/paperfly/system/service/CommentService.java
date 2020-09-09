package com.paperfly.system.service;

import com.paperfly.system.pojo.Comment;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;

public interface CommentService {
    CommonResult addComment(Comment comment);
    CommonResult selectComments(Move move);
    CommonResult deleteComment(Comment comment);

}
