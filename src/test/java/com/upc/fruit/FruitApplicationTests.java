package com.upc.fruit;

import com.upc.fruit.entity.GoodsDetail;
import com.upc.fruit.mapper.GoodsDetailMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class FruitApplicationTests {
    Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    GoodsDetailMapper goodsDetailMapper;

    @Test
    void contextLoads() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        System.out.println("当前时间"+df.format(new Date()));// new Date()为获取当前系统时间
        List<GoodsDetail> list =goodsDetailMapper.getAllGoodsDetail();
        logger.info(list.toString());
        logger.info(list.get(0).toString());
    }


}
