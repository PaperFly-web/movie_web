package com.paperfly.system.mapper;

import com.paperfly.system.pojo.Move;
import com.paperfly.system.pojo.View;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MoveMapper {
    int insert(Move move);
    List<Move> selectMoves(Move move);
    List<Move> selectMyselfMoves(Move move);
    List<Move> selectMovesByTime(Move move);
    List<Move> selectMovesByHot(Move move);
    int deleteMoves(List<Move> moves);
    int updateMoveHot(View view);
    List<Move> findMoveByKeyWords(@Param("keyWords") String keyWords,@Param("page")Integer page);
}