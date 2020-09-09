package com.paperfly.admin.service;

import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMovieService {
    CommonResult deleteMoves(List<Move> moves);
    CommonResult selectMovieWebData();
    CommonResult selectMovie( String isPass,  Integer page);
}
