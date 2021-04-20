package com.fengfan.study.api.data.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserInfo {

    private Integer Id ;
    private String name ;
    private String phone;
    private String strDay;
    private String content ;
}
