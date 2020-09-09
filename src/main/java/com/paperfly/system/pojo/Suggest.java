package com.paperfly.system.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Suggest {
    private Integer succId;

    private String succUserName;

    private String succPhone;

    private Date succCreateTime;

    private String succContent;

    private Integer page;

}