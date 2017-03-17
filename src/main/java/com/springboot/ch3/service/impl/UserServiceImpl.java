package com.springboot.ch3.service.impl;

import com.springboot.ch3.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author zhouhy
 * @create 2017 -03-17 下午1:25
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    /**
     * 创建一个用户
     *
     * @param name
     * @param age
     */
    @Override
    public void create(int id,String name, Integer age) {

        jdbcTemplate.update("INSERT INTO USER (ID,NAME,AGE)VALUES (?,?,?)",id,name,age);

    }

    /**
     * @param name
     */
    @Override
    public void deleteByName(String name) {
        jdbcTemplate.update("DELETE FROM USER WHERE NAME=?",name);

    }

    /**
     * 获取用户总数
     *
     * @return
     */
    @Override
    public Integer getAllCount() {
        return jdbcTemplate.queryForObject("select count(1) from USER",Integer.class);
    }

    /**
     * 删除所有用户
     */
    @Override
    public void deleteAllUsers() {
        jdbcTemplate.update("DELETE from USER ");

    }
}
