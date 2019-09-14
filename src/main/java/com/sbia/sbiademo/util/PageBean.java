package com.sbia.sbiademo.util;

import java.util.List;

public class PageBean<T> {
    /**
     * 总记录数
     */
    private Integer total;
    /**
     * 每页记录数
     */
    private Integer pageSize;
    /**
     * 当前页码
     */
    private Integer pageNumber;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 起始索引
     */
    private Integer startIndex;
    /**
     * 查询的数据
     */
    private List<T> Tlist;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public List<T> getTlist() {
        return Tlist;
    }

    public void setTlist(List<T> tlist) {
        Tlist = tlist;
    }
}
