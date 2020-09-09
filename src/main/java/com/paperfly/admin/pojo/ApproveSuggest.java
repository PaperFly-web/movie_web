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
public class ApproveSuggest {
    private Long approveCId;

    private Date approveCCreateTime;

    private String approveCStatues;

    private Integer userId;

    private Integer cId;

    private String approveCNote;

}