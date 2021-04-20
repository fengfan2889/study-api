package com.fengfan.study.api.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UploadServiceTest {

    @Autowired
    private UploadService uploadService ;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void save() {
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
        System.out.println(jsonObject.toJSONString());
        uploadService.save(jsonObject);
    }

    @Test
    void query() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tableName","tb_test");
        JSONObject param = new JSONObject() ;
        param.put("columnName","str_Day") ;
        param.put("equals","=");
        param.put("value","20210418");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(param) ;
        jsonObject.put("params",jsonArray) ;
        System.out.println(jsonObject.toJSONString());
        System.out.println(uploadService.query(jsonObject));
        System.out.println(JSONObject.toJSONString(uploadService.query(jsonObject)));
    }
}