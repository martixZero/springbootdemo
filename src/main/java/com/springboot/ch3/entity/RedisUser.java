package com.springboot.ch3.entity;

import java.io.Serializable;

/**
 * @author zhouhy
 * @create 2017 -03-17 下午5:13
 */

public class RedisUser implements Serializable {

    private static final long  serialVersionUID = -1L;

    private String username;
    private Integer num;




    public RedisUser(String username, Integer num) {

        this.username = username;
        this.num = num;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
