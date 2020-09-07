package com.upc.fruit.controller;

import com.upc.fruit.entity.*;
import com.upc.fruit.mapper.CommentsMapper;
import com.upc.fruit.mapper.GoodsDetailMapper;
import com.upc.fruit.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller

public class RecommendController {
    Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    GoodsDetailMapper goodsDetailMapper;
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    UserMapper userMapper;
    @RequestMapping(value = "/t-home-recommends-0",method = RequestMethod.GET)
    @ResponseBody
    public List<RecommendGoods> getRecommendGoods()
    {
        List<GoodsDetail> goodsDetails=goodsDetailMapper.getAllGoodsDetail();
        List<RecommendGoods> recommendGoodsLis=new ArrayList<>();
        for(GoodsDetail goods:goodsDetails)
        {
               recommendGoodsLis.add(new RecommendGoods(goods));
        }
        return recommendGoodsLis;
    }
    @RequestMapping(value = "/t-goodsLike",method = RequestMethod.POST)
    @ResponseBody
    public Object getSearchList(@RequestBody Map<String,String> map)
    {
        if(map!=null)
        {
            logger.info(map.toString());
            String name="%"+map.get("goodsName")+"%";
            List<GoodsDetail> goodsDetailList=goodsDetailMapper.getSearchGoodsList(name);
            logger.info(goodsDetailList.toString());
            return goodsDetailList;
        }
        else return "error";
    }
    @PostMapping("/getClassifyGoods")
    @ResponseBody
    public Object getclassifyGoods(@RequestBody Map<String,Integer> map)
    {
        if (map!=null)
        {
            logger.info(map.toString());
            Integer classifyId =map.get("classifyId");
            List<GoodsDetail> goodsDetails=goodsDetailMapper.getGoodsListByClassifyId(classifyId);
            return goodsDetails;
        }
        else return "error";
    }

}
