package com.deloitte.platform.common.core.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 20:42 06/03/2019
 * @Description :
 * @Modified :
 */
public class GdcPage<T> implements Iterable<T>, Serializable {
    private static final long serialVersionUID = -3720998571176536865L;
    private List<T> content = new ArrayList<>();
    private long total;
    private int pageNo;
    private int pageSize;
    private int contentSize;
    private int totalPage;

    public GdcPage() {
    }

    public GdcPage(List<T> content, long total, int pageNo, int pageSize) {
        this.content = content;
        this.total = total;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public GdcPage(IPage iPage){
        this.content = iPage.getRecords();
        this.total = iPage.getTotal();
        this.pageNo = (int)iPage.getCurrent();
        this.pageSize = (int)iPage.getSize();
    }

    public boolean hasPrevious() {
        return getPageNo() > 0;
    }

    public boolean hasNext() {
        return getPageNo() + 1 < getTotalPage();
    }


//    public boolean isFirst() {
//        return !hasPrevious();
//    }
//
//    boolean isLast() {
//        return !hasNext();
//    }


    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }


    public int getTotalPage() {
        return getPageSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getPageSize());
    }


    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    public void setContent(List<T> content) {
        this.content = content;
    }


    public boolean hasContent() {
        return getContentSize() > 0;
    }


    public int getContentSize() {
        return content.size();
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public int getPageNo() {
        return pageNo;
    }


    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    @Override
    public Iterator<T> iterator() {
        return getContent().iterator();
    }
}