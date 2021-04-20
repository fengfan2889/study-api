package com.fengfan.study.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fengfan.study.api.dao.H2UploadDao;
import com.fengfan.study.api.service.UploadService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

@Service
public class H2UploadServiceImpl implements UploadService {

    private final H2UploadDao h2UploadDao;

    public H2UploadServiceImpl(H2UploadDao h2UploadDao) {
        this.h2UploadDao = h2UploadDao;
    }

    @Override
    public Boolean save(JSONObject jsonObject) {
        h2UploadDao.createData(jsonObject.toJSONString()) ;
        h2UploadDao.insertData(jsonObject.toJSONString()) ;
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

        Page result = h2UploadDao.queryData("",jsonObject.toJSONString());
        PageInfo pageInfo = new PageInfo<>(result);
        return pageInfo ;
    }


}
