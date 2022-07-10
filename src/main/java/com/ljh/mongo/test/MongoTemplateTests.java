package com.ljh.mongo.test;

import com.ljh.mongo.entity.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
        boolean exists = mongoTemplate.collectionExists("products");
        if (!exists){
            mongoTemplate.createCollection("products");
        }
    }

    //2、删除集合
    @Test
    public void dropCollection(){
        mongoTemplate.dropCollection("products");
    }


    //3、新增数据
    @Test
    public void insertDocument(){
        //User user = new User(2, "李四", 2500d, new Date());
        //mongoTemplate.save(user); //id存在的时候，save是更新操作；save 不可以进行P处理,遍历一条条插入
        //mongoTemplate.insert(user);//id存在的时候，主键冲突; insert 可以进行p处理，一次性多条插入
        mongoTemplate.insert(Arrays.asList(
                new User(3, "李四3", 2503d, new Date()),
                new User(4, "李四4", 2504d, new Date()),
                new User(5, "李四5", 2505d, new Date()),
                new User(6, "李四6", 2506d, new Date()),
                new User(7, "李四7", 2507d, new Date())),"user");

    }

    //4、查询所有
    @Test
    public void findall(){
        List<User> userList = mongoTemplate.findAll(User.class, "user");
        userList.forEach(System.out::println);
    }

    //5、根据id查询一条
    @Test
    public void findbyid(){
        User user = mongoTemplate.findById(1, User.class);
        System.out.println(user);
    }

    //6、条件查询
    @Test
    public void find(){
        List<User> userList = mongoTemplate.find(new Query(), User.class);
        userList.forEach(System.out::println);
    }

    //7、等值查询
    @Test
    public void findQuery(){
        Query query = Query.query(Criteria.where("name").is("李四"));
        List<User> userList = mongoTemplate.find(query, User.class);
        userList.forEach(System.out::println);
    }


    //8、> < >= <= 查询
    @Test
    public void findQuery2(){
        Query query = Query.query(Criteria.where("salary").lte(2503));
        List<User> userList = mongoTemplate.find(query, User.class);
        userList.forEach(System.out::println);
    }

    //9、and查询
    @Test
    public void findQuery3(){
        Query query = Query.query(Criteria.where("name").is("李四3").and("salary").is(2503));
        List<User> userList = mongoTemplate.find(query, User.class);
        userList.forEach(System.out::println);
    }


    //10、or查询
    @Test
    public void findQuery4(){
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("name").is("张三"),Criteria.where("name").is("李四"));
        Query query = Query.query(criteria);
        List<User> userList = mongoTemplate.find(query, User.class);
        userList.forEach(System.out::println);
    }


    //11、and or查询
    @Test
    public void findQuery5(){
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("name").is("张三").orOperator(Criteria.where("salary").is(2500)));
        Query query = Query.query(criteria);
        List<User> userList = mongoTemplate.find(query, User.class);
        userList.forEach(System.out::println);
    }

    //12、排序查询
    @Test
    public void findQuery6(){
        Query query = new Query();
        query.with(Sort.by(Sort.Order.desc("salary")));
        List<User> userList = mongoTemplate.find(query, User.class);
        userList.forEach(System.out::println);
    }

    //13、分页查询
    @Test
    public void findQuery7(){
        Query query = new Query();
        query.with(Sort.by(Sort.Order.desc("salary"))).skip(0).limit(4);
        List<User> userList = mongoTemplate.find(query, User.class);
        userList.forEach(System.out::println);
    }

    //14 条数
    @Test
    public void findQuery8(){
        Query query = new Query();
        query.with(Sort.by(Sort.Order.desc("salary"))).skip(0).limit(4);
        long count = mongoTemplate.count(query, User.class);
        System.out.println(count);
    }

    //15、去重查询
    @Test
    public void findQuery9(){
        Query query = new Query();
        List<String> name = mongoTemplate.findDistinct(query, "salary", User.class, String.class);
        System.out.println(name);
    }

    //16、使用json查询
    @Test
    public void findQuery10(){
        Query query = new BasicQuery("{$or:[{name:'张三'},{name:'李四'}]}");
        List<User> userList = mongoTemplate.find(query, User.class);
        userList.forEach(System.out::println);
    }

    //17、更新
    @Test
    public void update(){
        Update update = new Update();
        update.set("salary",2510);
        Query query = Query.query(Criteria.where("name").is("李四"));
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);
        System.out.println(updateResult);

        //多条更新
        //mongoTemplate.updateMulti(query,update,User.class);
    }

    //18 删除
    @Test
    public void delete(){
        Query query = new Query(Criteria.where("name").is("李四7"));
        DeleteResult remove = mongoTemplate.remove(query, User.class);
        System.out.println(remove);
    }


}
