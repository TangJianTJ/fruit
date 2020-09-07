package com.upc.fruit.uti;

import lombok.Data;

@Data
public class PageQuery {
    private String query;
    private int pagenum;
    private int pagesize;

    public PageQuery(String query, int pagenum, int pagesize) {
        this.query = query;
        this.pagenum = pagenum;
        this.pagesize = pagesize;
    }
}
