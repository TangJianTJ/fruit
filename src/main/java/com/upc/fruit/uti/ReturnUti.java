package com.upc.fruit.uti;

import com.upc.fruit.entity.*;

import java.util.ArrayList;
import java.util.List;

public class ReturnUti {
    public static List<Admin> getAdminList(int pagenum, int pagesize, List<Admin> list)
    {
        List<Admin> objectList=new ArrayList<>();
        if(list.size()>=pagesize*pagenum)
        {
            for (int i = 0; i < pagesize; i++) {
                objectList.add(list.get(pagesize * (pagenum - 1) + i));
            }
        }
        else {
            for (int j = pagesize *(pagenum-1); j < list.size(); j++) {
                objectList.add(list.get(j));
            }
        }
        return objectList;
    }
    public static List<User> getUserList(int pagenum, int pagesize, List<User> list)
    {
        List<User> objectList=new ArrayList<>();
        if(list.size()>=pagesize*pagenum)
        {
            for (int i = 0; i < pagesize; i++) {
                objectList.add(list.get(pagesize * (pagenum - 1) + i));
            }
        }
        else {
            for (int j = pagesize *(pagenum-1); j < list.size(); j++) {
                objectList.add(list.get(j));
            }
        }
        return objectList;
    }
    public static List<GoodsDetail> getGoodsList(int pagenum, int pagesize, List<GoodsDetail> list)
    {
        List<GoodsDetail> objectList=new ArrayList<>();
        if(list.size()>=pagesize*pagenum)
        {
            for (int i = 0; i < pagesize; i++) {
                objectList.add(list.get(pagesize * (pagenum - 1) + i));
            }
        }
        else {
            for (int j = pagesize *(pagenum-1); j < list.size(); j++) {
                objectList.add(list.get(j));
            }
        }
        return objectList;
    }

    public static List<Classify> getClassifyList(int pagenum, int pagesize, List<Classify> list)
    {
        List<Classify> objectList=new ArrayList<>();
        if(list.size()>=pagesize*pagenum)
        {
            for (int i = 0; i < pagesize; i++) {
                objectList.add(list.get(pagesize * (pagenum - 1) + i));
            }
        }
        else {
            for (int j = pagesize *(pagenum-1); j < list.size(); j++) {
                objectList.add(list.get(j));
            }
        }
        return objectList;
    }

    public static List<Orders> getOrderList(int pagenum, int pagesize, List<Orders> list)
    {
        List<Orders> objectList=new ArrayList<>();
        if(list.size()>=pagesize*pagenum)
        {
            for (int i = 0; i < pagesize; i++) {
                objectList.add(list.get(pagesize * (pagenum - 1) + i));
            }
        }
        else {
            for (int j = pagesize *(pagenum-1); j < list.size(); j++) {
                objectList.add(list.get(j));
            }
        }
        return objectList;
    }

}
