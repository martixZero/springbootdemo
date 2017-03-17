package com.springboot.ch2.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhouhy
 * @create 2017 -03-16 下午8:07
 */
@Component
public class ValueProperties {


    @Value("${com.martix.learn.name}")
    private String name;
    @Value("${com.martix.learn.title}")
    private String title;
    @Value("${com.martix.learn.desc}")
    private String desc;
    @Value("${com.martix.learn.value}")
    private String value;
    @Value("${com.martix.learn.number}")
    private Integer number;
    @Value("${com.martix.learn.bignumber}")
    private Long bignumber;
    @Value("${com.martix.learn.num1}")
    private Integer num1;
    @Value("${com.martix.learn.num2}")
    private Integer num2;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getBignumber() {
        return bignumber;
    }

    public void setBignumber(Long bignumber) {
        this.bignumber = bignumber;
    }

    public Integer getNum1() {
        return num1;
    }

    public void setNum1(Integer num1) {
        this.num1 = num1;
    }

    public Integer getNum2() {
        return num2;
    }

    public void setNum2(Integer num2) {
        this.num2 = num2;
    }
}
