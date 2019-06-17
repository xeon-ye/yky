package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.FinCompanyVo;
import com.deloitte.platform.api.dss.finance.vo.FinanceLiabilitiesVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.services.dss.finance.mapper.FinanceLiabilitiesMapper;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import com.deloitte.services.dss.finance.service.IFinanceLiabilitiesService;
import com.deloitte.services.dss.util.MathUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-12
 * @Description :  FinanceLiabilities服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinanceLiabilitiesServiceImpl extends ServiceImpl implements IFinanceLiabilitiesService {


    @Autowired
    private FinanceLiabilitiesMapper financeLiabilitiesMapper;

    @Autowired
    private IFinCompanyService iFinCompanyService;

    //拼接机构数组的方法
    public String getComCode(Map map){
        List<String> comCodeList = (List<String>) map.get("comCode");
        String comCode = "";
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            for (int i = 0; i < finCompanyVos.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCode + finCompanyVos.get(i).getComCode() + "'";
                } else {
                    comCode = comCode + ",'" + finCompanyVos.get(i).getComCode() + "'";
                }
            }
            comCode = comCode + ")";
        } else {
            for (int i = 0; i < comCodeList.size(); i++) {
                if (i == 0) {
                    comCode = "('" + comCodeList.get(i) + "'";
                } else {
                    comCode = comCode + ",'" + comCodeList.get(i) + "'";
                }
            }
            comCode = comCode + ")";
        }
        return comCode;
    }

    @Override
    public List<IncomeBudgetVo> queryLiabilities(Map map) {
        Map table = new HashMap();
        table.put("periodCode",map.get("month"));
        String comCode = this.getComCode(map);
        table.put("comCode",comCode);
        List<IncomeBudgetVo> list = financeLiabilitiesMapper.queryLiabilities(table);
        return list;
    }

    @Override
    public List<IncomeBudgetVo> selectLiabilities(Map map) {
        Map table = new HashMap();
        table.put("periodCode",map.get("month"));
        String comCode = this.getComCode(map);
        table.put("comCode",comCode);
        List<IncomeBudgetVo> list = financeLiabilitiesMapper.selectLiabilities(table);
        return list;
    }

    @Override
    public List<List<IncomeBudgetVo>> queryComLiabilities(AcceptVo myData) {
        List<List<IncomeBudgetVo>> lists = new ArrayList<List<IncomeBudgetVo>>();
        IncomeBudgetVo incomeBudgetVo = new IncomeBudgetVo();
        incomeBudgetVo.setPeriodCode(myData.getPeriodCode());
        if(myData.getComCode().isEmpty()){
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                incomeBudgetVo.setComCode(finCompanyVos.get(i).getComCode());
                List<IncomeBudgetVo> lists1 = financeLiabilitiesMapper.queryComLiabilities(incomeBudgetVo);
                if(!lists1.isEmpty())
                    lists.add(lists1);
            }
        }else {
            for(int i = 0; i < myData.getComCode().size(); i++){
                incomeBudgetVo.setComCode(myData.getComCode().get(i));
                List<IncomeBudgetVo> incomeBudgetVos = financeLiabilitiesMapper.queryComLiabilities(incomeBudgetVo);
                lists.add(incomeBudgetVos);
            }
        }
        return lists;
    }

    @Override
    public List<List<IncomeBudgetVo>> queryComLiabilitiesSum(AcceptVo myData) {
        /*List<List<IncomeBudgetVo>> result = new ArrayList<List<IncomeBudgetVo>>();
        List<IncomeBudgetVo> incomeBudgetVos = this.queryLiabilities(myData);
        List<List<IncomeBudgetVo>> lists = this.queryComLiabilities(myData);
        BigDecimal ytdNL = BigDecimal.valueOf(0);
        BigDecimal ytdDL = BigDecimal.valueOf(0);
        BigDecimal ytdND = BigDecimal.valueOf(0);
        BigDecimal ytdDD = BigDecimal.valueOf(0);
        for(int i = 0; i < lists.size(); i++){
            ytdNL = lists.get(i).get(0).getYtdN().add(ytdNL);
            ytdDL = lists.get(i).get(0).getYtdD().add(ytdDL);
            ytdND = lists.get(i).get(1).getYtdN().add(ytdND);
            ytdDD = lists.get(i).get(1).getYtdD().add(ytdDD);
        }
        incomeBudgetVos.get(0).setYtdN(ytdNL);
        incomeBudgetVos.get(0).setYtdD(ytdDL);
        incomeBudgetVos.get(0).setRate(
                incomeBudgetVos.get(0).getYtdN()
                        .divide(incomeBudgetVos.get(0).getYtdD(),2,BigDecimal.ROUND_HALF_UP)
        );
        incomeBudgetVos.get(1).setYtdN(ytdND);
        incomeBudgetVos.get(1).setYtdD(ytdDD);
        incomeBudgetVos.get(1).setRate(
                incomeBudgetVos.get(1).getYtdN()
                        .divide(incomeBudgetVos.get(1).getYtdD(),2,BigDecimal.ROUND_HALF_UP)
        );
        result.add(incomeBudgetVos);
        return result;*/
        return null;
    }

   /* @Override
    public List<List<IncomeBudgetVo>> queryComLiabilitiesRate(AcceptVo myData) {
        List<List<IncomeBudgetVo>> listNow = this.queryComLiabilitiesSum(myData);
        String s = Integer.parseInt(myData.getPeriodCode()) - 1 + "";
        myData.setPeriodCode(s);
        List<List<IncomeBudgetVo>> listBefore = this.queryComLiabilitiesSum(myData);
        for (int i = 0; i < listNow.size(); i++){
            for(int j = 0 ; j < 2; j++){
                listNow.get(i).get(j).setRate(
                        listNow.get(i).get(j).getRate()
                        .divide(listBefore.get(i).get(j).getRate())
                        .subtract(BigDecimal.valueOf(1))
                );
            }
        }
        return listNow;
    }*/

}

