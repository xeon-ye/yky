package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.finance.vo.FinCompanyVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.platform.api.dss.finance.vo.ProjectVo;
import com.deloitte.services.dss.finance.mapper.FinAchievementMapper;
import com.deloitte.services.dss.finance.mapper.FinProjectMapper;
import com.deloitte.services.dss.finance.service.IFinAchivementService;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import com.deloitte.services.dss.finance.service.IFinProjectService;
import com.deloitte.services.dss.util.TimeUtil;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description :  FinanceExecution服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinProjectServiceImpl extends ServiceImpl implements IFinProjectService {

    @Autowired
    private FinProjectMapper finProjectMapper;
    @Autowired
    private IFinCompanyService finCompanyService;

    public String getComCode(List<String> comCodeList){
        String comCode = "";
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            comCode = comCode + ")";
            return comCode;
        } else {
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            comCode = comCode + ")";
            return comCode;
        }
    }

    public String getIndexCode(List<String> indexCodeList){
        String indexCode = "";
        if (null == indexCodeList || (indexCodeList != null && indexCodeList.size() == 0)) {
            List<ProjectVo> projectVos = finProjectMapper.selectProIndexCode();
            for (int i = 0; i < projectVos.size(); i++) {
                if (i == 0) {
                    indexCode = "('" + indexCode + projectVos.get(i).getIndexCode() + "'";
                } else {
                    indexCode = indexCode + ",'" + projectVos.get(i).getIndexCode() + "'";
                }
            }
            indexCode = indexCode + ")";
            return indexCode;
        } else {
            for (int i = 0; i < indexCodeList.size(); i++) {
                if (i == 0) {
                    indexCode = "('" + indexCodeList.get(i) + "'";
                } else {
                    indexCode = indexCode + ",'" + indexCodeList.get(i) + "'";
                }
            }
            indexCode = indexCode + ")";
            return indexCode;
        }
    }


    @Override
    public List<ProjectVo> selectProject(Map map) {
        Map table = new Hashtable();
        List<String> comCodeList = (List<String>) map.get("comCode");
        List<String> indexCodeList = (List<String>) map.get("indexCodes");
        String comCode = this.getComCode(comCodeList);
        String indexCode = this.getIndexCode(indexCodeList);
        table.put("comCode",comCode);
        table.put("indexCode",indexCode);
        List<ProjectVo> list = finProjectMapper.selectProject(table);

        List<IncomeBudgetVo> list1 = new ArrayList<IncomeBudgetVo>();

        if("FIND0067".equals(indexCodeList.get(0))){
            table.put("periodCode", TimeUtil.getYear()+"-"+TimeUtil.getMonth());
            list1 = finProjectMapper.selectProjectNowAll(table);
        }else{
            table.put("periodCode", TimeUtil.getYear());
            list1 = finProjectMapper.selectProjectNow(table);
        }
        if(null != list && list.size() > 0 && null != list1 && list1.size()>0){
            for(int i = 0; i < list.size(); i++){
                if( null != list.get(i)&& null != list.get(0)&&null!=list1.get(0)){
                    list.get(i).setYtdT0(list1.get(0).getYtd());
                }
            }
        }

        return list;
    }

    @Override
    public List<ProjectVo> selectProEveStu(Map map) {
        Map table = new Hashtable();
        List<String> comCodeList = (List<String>) map.get("comCode");
        List<String> indexCodeList = (List<String>) map.get("indexCodes");
        String comCode = this.getComCode(comCodeList);
        String indexCode = this.getIndexCode(indexCodeList);

        table.put("comCode",comCode);
        table.put("indexCode",indexCode);
        return finProjectMapper.selectProEveStu(table);
    }

    @Override
    public List<ProjectVo> selectProStu(Map map) {
        Map table = new Hashtable();
        List<String> comCodeList = (List<String>) map.get("comCode");
        List<String> indexCodeList = (List<String>) map.get("indexCodes");
        String comCode = this.getComCode(comCodeList);
        String indexCode = this.getIndexCode(indexCodeList);

        table.put("comCode",comCode);
        table.put("indexCode",indexCode);
        return finProjectMapper.selectProStu(table);
    }

    @Override
    public List<ProjectVo> selectProIndexCode() {
        List<ProjectVo> projectVos = finProjectMapper.selectProIndexCode();
        return projectVos;
    }
}

