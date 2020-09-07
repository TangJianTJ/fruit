package com.upc.fruit.uti;

import lombok.Data;

import java.util.List;

@Data
public class QuaryReturn {
    private int pagenum;
    private int total;
    private List<?> list;
    private int status;
    private String msg;

    public QuaryReturn(int pagenum, int total, List<?> list) {
        this.pagenum = pagenum;
        this.total = total;
        this.list = list;
    }
    public QuaryReturn()
    {

    }
}
