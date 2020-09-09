package com.paperfly.admin.mapper;

import com.paperfly.admin.pojo.ApproveMove;
import com.paperfly.system.pojo.Move;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApproveMoveMapper {
    List<Move> selectNoPassMoves(Move move);
    int approveMove(List<ApproveMove> approveMoves);
}