package com.javen.vo;

import com.javen.param.PageParam;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int currentPage = 0; //当前页数
    private long totalPage;       //总页数
    private long totalNumber;    //总记录数
    private List<T> list;        //数据集

    public static Page NULL = new Page(0, 0, 15, new ArrayList());

    public Page() {
        super();
    }

    /**
     * @param startLine   当前页数
     * @param totalNumber 总记录数
     * @param pageSize    页大小
     * @param list        页数据
     */
    public Page(int startLine, long totalNumber, int pageSize, List<T> list) {
        super();
        this.currentPage = startLine / pageSize + 1;
        this.totalNumber = totalNumber;
        this.list = list;
        this.totalPage = totalNumber % pageSize == 0 ? totalNumber
                / pageSize : totalNumber / pageSize + 1;
    }

    public Page(PageParam pageParam, long totalNumber, List<T> list) {
        super();
        this.currentPage = pageParam.getCurrentPage();
        this.totalNumber = totalNumber;
        this.list = list;
        this.totalPage = totalNumber % pageParam.getPageSize() == 0 ? totalNumber
                / pageParam.getPageSize() : totalNumber / pageParam.getPageSize() + 1;
    }


    @Override
    public String toString() {
        return "Page{" +
                "currentPage=" + currentPage +
                ", totalPage=" + totalPage +
                ", totalNumber=" + totalNumber +
                ", list=" + list +
                '}';
    }
} 