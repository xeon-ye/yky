package com.deloitte.services.srpmp.outline.util;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-25
 * @Description :  计算分页
 * @Modified :
 */
public class PageUtil {

    /**
     * 页数量==总条数/页大小--如果整除就是该值，否则+1
     *
     * @param totalCount 总条数
     * @param pageSize 每页显示的条数
     * @return
     */
    public static int getTotalPageNum(int totalCount, int pageSize) {
        int pageCount = totalCount/pageSize;
        return totalCount % pageSize == 0 ? pageCount : pageCount + 1;
    }

    /**
     * 起始位置
     *
     * @param currentPageNum
     * @param pageSize
     * @return
     */
    public static int getStartIndex(int currentPageNum, int pageSize) {
        if(currentPageNum == 0) {
            currentPageNum = 1;
        }
        return (currentPageNum -1) * pageSize;
    }

    /**
     * 结束位置
     *
     * @param currentPageNum
     * @param pageSize
     * @return
     */
    public static int getEndIndex(int currentPageNum, int pageSize, int totalCount) {
        int endPageNum = getStartIndex(currentPageNum, pageSize) + pageSize;
        if(endPageNum > totalCount) {
            endPageNum = totalCount;
        }
        return endPageNum;
    }

}
