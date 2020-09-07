package com.upc.fruit.controller;

import com.upc.fruit.entity.*;
import com.upc.fruit.mapper.AdminMapper;
import com.upc.fruit.mapper.ClassifyMapper;
import com.upc.fruit.mapper.GoodsDetailMapper;
import com.upc.fruit.security.UserLoginToken;
import com.upc.fruit.uti.PageQuery;
import com.upc.fruit.uti.QuaryReturn;
import com.upc.fruit.uti.ReturnUti;
import org.apache.ibatis.annotations.Delete;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ClassifyController {
    Logger logger= LoggerFactory.getLogger(getClass());
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    @Autowired
    ClassifyMapper classifyMapper;
    @Autowired
    GoodsDetailMapper goodsDetailMapper;
    @UserLoginToken
    @PostMapping("/getCateList")
    public Object getCateList(@RequestBody PageQuery pageQuery)
    {
       if(pageQuery!=null)
       {
           logger.info(pageQuery.toString());
           List<Classify> classifyList=classifyMapper.getAllCates();
           QuaryReturn quaryReturn=new QuaryReturn();
           quaryReturn.setPagenum(pageQuery.getPagenum());
           quaryReturn.setTotal(classifyList.size());
           List<Classify> classifyList1=new ArrayList<>();
           if(classifyList.size()>0) {
              classifyList1 = ReturnUti.getClassifyList(pageQuery.getPagenum(), pageQuery.getPagesize(), classifyList);
               quaryReturn.setList(classifyList1);
               quaryReturn.setMsg("获取商品分类列表成功！");
               quaryReturn.setStatus(200);
           }
         else {
             quaryReturn.setList(classifyList1);
             quaryReturn.setStatus(201);
             quaryReturn.setMsg("商品分类为空！");
           }
         logger.info(quaryReturn.toString());
           return quaryReturn;
       }
       else return new Failure("获取商品分类列表失败！",400);
    }
    @UserLoginToken
    @DeleteMapping("/deleteCate")
    public Object deleteCate(@RequestParam("id") Integer id)
    {
        int result=0;
        int result1=0;
        if(id!=null)
        {
            logger.info(id + " ");
            result=classifyMapper.deletegoodsByClassify(id);
            if(result==1) {
               result1=classifyMapper.deleteCate(id);
            }
            else {
                List<Integer> list=goodsDetailMapper.getGoodsIdByClassify(id);
                for(Integer goodsid:list)
                {
                    GoodsController.deleteGood(goodsDetailMapper,goodsid);
                }
                 result1 = classifyMapper.deleteCate(id);
            }
            if(result1 == 1)
                return new Success("删除成功！", 200);
            else
                return new Failure("删除失败！", 201);
        }
        return new Failure("删除失败！", 400);
    }
    @UserLoginToken
    @PostMapping("/addCate")
    public Object addCate(@RequestBody Classify classify)
    {
        if(classify!=null)
        {
            logger.info(classify.toString());
            classify.setCreatedTime(df.format(new Date()));
            classify.setUpdateTime(df.format(new Date()));
            classifyMapper.createCate(classify);
            return new Success("添加成功！", 200);
        }
        else
            return new Failure("添加失败！", 400);
    }
    @UserLoginToken
    @PostMapping("/updateCate")
    public Object updateCate(@RequestBody Classify classify)
    {
        int result=0;
        if(classify!=null)
        {
            logger.info(classify.toString());
            classify.setUpdateTime(df.format(new Date()));
             result=classifyMapper.updateClassifyName(classify);
             if(result==1)
                 return new Success("修改成功！", 200);
             else
                 return new Failure("添加失败！", 201);
        }
        else
            return new Failure("添加失败！", 400);

    }

}
