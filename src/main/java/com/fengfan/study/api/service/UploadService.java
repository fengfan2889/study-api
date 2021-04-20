package com.fengfan.study.api.service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;

public interface UploadService {

    Boolean save(JSONObject jsonObject) ;

    PageInfo query(JSONObject jsonObject);
}
