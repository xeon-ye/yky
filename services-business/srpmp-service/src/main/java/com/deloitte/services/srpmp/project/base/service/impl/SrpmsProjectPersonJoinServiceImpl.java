package com.deloitte.services.srpmp.project.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectPersonJoinQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectPersonJoinVo;
import com.deloitte.services.srpmp.common.enums.PersonJoinWayEnums;
import com.deloitte.services.srpmp.common.util.DateParseUtil;
import com.deloitte.services.srpmp.project.apply.util.LocalDateUtils;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProjectPersonJoin;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectPersonJoinMapper;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectPersonJoinService;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description : SrpmsProjectPersonJoin服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsProjectPersonJoinServiceImpl extends ServiceImpl<SrpmsProjectPersonJoinMapper, SrpmsProjectPersonJoin>
		implements ISrpmsProjectPersonJoinService {

	@Autowired
	private SrpmsProjectPersonJoinMapper srpmsProjectPersonJoinMapper;

	@Override
	public void cleanAndSavePersonJoin(List<SrpmsProjectPersonJoinVo> personJoinVoList, PersonJoinWayEnums joinWayEnum,
			Long projectId) {
		deleteByJoinWayAndProjectId(joinWayEnum, projectId);
		savePersonJoin(personJoinVoList, joinWayEnum, projectId);
	}

	@Override
	public void savePersonJoin(List<SrpmsProjectPersonJoinVo> personJoinVoList, PersonJoinWayEnums joinWayEnum,
			Long projectId) {
		if (personJoinVoList == null) {
			return;
		}
		List<SrpmsProjectPersonJoin> personJoinList = new ArrayList<>();
		for (SrpmsProjectPersonJoinVo personJoinVo : personJoinVoList) {
			if (personJoinVo.getPersonName() != null && personJoinVo.getPersonName().length() != 0) {
				SrpmsProjectPersonJoin personJoinEntity = new SrpmsProjectPersonJoin();
				BeanUtils.copyProperties(personJoinVo, personJoinEntity);
				if (personJoinVo.getBirthDate() != null){
					personJoinEntity.setBirthDate(
							DateParseUtil.parseLocalDateTime(personJoinVo.getBirthDate()));
				}
				personJoinEntity.setJoinWay(joinWayEnum.getCode());
				personJoinEntity.setProjectId(projectId);
				personJoinEntity.setId(null);
				personJoinList.add(personJoinEntity);
			}
		}
		this.saveBatch(personJoinList);
	}

	/**
	 * 根据合作类型和项目Id删除项目参与人员
	 *
	 * @param joinWayEnum
	 * @param projectId
	 */
	@Override
	public void deleteByJoinWayAndProjectId(PersonJoinWayEnums joinWayEnum, Long projectId) {
		if (joinWayEnum == null)
			return;
		UpdateWrapper<SrpmsProjectPersonJoin> personJoinUpdateWrapper = new UpdateWrapper<>();
		personJoinUpdateWrapper.eq(SrpmsProjectPersonJoin.JOIN_WAY, joinWayEnum.getCode());
		personJoinUpdateWrapper.eq(SrpmsProjectPersonJoin.PROJECT_ID, projectId);
		this.remove(personJoinUpdateWrapper);
	}

	@Override
	public void copyPersonJoin(Long budgetTaskId, Long projectId) {
		QueryWrapper<SrpmsProjectPersonJoin> personQueryWrapper = new QueryWrapper<>();
		personQueryWrapper.eq(SrpmsProjectPersonJoin.JOIN_WAY, PersonJoinWayEnums.APPLY_MAIN_MEMBER.getCode());
		personQueryWrapper.eq(SrpmsProjectPersonJoin.PROJECT_ID, projectId);
		List<SrpmsProjectPersonJoin> dblist = this.list(personQueryWrapper);
		if (!CollectionUtils.isEmpty(dblist)) {
			List<SrpmsProjectPersonJoin> personJoinList = new ArrayList<>();
			for (SrpmsProjectPersonJoin personJoinVo : dblist) {
				SrpmsProjectPersonJoin personJoinEntity = new SrpmsProjectPersonJoin();
				BeanUtils.copyProperties(personJoinVo, personJoinEntity);
				// personJoinEntity.setJoinWay(PersonJoinWayEnums.TASK_BUDGET_INNOVATE_PRE_MOST_MEMBER.getCode());
				personJoinEntity.setId(null);
				personJoinList.add(personJoinEntity);
			}
			this.saveBatch(personJoinList);
		}
	}

	@Override
	public List<SrpmsProjectPersonJoinVo> queryPersonJoinListByJoinWay(PersonJoinWayEnums joinWay, Long projectId) {
		QueryWrapper<SrpmsProjectPersonJoin> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(SrpmsProjectPersonJoin.JOIN_WAY, joinWay.getCode());
		queryWrapper.eq(SrpmsProjectPersonJoin.PROJECT_ID, projectId);
		List<SrpmsProjectPersonJoin> entityList = this.list(queryWrapper);
		if (entityList != null) {
			List<SrpmsProjectPersonJoinVo> voList = new ArrayList<>();
			for (SrpmsProjectPersonJoin personJoinEntity : entityList) {
				SrpmsProjectPersonJoinVo personJoinVo = new SrpmsProjectPersonJoinVo();
				BeanUtils.copyProperties(personJoinEntity, personJoinVo);
				personJoinVo.setBirthDate(
						LocalDateUtils.format(personJoinEntity.getBirthDate(), LocalDateUtils.PARRERN_Y_M_D));
				voList.add(personJoinVo);
			}

			return voList;
		}
		return new ArrayList<>();
	}

	@Override
	public IPage<SrpmsProjectPersonJoin> selectPage(SrpmsProjectPersonJoinQueryForm queryForm) {
		QueryWrapper<SrpmsProjectPersonJoin> queryWrapper = new QueryWrapper<SrpmsProjectPersonJoin>();
		// getQueryWrapper(queryWrapper,queryForm);
		return srpmsProjectPersonJoinMapper.selectPage(
				new Page<SrpmsProjectPersonJoin>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);

	}

	@Override
	public List<SrpmsProjectPersonJoin> selectList(SrpmsProjectPersonJoinQueryForm queryForm) {
		QueryWrapper<SrpmsProjectPersonJoin> queryWrapper = new QueryWrapper<SrpmsProjectPersonJoin>();
		// getQueryWrapper(queryWrapper,queryForm);
		return srpmsProjectPersonJoinMapper.selectList(queryWrapper);
	}

	/**
	 * 通用查询
	 * 
	 * @param queryWrapper,queryForm
	 * @return public QueryWrapper<SrpmsProjectPersonJoin>
	 *         getQueryWrapper(QueryWrapper<SrpmsProjectPersonJoin>
	 *         queryWrapper,BaseQueryForm<SrpmsProjectPersonJoinQueryParam>
	 *         queryForm){ //条件拼接
	 *         if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getProjectId())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.PROJECT_ID,srpmsProjectPersonJoin.getProjectId());
	 *         } if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getProjectNum())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.PROJECT_NUM,srpmsProjectPersonJoin.getProjectNum());
	 *         } if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getJoinWay())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.JOIN_WAY,srpmsProjectPersonJoin.getJoinWay());
	 *         } if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getGender())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.GENDER,srpmsProjectPersonJoin.getGender());
	 *         } if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getBirthDate())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.BIRTH_DATE,srpmsProjectPersonJoin.getBirthDate());
	 *         }
	 *         if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getPositionTitle())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.POSITION_TITLE,srpmsProjectPersonJoin.getPositionTitle());
	 *         }
	 *         if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getPersonCategory())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.PERSON_CATEGORY,srpmsProjectPersonJoin.getPersonCategory());
	 *         } if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getDegree())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.DEGREE,srpmsProjectPersonJoin.getDegree());
	 *         } if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getDeptName())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.DEPT_NAME,srpmsProjectPersonJoin.getDeptName());
	 *         } if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getPhone())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.PHONE,srpmsProjectPersonJoin.getPhone());
	 *         } if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getIdCard())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.ID_CARD,srpmsProjectPersonJoin.getIdCard());
	 *         }
	 *         if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getWorkPerYear())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.WORK_PER_YEAR,srpmsProjectPersonJoin.getWorkPerYear());
	 *         } if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getBelongTask())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.BELONG_TASK,srpmsProjectPersonJoin.getBelongTask());
	 *         } if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getCreateTime())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.CREATE_TIME,srpmsProjectPersonJoin.getCreateTime());
	 *         } if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getCreateBy())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.CREATE_BY,srpmsProjectPersonJoin.getCreateBy());
	 *         } if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getUpdateTime())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.UPDATE_TIME,srpmsProjectPersonJoin.getUpdateTime());
	 *         } if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getUpdateBy())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.UPDATE_BY,srpmsProjectPersonJoin.getUpdateBy());
	 *         }
	 *         if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getSourcePersonId())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.SOURCE_PERSON_ID,srpmsProjectPersonJoin.getSourcePersonId());
	 *         }
	 *         if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getOtherProjectRole())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.OTHER_PROJECT_ROLE,srpmsProjectPersonJoin.getOtherProjectRole());
	 *         }
	 *         if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getOtherProjectName())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.OTHER_PROJECT_NAME,srpmsProjectPersonJoin.getOtherProjectName());
	 *         }
	 *         if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getOtherProjectSource())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.OTHER_PROJECT_SOURCE,srpmsProjectPersonJoin.getOtherProjectSource());
	 *         }
	 *         if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getOtherProjectDateStart())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.OTHER_PROJECT_DATE_START,srpmsProjectPersonJoin.getOtherProjectDateStart());
	 *         }
	 *         if(StringUtils.isNotBlank(srpmsProjectPersonJoin.getOtherProjectDateEnd())){
	 *         queryWrapper.like(SrpmsProjectPersonJoin.OTHER_PROJECT_DATE_END,srpmsProjectPersonJoin.getOtherProjectDateEnd());
	 *         } return queryWrapper; }
	 */
}
