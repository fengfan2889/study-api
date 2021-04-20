package com.fengfan.study.api.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fengfan.study.api.data.bean.UserInfo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface H2UploadDao {

    @Insert({"insert into tb_test (name,phone,str_day,content) values (#{name},#{phone},#{strDay},#{content})"})
    int insertUser(UserInfo userInfo) ;

    //动态拼接语句
    @InsertProvider(type = SqlBuilder.class, method = "insert")
    int insertData(@Param("data") String data);

    //动态拼接语句
    @InsertProvider(type = SqlBuilder.class, method = "create")
    int createData(@Param("data") String data);

    //动态拼接查询语句
    @SelectProvider(type = SqlBuilder.class, method = "queryData")
    Page<Map<String,String>> queryData(String msgId, String Params);

    class SqlBuilder {
        //动态构造查询语句
        public String queryData(final String msgId,String Params) {
            JSONObject jSONObject = JSONObject.parseObject(Params) ;
            String tableName = jSONObject.getString("tableName") ;
            StringBuffer sql =new StringBuffer();
            sql.append("select * from " + tableName + " where 1=1");
            JSONArray jsonArray = jSONObject.getJSONArray("params") ;
            if (jsonArray != null && jsonArray.size() > 0) {
                for (int i=0 ;i<jsonArray.size();i++){
                    JSONObject param = jsonArray.getJSONObject(i);
                    sql.append(" and " + param.getString("columnName") + param.getString("equals") + "'" + param.getString("value") + "'") ;
                }
            }
            System.out.println("查询sql=="+sql.toString());
            return sql.toString();
        }

        //删除的方法
        public String insert(@Param("data")final String data){
            StringBuffer sql =new StringBuffer();
            StringBuffer insertSqlBuffer = new StringBuffer();
            JSONObject jSONObject = JSONObject.parseObject(data) ;
            String tableName = jSONObject.getString("tableName") ;

            JSONArray jsonArray = jSONObject.getJSONArray("content") ;
            if(jsonArray == null && jsonArray.size() == 0){
                return "";
            }
            insertSqlBuffer.append("insert into " + tableName + " (") ;
            JSONObject record = jsonArray.getJSONObject(0) ;
            String split = "" ;
            for (String s : record.keySet()) {
                insertSqlBuffer.append(s + ",") ;
            }
            String insertSql = insertSqlBuffer.toString() ;
            insertSql = insertSql.substring(0,insertSql.length()-1);
            insertSql = insertSql + " ) values (" ;


            if (jsonArray != null && jsonArray.size() > 0) {
                for (int i=0 ;i<jsonArray.size();i++){
                    record = jsonArray.getJSONObject(i);
                    String oneSql = insertSql ;
                    for (String s : record.keySet()) {
                        oneSql = oneSql + "'" + record.getString(s) + "'," ;
                    }
                    oneSql = oneSql.substring(0,oneSql.length() -1) ;
                    oneSql = oneSql + ");";
                    sql.append(oneSql);
                }
            }
            System.out.println("sql=="+sql.toString());
            return sql.toString();
        }
        public String create(@Param("data")final String data){
            StringBuffer sql =new StringBuffer();
            JSONObject jSONObject = JSONObject.parseObject(data) ;
            String tableName = jSONObject.getString("tableName") ;
            sql.append("create table if not exists " + tableName + " (");
            JSONArray jsonArray = jSONObject.getJSONArray("content") ;
            if(jsonArray == null && jsonArray.size() == 0){
                return "";
            }
            JSONObject record = jsonArray.getJSONObject(0) ;
            String split = "" ;
            for (String s : record.keySet()) {
                sql.append(" " + s + " varchar(1000)," );

            }
            sql.append(" ID INT auto_increment PRIMARY KEY");
            sql.append(") ;");
            System.out.println("sql=="+sql.toString());
            return sql.toString();
        }

    }

}
