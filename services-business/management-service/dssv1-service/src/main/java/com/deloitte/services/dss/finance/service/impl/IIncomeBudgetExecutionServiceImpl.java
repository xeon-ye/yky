package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.finance.vo.FinCompanyVo;
import com.deloitte.platform.api.dss.finance.vo.FinanceDepreciationVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.services.dss.finance.mapper.FinanceDepreciationMapper;
import com.deloitte.services.dss.finance.mapper.IncomeBudgetExecutionMapper;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import com.deloitte.services.dss.finance.service.IFinExpenditureSerivice;
import com.deloitte.services.dss.finance.service.IFinanceDepreciationService;
import com.deloitte.services.dss.finance.service.IIncomeBudgetExecutionService;
import com.deloitte.services.dss.util.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-12
 * @Description :  FinanceDepreciation服务实现类
 * @Modified :
 */
@Service
@Transactional
public class IIncomeBudgetExecutionServiceImpl extends ServiceImpl implements IIncomeBudgetExecutionService {


    @Autowired
    private IncomeBudgetExecutionMapper incomeBudgetExecutionMapper;

    @Autowired
    private IFinCompanyService finCompanyService;
    @Autowired
    private IFinExpenditureSerivice finExpenditureSerivice;

    @Override
    public List<IncomeBudgetVo> expBudExeMonth(Map map) {
        Map table = new Hashtable();
        table.put("periodNow",map.get("month"));
        String periodBefore = map.get("month").toString().split("-")[0] + "-1";
        table.put("periodBefore",periodBefore);
        table.put("indexCode",map.get("indexCode"));
        table.put("monthTotal",finExpenditureSerivice.selectMonth(map));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode= "";
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                if(i == 0){
                    comCode = "('"+comCode+finCompanyVos.get(i).getComCode()+"'";
                }else{
                    comCode = comCode+",'"+finCompanyVos.get(i).getComCode()+"'";
                }
            }
            map.put("comCode",comCode+")");
            List<IncomeBudgetVo> list = incomeBudgetExecutionMapper.expBudExeMonth(table);
            return list;
        }else {
            String comCode= "";
            for(int i = 0; i < comCodeList.size(); i++){
                if(i == 0){
                    comCode = "('"+comCodeList.get(i)+"'";
                }else{
                    comCode = comCode+",'"+comCodeList.get(i)+"'";
                }
            }
            map.put("comCode",comCode+")");
            map.put("monthTotal",finExpenditureSerivice.selectMonth(map));
            List<IncomeBudgetVo> list = incomeBudgetExecutionMapper.expBudExeMonth(map);
            return list;
        }
    }

    @Override
    public List<IncomeBudgetVo> expRateMonth(Map map) {
        Map table = new Hashtable();
        table.put("periodNow",map.get("month"));
        String periodBefore = map.get("month").toString().split("-")[0] + "-1";
        table.put("periodBefore",periodBefore);
        table.put("indexCode",map.get("indexCode"));
        table.put("monthTotal",finExpenditureSerivice.selectMonth(map));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode= "";
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                if(i == 0){
                    comCode = "('"+comCode+finCompanyVos.get(i).getComCode()+"'";
                }else{
                    comCode = comCode+",'"+finCompanyVos.get(i).getComCode()+"'";
                }
            }
            table.put("comCode",comCode+")");
            return incomeBudgetExecutionMapper.expRateMonth(table);
        }else {
            String comCode= "";
            for(int i = 0; i < comCodeList.size(); i++){
                if(i == 0){
                    comCode = "('"+comCodeList.get(i)+"'";
                }else{
                    comCode = comCode+",'"+comCodeList.get(i)+"'";
                }
            }
            table.put("comCode",comCode+")");
            return incomeBudgetExecutionMapper.expRateMonth(table);
        }


    }

    @Override
    public List<IncomeBudgetVo> queryIncomeBudExe(Map map) {
        Map table = new Hashtable();
        table.put("periodNow", map.get("month"));
        String periodBefore = map.get("month").toString().split("-")[0] + "-1";
        table.put("periodBefore", periodBefore);
        table.put("indexCode", map.get("indexCode"));
        table.put("monthTotal",finExpenditureSerivice.selectMonth(map));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryIncomeBudExe(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryIncomeBudExe(table);
        }
    }

    @Override
    public List<IncomeBudgetVo> queryIncomeRate(Map map) {
        Map table = new Hashtable();
        table.put("periodNow", map.get("month"));
        String periodBefore = map.get("month").toString().split("-")[0] + "-1";
        table.put("periodBefore", periodBefore);
        table.put("indexCode", map.get("indexCode"));
        table.put("monthTotal",finExpenditureSerivice.selectMonth(map));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryIncomeRate(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryIncomeRate(table);
        }
    }

    @Override
    public List<IncomeBudgetVo> queryComIncomeBudExe(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        if(map.get("indexCode") != null)
            table.put("indexCode", map.get("indexCode"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComIncomeBudExe(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComIncomeBudExe(table);
        }
    }

    @Override
    public List<IncomeBudgetVo> queryComIncome(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        table.put("indexCode", map.get("indexCode"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComIncome(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComIncome(table);
        }
    }

    @Override
    public List<IncomeBudgetVo> queryTypeIncomeBudExe(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryTypeIncomeBudExe(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryTypeIncomeBudExe(table);
        }
    }

    @Override
    public List<IncomeBudgetVo> queryComIncomeBud(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComIncomeBud(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComIncomeBud(table);
        }
    }

    @Override
    public List<IncomeBudgetVo> queryTotalExpExe(Map map) {
        List<IncomeBudgetVo> list = incomeBudgetExecutionMapper.queryTotalExpExe(map);
        return list;
    }

    @Override
    public List<IncomeBudgetVo> queryComExpBudExe(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        table.put("indexCode", map.get("indexCode"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComExpBudExe(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComExpBudExe(table);
        }
    }

    @Override
    public List<IncomeBudgetVo> queryComExpRate(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        table.put("indexCode", map.get("indexCode"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComExpRate(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComExpRate(table);
        }
    }

    @Override
    public List<IncomeBudgetVo> queryComExpGrowth(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        table.put("indexCode", map.get("indexCode"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComExpGrowth(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComExpGrowth(table);
        }
    }

    @Override
    public List<IncomeBudgetVo> queryComExp(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        table.put("indexCode", map.get("indexCode"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComExp(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryComExp(table);
        }
    }

    @Override
    public List<IncomeBudgetVo> queryProExpRate(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        List<String> indexCodeList = (List<String>)map.get("indexCode");
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
        }
        if (null == indexCodeList || (indexCodeList != null && indexCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
        }
        return incomeBudgetExecutionMapper.queryProExpRate(table);
    }

    @Override
    public List<IncomeBudgetVo> queryExpTypeRate(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryExpTypeRate(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryExpTypeRate(table);
        }
    }

    @Override
    public List<IncomeBudgetVo> queryExpTypeGrowthRate(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryExpTypeGrowthRate(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryExpTypeGrowthRate(table);
        }
    }

    @Override
    public List<IncomeBudgetVo> queryExpTypeExp(Map map) {
        Map table = new Hashtable();
        table.put("periodCode", map.get("month"));
        List<String> comCodeList = (List<String>) map.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = finCompanyService.selectCompany();
            String comCode = "";
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryExpTypeExp(table);
        } else {
            String comCode = "";
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            table.put("comCode", comCode + ")");
            return incomeBudgetExecutionMapper.queryExpTypeExp(table);
        }
    }
}

