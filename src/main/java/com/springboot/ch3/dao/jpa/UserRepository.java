//package com.springboot.ch3.dao.jpa;
//
//import com.springboot.ch3.entity.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
///**
// * @author zhouhy
// * @create 2017 -03-17 下午2:11
// */
//
//public interface UserRepository extends JpaRepository<User,Long> {
//    User findByName(String name);
//
//    User findByNameAndAge(String name,Integer age);
//
////    @Query("from USER u where u.name=:name")
////    User findUser(@Param("name") String name);
//
//}
