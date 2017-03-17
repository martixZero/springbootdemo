package com.springboot.ch3.entity;

import javax.persistence.Id;

/**
 * @author zhouhy
 * @create 2017 -03-17 下午7:08
 */

public class MUser {

    @Id
    private Long id;

    private String username;

    private Integer age;

    public MUser(Long id, String username, Integer age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
