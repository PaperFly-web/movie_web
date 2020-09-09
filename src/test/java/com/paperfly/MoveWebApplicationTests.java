package com.paperfly;

import com.paperfly.admin.mapper.ApproveMoveMapper;
import com.paperfly.admin.pojo.ApproveMove;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.User;
import com.paperfly.system.service.UserService;
import com.paperfly.system.utils.MyFileUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.xml.stream.events.DTD;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class MoveWebApplicationTests {
    @Autowired
    UserService userService;

    @Autowired
    ApproveMoveMapper approveMoveMapper;
    @Test
    void contextLoads() {
        CommonResult commonResult=null;
        User user=new User();
        user.setUserName("胡金戈你好次哦啊");
        user.setUserCreateTime(new Date());
        user.setUserPhone("18852936683");
        user.setUserPwd("123456");
        user.setUserRole("0");
        user.setUserPerm("0");
        commonResult=userService.insert(user);

        System.out.println(commonResult);
    }

    @Test
    void t2(){
        List<ApproveMove> approveMoves=new ArrayList<>();
        for (int i=11;i<13;i++){
            ApproveMove approveMove=new ApproveMove();
            approveMove.setApproveMIsPass("1");
            approveMove.setApproveMNote("测试的第:"+i+"个数据备注");
            approveMove.setMId(i);
            approveMove.setUserId(1);
            approveMove.setApproveMCreateTime(new Date());
            approveMoves.add(approveMove);
        }
        int i = approveMoveMapper.approveMove(approveMoves);
        System.out.println("操作成功:"+i);
    }

    @Test
    void t5(){
        String [] mainRole,type,area,direct,story;
        String moviePath="D:\\1后期\\图片\\视频+照片/";
        String picPath="D:\\1后期\\图片\\视频+照片/";
        FileInputStream movieInputStream=null;
        FileInputStream picInputStream=null;
        try {
            for (int i=1;i<=2;i++){
                String picName=UUID.randomUUID()+""+i+".jpeg";
                String movieName=UUID.randomUUID()+""+i+".mp4";
                movieInputStream=new FileInputStream(moviePath+""+i+".mp4");
                picInputStream=new FileInputStream(picPath+""+i+".jpeg");
                MultipartFile movieMultipartFile=new MockMultipartFile(movieName,movieInputStream);
                MultipartFile picMultipartFile=new MockMultipartFile(picName,picInputStream);
                MyFileUtil.uploadFile("D:\\move_web\\move/"+""+i+".mp4",movieMultipartFile);
                MyFileUtil.uploadFile("D:\\move_web\\move_pic/"+""+i+".jpeg",picMultipartFile);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
