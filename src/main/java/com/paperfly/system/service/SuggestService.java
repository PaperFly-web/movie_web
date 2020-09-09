package com.paperfly.system.service;

import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.Suggest;
import com.paperfly.system.properties.CommonlyUsed;

public interface SuggestService {
    CommonResult insert(Suggest suggest);
    CommonResult selectSuggest(Integer page);
}
