package com.upc.fruit.mapper;

import com.upc.fruit.entity.Admin;
import com.upc.fruit.entity.GoodsDetail;
import com.upc.fruit.entity.Inventory;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsDetailMapper {
    @Select("select *from goodsDetail ")
    public List<GoodsDetail> getAllGoodsDetail();
    @Select("select *from goodsDetail where id= #{goodsId}")
    public GoodsDetail getGoodsDetail(Integer goodsId);
    @Select("select *from goodsDetail where goodsName like '%${goodsName}%'")
    public List<GoodsDetail> getSearchGoodsList(String goodsName);
    @Select("select * from inventory where id= #{id}")
    public Inventory getInventory(Integer id);
    @Select("select classifyName from classify where id = #{id}")
    public String getClassifyName(Integer id);
    @Delete("delete from goodsDetail where id =#{id}")
    public int deleteGood(Integer id);
    @Delete("delete from orders where goodsId =#{id}")
    public int deleteOrdersByGoodsId(Integer id);
    @Delete("delete from inventory where id= #{id}")
    public int deleteInventoryByGoodsId(Integer id);
    @Delete("delete from comment where goodsId = #{id}")
    public int deleteCommentByGoodsId(Integer id);
    @Delete("delete from shoppingCarBean where goodsId = #{id}")
    public int deleteShoppingCarBeanByGoodsId(Integer id);
    @Select("select id from goodsDetail where goodsClassify = #{id}")
    public List<Integer> getGoodsIdByClassify(Integer id);
    @Insert("insert into goodsDetail(goodsName,goodsPrice,goodsImage,goodsClassify,goodsIntroduction,effect,createTime,updateTime,nutritionInfo) values (#{goodsName},#{goodsPrice}," +
            "#{goodsImage},#{goodsClassify},#{goodsIntroduction},#{effect},#{createTime},#{updateTime},#{nutritionInfo})")
    public int addGoodItem(GoodsDetail goodsDetail);
    @Insert("insert into inventory(id,goodsNumber,goodsSell) values (#{id},#{goodsNumber},#{goodsSell})")
    public int addInventory (Inventory inventory);
    @Select("select id from goodsDetail where goodsName = #{goodsName}")
    public Integer getGoodIdByGoodName(String goodsName);
    @Update("update goodsDetail set goodsName = #{goodsName},goodsPrice = #{goodsPrice},goodsClassify = #{goodsClassify},updateTime = #{updateTime} where id = #{id}")
    public int updateGoodInfo(GoodsDetail goodsDetail);
    @Update("update inventory set goodsNumber =#{goodsNumber} where id =#{id}")
    public int updateInventoryInfo(double goodsNumber,Integer id);
    @Select("select *from goodsDetail where goodsClassify = #{id}")
    public List<GoodsDetail> getGoodsListByClassifyId(Integer id);
}
