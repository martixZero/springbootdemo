package com.springboot.ch3.exception;

/**
 * @author zhouhy
 * @create 2017 -03-17 下午12:51
 */

public class MyException extends Exception {

    public MyException(String message) {
        super(message);
    }
}
