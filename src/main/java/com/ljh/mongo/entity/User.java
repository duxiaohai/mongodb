package com.ljh.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * Explain:
 * Author: linjianhai
 * Date: 2022/7/10 20:54
 */
@Document("user")
public class User {
  @Id
  private Integer userId;

  @Field(value = "name")
  private String userName;

  @Field
  private Double salary;

  @Field
  private Date birthday;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public User(Integer userId, String userName, Double salary, Date birthday) {
        this.userId = userId;
        this.userName = userName;
        this.salary = salary;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                '}';
    }
}
