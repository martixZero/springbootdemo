package com.springboot;

import com.springboot.ch3.dao.jpa.MUserRepository;
import com.springboot.ch3.entity.MUser;
import com.springboot.ch3.entity.RedisUser;
import com.springboot.ch3.service.UserService;
import com.springboot.domain.p.User;
import com.springboot.domain.p.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

//本地化测试当前应用SpringApplicationConfiguration赋值不同
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(SpringbootdemoApplication.class)
@WebAppConfiguration
public class ApplicationTests {

	private static final Log log = LogFactory.getLog(ApplicationTests.class);
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;
	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String,RedisUser> redisTemplate;

	@Autowired
	private MUserRepository mUserRepository;
	@Before
	public void setup(){
		mUserRepository.deleteAll();
		userRepository.deleteAll();
		userService.deleteAllUsers();
	}

	@Test
	public void testuser(){
		userService.create(1,"a",1);
		userService.create(2,"b",2);
		userService.create(3,"c",3);
		userService.create(4,"d",4);
		userService.create(5,"e",5);
		userService.create(6,"as",6);
		log.info("count1: "+userService.getAllCount().intValue());
		Assert.assertEquals(6,userService.getAllCount().intValue());
		userService.deleteByName("a");
		userService.deleteByName("b");
		log.info("count2: "+userService.getAllCount().intValue());
		Assert.assertEquals(4,userService.getAllCount().intValue());
//		userService.deleteAllUsers();
	}

	@Test
	public void testJpa(){
		userRepository.save(new User("AAA",23));
		userRepository.save(new User("BBB",25));
		userRepository.save(new User("CCC",24));
		userRepository.save(new User("DDD",32));
		userRepository.save(new User("EEE",234));
		userRepository.save(new User("FFF",56));
		userRepository.save(new User("QQQ",57));
		Assert.assertEquals(7,userRepository.findAll().size());
		Assert.assertEquals(24,userRepository.findByName("CCC").getAge().longValue());
		Assert.assertEquals("QQQ",userRepository.findByNameAndAge("QQQ",57).getName().toString());
		userRepository.delete(userRepository.findByName("FFF"));
		Assert.assertEquals(6,userRepository.findAll().size());
	}

	@Test
	public void testRedis(){
		stringRedisTemplate.opsForValue().set("aaa","121");
		log.info("redis aaa: "+stringRedisTemplate.opsForValue().get("aaa"));
		Assert.assertEquals("121",stringRedisTemplate.opsForValue().get("aaa"));
	}
	@Test
	public void testRedisUser(){
		RedisUser redisUser=new RedisUser("alibaba",123);

		redisTemplate.opsForValue().set("usr1",redisUser);
		Assert.assertEquals(123,redisTemplate.opsForValue().get("usr1").getNum().intValue());
		log.info("redisUser: "+redisTemplate.opsForValue().get("usr1").getUsername());
	}

	@Test
	public void testMongoUser(){
		mUserRepository.save(new MUser(1l,"asd",23));
		mUserRepository.save(new MUser(2l,"ws",23));
		mUserRepository.save(new MUser(3l,"sdfdf",23));
		MUser mUser=mUserRepository.findByUsername("ws");
		System.out.printf("muser: "+mUser.getUsername());
		log.info("mongodb user count: "+mUserRepository.findAll().size());
		Assert.assertEquals(3,mUserRepository.findAll().size());
		mUserRepository.delete(1l);
		Assert.assertEquals(2,mUserRepository.findAll().size());
		log.info("mongodb user count1: "+mUserRepository.findAll().size());

	}

}
