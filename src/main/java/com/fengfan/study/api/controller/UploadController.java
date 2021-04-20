package com.fengfan.study.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.fengfan.study.api.service.UploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "数据库动态保存查询")
@RestController("/up")
public class UploadController {

    private Logger logger = LoggerFactory.getLogger("business") ;

    private final UploadService uploadService ;

    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @PostMapping("/save")
    @ApiOperation(value = "保存",notes="如果表不存在，将创建表，不能使用Id字段。字段统一默认为varchar(1000),如果表存在，直接插入 post例子：{\"content\":[{\"phone\":\"12345678978\",\"name\":\"cash\",\"content\":\"1111111111\",\"str_day\":\"20210418\"}],\"tableName\":\"tb_test\"}")
    String staticUp(String msgId,@RequestBody String body){
        logger.trace("save request" + body);
        uploadService.save(JSONObject.parseObject(body)) ;
        logger.trace("save response ok") ;
        return "OK" ;
    }

    @PostMapping("/query")
    @ApiOperation(value = "查询",notes=" 参数例子：{\"params\":[{\"equals\":\"=\",\"value\":\"20210418\",\"columnName\":\"str_Day\"}],\"tableName\":\"tb_test\"}")
    String query(String msgId,@RequestBody String body){
        logger.trace("query request" + body) ;
        String result = JSONObject.toJSONString(uploadService.query(JSONObject.parseObject(body))) ;
        logger.trace("query response" + result) ;
        return result ;
    }


}
