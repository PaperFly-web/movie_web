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
public class View {
    private Integer vId;

    private Integer userId;

    private Integer mId;

    private Date vCreateTime;

    private Integer page;

    private User user;

    private Move move;

}