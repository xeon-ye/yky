package com.deloitte.platform.common.core.entity.form;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

@ApiModel
@Slf4j
public class BaseQueryForm<P extends BaseParam> extends BaseForm {

    /**
     * 当期页
     */
    private int currentPage=0;

    /**
     * 每页显示条数
     */
    private int pageSize=15;


    /**
     * Form转化为Param
     *
     * @param clazz
     * @return
     */
    public P toParam(Class<P> clazz) {
        P p = null;
        try {
            p = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Param NewInstance Error");
        }
        BeanUtils.copyProperties(this, p);
        return p;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

}
