package com.upc.fruit.mapper;

import com.upc.fruit.entity.Admin;
import com.upc.fruit.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where phoneNumber=#{phoneNumber}")
    public User getUserByPhoneNumber(String phoneNumber);
    @Insert("insert into user(phoneNumber,password) values(#{phoneNumber},#{password})")
    public int insertUser(User user);
    @Update("update user set sex = #{sex} ,email = #{email},nickName = #{nickName},birthday = #{birthday},headImage = #{headImage} where id = #{id}")
    public int updateUser(User user);
    @Select("select * from user where id=#{id}")
    public User getUserById(Integer id);
    @Select("select id,nickName,sex,phoneNumber,email,birthday from user")
    public List<User> getAllUsers();
    @Select("select id,nickName,sex,phoneNumber,email,birthday from user where nickName like '%${nickName}%' ")
    public List<User> getUser(String nickName);
}
