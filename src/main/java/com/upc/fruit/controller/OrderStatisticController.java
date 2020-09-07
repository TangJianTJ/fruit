package com.upc.fruit.controller;

import com.upc.fruit.entity.Failure;
import com.upc.fruit.entity.Success;
import com.upc.fruit.mapper.GoodsDetailMapper;
import com.upc.fruit.mapper.OrdersMapper;
import com.upc.fruit.security.UserLoginToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class OrderStatisticController {
    Logger logger = LoggerFactory.getLogger(getClass());
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    @Autowired
    OrdersMapper ordersMapper;

    //@UserLoginToken
    @PostMapping("/getDayIncome")
    public Object getDayIncome(@RequestBody Map<String, String> map) {
        if (map != null) {
            logger.info(map.toString());
            String date1 = map.get("value");
            String date2 = date1 + " " + "23:59:59";
            date1 = date1 + " " + "00:00:00";
            logger.info(date1 + "   " + date2);
            List<Double> payList = ordersMapper.getIncome(date1, date2);
            double sum = 0;
            if (payList.size() != 0) {
                for (Double price : payList) {
                    sum = sum + price;
                }
            }
            return new Success(sum + "元", 200);

        }
        return new Failure("获取失败！", 400);
    }

    @PostMapping("/getToDayIncome")
    public Object ToDay(@RequestBody Map<String, String> map) {
        if (map != null) {
            logger.info(map.toString());
            String today = df.format(new Date());
            String endTime = df1.format(new Date());
            endTime = endTime + " " + "23:59:59";
            logger.info(today + "   " + endTime);
            List<Double> payList = ordersMapper.getIncome(today, endTime);
            double sum = 0;
            if (payList.size() != 0) {
                for (Double price : payList) {
                    sum = sum + price;
                }
            }
            return new Success(sum + "元", 200);
        }
        return new Failure("获取失败！", 400);
    }

    @PostMapping("/getMonthIncome")
    public Object getMonthIncome(@RequestBody Map<String, String> map) {
        if (map != null) {
            logger.info(map.toString());
            String date1 = map.get("value1");
            String date2 = date1 +"-30"+" " + "23:59:59";
            date1 = date1 +"-01"+" " + "00:00:00";
            logger.info(date1 + "   " + date2);
            List<Double> payList = ordersMapper.getIncome(date1, date2);
            double sum = 0;
            if (payList.size() != 0) {
                for (Double price : payList) {
                    sum = sum + price;
                }
            }
            return new Success(sum + "元", 200);

        }
        return new Failure("获取失败！", 400);
    }
}
