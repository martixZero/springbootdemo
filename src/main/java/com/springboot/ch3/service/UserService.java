package com.springboot.ch3.service;

/**
 * @author zhouhy
 * @create 2017 -03-17 下午1:21
 */

public interface UserService {
    /**
     * 创建一个用户
     * @param name
     * @param age
     */
    void create(int id,String name,Integer age);

    /**
     *
     * @param name
     */
    void deleteByName(String name);

    /**
     * 获取用户总数
     * @return
     */
    Integer getAllCount();

    /**
     * 删除所有用户
     */
    void deleteAllUsers();
}
