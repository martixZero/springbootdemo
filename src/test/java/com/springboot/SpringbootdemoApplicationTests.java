package com.springboot;

import com.springboot.ch1.controller.ch1Controller;
import com.springboot.ch3.web.UserController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//用于mvc测试
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class SpringbootdemoApplicationTests {


    private static Log log = LogFactory.getLog(SpringbootdemoApplication.class);
    private MockMvc mvc;

    @Before
    public void setUp() {
//		mvc= MockMvcBuilders.standaloneSetup(new ch1Controller()).build();
        mvc = MockMvcBuilders.standaloneSetup(new ch1Controller(), new UserController()).build();

    }

    @Test
    public void gethello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/index").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(equalTo("hello,world")));
    }

    @Test
    public void testUserController() throws Exception {

        RequestBuilder requestBuilder = null;
        requestBuilder = get("/users/");
        // 1、get查一下user列表，应该为空
        mvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));
        // 2、post提交一个user
        requestBuilder = post("/users/").param("id", "1").param("name", "martix").param("age", "12");
        mvc.perform(requestBuilder).andExpect(content().string(equalTo("success")));
        // 3、get获取user列表，应该有刚才插入的数据
        requestBuilder = get("/users/");
        mvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"martix\",\"age\":12}]")));

        // 4、put修改id为1的user
        requestBuilder = put("/users/1").param("name", "marin").param("age", "23");
        mvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().string(equalTo("success")));
        requestBuilder = delete("/users/1");
        mvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(content().string("success"));
        requestBuilder = get("/users/");
        // 1、get查一下user列表，应该为空
        mvc.perform(requestBuilder).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));
    }

}
