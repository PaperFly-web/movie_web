package com.paperfly.admin.service.impl;

import com.paperfly.admin.mapper.ApproveMoveMapper;
import com.paperfly.admin.pojo.ApproveMove;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;
import com.paperfly.system.properties.CodeProperties;
import com.paperfly.system.properties.CommonlyUsed;
import com.paperfly.admin.service.ApproveMoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApproveMoveServiceImpl implements ApproveMoveService {
    @Autowired
    ApproveMoveMapper approveMoveMapper;
    @Override
    public CommonResult approveMove(List<ApproveMove> approveMoves) {
        int result = approveMoveMapper.approveMove(approveMoves);
        if(result>0){
            return new CommonResult(CodeProperties.SuccessCode,"授权电影操作成功");
        }else {
            return new CommonResult(CodeProperties.ErrorCode,"授权电影操作失败");
        }
    }

    @Override
    public CommonResult selectNoPassMoves(Move move) {
        List<Move> moves = approveMoveMapper.selectNoPassMoves(move);
        if(moves.size()== CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.SuccessCode,"查询电影成功",moves);
        }else if (moves.size()>0&&moves.size()<=CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.HalfSuccessCode,"电影查询到了最后的数据了",moves);
        }else {
            return new CommonResult(CodeProperties.NotFoundCode,"没有查询到电影,查询失败");
        }
    }
}
