package com.paperfly.admin.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieWebData {
    private Integer lastRegistCount;
    private Integer todayUserLoginCount;
    private Integer userCount;
    private Integer lastMovieCount;
    private Integer passMovieCount;
    private Integer noPassMovieCount;
    private Integer movieCount;
}
