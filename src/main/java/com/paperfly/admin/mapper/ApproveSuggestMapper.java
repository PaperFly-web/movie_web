package com.paperfly.admin.mapper;

import com.paperfly.admin.pojo.ApproveSuggest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApproveSuggestMapper {
    int deleteByPrimaryKey(Long approveCId);

    int insert(ApproveSuggest record);

    int insertSelective(ApproveSuggest record);

    ApproveSuggest selectByPrimaryKey(Long approveCId);

    int updateByPrimaryKeySelective(ApproveSuggest record);

    int updateByPrimaryKey(ApproveSuggest record);
}