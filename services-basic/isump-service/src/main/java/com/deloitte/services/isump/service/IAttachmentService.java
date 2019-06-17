package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.AttachmentQueryForm;
import com.deloitte.services.isump.entity.Attachment;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-04
 * @Description : Attachment服务类接口
 * @Modified :
 */
public interface IAttachmentService extends IService<Attachment> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Attachment>
     */
    IPage<Attachment> selectPage(AttachmentQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Attachment>
     */
    List<Attachment> selectList(AttachmentQueryForm queryForm);

    /**
     * 删除数据
     * @param id
     * @return
     */
    int delByMasterId(@Param("id") String id);
}
