package com.upc.fruit.mapper;

import com.upc.fruit.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserCommentsMapper {
    @Select("select * from userComment where goodsId = #{id}")
    public List<Comment> getComments(Integer id);
    @Insert("insert into comment(userId,grade,goodsId,commentDescribe) values(#{userId},#{grade},#{goodsId},#{commentDescribe})")
    public int insertComment(Comment comment);

}
