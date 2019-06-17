package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.base.param.BaseContentCommitmentQueryForm;
import com.deloitte.services.fssc.base.entity.BaseContentCommitment;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-07
 * @Description : BaseContentCommitment服务类接口
 * @Modified :
 */
public interface IBaseContentCommitmentService extends IService<BaseContentCommitment> {

    /**
     * 根据单据类型ID查承若书
     * @param documentTypeId
     * @return
     */
    BaseContentCommitment getByDocumentTypeId(Long documentTypeId);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseContentCommitment>
     */
    IPage<BaseContentCommitment> selectPage(BaseContentCommitmentQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseContentCommitment>
     */
    List<BaseContentCommitment> selectList(BaseContentCommitmentQueryForm queryForm);

    /**
     * 更新承诺书内容
     * @param baseContentCommitment
     * @return
     */
    boolean updateContentCommitment(BaseContentCommitment baseContentCommitment);

    /**
     * 保持承认书
     * @param baseContentCommitment
     * @return
     */
    boolean saveContentCommitment(BaseContentCommitment baseContentCommitment);

    /**
     * 修改状态
     * @param ids
     * @param status
     * @return
     */
    boolean modifyStatus(List<Long> ids,String status);
}
