package com.paperfly.system.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Move {

    private Integer mId;

    private Integer mSize;

    private Integer userId;

    private Integer mHot;

    private String mSavePath;

    private String mDirect;

    private String mStory;

    private String mArea;

    private String mType;

    private String mMainRole;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date mPublicTime;

    private String mPicSavePath;

    private String mDuration;

    private String mName;

    private String  mIsPass;

    private Integer page;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date mCreateTime;

    private String mFalseSavePath;

    private String mFalsePicSavePath;

    private Integer count;

    private User user;
    public Move(Integer userId,Integer mId){
        this.userId=userId;
        this.mId=mId;
    }
}