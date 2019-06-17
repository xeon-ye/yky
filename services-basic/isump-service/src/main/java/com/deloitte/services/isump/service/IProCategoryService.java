package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.ProCategoryQueryForm;
import com.deloitte.platform.api.isump.vo.ProCategoryVo;
import com.deloitte.services.isump.entity.ProCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description : ProCategory服务类接口
 * @Modified :
 */
public interface IProCategoryService extends IService<ProCategory> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ProCategory>
     */
    IPage<ProCategory> selectPage(ProCategoryQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ProCategory>
     */
    List<ProCategory> selectList(ProCategoryQueryForm queryForm);

    /**
     * 根据副账号id查询项目类型列表
     * @param id
     * @return
     */
    List<ProCategoryVo> getByDeputyAccountId(long id);
}
