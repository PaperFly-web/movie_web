package com.paperfly.system.service;

import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;
import com.paperfly.system.pojo.View;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface MoveService {
    CommonResult uploadMove(Move move, MultipartFile file, MultipartFile pic);
    CommonResult selectMoves(Move move);
    CommonResult selectMyselfMoves(Move move);
    CommonResult deleteMoves(List<Move> moves);
    CommonResult updateMoveHot(View view);
    CommonResult selectMovesByTime(Move move);
    CommonResult selectMovesByHot(Move move);
    CommonResult selectMoveForIndex(Move move);
    CommonResult findMoveByKeyWords(@Param("keyWords") String keyWords, @Param("page")Integer page);

}
