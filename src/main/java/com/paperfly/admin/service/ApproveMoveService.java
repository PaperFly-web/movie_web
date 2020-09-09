package com.paperfly.admin.service;

import com.paperfly.admin.pojo.ApproveMove;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;

import java.util.List;

public interface ApproveMoveService {
   CommonResult approveMove(List<ApproveMove> approveMoves);
   CommonResult selectNoPassMoves(Move move);
}
