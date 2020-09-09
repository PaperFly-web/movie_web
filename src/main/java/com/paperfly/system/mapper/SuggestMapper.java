package com.paperfly.system.mapper;

import com.paperfly.system.pojo.Suggest;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SuggestMapper {


    int insert(Suggest suggest);
    List<Suggest> selectSuggest(Integer page);
}