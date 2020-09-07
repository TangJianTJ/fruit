package com.upc.fruit.mapper;

import com.upc.fruit.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentsMapper {
    @Select("select *from comment where goodsId = #{goodsId}")
    public List<Comment> getComments(Integer goodsId);
    @Insert("insert into comment(userId,grade,goodsId,orderId,commentDescribe) values (#{userId},#{grade},#{goodsId},#{orderId},#{commentDescribe})")
    public int addComment(Comment comment);
}
