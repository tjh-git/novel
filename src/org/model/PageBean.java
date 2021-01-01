package org.model;

import java.util.List;

/**
 * 分页对象
 * @param <T>
 */
public class PageBean<T> {
    private int totalCount;//总页数
    private int totalPage;//总页码=总记录数%每页显示条数=0？总记录数/每页显示数：总记录数/每页显示条数+1
    private List<T> list;//每页list数据
    private int currentPage;//当前页码
    private int rows;//每页的条数

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list+
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}
