package com.paperfly.system.mapper;

import com.paperfly.system.pojo.Comment;
import com.paperfly.system.pojo.Move;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    int addComment(Comment record);
    List<Comment> selectComments(Move move);
    int deleteComment(Comment comment);
}