package com.deloitte.services.dss.finance.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.FinCompanyVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.services.dss.finance.mapper.FinExpenditureMapper;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import com.deloitte.services.dss.finance.service.IFinExpenditureSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Author : chitose
 * @Date : Create in 2019-04-17
 * @Description :  FinEtlPretreatment服务实现类
 * @Modified :
 */
@Service
@Transactional
public class FinExpenditureServiceImpl extends ServiceImpl implements IFinExpenditureSerivice {

    @Autowired
    private FinExpenditureMapper finExpenditureMapper;

    @Autowired
    private IFinCompanyService iFinCompanyService;
    @Autowired
    private IFinExpenditureSerivice finExpenditureSerivice;

    @Override
    public List<IncomeBudgetVo> selectTotalExp(Map param) {
        Map map = new Hashtable<String,String>();
        map.put("periodCode",param.get("month"));
        map.put("indexCode",param.get("indexCode"));
        List<String> comCodeList = (List<String>) param.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            String comCode= "";
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                if(i == 0){
                    comCode = "('"+comCode+finCompanyVos.get(i).getComCode()+"'";
                }else{
                    comCode = comCode+",'"+finCompanyVos.get(i).getComCode()+"'";
                }
            }
            map.put("comCode",comCode+")");
            return  finExpenditureMapper.selectTotalExp(map);

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
            return  finExpenditureMapper.selectTotalExp(map);
        }
        /*if(null != map.get("comCode")){
            List<String >  comCodeList = (List<String> )map.get("comCode");
            if (null != comCodeList && comCodeList.size() > 0 ) {
                String comCode= "";
                for(int i = 0 ; i < comCodeList.size(); i++){
                    if(i == 0){
                        comCode = "('"+comCode+comCodeList.get(i)+"'";
                    }else{
                        comCode = comCode+",'"+comCodeList.get(i)+"'";
                    }
                }
                map.put("comCode",comCode+")");
            }else{
                String comCode= "";
                for(int i = 0; i < comCodeList.size(); i++){
                    if(i == 0){
                        comCode = "('"+comCodeList.get(i)+"'";
                    }else{
                        comCode = comCode+",'"+comCodeList.get(i)+"'";
                    }
                }
                map.put("comCode",comCode+")");
            }
        }*/
        /*List<IncomeBudgetVo> incomeBudgetVos = finExpenditureMapper.selectTotalExp(map);
        return incomeBudgetVos;*/
    }

    @Override
    public List<List<IncomeBudgetVo>> selectComExp(AcceptVo acceptVo) {
        List<List<IncomeBudgetVo>> lists = new ArrayList<List<IncomeBudgetVo>>();
        IncomeBudgetVo incomeBudgetVo = new IncomeBudgetVo();
        incomeBudgetVo.setPeriodCode(acceptVo.getPeriodCode());
        if(acceptVo.getComCode().isEmpty()){
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                incomeBudgetVo.setComCode(finCompanyVos.get(i).getComCode());
                List<IncomeBudgetVo> incomeBudgetVos1 = finExpenditureMapper.selectComExp(incomeBudgetVo);
                lists.add(incomeBudgetVos1);
            }
        }else {
            for(int i = 0; i < acceptVo.getComCode().size(); i++){
                incomeBudgetVo.setComCode(acceptVo.getComCode().get(i));
                List<IncomeBudgetVo> incomeBudgetVos = finExpenditureMapper.selectComExp(incomeBudgetVo);
                lists.add(incomeBudgetVos);
            }
        }
        return lists;
    }

    @Override
    public List<IncomeBudgetVo> selectExpMonth(Map param) {
        Map map = new HashMap();
        map.put("periodCodeNow",param.get("periodCodeNow"));
        map.put("periodCodeBefore",param.get("periodCodeBefore"));
        map.put("monthTotal",finExpenditureSerivice.selectMonth(param));
        List<String> comCodeList = (List<String>) param.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            String comCode= "";
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                if(i == 0){
                    comCode = "('"+comCode+finCompanyVos.get(i).getComCode()+"'";
                }else{
                    comCode = comCode+",'"+finCompanyVos.get(i).getComCode()+"'";
                }
            }
            map.put("comCode",comCode+")");
            return  finExpenditureMapper.selectExpMonth(map);

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
            return  finExpenditureMapper.selectExpMonth(map);
        }
    }

    @Override
    public List<List<IncomeBudgetVo>> selectComExpMonth(AcceptVo acceptVo) {
        List<List<IncomeBudgetVo>> lists = new ArrayList<List<IncomeBudgetVo>>();
        Map<String,String> map = new Hashtable<String,String>();
        map.put("periodCodeNow",acceptVo.getPeriodCode());
        String periodCodeBefore = Integer.parseInt(acceptVo.getPeriodCode().split("-")[0]) + "-1";
        map.put("periodCodeBefore",periodCodeBefore);
        if(null== acceptVo.getComCode() ||  acceptVo.getComCode() .size() == 0){
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                map.put("comCode",finCompanyVos.get(i).getComCode());
                List<IncomeBudgetVo> incomeBudgetVos1 = finExpenditureMapper.selectComExpMonth(map);
                lists.add(incomeBudgetVos1);
            }
        }else {
            for(int i = 0; i < acceptVo.getComCode().size(); i++){
                map.put("comCode",acceptVo.getComCode().get(i));
                List<IncomeBudgetVo> incomeBudgetVos = finExpenditureMapper.selectComExpMonth(map);
                lists.add(incomeBudgetVos);
            }
        }
        return lists;
    }

    @Override
    public List<IncomeBudgetVo> selectExpPerMonth(Map param) {
        Map map = new Hashtable<String,String>();
        map.put("periodCode",param.get("month"));
        map.put("indexCode",param.get("indexCode"));
        List<String> comCodeList = (List<String>) param.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            String comCode= "";
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                if(i == 0){
                    comCode = "('"+comCode+finCompanyVos.get(i).getComCode()+"'";
                }else{
                    comCode = comCode+",'"+finCompanyVos.get(i).getComCode()+"'";
                }
            }
            map.put("comCode",comCode+")");
            return  finExpenditureMapper.selectExpPerMonth(map);

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
            return  finExpenditureMapper.selectExpPerMonth(map);
        }
    }

    @Override
    public List<IncomeBudgetVo> expComPerMonth(Map param) {
        Map map = new Hashtable<String,String>();
        map.put("periodCodeNow",param.get("month"));
        String periodCodeBefore = Integer.parseInt(param.get("month").toString().split("-")[0]) + "-1";
        map.put("periodCodeBefore",periodCodeBefore);
        map.put("indexCode",param.get("indexCode"));
        map.put("monthTotal",finExpenditureSerivice.selectMonth(param));
        List<String> comCodeList = (List<String>) param.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            String comCode= "";
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                if(i == 0){
                    comCode = "('"+comCode+finCompanyVos.get(i).getComCode()+"'";
                }else{
                    comCode = comCode+",'"+finCompanyVos.get(i).getComCode()+"'";
                }
            }
            map.put("comCode",comCode+")");
            return  finExpenditureMapper.expComPerMonth(map);

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
            return  finExpenditureMapper.expComPerMonth(map);
        }
    }

    @Override
    public List<IncomeBudgetVo> selectExpComRate(Map param) {
        Map map = new Hashtable<String,String>();
        map.put("periodCodeNow",param.get("month"));
        String periodCodeBefore = Integer.parseInt(param.get("month").toString().split("-")[0]) + "-1";
        map.put("periodCodeBefore",periodCodeBefore);
        map.put("indexCode",param.get("indexCode"));
        map.put("monthTotal",selectMonth(param));
        List<String> comCodeList = (List<String>) param.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            String comCode= "";
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                if(i == 0){
                    comCode = "('"+comCode+finCompanyVos.get(i).getComCode()+"'";
                }else{
                    comCode = comCode+",'"+finCompanyVos.get(i).getComCode()+"'";
                }
            }
            map.put("comCode",comCode+")");
            return  finExpenditureMapper.selectExpComRate(map);

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
            return  finExpenditureMapper.selectExpComRate(map);
        }
    }

    @Override
    public List<IncomeBudgetVo> selectExpComRateY(Map param) {
        Map map = new Hashtable<String,String>();
        map.put("periodCode",param.get("month"));
        map.put("indexCode",param.get("indexCode"));
        List<String> comCodeList = (List<String>) param.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            String comCode= "";
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                if(i == 0){
                    comCode = "('"+comCode+finCompanyVos.get(i).getComCode()+"'";
                }else{
                    comCode = comCode+",'"+finCompanyVos.get(i).getComCode()+"'";
                }
            }
            map.put("comCode",comCode+")");
            return  finExpenditureMapper.selectExpComRateY(map);

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
            return  finExpenditureMapper.selectExpComRateY(map);
        }
    }

    @Override
    public List<IncomeBudgetVo> selectExpComGrowthX(Map param) {
        Map map = new Hashtable<String,String>();
        map.put("periodCode",param.get("month"));
        map.put("indexCode",param.get("indexCode"));
        List<String> comCodeList = (List<String>) param.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            String comCode= "";
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                if(i == 0){
                    comCode = "('"+comCode+finCompanyVos.get(i).getComCode()+"'";
                }else{
                    comCode = comCode+",'"+finCompanyVos.get(i).getComCode()+"'";
                }
            }
            map.put("comCode",comCode+")");
            return  finExpenditureMapper.selectExpComGrowthX(map);

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
            return  finExpenditureMapper.selectExpComGrowthX(map);
        }
    }

    @Override
    public List<IncomeBudgetVo> selectExpCarcle(Map param) {
        Map map = new Hashtable<String,String>();
        map.put("periodCode",param.get("month"));
        map.put("indexCode",param.get("indexCode"));
        List<String> comCodeList = (List<String>) param.get("comCode");
        if (null == comCodeList || (comCodeList != null && comCodeList.size() == 0)) {
            List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
            String comCode= "";
            for(int i = 0 ; i < finCompanyVos.size(); i++){
                if(i == 0){
                    comCode = "('"+comCode+finCompanyVos.get(i).getComCode()+"'";
                }else{
                    comCode = comCode+",'"+finCompanyVos.get(i).getComCode()+"'";
                }
            }
            map.put("comCode",comCode+")");
            return  finExpenditureMapper.selectExpCarcle(map);

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
            return  finExpenditureMapper.selectExpCarcle(map);
        }
    }

    @Override
    public String selectMonth(Map map) {
        String month = map.get("month").toString();
        String[] str = month.split("-");
        StringBuffer stringBuffer = new StringBuffer();
        if(str != null && str.length>0){
            int monthTotal = Integer.parseInt(str[1]);
            for(int i=1;i<=monthTotal;i++){
                if(i==1){
                    stringBuffer.append("('"+str[0]+"-"+i+"',");
                }else if(i==monthTotal){
                    stringBuffer.append("'"+str[0]+"-"+i+"')");
                }else {
                    stringBuffer.append("'"+str[0]+"-"+i+"',");
                }
            }
        }
        return stringBuffer.toString();
    }
}
