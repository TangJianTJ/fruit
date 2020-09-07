package com.upc.fruit.mapper;

import com.upc.fruit.entity.Classify;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ClassifyMapper {
    @Insert("insert into classify (classifyName,createdTime,updateTime) values (#{classifyName},#{createdTime},#{updateTime})")
    public int createCate(Classify classify);
    @Select("select * from classify ")
    public List<Classify> getAllCates();
    @Delete("delete from classify where id =#{id}")
    public int deleteCate(Integer id);
    @Delete("delete from goodsDetail where goodsClassify = #{id}")
    public int deletegoodsByClassify(Integer id);
    @Update("update classify set classifyName = #{classifyName},updateTime = #{updateTime} where id = #{id}")
    public int updateClassifyName (Classify classify);
}
