package com.paperfly.system.mapper;

import com.paperfly.system.pojo.View;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ViewMapper {
    List<View> selectViewHistory(View view);
}