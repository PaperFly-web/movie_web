package com.paperfly.system.service;

import com.paperfly.system.pojo.CommonResult;
import com.paperfly.system.pojo.View;

public interface ViewService {
    CommonResult selectViewHistory(View view);
}
