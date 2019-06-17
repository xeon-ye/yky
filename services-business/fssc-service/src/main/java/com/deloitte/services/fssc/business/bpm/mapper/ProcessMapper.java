package com.deloitte.services.fssc.business.bpm.mapper;

import com.deloitte.services.fssc.business.bpm.entity.Process;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 流程实例与单据关联表 Mapper 接口
 * </p>
 *
 * @author qiliao
 * @since 2019-03-18
 */
public interface ProcessMapper extends BaseMapper<Process> {

    Long findDocument(@Param("id") Long id, @Param("table") String table);

    String findStatus(@Param("documentId") Long documentId, @Param("documentType")String documentType);

    void updateDocumentStatus(@Param("documentId")Long documentId,
            @Param("documentType")String documentType,@Param("documentStatus")String documentStatus);
}
