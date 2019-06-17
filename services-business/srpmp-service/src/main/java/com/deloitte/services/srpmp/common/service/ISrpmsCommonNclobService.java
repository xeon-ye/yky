package com.deloitte.services.srpmp.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.srpmp.common.entity.SrpmsCommonNclob;

/**
 * @Author : pengchao
 * @Date : Create in 2019-04-30
 * @Description : SrpmsCommonNclob服务类接口
 * @Modified :
 */
public interface ISrpmsCommonNclobService extends IService<SrpmsCommonNclob> {

	public String getApplyVo(long projectId);
}