package com.upc.fruit.controller;

import com.upc.fruit.entity.ReceiveAddress;
import com.upc.fruit.entity.ReceiveAddressBean;
import com.upc.fruit.mapper.ReceiveAddressMapper;
import org.apache.ibatis.annotations.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class ReceiveAddressController {
    Logger logger= LoggerFactory.getLogger(getClass());
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    @Autowired
    ReceiveAddressMapper receiveAddressMapper;
    @RequestMapping(value = "/t-createReceive-addresses",method = RequestMethod.POST)
    @ResponseBody
    public Object createReceiveAddress(@RequestBody ReceiveAddress receiveAddress)
    {
        if(receiveAddress!=null)
        {
            logger.info(receiveAddress.toString());
            logger.info(receiveAddress.getAddress());
            //SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            System.out.println("当前时间"+df.format(new Date()));// new Date()为获取当前系统时间
            receiveAddress.setCreateTime(df.format(new Date()));
           Integer result= receiveAddressMapper.saveReceiveAddress(receiveAddress);
           return result;
        }
        else
            return "error";
    }
    @RequestMapping(value = "/t-updateReceive-addresses",method = RequestMethod.POST)
    public Object updateReceiveAddress(@RequestBody ReceiveAddress receiveAddress)
    {
        if(receiveAddress!=null)
        {
            logger.info(receiveAddress.toString());
            receiveAddress.setCreateTime(df.format(new Date()));
           Integer result= receiveAddressMapper.updateReceiveAddress(receiveAddress);
           return  result;
        }
        else
            return "error";
    }
    @RequestMapping(value = "/t-getReceive-addresses",method = RequestMethod.POST)
    public Object getReceiveAddressList(@RequestBody Map<String,Integer> map)
    {
       if(map!=null)
       {
           logger.info("用户Id："+map.toString());
           List<ReceiveAddress> receiveAddressList=receiveAddressMapper.getReceiveAddressList(map.get("userId"));
           List<ReceiveAddressBean> receiveAddressBeanList=new ArrayList<>();
           for(ReceiveAddress receiveAddress:receiveAddressList)
           {
               receiveAddressBeanList.add(new ReceiveAddressBean(receiveAddress));
           }
           return receiveAddressBeanList;
       }
       else return "error";
    }
}
