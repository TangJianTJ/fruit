package com.upc.fruit.controller;

import com.alibaba.fastjson.JSONObject;
import com.upc.fruit.entity.Admin;
import com.upc.fruit.entity.Failure;
import com.upc.fruit.entity.Success;
import com.upc.fruit.mapper.AdminMapper;
import com.upc.fruit.security.TokenService;
import com.upc.fruit.security.UserLoginToken;
import com.upc.fruit.uti.PageQuery;
import com.upc.fruit.uti.QuaryReturn;
import com.upc.fruit.uti.ReturnUti;
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
public class AdminController {
    Logger logger= LoggerFactory.getLogger(getClass());
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    TokenService tokenService;
    @RequestMapping(value = "/adLogin",method = RequestMethod.POST)
    public Object getAdminLogin(@RequestBody Map<String,Object> map)
    {
        JSONObject jsonObject = new JSONObject();
        logger.info(map.toString());
        Admin admin=adminMapper.findAdminByName(map.get("userName").toString());
        if(admin==null)
        {
            jsonObject.put("msg","登录失败，用户不存在");
            return jsonObject;
        }
        else {
            if(admin.getPassword().equals(map.get("password")))
            {
                String token = tokenService.getToken(admin);
                jsonObject.put("token",token);
                jsonObject.put("user",admin);
                jsonObject.put("status",200);
                jsonObject.put("msg","success");
                return jsonObject;
            }
            else {
                jsonObject.put("msg","登录失败，密码错误");
                return jsonObject;
            }
        }
    }
    @UserLoginToken
    @GetMapping("/getMessage")
    public String getMessage()
    {
        return "验证成功";
    }

    @UserLoginToken
    @RequestMapping(value = "/admins",method = RequestMethod.POST)
    public Object getUserList(@RequestBody PageQuery pageQuery)
    {
      logger.info(pageQuery.toString());
        QuaryReturn quaryReturn=new QuaryReturn();
        quaryReturn.setPagenum(pageQuery.getPagenum());
        List<Admin> adminList=new ArrayList<>();
        if(pageQuery.getQuery().equals(""))
        {
            adminList=adminMapper.getAllAdmin();
        }
        else
            adminList=adminMapper.getAdmin(pageQuery.getQuery());
        quaryReturn.setTotal(adminList.size());
        List<Admin> adminList1=new ArrayList<>();
        if(adminList.size()>0) {
          adminList1 = ReturnUti.getAdminList(pageQuery.getPagenum(), pageQuery.getPagesize(), adminList);

        }
        quaryReturn.setList(adminList1);
        quaryReturn.setStatus(200);
        quaryReturn.setMsg("获取管理员用户成功！");
        logger.info(quaryReturn.toString());
        return quaryReturn;
    }
    @UserLoginToken
    @PutMapping("/userStateChange")
    public Object saveUserState(@RequestParam ("id") Integer id,@RequestParam("state") Boolean state)
    {
        logger.info(id+" ");
        int result=  adminMapper.updateState(state,id);
        if(result ==1) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("data", adminMapper.findAdminById(id));
            jsonObject.put("msg", "修改成功");
            jsonObject.put("status", 200);
            return jsonObject;
        }
        return new Failure("修改失败！",400);
    }
    @UserLoginToken
    @PostMapping("/addAdmin")
    public Object saveAdmin(@RequestBody Admin admin) {

        if (admin != null) {
            logger.info(admin.toString());
            admin.setCreatedTime(df.format(new Date()));
            admin.setState(true);
            int result = adminMapper.addAdmin(admin);
            if (result == 1) {
              return new Success("添加成功！", 200);
            } else
                return new Failure("用户名已存在！", 201);
        }
        return new Failure("添加失败！",400);
    }
    @UserLoginToken
    @DeleteMapping("/deleteAdmin")
    public Object deleteAdmin(@RequestParam ("id") Integer id)
    {
        logger.info(id+" ");
        int result=adminMapper.deleteAdmin(id);
        if (result == 1) {
            return new Success("删除成功！", 200);
        } else
            return new Failure("删除失败！", 400);
    }
    @UserLoginToken
    @PostMapping("/updateAdmin")
    public Object updateAdmin(@RequestBody Admin admin)
    {
        if (admin!=null)
        {
            logger.info(admin.toString());
            int result=adminMapper.updateAdmin(admin);
            if (result == 1) {
                return new Success("修改成功！", 200);
            } else
                return new Failure("修改失败！", 201);
        }
        return new Failure("修改失败！", 400);
    }


}
