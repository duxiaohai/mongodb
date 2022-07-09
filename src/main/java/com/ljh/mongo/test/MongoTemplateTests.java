package com.ljh.mongo.test;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.net.UnknownHostException;

/**
 * Explain:
 * Author: linjianhai
 * Date: 2022/7/9 20:40
 */
public class MongoTemplateTests extends SpringApplicationTest {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoTemplateTests(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    //1、创建集合
    @Test
    public void createCollection() {
       mongoTemplate.createCollection("products2");
    }


    //2、删除集合
    @Test
    public void dropCollection(){
        mongoTemplate.dropCollection("products2");
    }

    //1、删除集合
    @Test
    public void insertCollection(){

        mongoTemplate.insert("products");
    }

}
