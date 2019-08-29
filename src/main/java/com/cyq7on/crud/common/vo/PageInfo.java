package com.cyq7on.crud.common.vo;

import com.github.pagehelper.Page;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @description
* @author cyq7on
* @create 2019/8/29
**/
@Data
public class PageInfo<T> implements Serializable {

    private long total;

    private List<T> list;

    public PageInfo() {
        this.list = new ArrayList<>();
        this.total = 0;
    }

    public PageInfo(List<T> list) {
        if(list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.total = page.getTotal();
            this.list = page.getResult();
        }
    }

    public static <T> PageInfo<T> data(List<T> list) {
        if(list == null) {
            return new PageInfo<>();
        }
        return data(list,list.size());
    }

    public static <T> PageInfo<T> data(List<T> list, long count) {
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setList(list);
        pageInfo.setTotal(count);
        return pageInfo;
    }

}
