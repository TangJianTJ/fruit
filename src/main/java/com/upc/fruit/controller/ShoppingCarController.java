package com.upc.fruit.controller;

import com.upc.fruit.entity.GoodsDetail;
import com.upc.fruit.entity.ShoppingCar;
import com.upc.fruit.entity.ShoppingCarBean;
import com.upc.fruit.mapper.GoodsDetailMapper;
import com.upc.fruit.mapper.ShoppingCarMapper;
import com.upc.fruit.mapper.UserCommentsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ShoppingCarController {
    Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    GoodsDetailMapper goodsDetailMapper;
    @Autowired
    ShoppingCarMapper shoppingCarMapper;
    @RequestMapping(value = "/t-post_ShoppingCar",method = RequestMethod.POST)
    public Object postShoppingCar(@RequestBody Map<String,Integer> map )
    {
        logger.info(map.toString());
      int result=  shoppingCarMapper.insertIntoShoppingCar(map.get("userId"),map.get("goodsId"),map.get("goodsCount"));
      return result;
    }
    @RequestMapping(value = "/t-shopping-cars",method = RequestMethod.POST)
    public Object getShoppingCar(@RequestBody Map<String,Integer> map)
    {
        if(map!=null)
        {
            logger.info(map.toString());
            List<ShoppingCar> shoppingCarList = shoppingCarMapper.getShoppingCarList(map.get("userId"));
            logger.info(shoppingCarList.toString());
            List<ShoppingCarBean> shoppingCarBeanList = new ArrayList<>();
            for (ShoppingCar shoppingCar : shoppingCarList)
            {
                GoodsDetail goodsDetail = goodsDetailMapper.getGoodsDetail(shoppingCar.getGoodsId());
                ShoppingCarBean shoppingCarBean = new ShoppingCarBean(shoppingCar, goodsDetail);
                shoppingCarBeanList.add(shoppingCarBean);
            }
            Map<String,List<ShoppingCarBean>> map1=new HashMap<>();
            map1.put("shoppingCars",shoppingCarBeanList);
            return map1;
        }
        else return "error";
    }
    @RequestMapping(value = "/t-goodsBatchDelete",method = RequestMethod.POST)
    public Object updateShoppingCar(@RequestBody Map<String,List<Integer>> map)
    {
        if(map!=null)
        {
            logger.info(map.toString());
            List<Integer> shoppingCarList=map.get("number");
            for(Integer integer:shoppingCarList)
            {
              int result=  shoppingCarMapper.deleteShoppingCar(integer);
              logger.info("deleteShoppingCar resule："+result);
            }
            return "success";
        }
        else return "error";
    }

}
