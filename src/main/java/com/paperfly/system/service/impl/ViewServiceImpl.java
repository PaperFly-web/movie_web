package com.paperfly.system.service.impl;

import com.paperfly.system.mapper.ViewMapper;
import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.View;
import com.paperfly.system.properties.CodeProperties;
import com.paperfly.system.properties.CommonlyUsed;
import com.paperfly.system.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewServiceImpl implements ViewService {


    @Autowired
    ViewMapper viewMapper;

    @Override
    public CommonResult selectViewHistory(View view) {
        view.setPage(view.getPage()*CommonlyUsed.PAGE_NUM);
        List<View> views = viewMapper.selectViewHistory(view);
        if(views.size()== CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.SuccessCode,"查询浏览历史成功",views);
        }else if (views.size()>0&&views.size()<=CommonlyUsed.PAGE_NUM){
            return new CommonResult(CodeProperties.HalfSuccessCode,"查询浏览历到了最后的数据了",views);
        }else {
            return new CommonResult(CodeProperties.NotFoundCode,"没有查询到浏览历史");
        }

    }
}
