package com.upc.fruit.mapper;

import com.upc.fruit.entity.Admin;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
   @Select("select *from admin where id = #{id}")
    public Admin findAdminById(Integer id);
   @Select("select *from admin where userName = #{userName}")
    public Admin findAdminByName(String name);
   @Select("select *from admin where userName like '%${userName}%' ")
    public List<Admin> getAdmin(String userName);
   @Select("select *from admin ")
   public List<Admin> getAllAdmin();
   @Update("update admin set state = #{state} where id = #{id}")
    public int updateState(boolean state,Integer id);
   @Insert("insert into admin (userName,password,email,createdTime,phoneNumber,state) values (#{userName},#{password},#{email},#{createdTime},#{phoneNumber},#{state})")
    public int addAdmin(Admin admin);
   @Delete("delete from admin where id = #{id}")
    public int deleteAdmin(Integer id);
   @Update("update admin set phoneNumber = #{phoneNumber},email = #{email} where id = #{id}")
    public int updateAdmin(Admin admin);
}
