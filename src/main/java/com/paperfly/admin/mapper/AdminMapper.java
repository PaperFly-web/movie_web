package com.paperfly.admin.mapper;

import com.paperfly.system.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<User> selectUsers(User user);
    int updateUserPerm(List<User> users);
    int updateUserStatus(List<User> users);
}
