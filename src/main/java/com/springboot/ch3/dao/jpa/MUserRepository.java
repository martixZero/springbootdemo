package com.springboot.ch3.dao.jpa;

import com.springboot.ch3.entity.MUser;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author zhouhy
 * @create 2017 -03-17 下午7:09
 */

public interface MUserRepository extends MongoRepository<MUser,Long> {

    MUser findByUsername(String username);
}
