package com.springboot.ch1.controller;

import com.springboot.ch3.exception.MyException;
import com.springboot.ch3.exception.UserException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhouhy
 * @create 2017 -03-16 下午7:53
 */
@Controller
public class ch1Controller {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello,world";
    }

    @RequestMapping("/helloerror")
    public String helloerror() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("json格式返回异常");
    }
    @RequestMapping("/jsonuser")
    public String jsonuser() throws UserException {
        throw new UserException("json+user格式返回异常");
    }

    @RequestMapping("/")
    public String index(ModelMap modelMap){
        modelMap.addAttribute("host","http://baidu.com");
        return "index";
    }

    @RequestMapping("/ftl")
    public String ftl(ModelMap modelMap){
        modelMap.addAttribute("host","http://baidu.com");
        return "index1";
    }

    @RequestMapping("/vm")
    public String vm(ModelMap modelMap){
        modelMap.addAttribute("host","http://www.martix.org");
        return "index2";
    }
}
