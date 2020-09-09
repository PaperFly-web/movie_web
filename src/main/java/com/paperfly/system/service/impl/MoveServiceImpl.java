package com.paperfly.system.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.UUID;
import com.paperfly.system.mapper.MoveMapper;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;
import com.paperfly.system.pojo.View;
import com.paperfly.system.properties.CodeProperties;
import com.paperfly.system.properties.CommonlyUsed;
import com.paperfly.system.service.MoveService;
import com.paperfly.system.utils.MyFileUtil;
import com.paperfly.system.utils.ReadVideoTimeLong;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MoveServiceImpl implements MoveService {
    @Autowired
    MoveMapper moveMapper;
    @Override

    public CommonResult uploadMove(Move move, MultipartFile file, MultipartFile pic) {

        String fileName=file.getOriginalFilename();
        String picName=pic.getOriginalFilename();
        log.info("电影文件类型:"+file.getContentType()+"文件名称:"+file.getName());
        log.info("图片文件类型:"+pic.getContentType()+"文件名称:"+pic.getName());

        String moveType = MyFileUtil.getExtName(fileName);

        String movePicType = MyFileUtil.getExtName(picName);
        /**
         * 由于浏览器不能访问电脑的绝对路径，所以电影，电影封面的保存地址添加了一个虚拟路径来提替换
         * falseSavePath  电影的虚拟路径
         * falsePicSavePath  封面的虚拟路径
         * */
        String savePathUUID=UUID.randomUUID()+"";
        String picSavePathUUID=UUID.randomUUID()+"";
        String savePath=CommonlyUsed.MOVE_SAVE_PATH+"/"+ savePathUUID+moveType;
        String falseSavePath=CommonlyUsed.MOVE_FALSE_SAVE_PATH+"/"+ savePathUUID+moveType;
        String picSavePath=CommonlyUsed.MOVE_PIC_SAVE_PATH+"/"+ picSavePathUUID+movePicType;
        String falsePicSavePath=CommonlyUsed.MOVE_FALSE_PIC_SAVE_PATH+"/"+ picSavePathUUID+movePicType;
        if (!file.getContentType().equals("video/mp4")){
            return new CommonResult(CodeProperties.ErrorCode,"只能上传MP4格式视频");
        }else if(!pic.getContentType().equals("image/jpeg")&&!pic.getContentType().equals("image/png")){
            return new CommonResult(CodeProperties.ErrorCode,"只能上传jpg,png封面");
        } else {
            MyFileUtil.uploadFile(picSavePath,pic);
            MyFileUtil.uploadFile(savePath,file);
            //获取视频时长
            String videoTime = ReadVideoTimeLong.ReadVideoTime(savePath);
            move.setMDuration(videoTime);
            move.setMSize(Math.toIntExact(file.getSize() / (1024 * 1024)));
            move.setMHot(0);
            move.setMCreateTime(new Date());
            move.setMPicSavePath(picSavePath);
            move.setMFalsePicSavePath(falsePicSavePath);
            move.setMSavePath(savePath);
            move.setMPublicTime(new Date());
            move.setMFalseSavePath(falseSavePath);
            int insert = moveMapper.insert(move);
            if(insert>0){

                return new CommonResult(CodeProperties.SuccessCode,"电影上传成功");
            }else {
                return new CommonResult(CodeProperties.ErrorCode,"电影上传失败");
            }
        }

    }

    @Override
    public CommonResult selectMoves(Move move) {
        move.setPage(move.getPage()*CommonlyUsed.PAGE_NUM);
        List<Move> moves = moveMapper.selectMoves(move);
        log.info("传入来的查询电影参数:"+move);
        log.info("查询的出来的电影:"+moves);
        if(moves.size()==CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.SuccessCode,"查询电影成功",moves);
        }else if (moves.size()>0&&moves.size()<=CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.HalfSuccessCode,"电影查询到了最后的数据了",moves);
        }else {
            return new CommonResult(CodeProperties.NotFoundCode,"没有查询到电影,查询失败");
        }

    }

    @Override
    public CommonResult selectMyselfMoves(Move move) {
        move.setPage(move.getPage()*CommonlyUsed.PAGE_NUM);
        List<Move> moves = moveMapper.selectMyselfMoves(move);
        log.info("查询自己的电影:传入来的查询电影参数:"+move);
        log.info("查询自己的电影:查询的出来的电影:"+moves);
        if(moves.size()== CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.SuccessCode,"查询电影成功",moves);
        }else if (moves.size()>0&&moves.size()<=CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.HalfSuccessCode,"电影查询到了最后的数据了",moves);
        }else {
            return new CommonResult(CodeProperties.NotFoundCode,"没有查询到电影,查询失败");
        }
    }

    @Override
    public CommonResult deleteMoves(List<Move> moves) {
        //删除数据库的数据
        int i = moveMapper.deleteMoves(moves);
        if(i>0){
            /**
             * 删除真实存在的电影和封面
             * */
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
    public CommonResult updateMoveHot(View view) {
        int i = moveMapper.updateMoveHot(view);
        /**
         * 大于1，就是操作成功：更新电影热度和记录观看电影历史
         * */
        if(i>=1){
            return new CommonResult(CodeProperties.SuccessCode, "更新电影热度成功,并成功记录观看历史",view);
        }else {
            return new CommonResult(CodeProperties.ErrorCode, "操作失败,可能电影不存在",view);
        }
    }

    @Override
    public CommonResult selectMovesByTime(Move move) {
        move.setPage(move.getPage()*CommonlyUsed.PAGE_NUM);
        List<Move> moves = moveMapper.selectMovesByTime(move);
        if(moves.size()== CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.SuccessCode,"查询电影成功",moves);
        }else if (moves.size()>0&&moves.size()<=CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.HalfSuccessCode,"电影查询到了最后的数据了",moves);
        }else {
            return new CommonResult(CodeProperties.NotFoundCode,"没有查询到电影,查询失败");
        }
    }

    @Override
    public CommonResult selectMovesByHot(Move move) {
        move.setPage(move.getPage()*CommonlyUsed.PAGE_NUM);
        List<Move> moves = moveMapper.selectMovesByHot(move);
        if(moves.size()== CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.SuccessCode,"查询电影成功",moves);
        }else if (moves.size()>0&&moves.size()<=CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.HalfSuccessCode,"电影查询到了最后的数据了",moves);
        }else {
            return new CommonResult(CodeProperties.NotFoundCode,"没有查询到电影,查询失败");
        }
    }

    @Override
    public CommonResult selectMoveForIndex(Move move) {
        List<Move> moves = moveMapper.selectMoves(move);
        List<Move> allMoves=new ArrayList<>();
        if(moves.size()> CommonlyUsed.INDEX_MOVE_NUM){
            for (int i=0;i<CommonlyUsed.INDEX_MOVE_NUM;i++){
                allMoves.add(moves.get(i));
            }
            return new CommonResult(CodeProperties.SuccessCode,"查询电影成功",allMoves);
        }else if (moves.size()>0&&moves.size()<=CommonlyUsed.INDEX_MOVE_NUM){
            for (int i=0;i<moves.size();i++){
                allMoves.add(moves.get(i));
            }
            return new CommonResult(CodeProperties.HalfSuccessCode,"电影查询到了最后的数据了",allMoves);
        }else {
            return new CommonResult(CodeProperties.NotFoundCode,"没有查询到电影,查询失败");
        }

    }

    @Override
    public CommonResult findMoveByKeyWords(String keyWords, Integer page) {
        List<Move> moves = moveMapper.findMoveByKeyWords(keyWords, page);
        //设置查询总共的页数
        for (int i=0;i<moves.size();i++){
            Move move = moves.get(i);
            if(move.getCount()>=CommonlyUsed.PAGE_NUM){
                if(move.getCount()%CommonlyUsed.PAGE_NUM==0){
                    move.setCount(move.getCount()/CommonlyUsed.PAGE_NUM);
                }else {
                    move.setCount(move.getCount()/CommonlyUsed.PAGE_NUM+1);
                }
            }else {
                move.setCount(1);
            }
        }
        if(moves.size()== CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.SuccessCode,"查询电影成功",moves);
        }else if (moves.size()>0&&moves.size()<=CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.HalfSuccessCode,"查询到了最后一页电影数据了",moves);
        }else {
            return new CommonResult(CodeProperties.NotFoundCode,"没有查询到电影,查询失败");
        }
    }
}
