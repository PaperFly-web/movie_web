package com.paperfly.system.controller;

import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Suggest;
import com.paperfly.system.properties.CommonlyUsed;
import com.paperfly.system.service.SuggestService;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
public class SuggestController {

    @Autowired
    SuggestService suggestService;

    @PostMapping("/insertSuggest")
    @Transactional
    public String insertSuggest(Suggest suggest,Map<String,Object> map){
        suggest.setSuccCreateTime(new Date());
        CommonResult commonResult = suggestService.insert(suggest);
        map.put("msg",commonResult);
        return "webIntroduce/webIntroduce";
    }

    @PostMapping("/selectSuggest")
    @RequiresPermissions("3")
    @ResponseBody
    public CommonResult selectSuggest(Integer page){
        page=page* CommonlyUsed.COM_PAGE_NUM;
        CommonResult commonResult = suggestService.selectSuggest(page);
        return commonResult;
    }
}
