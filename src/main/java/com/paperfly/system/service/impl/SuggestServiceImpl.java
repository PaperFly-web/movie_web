package com.paperfly.system.service.impl;

import com.paperfly.system.mapper.SuggestMapper;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Suggest;
import com.paperfly.system.properties.CodeProperties;
import com.paperfly.system.properties.CommonlyUsed;
import com.paperfly.system.service.SuggestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestServiceImpl implements SuggestService {
    @Autowired
    SuggestMapper suggestMapper;

    @Override
    public CommonResult insert(Suggest suggest) {
        int insert = suggestMapper.insert(suggest);
        if(insert>0){
            return new CommonResult(CodeProperties.SuccessCode, "提交建议成功");
        }else {
            return new CommonResult(CodeProperties.ErrorCode, "提交建议失败",suggest);
        }
    }

    @Override
    public CommonResult selectSuggest(Integer page) {
        List<Suggest> suggests = suggestMapper.selectSuggest(page);
        if(suggests.size()>=6){
            return new CommonResult(CodeProperties.SuccessCode, "查询建议成",suggests);
        }else if(suggests.size()<6&&suggests.size()>0){
            return new CommonResult(CodeProperties.HalfSuccessCode,"查询到最后一页了",suggests);
        }
        else {
            return new CommonResult(CodeProperties.NotFoundCode, "没有查询到建议");
        }
    }
}
