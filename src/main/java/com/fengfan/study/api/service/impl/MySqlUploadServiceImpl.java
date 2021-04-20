package com.fengfan.study.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fengfan.study.api.dao.H2UploadDao;
import com.fengfan.study.api.dao.MySqlUploadDao;
import com.fengfan.study.api.service.UploadService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

//@Service
public class MySqlUploadServiceImpl implements UploadService {

    private final MySqlUploadDao mySqlUploadDao;

    public MySqlUploadServiceImpl(MySqlUploadDao mySqlUploadDao) {
        this.mySqlUploadDao = mySqlUploadDao;
    }

    @Override
    public Boolean save(JSONObject jsonObject) {
        mySqlUploadDao.insertData(jsonObject.toJSONString()) ;
        return true ;
    }

    @Override
    public PageInfo query(JSONObject jsonObject) {
        int pageNum = 1 ;
        if(jsonObject.containsKey("pageNum")){
            pageNum = jsonObject.getInteger("pageNum") ;
        }
        int pageSize = 65535 ;
        if(jsonObject.containsKey("pageSize")){
            pageSize = jsonObject.getInteger("pageSize") ;
        }
        PageHelper.startPage(pageNum,pageSize) ;
        Page result = mySqlUploadDao.queryData("",jsonObject.toJSONString());
        PageInfo pageInfo = new PageInfo<>(result);
        return pageInfo ;
    }


}
