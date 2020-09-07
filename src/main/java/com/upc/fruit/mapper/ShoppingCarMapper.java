package com.upc.fruit.mapper;

import com.upc.fruit.entity.ShoppingCar;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShoppingCarMapper {
    @Select("select * from shoppingCarBean where userId = #{userId}")
    public List<ShoppingCar> getShoppingCarList(Integer userId);
    @Insert("insert into shoppingCarBean(userId,goodsId,goodsCount) values (#{userId},#{goodsId},#{goodsCount})")
    public int insertIntoShoppingCar(Integer userId,Integer goodsId,int goodsCount);
    @Delete("delete from shoppingCarBean where id= #{id}")
    public int deleteShoppingCar(Integer id);

}
