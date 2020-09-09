package com.paperfly.admin.service.impl;

import cn.hutool.core.io.FileUtil;
import com.paperfly.admin.mapper.AdminMovieMapper;
import com.paperfly.admin.pojo.MovieWebData;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;
import com.paperfly.system.properties.CodeProperties;
import com.paperfly.admin.service.AdminMovieService;
import com.paperfly.system.properties.CommonlyUsed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class AdminMovieMovieServiceImpl implements AdminMovieService {
    @Autowired
    AdminMovieMapper adminMovieMapper;

    @Override
    public CommonResult deleteMoves(List<Move> moves) {
        int i = adminMovieMapper.deleteMoves(moves);

        if(i>0){
            //删除真正存储的电影
            for (int j=0;j<moves.size();j++){
                FileUtil.del("D:/"+moves.get(j).getMFalseSavePath());
                FileUtil.del("D:/"+moves.get(j).getMFalsePicSavePath());
            }
            return new CommonResult(CodeProperties.SuccessCode,"删除成功"+i+"个电影");
        }else {
            return new CommonResult(CodeProperties.ErrorCode,"删除电影失败");
        }
    }

    @Override
    public CommonResult selectMovieWebData() {
        MovieWebData movieWebData = adminMovieMapper.selectMovieWebData();
        return new CommonResult(200,"漏网之鱼电影网数据",movieWebData);
    }

    @Override
    public CommonResult selectMovie(String isPass, Integer page) {
        DecimalFormat df = new DecimalFormat(".00");
        page=page*CommonlyUsed.PAGE_NUM;
        List<Move> moves = adminMovieMapper.selectMovie(isPass, page);
        if(moves.size()== CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.SuccessCode,"查询电影成功",moves);
        }else if (moves.size()>0&&moves.size()<=CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.HalfSuccessCode,"电影查询到了最后的数据了",moves);
        }else {
            return new CommonResult(CodeProperties.NotFoundCode,"没有查询到电影,查询失败");
        }
    }


}
