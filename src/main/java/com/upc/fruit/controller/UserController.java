package com.upc.fruit.controller;

import com.upc.fruit.entity.Admin;
import com.upc.fruit.entity.Failure;
import com.upc.fruit.entity.User;
import com.upc.fruit.mapper.UserMapper;
import com.upc.fruit.security.UserLoginToken;
import com.upc.fruit.uti.PageQuery;
import com.upc.fruit.uti.QuaryReturn;
import com.upc.fruit.uti.ReturnUti;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    String phoneCode;
    Logger logger= LoggerFactory.getLogger(getClass());
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
    @Autowired
    UserMapper userMapper;
    //获取验证码
    @RequestMapping(value = "/t-getAuthCode", method = RequestMethod.POST)
    public Object getPhoneCode(@RequestBody User user)
    {
        logger.info(user.getPhoneNumber());
        phoneCode=String.valueOf((int)((Math.random()*9+1)*1000));
        logger.info("验证码是："+phoneCode);
        return phoneCode;
    }
    @RequestMapping(value = "/t-usersByPhoneNumber",method = RequestMethod.POST)
    public Object getUserByPhone( @RequestBody User user)
    {
        if(user!=null) {
            logger.info(user.getPhoneNumber());
            User user1=userMapper.getUserByPhoneNumber(user.getPhoneNumber());
            return user1;
        }
        else return "error";
    }
    //用户注册
    @RequestMapping(value = "/t-users",method = RequestMethod.POST)
    public Object getUser(@RequestBody User user)
    {
        logger.info(user.getPhoneNumber());
        logger.info(user.getPassword());
        userMapper.insertUser(user);
        return user;
    }
    //用户登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object getLogin(@RequestBody User user)
    {
        if(user!=null)
        {
            logger.info(user.getPhoneNumber()+"-----"+user.getPassword());
            User user1=userMapper.getUserByPhoneNumber(user.getPhoneNumber());
            return user1;
        }
        else return "error";
    }
    //用户信息修改
    @RequestMapping(value = "/t-usersUpdate",method = RequestMethod.POST)
    public Object updateUser(@RequestBody User user)
    {
      if(user!=null)
      {
          logger.info(user.getSex()+" ");
        int result= userMapper.updateUser(user);
        logger.info(result+"结果");
          return userMapper.getUserById(user.getId());
      }
      else return "error";
    }
    @UserLoginToken
    @PostMapping("/getUsers")
    public Object getUsers(@RequestBody PageQuery pageQuery)
    {
        if(pageQuery!=null)
        {
            logger.info(pageQuery.toString());
            QuaryReturn quaryReturn=new QuaryReturn();
            quaryReturn.setPagenum(pageQuery.getPagenum());
            List<User> userList=new ArrayList<>();
            if(pageQuery.getQuery().equals(""))
            {
                userList=userMapper.getAllUsers();
            }
            else userList=userMapper.getUser(pageQuery.getQuery());
            logger.info(userList.toString());
            quaryReturn.setTotal(userList.size());
            List<User> userList1=new ArrayList<>();
            if(userList.size()>0) {
                userList1 = ReturnUti.getUserList(pageQuery.getPagenum(), pageQuery.getPagesize(), userList);
            }
            quaryReturn.setList(userList1);
            quaryReturn.setStatus(200);
            quaryReturn.setMsg("获取用户列表成功！");
            logger.info(quaryReturn.toString());
            return quaryReturn;
        }
        else return new Failure("获取用户列表失败！",400);
    }

}
