package com.springboot.ch3.exception;

/**
 * @author zhouhy
 * @create 2017 -03-17 下午1:09
 */

public class UserException extends Exception {

    public UserException(String message) {
        super(message);
    }
}
