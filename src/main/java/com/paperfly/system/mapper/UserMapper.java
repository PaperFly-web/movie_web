package com.paperfly.system.mapper;

import com.paperfly.system.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insert(User record);
    User login(User user);
    int modifyUserInfo(User user);
    User selectUserInfo(User user);
}