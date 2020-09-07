package com.upc.fruit.mapper;

import com.upc.fruit.entity.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersMapper {
    @Insert("insert into orders(orderNumber,orderPay,goodsCount,orderState,reviewState,receiveTime,receivePlace,createTime,updateTime, userId,goodsId,addressId,isDelete) values (#{orderNumber},#{orderPay},#{goodsCount},#{orderState},#{reviewState},#{receiveTime},#{receivePlace},#{createTime},#{updateTime},#{userId},#{goodsId},#{addressId},#{isDelete})")
    public int createOrder(Orders order);
    @Select("select *from orders where userId = #{userId} and isDelete =0")
    public List<Orders> getOrders(Integer userId);
    @Update("update orders set orderState = #{orderState},reviewState = #{reviewState},updateTime = #{updateTime} where id=#{id}")
    public int updateOrders(Orders orders);
    @Select("select *from orders")
    public List<Orders> getAllOrders();
    @Select("select *from orders where userId = #{userId} ")
    public List<Orders> getOrdersByUserId(Integer userId);
    @Update("update orders set isSend = #{state},orderState = 1,updateTime = #{updateTime} where id = #{id}")
    public int updateOrderState(boolean state,String updateTime,Integer id);
    @Select("select *from orders where id = #{id} ")
    public Orders getOrderById(Integer id);
    @Update("update inventory set goodsNumber = goodsNumber - goodsSell,goodsSell = #{goodsSell} where id = #{id}")
    public int updateGoodsInventory (double goodsSell,Integer id);
    @Update("update orders set isDelete = 1 where id = #{id}")
    public int deleteUserOrder(Integer id);
    @Update("update orders set reviewState = #{reviewState} where id = #{id}")
    public int updateOrderReviewState (int reviewState,Integer id);
    @Select("select orderPay from orders  where createTime between #{date1} and #{date2}")
    public List<Double> getIncome(String date1,String date2);

}
