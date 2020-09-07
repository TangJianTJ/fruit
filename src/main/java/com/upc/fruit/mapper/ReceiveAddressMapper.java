package com.upc.fruit.mapper;

import com.upc.fruit.entity.ReceiveAddress;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReceiveAddressMapper {
    @Select("select *from receiveAddress where userId= #{userId}")
    public List<ReceiveAddress> getReceiveAddressList(Integer userId);
    @Insert("insert into receiveAddress(address,street,receiveUser,receivePhoneNumber,createTime,userId) values(#{address},#{street},#{receiveUser},#{receivePhoneNumber},#{createTime},#{userId})")
    public int saveReceiveAddress(ReceiveAddress receiveAddress);
    @Update("update receiveAddress set address=#{address},street=#{street},receiveUser=#{receiveUser},receivePhoneNumber=#{receivePhoneNumber},updateTime=#{updateTime},userId=#{userId} where id=#{id}")
    public int updateReceiveAddress(ReceiveAddress receiveAddress);
    @Select("select *from receiveAddress where id= #{id}")
    public ReceiveAddress getReceiveAddressById(Integer id);
}

