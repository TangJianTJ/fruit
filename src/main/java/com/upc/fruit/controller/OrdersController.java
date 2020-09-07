package com.upc.fruit.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.upc.fruit.entity.*;
import com.upc.fruit.mapper.GoodsDetailMapper;
import com.upc.fruit.mapper.OrdersMapper;
import com.upc.fruit.mapper.ReceiveAddressMapper;
import com.upc.fruit.mapper.UserMapper;
import com.upc.fruit.security.UserLoginToken;
import com.upc.fruit.uti.PageQuery;
import com.upc.fruit.uti.QuaryReturn;
import com.upc.fruit.uti.ReturnUti;
import netscape.javascript.JSObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class OrdersController {
    Logger logger= LoggerFactory.getLogger(getClass());
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    GoodsDetailMapper goodsDetailMapper;
    @Autowired
    ReceiveAddressMapper receiveAddressMapper;
    @Autowired
    UserMapper userMapper;
    @RequestMapping(value = "/t-ordersCreate",method = RequestMethod.POST)
    public Object createOrders(@RequestBody Map<String,List<Orders>> map)
    {
        if(map!=null)
        {
            logger.info("订单详细："+map.toString());
            List<Orders> orderList=map.get("tOrderDTO");
            for(Orders object:orderList)
            {
              logger.info("商品ID："+object.getGoodsId());
              object.setCreateTime(df.format(new Date()));
              object.setOrderState(2);
              object.setReviewState(1);
              object.setIsDelete(0);
              object.setIsSend(false);
              ordersMapper.updateGoodsInventory(object.getGoodsCount(),object.getGoodsId());
              object.setOrderNumber(String.valueOf(System.currentTimeMillis()));
              logger.info("订单号："+object.getOrderNumber());
               ordersMapper.createOrder(object);
            }
            return "success";
        }
        else return "error";
    }
    @RequestMapping(value = "/t-getOrders",method = RequestMethod.POST)
    public Object getOrders(@RequestBody Map<String,Integer> map)
    {
        if(map!=null)
        {
            logger.info(map.toString());
            Integer userId=map.get("userId");
            List<Orders> ordersList=ordersMapper.getOrders(userId);
            List<OrderBean> orderBeanList=getOrdersByUserId(userId);
            Map<String,List<OrderBean>> map1=new HashMap<>();
            map1.put("orders",orderBeanList);
            return map1;
        }
        else return "error";
    }
    @RequestMapping(value = "/t_getUserInfoAll",method = RequestMethod.POST)
    public Object getUserInfoAll(@RequestBody Map<String,Integer> map)
    {
        if(map!=null)
        {
            logger.info(map.toString());
            User user=userMapper.getUserById(map.get("userId"));
            UserInfoAll userInfoAll=new UserInfoAll(user.getNickName(),user.getHeadImage(),getOrdersByUserId(map.get("userId")));
            return userInfoAll;
        }
        else return "error";
    }
    @PostMapping("/t-ordersDelete")
    public Object deleteOrdersById(@RequestBody Map<String,Integer> map)
    {
        if(map!=null)
        {
            logger.info(map.toString());
            int result = ordersMapper.deleteUserOrder(map.get("id"));
            if(result == 1)
                return "success";
            else return "error";
        }
        else return "error";
    }
    @RequestMapping(value = "/t-ordersUpdate",method = RequestMethod.POST)
    public Object updateOrder(@RequestBody Orders orders)
    {
        if(orders!=null)
        {
            logger.info(orders.toString());
            orders.setUpdateTime(df.format(new Date()));
            int result=ordersMapper.updateOrders(orders);
            return result;
        }
        else return "error";
    }
    @UserLoginToken
    @PostMapping("/getOrderList")
    public Object getAllOrders(@RequestBody PageQuery pageQuery)
    {
        if(pageQuery!=null)
        {
            logger.info(pageQuery.toString());
            QuaryReturn quaryReturn=new QuaryReturn();
            quaryReturn.setPagenum(pageQuery.getPagenum());
            List<Orders> ordersList=new ArrayList<>();
            if(pageQuery.getQuery().equals(""))
            {
               ordersList=ordersMapper.getAllOrders();
            }
            else {
               List<User> userList = userMapper.getUser(pageQuery.getQuery());
               for(User user:userList)
               {
                   List<Orders> ordersList1=ordersMapper.getOrdersByUserId(user.getId());
                   ordersList.addAll(ordersList1);
               }
            }
            logger.info(ordersList.toString());
            quaryReturn.setTotal(ordersList.size());
            List<Orders> ordersList2 = new ArrayList<>();
            if(ordersList.size()>0)
            {
                ordersList2 = ReturnUti.getOrderList(pageQuery.getPagenum(), pageQuery.getPagesize(), ordersList);
                List<OrderInfo> orderInfoList=new ArrayList<>();
                for(Orders orders:ordersList2)
                {
                    OrderBean orderBean=new OrderBean(orders);
                    orderBean.setGoods(goodsDetailMapper.getGoodsDetail(orders.getGoodsId()));
                    orderBean.setReceiveAddress(new ReceiveAddressBean(receiveAddressMapper.getReceiveAddressById(orders.getAddressId())));
                    orderBean.setUser(userMapper.getUserById(orders.getUserId()));
                    OrderInfo orderInfo=new OrderInfo(orderBean);
                    orderInfoList.add(orderInfo);
                }
                quaryReturn.setList(orderInfoList);
            }
            quaryReturn.setStatus(200);
            quaryReturn.setMsg("获取订单列表成功！");
            logger.info(quaryReturn.toString());
            return quaryReturn;
        }
        return new Failure("获取商品列表失败！",400);
    }
    @UserLoginToken
    @PutMapping("/orderStateChange")
    public Object saveOrderState(@RequestParam ("id") Integer id,@RequestParam("send") Boolean state)
    {
        logger.info(id+" ");
        int result=  ordersMapper.updateOrderState(state,df.format(new Date()),id);
        if(result ==1) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", ordersMapper.getOrderById(id));
            jsonObject.put("msg", "修改成功");
            jsonObject.put("status", 200);
            return jsonObject;
        }
        return new Failure("修改失败！",400);
    }

    public List<OrderBean> getOrdersByUserId(Integer userId)
    {
        List<Orders> ordersList=ordersMapper.getOrders(userId);
        List<OrderBean> orderBeanList=new ArrayList<>();
        for(Orders orders:ordersList)
        {
            OrderBean orderBean=new OrderBean(orders);
            orderBean.setGoods(goodsDetailMapper.getGoodsDetail(orders.getGoodsId()));
            orderBean.setReceiveAddress(new ReceiveAddressBean(receiveAddressMapper.getReceiveAddressById(orders.getAddressId())));
            orderBean.setUser(userMapper.getUserById(orders.getUserId()));
            orderBeanList.add(orderBean);
        }
        return orderBeanList;
    }

}
