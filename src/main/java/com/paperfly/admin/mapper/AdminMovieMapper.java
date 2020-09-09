package com.paperfly.admin.mapper;

import com.paperfly.admin.pojo.MovieWebData;
import com.paperfly.system.pojo.Move;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMovieMapper {

    int deleteMoves(List<Move> moveList);
    MovieWebData selectMovieWebData();
    List<Move> selectMovie(@Param("isPass") String isPass, @Param("page") Integer page);
}