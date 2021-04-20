package com.fengfan.study.api.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fengfan.study.api.data.bean.UserInfo;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class H2UploadDaoTest {

    @Autowired
    private H2UploadDao h2UploadDao;

    UserInfo userInfo;

    @Before
    public void doBefore(){


    }

    @Test
    void insertUser() {
        userInfo = new UserInfo();
        userInfo.setName("white");
        userInfo.setPhone("13400000000");
        userInfo.setStrDay("20210417");
        userInfo.setContent("content test");
        System.out.println(userInfo);
        h2UploadDao.insertUser(userInfo) ;

    }

    @Test
    void insertData() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tableName","tb_test");
        JSONObject record = new JSONObject() ;
        record.put("str_day","20210418") ;
        record.put("name","cash") ;
        record.put("phone","12345678978");
        record.put("content","1111111111") ;
        JSONArray content = new JSONArray();
        content.add(record);
        jsonObject.put("content",content) ;
        System.out.println(jsonObject);
        h2UploadDao.insertData(jsonObject.toJSONString());
    }

    @Test
    void queryData() {
        System.out.println(userInfo);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tableName","tb_test");
        JSONObject param = new JSONObject() ;
        param.put("columnName","str_Day") ;
        param.put("equals","=");
        param.put("value","20210418");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(param) ;
        jsonObject.put("params",jsonArray) ;

        System.out.println(h2UploadDao.queryData("",jsonObject.toJSONString()));
    }
}