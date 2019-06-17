package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.OuQueryForm;
import com.deloitte.services.project.entity.Ou;
import com.deloitte.services.project.mapper.OuMapper;
import com.deloitte.services.project.service.IOuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Ou服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OuServiceImpl extends ServiceImpl<OuMapper, Ou> implements IOuService {


    @Autowired
    private OuMapper ouMapper;

    @Override
    public IPage<Ou> selectPage(OuQueryForm queryForm ) {
        QueryWrapper<Ou> queryWrapper = new QueryWrapper <Ou>();
        //getQueryWrapper(queryWrapper,queryForm);
        return ouMapper.selectPage(new Page<Ou>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Ou> selectList(OuQueryForm queryForm) {
        QueryWrapper<Ou> queryWrapper = new QueryWrapper <Ou>();
        //getQueryWrapper(queryWrapper,queryForm);
        return ouMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Ou> getQueryWrapper(QueryWrapper<Ou> queryWrapper,OuQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getOuId())){
            queryWrapper.eq(Ou.OU_ID,queryForm.getOuId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(Ou.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getOuCode())){
            queryWrapper.eq(Ou.OU_CODE,queryForm.getOuCode());
        }
        if(StringUtils.isNotBlank(queryForm.getOuName())){
            queryWrapper.eq(Ou.OU_NAME,queryForm.getOuName());
        }
        if(StringUtils.isNotBlank(queryForm.getDepartmentCode())){
            queryWrapper.eq(Ou.DEPARTMENT_CODE,queryForm.getDepartmentCode());
        }
        if(StringUtils.isNotBlank(queryForm.getDepartment())){
            queryWrapper.eq(Ou.DEPARTMENT,queryForm.getDepartment());
        }
        if(StringUtils.isNotBlank(queryForm.getCorporateRepreId())){
            queryWrapper.eq(Ou.CORPORATE_REPRE_ID,queryForm.getCorporateRepreId());
        }
        if(StringUtils.isNotBlank(queryForm.getContactsId())){
            queryWrapper.eq(Ou.CONTACTS_ID,queryForm.getContactsId());
        }
        if(StringUtils.isNotBlank(queryForm.getOuOrganization())){
            queryWrapper.eq(Ou.OU_ORGANIZATION,queryForm.getOuOrganization());
        }
        if(StringUtils.isNotBlank(queryForm.getActualStaffNumber())){
            queryWrapper.eq(Ou.ACTUAL_STAFF_NUMBER,queryForm.getActualStaffNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getNumberResearchers())){
            queryWrapper.eq(Ou.NUMBER__RESEARCHERS,queryForm.getNumberResearchers());
        }
        if(StringUtils.isNotBlank(queryForm.getNumberOfRetirees())){
            queryWrapper.eq(Ou.NUMBER_OF_RETIREES,queryForm.getNumberOfRetirees());
        }
        if(StringUtils.isNotBlank(queryForm.getResearchersAged3550())){
            queryWrapper.eq(Ou.RESEARCHERS_AGED_35_50,queryForm.getResearchersAged3550());
        }
        if(StringUtils.isNotBlank(queryForm.getAcademicianQuantity())){
            queryWrapper.eq(Ou.ACADEMICIAN_QUANTITY,queryForm.getAcademicianQuantity());
        }
        if(StringUtils.isNotBlank(queryForm.getPhdQuantity())){
            queryWrapper.eq(Ou.PHD_QUANTITY,queryForm.getPhdQuantity());
        }
        if(StringUtils.isNotBlank(queryForm.getMasterQuantity())){
            queryWrapper.eq(Ou.MASTER_QUANTITY,queryForm.getMasterQuantity());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalEquipment10())){
            queryWrapper.eq(Ou.TOTAL_EQUIPMENT_10,queryForm.getTotalEquipment10());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalEquipmentUsing10())){
            queryWrapper.eq(Ou.TOTAL_EQUIPMENT_USING_10,queryForm.getTotalEquipmentUsing10());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgTotalEquipment10())){
            queryWrapper.eq(Ou.ORG_TOTAL_EQUIPMENT_10,queryForm.getOrgTotalEquipment10());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgTotalEquipmentUsing10())){
            queryWrapper.eq(Ou.ORG_TOTAL_EQUIPMENT_USING_10,queryForm.getOrgTotalEquipmentUsing10());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalEquipment50())){
            queryWrapper.eq(Ou.TOTAL_EQUIPMENT_50,queryForm.getTotalEquipment50());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalEquipmentUsing50())){
            queryWrapper.eq(Ou.TOTAL_EQUIPMENT_USING_50,queryForm.getTotalEquipmentUsing50());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgTotalEqu50())){
            queryWrapper.eq(Ou.ORG_TOTAL_EQU_50,queryForm.getOrgTotalEqu50());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgTotalEquUsing50())){
            queryWrapper.eq(Ou.ORG_TOTAL_EQU_USING_50,queryForm.getOrgTotalEquUsing50());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalEquipment100())){
            queryWrapper.eq(Ou.TOTAL_EQUIPMENT_100,queryForm.getTotalEquipment100());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalEquipmentUsing100())){
            queryWrapper.eq(Ou.TOTAL_EQUIPMENT_USING_100,queryForm.getTotalEquipmentUsing100());
        }
        if(StringUtils.isNotBlank(queryForm.getOriEquipment100())){
            queryWrapper.eq(Ou.ORI_EQUIPMENT_100,queryForm.getOriEquipment100());
        }
        if(StringUtils.isNotBlank(queryForm.getOriEquipmentUsing100())){
            queryWrapper.eq(Ou.ORI_EQUIPMENT_USING_100,queryForm.getOriEquipmentUsing100());
        }
        if(StringUtils.isNotBlank(queryForm.getZxNumber5Years())){
            queryWrapper.eq(Ou.ZX_NUMBER_5_YEARS,queryForm.getZxNumber5Years());
        }
        if(StringUtils.isNotBlank(queryForm.getZxFunding5Years())){
            queryWrapper.eq(Ou.ZX_FUNDING_5_YEARS,queryForm.getZxFunding5Years());
        }
        if(StringUtils.isNotBlank(queryForm.getZxNumber1Years())){
            queryWrapper.eq(Ou.ZX_NUMBER_1_YEARS,queryForm.getZxNumber1Years());
        }
        if(StringUtils.isNotBlank(queryForm.getZxFunding1Years())){
            queryWrapper.eq(Ou.ZX_FUNDING_1_YEARS,queryForm.getZxFunding1Years());
        }
        if(StringUtils.isNotBlank(queryForm.getHxNumber5Years())){
            queryWrapper.eq(Ou.HX_NUMBER_5_YEARS,queryForm.getHxNumber5Years());
        }
        if(StringUtils.isNotBlank(queryForm.getHxFunding5Years())){
            queryWrapper.eq(Ou.HX_FUNDING_5_YEARS,queryForm.getHxFunding5Years());
        }
        if(StringUtils.isNotBlank(queryForm.getHxNumber1Years())){
            queryWrapper.eq(Ou.HX_NUMBER_1_YEARS,queryForm.getHxNumber1Years());
        }
        if(StringUtils.isNotBlank(queryForm.getHxFunding1Years())){
            queryWrapper.eq(Ou.HX_FUNDING_1_YEARS,queryForm.getHxFunding1Years());
        }
        if(StringUtils.isNotBlank(queryForm.getGjhzNumber5Years())){
            queryWrapper.eq(Ou.GJHZ_NUMBER_5_YEARS,queryForm.getGjhzNumber5Years());
        }
        if(StringUtils.isNotBlank(queryForm.getGjhzValue5Years())){
            queryWrapper.eq(Ou.GJHZ_VALUE_5_YEARS,queryForm.getGjhzValue5Years());
        }
        if(StringUtils.isNotBlank(queryForm.getGjhzNumber1Years())){
            queryWrapper.eq(Ou.GJHZ_NUMBER_1_YEARS,queryForm.getGjhzNumber1Years());
        }
        if(StringUtils.isNotBlank(queryForm.getGjhzValue1Years())){
            queryWrapper.eq(Ou.GJHZ_VALUE_1_YEARS,queryForm.getGjhzValue1Years());
        }
        if(StringUtils.isNotBlank(queryForm.getqCouPro5Y())){
            queryWrapper.eq(Ou.Q_COU_PRO_5_Y,queryForm.getqCouPro5Y());
        }
        if(StringUtils.isNotBlank(queryForm.getqPin5Y())){
            queryWrapper.eq(Ou.Q_PIN_5_Y,queryForm.getqPin5Y());
        }
        if(StringUtils.isNotBlank(queryForm.getqProLast5Y())){
            queryWrapper.eq(Ou.Q_PRO_LAST_5_Y,queryForm.getqProLast5Y());
        }
        if(StringUtils.isNotBlank(queryForm.getqInvLast5Y())){
            queryWrapper.eq(Ou.Q_INV_LAST_5_Y,queryForm.getqInvLast5Y());
        }
        if(StringUtils.isNotBlank(queryForm.getqXinLast5Y())){
            queryWrapper.eq(Ou.Q_XIN_LAST_5_Y,queryForm.getqXinLast5Y());
        }
        if(StringUtils.isNotBlank(queryForm.getqPatLast5Y())){
            queryWrapper.eq(Ou.Q_PAT_LAST_5_Y,queryForm.getqPatLast5Y());
        }
        if(StringUtils.isNotBlank(queryForm.getqSciLast5Y())){
            queryWrapper.eq(Ou.Q_SCI_LAST_5_Y,queryForm.getqSciLast5Y());
        }
        if(StringUtils.isNotBlank(queryForm.getqCon1Y())){
            queryWrapper.eq(Ou.Q_CON_1_Y,queryForm.getqCon1Y());
        }
        if(StringUtils.isNotBlank(queryForm.getqPin1Y())){
            queryWrapper.eq(Ou.Q_PIN_1_Y,queryForm.getqPin1Y());
        }
        if(StringUtils.isNotBlank(queryForm.getqPro1Y())){
            queryWrapper.eq(Ou.Q_PRO_1_Y,queryForm.getqPro1Y());
        }
        if(StringUtils.isNotBlank(queryForm.getqInv1Y())){
            queryWrapper.eq(Ou.Q_INV_1_Y,queryForm.getqInv1Y());
        }
        if(StringUtils.isNotBlank(queryForm.getqXinLast1Y())){
            queryWrapper.eq(Ou.Q_XIN_LAST_1_Y,queryForm.getqXinLast1Y());
        }
        if(StringUtils.isNotBlank(queryForm.getqPatLast1Y())){
            queryWrapper.eq(Ou.Q_PAT_LAST_1_Y,queryForm.getqPatLast1Y());
        }
        if(StringUtils.isNotBlank(queryForm.getqSciLast1Y())){
            queryWrapper.eq(Ou.Q_SCI_LAST_1_Y,queryForm.getqSciLast1Y());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Ou.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Ou.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Ou.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Ou.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Ou.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Ou.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Ou.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Ou.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Ou.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Ou.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Ou.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

