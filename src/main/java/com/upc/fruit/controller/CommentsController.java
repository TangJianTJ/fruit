package com.upc.fruit.controller;

import com.upc.fruit.entity.Comment;
import com.upc.fruit.entity.CommentBean;
import com.upc.fruit.entity.GoodsDetail;
import com.upc.fruit.entity.User;
import com.upc.fruit.mapper.CommentsMapper;
import com.upc.fruit.mapper.OrdersMapper;
import com.upc.fruit.mapper.UserCommentsMapper;
import com.upc.fruit.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class CommentsController {
    Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    OrdersMapper ordersMapper;
    @PostMapping("/t_getGoodsComments")
    @ResponseBody
    public Object getGoodsComments(@RequestBody Map<String,Integer> map)
    {
        if(map!=null)
        {
            logger.info(map.toString());
            List<Comment> commentList = commentsMapper.getComments(map.get("goodsId"));
            List<CommentBean> commentBeanList = new ArrayList<>();
            for(Comment comment:commentList)
            {
                CommentBean commentBean=new CommentBean(comment);
                User user=userMapper.getUserById(comment.getUserId());
                commentBean.setHeadImage(user.getHeadImage());
                commentBean.setNickName(user.getNickName());
                commentBeanList.add(commentBean);
            }
            return commentBeanList;
        }
        else
            return "error";
    }
    @PostMapping("/t_addGoodsComment")
    @ResponseBody
    public Object addGoodsComment(@RequestBody Comment comment)
    {
        if(comment!=null)
        {
            logger.info(comment.toString());
            int result =commentsMapper.addComment(comment);
            if(result ==1) {
                ordersMapper.updateOrderReviewState(0,comment.getOrderId());
                return "success";
            }
            else return "error";
        }
        return "error";
    }

}
