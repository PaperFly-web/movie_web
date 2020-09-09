package com.paperfly.system.controller;

import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Move;
import com.paperfly.system.properties.CommonlyUsed;
import com.paperfly.system.service.MoveService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MoveController {
    @Autowired
    HttpSession session;
    @Autowired
    MoveService moveService;


    /**
     * 电影上传
     * 需要前台参数
     * mDirect,mStory,mArea，mType，mMainRole，mPublicTime，mDuration，mName
     * */
    @PostMapping("/uploadMove")
    @RequiresAuthentication
    @Transactional
    public String uploadMove(Move move, MultipartFile file,MultipartFile pic,Map<String,Object> map){
        Integer userId = (Integer) session.getAttribute("userId");
        move.setUserId(userId);
        CommonResult commonResult = moveService.uploadMove(move, file,pic);
        map.put("msg",commonResult);
        return "movie/uploadMove";
    }

    /**
     * 查询所有电影（分页查询）
     * 需要前端参数
     * page
     * 返回的参数
     *       move的所有参数，move拥有者的姓名
     * */
    @PostMapping("/selectMoves")
    @ResponseBody
    public CommonResult selectMoves(Move move){
        CommonResult commonResult = moveService.selectMoves(move);
        return commonResult;
    }

    /**
     * 查询自己的电影（分页查询）
     * 需要前端传入参数
     *  page
     *  返回的参数
     *      move的所有参数，move拥有者的姓名
     * */
    @PostMapping("/selectMyselfMoves")
    @ResponseBody
    @RequiresAuthentication
    public CommonResult selectMyselfMoves(Move move){
        move.setUserId((Integer) session.getAttribute("userId"));
        CommonResult commonResult = moveService.selectMyselfMoves(move);
        return commonResult;
    }

   /**
    * 需要前端传入参数
    * page
    * 返回move所有信息，和用户名
    * */
    @PostMapping("/selectMovesByTime")
    @ResponseBody
    public CommonResult selectMovesByTime(Move move){
        CommonResult commonResult = moveService.selectMovesByTime(move);
        return commonResult;
    }

    /**
     * 需要前端传入参数
     * page
     * 返回move所有信息，和用户名
     * */
    @PostMapping("/selectMovesByHot")
    @ResponseBody
    public CommonResult selectMovesByHot(Move move){
        CommonResult commonResult = moveService.selectMovesByHot(move);
        return commonResult;
    }
    @PostMapping("/selectMoveForIndex")
    @ResponseBody
    public CommonResult selectMoveForIndex(){
        //因为首页的视频数量是固定的，所以不需要传入page，后天给个page 就好
        Move move=new Move();
        move.setPage(0);
        CommonResult commonResult = moveService.selectMoveForIndex(move);
        return commonResult;
    }



    @PostMapping("/findMoveByKeyWordsAndAjax")
    @ResponseBody
    public CommonResult findMoveByKeyWordsAndAjax(String keyWords,Integer page){
        page=page* CommonlyUsed.PAGE_NUM;
        Object userId = session.getAttribute("userId");
        CommonResult commonResult = moveService.findMoveByKeyWords(keyWords, page);
        if(userId==null){
            commonResult.setMessage("用户没登录");
        }

        return commonResult;
    }
    /**
     * 需要前端参数
     * mId，userId
     * */
    @PostMapping("/deleteMoves")
    @ResponseBody
    @RequiresAuthentication
    public CommonResult deleteMoves(Integer[] mId){
        /*获取会话的用户id数据*/
        Integer userId= (Integer) session.getAttribute("userId");

        List<Move> moves=new ArrayList<>();

        for (int i=0;i<mId.length;i++){
            /**
             * 用户删除电影得判断是不是本人操作，所以需要传入用户id和电影存储的用户id比较
             * */
            moves.add(new Move(userId,mId[i]));
        }
        CommonResult commonResult = moveService.deleteMoves(moves);
        return commonResult;
    }


}
