package com.javen.param;

public class PageParam {
    private int startLine;       //起始行
    private Integer pageSize = 6;
    private Integer currentPage=0; 	   // 当前页
 
    public int getStartLine() {
        return pageSize*currentPage;
    } 
 
    public Integer getPageSize() {
        return pageSize;
    } 
 
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    } 
 
    public Integer getCurrentPage() {
        return currentPage;
    } 
 
    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    } 
 
    @Override 
    public String toString() {
        return "PageParam{" + 
                "startLine=" + startLine +
                ", pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                '}'; 
    } 
} 