package com.paperfly.admin.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApproveMove {
    private Integer approveMId;

    private String approveMIsPass;

    private Date approveMCreateTime;

    private Integer userId;

    private String approveMNote;

    private Integer page;

    private Integer mId;
}