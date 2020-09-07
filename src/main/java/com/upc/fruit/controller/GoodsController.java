package com.upc.fruit.controller;

import com.upc.fruit.entity.*;
import com.upc.fruit.mapper.AdminMapper;
import com.upc.fruit.mapper.ClassifyMapper;
import com.upc.fruit.mapper.GoodsDetailMapper;
import com.upc.fruit.security.UserLoginToken;
import com.upc.fruit.uti.PageQuery;
import com.upc.fruit.uti.QuaryReturn;
import com.upc.fruit.uti.ReturnUti;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class GoodsController {
    Logger logger= LoggerFactory.getLogger(getClass());
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    @Autowired
    GoodsDetailMapper goodsDetailMapper;
    @Autowired
    ClassifyMapper classifyMapper;
    @UserLoginToken
    @PostMapping("/getGoodsList")
    public Object getGoodsList(@RequestBody PageQuery pageQuery)
    {
        if(pageQuery!=null)
        {
            logger.info(pageQuery.toString());
            QuaryReturn quaryReturn=new QuaryReturn();
            quaryReturn.setPagenum(pageQuery.getPagenum());
            List<GoodsDetail> goodsList=new ArrayList<>();
            if(pageQuery.getQuery().equals(""))
            {
                goodsList=goodsDetailMapper.getAllGoodsDetail();
                logger.info(132+" ");
            }
            else goodsList=goodsDetailMapper.getSearchGoodsList(pageQuery.getQuery());
            logger.info(goodsList.toString());
            quaryReturn.setTotal(goodsList.size());
            List<GoodsDetail> userList1=new ArrayList<>();
            if(goodsList.size()>0) {
                List<Good> goodList=new ArrayList<>();
                userList1 = ReturnUti.getGoodsList(pageQuery.getPagenum(), pageQuery.getPagesize(), goodsList);
                for(GoodsDetail goodsDetail:userList1)
                {
                    Good good=new Good(goodsDetail);
                    good.setGoodsNumber(goodsDetailMapper.getInventory(goodsDetail.getId()).getGoodsNumber());
                    good.setGoodsSell(goodsDetailMapper.getInventory(goodsDetail.getId()).getGoodsSell());
                    good.setGoodsClassify(goodsDetailMapper.getClassifyName(goodsDetail.getGoodsClassify()));
                    goodList.add(good);
                }
                quaryReturn.setList(goodList);
            }
            quaryReturn.setStatus(200);
            quaryReturn.setMsg("获取商品列表成功！");
            logger.info(quaryReturn.toString());
            return quaryReturn;
        }
        else return new Failure("获取商品列表失败！",400);
    }
    @UserLoginToken
    @DeleteMapping("/deleteGood")
    public Object deleteAdmin(@RequestParam("id") Integer id)
    {
        logger.info(id+" ");

       int result= deleteGood(goodsDetailMapper,id);
        if (result == 1) {
            return new Success("删除成功！", 200);
        } else
            return new Failure("删除失败！", 400);
    }
    @UserLoginToken
    @GetMapping("/getClassifyList")
    public Object getCateList()
    {
        List<Classify> classifyList = classifyMapper.getAllCates();
        QuaryReturn quaryReturn=new QuaryReturn();
        quaryReturn.setList(classifyList);
        quaryReturn.setMsg("获取分类列表成功");
        quaryReturn.setStatus(200);
        return quaryReturn;
    }
    @UserLoginToken
    @PostMapping("/upload")
    public Object savePicture(@RequestParam("file") MultipartFile file)
    {
        if(file.isEmpty())
            logger.info("文件为空");
        else {
            logger.info(file.getName() + file.getOriginalFilename());
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            String filePath = "E://picture-up/"; // 上传后的路径
            fileName = UUID.randomUUID() + suffixName; // 新文件名
            File dest = new File(filePath + fileName);
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new Success("http://192.168.43.64:8080/fruit/api/image/"+fileName, 200);
        }
        return new Failure("上传失败！", 400);
    }
    //@UserLoginToken
    @PostMapping("/addGood")
    public Object addGood(@RequestBody Good goodsDetail)
    {
        if(goodsDetail!=null) {
            logger.info(goodsDetail.toString());
            goodsDetail.setGoodsIntroduction(getStringFromHtml(goodsDetail.getGoodsIntroduction()));
            logger.info(getStringFromHtml(goodsDetail.getGoodsIntroduction()));
            goodsDetail.setCreateTime(df.format(new Date()));
            goodsDetail.setEffect(getStringFromHtml(goodsDetail.getEffect()));
            goodsDetail.setNutritionInfo(getStringFromHtml(goodsDetail.getNutritionInfo()));
            GoodsDetail goodsDetail1 = transForGoodsDetail(goodsDetail);
            logger.info(goodsDetail1.toString());
            int result = goodsDetailMapper.addGoodItem(goodsDetail1);
            if (result == 1) {
                Integer id = goodsDetailMapper.getGoodIdByGoodName(goodsDetail1.getGoodsName());
                goodsDetailMapper.addInventory(new Inventory(id, goodsDetail.getGoodsNumber(), 0));
                return new Success("添加成功！", 200);
            }
        }
        return new Failure("添加失败！", 400);
    }
    @UserLoginToken
    @PostMapping("/updateGood")
    public Object updateGoodInfo(@RequestBody Good good)
    {
        if(good!=null)
        {
            logger.info(good.toString());
            good.setUpdateTime(df.format(new Date()));
            GoodsDetail goodsDetail =transForGoodsDetail(good);
            int result= goodsDetailMapper.updateGoodInfo(goodsDetail);
            if(result ==1) {
                goodsDetailMapper.updateInventoryInfo(good.getGoodsNumber(), good.getId());
                return new Success("修改成功！", 200);
            }
        }
        return new Failure("修改失败！", 400);
    }
    public static int deleteGood(GoodsDetailMapper goodsDetailMapper,Integer id)
    {
        int result=0;
        goodsDetailMapper.deleteInventoryByGoodsId(id);
        goodsDetailMapper.deleteShoppingCarBeanByGoodsId(id);
        goodsDetailMapper.deleteCommentByGoodsId(id);
        goodsDetailMapper.deleteOrdersByGoodsId(id);
        result =  goodsDetailMapper.deleteGood(id);
        return result;
    }

    public static String getStringFromHtml(String html)
    {
        //从html中提取纯文本
        String txtcontent = html.replaceAll("</?[^>]+>", ""); //剔出<html>的标签 正则表达式
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符
        return txtcontent;
    }
    public GoodsDetail transForGoodsDetail(Good good)
    {
        GoodsDetail goodsDetail=new GoodsDetail();
        goodsDetail.setGoodsClassify(Integer.parseInt(good.getGoodsClassify()));
        goodsDetail.setGoodsName(good.getGoodsName());
        goodsDetail.setGoodsImage(good.getGoodsImage());
        goodsDetail.setGoodsPrice(String.valueOf(good.getGoodsPrice()));
        goodsDetail.setGoodsIntroduction(good.getGoodsIntroduction());
        goodsDetail.setEffect(good.getEffect());
        goodsDetail.setNutritionInfo(good.getNutritionInfo());
        goodsDetail.setCreateTime(good.getCreateTime());
        goodsDetail.setId(good.getId());
        goodsDetail.setUpdateTime(good.getUpdateTime());
        return goodsDetail;
    }

}
