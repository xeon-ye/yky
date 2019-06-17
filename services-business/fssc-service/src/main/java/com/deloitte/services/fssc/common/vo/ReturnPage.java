package com.deloitte.services.fssc.common.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * 返回bootstrap table接受的标准格式数据
 *
 * @author jawjiang
 */
@Data
public class ReturnPage<T> implements Serializable{

    /**
     * 行数据
     */
    List<T> rows;

    /**
     * 总数
     */
    Long total;

    public ReturnPage(List<T> rows, Long total) {
        this.rows = rows;
        this.total = total;
    }

    public ReturnPage(List<T> rows, Integer total) {
        this.rows = rows;
        this.total = Long.valueOf(total);
    }

    /**
     * mybatis plus 分页结果转换
     * @param iPage
     */
    public ReturnPage(IPage iPage){
        this.rows = iPage.getRecords();
        this.total = iPage.getTotal();
    }
}
