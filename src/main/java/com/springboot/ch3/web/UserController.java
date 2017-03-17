package com.springboot.ch3.web;

import com.springboot.ch3.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author zhouhy
 * @create 2017 -03-16 下午9:19
 */
@RestController
@RequestMapping("/users")
public class UserController {

    static Map<Long,User> users= Collections.synchronizedMap(new HashMap<Long, User>());

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public List<User> getUserList(){
        List<User> r=new ArrayList<User>(users.values());
        return r;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String postUser(@ModelAttribute User user){
        users.put(user.getId(),user);
        return "success";
    }
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public User getUser(@PathVariable("id") Long Id,@ModelAttribute User user){
        return users.get(Id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute User user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "success";
    }

}
