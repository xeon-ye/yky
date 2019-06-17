package com.deloitte.services.srpmp.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.srpmp.common.constant.NclobConstant;
import com.deloitte.services.srpmp.common.entity.SrpmsCommonNclob;
import com.deloitte.services.srpmp.common.mapper.SrpmsCommonNclobMapper;
import com.deloitte.services.srpmp.common.service.ISrpmsCommonNclobService;

@Service
@Transactional
public class SrpmsCommonNclobServiceImpl extends ServiceImpl<SrpmsCommonNclobMapper, SrpmsCommonNclob>
		implements ISrpmsCommonNclobService {

	@Autowired
	private SrpmsCommonNclobMapper commonNclobMapper;

	public String getApplyVo(long projectId) {

		SrpmsCommonNclob commonNclobDo = new SrpmsCommonNclob();
		commonNclobDo.setProjectId(projectId);
		commonNclobDo.setType(NclobConstant.APPLY_BOOK);

		Long id = commonNclobMapper.selectByProjectIdAndType(commonNclobDo);
		if (id == null || id == 0) {
			return "";
		}
		return this.getById(id).getContent();
	}
}
