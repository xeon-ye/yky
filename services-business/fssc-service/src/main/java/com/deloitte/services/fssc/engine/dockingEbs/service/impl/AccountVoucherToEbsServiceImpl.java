package com.deloitte.services.fssc.engine.dockingEbs.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.fssc.engine.manual.vo.AvJudgementVo;
import com.deloitte.platform.api.fssc.engine.manual.vo.AvManualVoucherLineForm;
import com.deloitte.platform.api.fssc.engine.manual.vo.EbsReturnStatusVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.LocalDateTimeUtils;
import com.deloitte.services.fssc.business.bpm.mapper.ProcessMapper;
import com.deloitte.services.fssc.business.carryforward.entity.IncomeOfCarryForward;
import com.deloitte.services.fssc.business.carryforward.mapper.IncomeOfCarryForwardMapper;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.engine.automatic.entity.*;
import com.deloitte.services.fssc.engine.automatic.mapper.AvBaseElementMapper;
import com.deloitte.services.fssc.engine.automatic.mapper.AvLedgerUnitRelationMapper;
import com.deloitte.services.fssc.engine.automatic.mapper.AvVoucherLogicInfoMapper;
import com.deloitte.services.fssc.engine.automatic.service.*;
import com.deloitte.services.fssc.engine.dockingEbs.entity.*;
import com.deloitte.services.fssc.engine.dockingEbs.returnEntity.CrossValidationRuleReturnEbs;
import com.deloitte.services.fssc.engine.dockingEbs.service.IAccountVoucherToEbsService;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherHead;
import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherLine;
import com.deloitte.services.fssc.engine.manual.mapper.AvManualVoucherHeadMapper;
import com.deloitte.services.fssc.engine.manual.mapper.AvManualVoucherLineMapper;
import com.deloitte.services.fssc.engine.manual.service.IAvManualVoucherHeadService;
import com.deloitte.services.fssc.engine.manual.service.IAvManualVoucherLineService;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.collections.MapUtils;
import org.json.JSONObject;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Transactional
@Service
public class AccountVoucherToEbsServiceImpl implements IAccountVoucherToEbsService {
   @Autowired
    RestTemplate restTemplate;
    @Autowired
    IAvManualVoucherHeadService avManualVoucherHeadService;
    @Autowired
    IAvManualVoucherLineService avManualVoucherLineService;
    @Autowired
    AvVoucherLogicInfoMapper  avVoucherLogicInfoMapper;
    @Autowired
    IAvAccountLogicHeadService  avAccountLogicHeadService;
    @Autowired
    IAvAccountLogicLineService avAccountLogicLineService;

    @Autowired
    AvManualVoucherLineMapper avManualVoucherLineMapper;
    @Autowired
    AvManualVoucherHeadMapper avManualVoucherHeadMapper;
    @Autowired
    AvBaseElementMapper avBaseElementMapper;
    @Autowired
    public IAvDailyRatesService avDailyRatesService;
    @Autowired
    private FsscCommonServices commonServices;
    @Autowired
    private AvLedgerUnitRelationMapper avLedgerUnitRelationMapper;
    @Autowired
    private IAvLogicHeadLineDicService avLogicHeadLineDicService;
    @Autowired
    private ProcessMapper processMapper;
    @Autowired
    private IncomeOfCarryForwardMapper incomeOfCarryForwardMapper;

    @Value("${avToEbs}")
    private  String avToEbs;
    /**
     * EBS入总账之前COA段做交叉验证
     * @param url
     * @param ids
     * @return
     */
    @Override
    public EbsReturnStatusVo CrossValidationRuleSendToEsb(String url, List<Long> ids) {
        EbsReturnStatusVo entity = new EbsReturnStatusVo();
        String msg = "";
        List<AvCoaCrossValidationRuleToEbs> toEbsList = new ArrayList<AvCoaCrossValidationRuleToEbs>();
        List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
        Collection<AvManualVoucherHead> headList = avManualVoucherHeadService.listByIds(ids);
        for (AvManualVoucherHead h:headList){
            QueryWrapper<AvManualVoucherLine> queryWrapper = new QueryWrapper <AvManualVoucherLine>();
            queryWrapper.eq(AvManualVoucherLine.JE_HEADER_ID,h.getId());
            List<AvManualVoucherLine> lineList = avManualVoucherLineService.list(queryWrapper);
            for (AvManualVoucherLine i:lineList){
                Map<String, Object> map1 =  Maps.newLinkedHashMap();
                map1.put("SOURCE_HEAD_ID",h.getId()+"");
                map1.put("SOURCE_LINE_ID",i.getId()+"");
                map1.put("LEDGER_ID",h.getLedgerId()+"");
                map1.put("SEGMENT1",i.getSegment1()==null?"":i.getSegment1());
                map1.put("SEGMENT2",i.getSegment2()==null?"":i.getSegment2());
                map1.put("SEGMENT3",i.getSegment3()==null?"":i.getSegment3());
                map1.put("SEGMENT4",i.getSegment4()==null?"":i.getSegment4());
                map1.put("SEGMENT5",i.getSegment5()==null?"":i.getSegment5());
                map1.put("SEGMENT6",i.getSegment6()==null?"":i.getSegment6());
                map1.put("SEGMENT7",i.getSegment7()==null?"":i.getSegment7());
                map1.put("SEGMENT8",i.getSegment8()==null?"":i.getSegment8());
                map1.put("SEGMENT9",i.getSegment9()==null?"":i.getSegment9());
                map1.put("SEGMENT10",i.getSegment10()==null?"":i.getSegment10());
                map1.put("SEGMENT11",i.getSegment11()==null?"":i.getSegment11());
                map1.put("SEGMENT12",i.getSegment12()==null?"":i.getSegment12());
                map1.put("SEGMENT13",i.getSegment13()==null?"":i.getSegment13());
                map1.put("SEGMENT14",i.getSegment14()==null?"":i.getSegment14());
                map1.put("SEGMENT15",i.getSegment15()==null?"":i.getSegment15());
                map1.put("SEGMENT16",i.getSegment16()==null?"":i.getSegment16());
                map1.put("SEGMENT17",i.getSegment17()==null?"":i.getSegment17());
                map1.put("SEGMENT18",i.getSegment18()==null?"":i.getSegment18());
                map1.put("SEGMENT19",i.getSegment19()==null?"":i.getSegment19());
                map1.put("SEGMENT20",i.getSegment20()==null?"":i.getSegment20());
                listMaps.add(map1);
            }
        }

        try{
            url =avToEbs+"webservices/rest/validate/validate_itf/";
            //构建请求头
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            requestHeaders.setContentType(type);
            //使用base64进行加密
            String auth = "sysadmin" + ":" + "sysadmin";
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
            String authHeader = "Basic " + new String(encodedAuth);

            //把认证信息发到header中
            requestHeaders.add("Authorization", authHeader);
            Map<String,Object> map2 = new HashMap<String,Object>();
            map2.put("VALIDATE_RECORD",listMaps);
            Map<String,Object> map3 = new HashMap<String,Object>();
            map3.put("P_VALIDATE_TABLE",map2);
            Map<String,Object> map4 = new HashMap<String,Object>();
            map4.put("@xmlns",url);
            map4.put("Responsibility","");
            map4.put("RespApplication","");
            map4.put("SecurityGroup","");
            map4.put("NLSLanguage","");
            Map<String,Object> map5 = new HashMap<String,Object>();
            map5.put("@xmlns",url);
            map5.put("RESTHeader",map4);
            map5.put("InputParameters",map3);
            Map<String,Object> map6 = new HashMap<String,Object>();
            map6.put("VALIDATE_ITF_Input",map5);
            String objectToheader = JSONUtils.toJSONString(map6);
            //发送请求
            HttpEntity<String> requestEntity = new HttpEntity<String>(objectToheader, requestHeaders);
            String  backStr = restTemplate.postForEntity(url,requestEntity,String.class).getBody();//ebs返回结果
            log.info("测试EBS交叉验证结果"+backStr);
            //EBS返回结果处理
            JSONObject obj = new JSONObject(backStr);
            JSONObject OutputParameters =  (JSONObject)obj.get("OutputParameters");
            String X_RESPONSE_CODE = OutputParameters.get("X_RESPONSE_CODE").toString();
            if(!"SUCCESS".equals(X_RESPONSE_CODE)){
                JSONObject err = (JSONObject)OutputParameters.get("X_ERROR_RECORD");
                //返回单条数据
                if(err.get("X_ERROR_RECORD_ITEM").toString().substring(0,1).equals("{")){
                    JSONObject objerr = (JSONObject)err.get("X_ERROR_RECORD_ITEM");
                    String ERROR_MESSAGE = objerr.get("ERROR_MESSAGE").toString();
                    String SOURCE_HEAD_ID = objerr.get("SOURCE_HEAD_ID").toString();
                    String SOURCE_LINE_ID = objerr.get("SOURCE_LINE_ID").toString();
                    QueryWrapper<AvManualVoucherLine> queryWrapper = new QueryWrapper<AvManualVoucherLine>();
                    queryWrapper.eq(AvManualVoucherLine.JE_HEADER_ID,Long.parseLong(SOURCE_HEAD_ID));
                    queryWrapper.eq(AvManualVoucherLine.ID,Long.parseLong(SOURCE_LINE_ID));
                    List<AvManualVoucherLine> list = avManualVoucherLineService.list(queryWrapper);
                    if(list.size()>0){
                        AvManualVoucherLine line = new AvManualVoucherLine();
                        line.setJeHeaderId(Long.parseLong(SOURCE_HEAD_ID));
                        line.setId(Long.parseLong(SOURCE_LINE_ID));
                        line.setErrorMessage(ERROR_MESSAGE);
                        avManualVoucherLineService.saveOrUpdate(line);
                        msg = ERROR_MESSAGE;
                    }

                }else{//返回多条数据
                    JSONArray arr = err.getJSONArray("X_ERROR_RECORD_ITEM");
                    for(int i=0;i<arr.length();i++){
                        try{
                            JSONObject objerr = (JSONObject)arr.get(i);
                            String ERROR_MESSAGE = objerr.get("ERROR_MESSAGE").toString();
                            String SOURCE_HEAD_ID = objerr.get("SOURCE_HEAD_ID").toString();
                            String SOURCE_LINE_ID = objerr.get("SOURCE_LINE_ID").toString();
                            QueryWrapper<AvManualVoucherLine> queryWrapper = new QueryWrapper<AvManualVoucherLine>();
                            queryWrapper.eq(AvManualVoucherLine.JE_HEADER_ID,Long.parseLong(SOURCE_HEAD_ID));
                            queryWrapper.eq(AvManualVoucherLine.ID,Long.parseLong(SOURCE_LINE_ID));
                            List<AvManualVoucherLine> list = avManualVoucherLineService.list(queryWrapper);
                            if(list.size()>0){
                                AvManualVoucherLine line = new AvManualVoucherLine();
                                line.setJeHeaderId(Long.parseLong(SOURCE_HEAD_ID));
                                line.setId(Long.parseLong(SOURCE_LINE_ID));
                                line.setErrorMessage(ERROR_MESSAGE);
                                avManualVoucherLineService.saveOrUpdate(line);

                            }
                            msg += ERROR_MESSAGE+"/n";
                        }catch(Exception e){
                            log.error("交叉验证出错:"+e);
                            continue;
                        }

                    }
                }
                entity.setErrMsg(msg);
                entity.setStatus(false);

            }else{
                entity.setStatus(true);
            }

        }catch (Exception e){
            log.error("ebs项目错误"+e);
            entity.setStatus(false);
            entity.setErrMsg("推送到EBS的交叉验证出错！");
            return entity;
        }

        return entity;
    }
@Transactional
    public void reverseAccountForBusiness(String documentType,Long documentId,LocalDateTime defaultEffectiveDate){
        QueryWrapper<AvManualVoucherHead> queryWrapper = new QueryWrapper <AvManualVoucherHead>();
        queryWrapper.eq(AvManualVoucherHead.ACCOUNT_RESOURCE_TYPE_ID,documentId);
        queryWrapper.eq(AvManualVoucherHead.DOCUMENT_TYPE,documentType);
        queryWrapper.eq(AvManualVoucherHead.ACCRUAL_REV_FLAG,"N");
        List<AvManualVoucherHead> headList =  avManualVoucherHeadService.list(queryWrapper);
        UserVo userVo = commonServices.getCurrentUser();
        String  name =userVo.getName();
        if(headList.size()>0){
            AvManualVoucherHead headEntity = headList.get(0);
            Long id = headEntity.getId();
            //查询是否单据被结转
/*            List<IncomeOfCarryForward> forwardList =  incomeOfCarryForwardMapper.hasCarryByManualId(documentType,documentId);
            if(forwardList.size()>0){
                throw new FSSCException(FsscErrorType.AV_MANUAL_HAS_CARRAY);
            }*/
            QueryWrapper<AvManualVoucherLine> queryWrapperLine = new QueryWrapper <AvManualVoucherLine>();
            queryWrapperLine.eq(AvManualVoucherLine.JE_HEADER_ID,headEntity.getId());
            List<AvManualVoucherLine> oldLineList = avManualVoucherLineService.list(queryWrapperLine);
            //新生成冲销单据凭证

            AvManualVoucherHead headNewEntity = headEntity;
            headNewEntity.setId(null);
            headNewEntity.setDefaultEffectiveDate(defaultEffectiveDate);//冲销日期-》会计日期
            headNewEntity.setDocumentNum(null);//单据编码
            headNewEntity.setName(headNewEntity.getName()+"(冲销)");//凭证名
            headNewEntity.setTotalOriginalAmount(headEntity.getTotalOriginalAmount().multiply(new BigDecimal(-1)));//原币金额为负数
            headNewEntity.setTotalStandardAmount(headEntity.getTotalStandardAmount().multiply(new BigDecimal(-1)));//本币金额为负数
            headNewEntity.setPostStatus("N");//过账状态
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM");
            headNewEntity.setPeriodName(dateFormat.format(defaultEffectiveDate));
            headNewEntity.setDocSeqNum(null);//凭证编码-》ebs回传回来的
            headNewEntity.setAccrualRevFlag("N");//是否被冲销
            headNewEntity.setFromRecurringHeaderId(id);//标记原单据ID
            headNewEntity.setPostedDate(null);//过账时间
            headNewEntity.setCreateUserName(name);
            headNewEntity.setRequestId(null);
            headNewEntity.setCreateTime(LocalDateTime.now());
            headNewEntity.setUpdateTime(null);
            headNewEntity.setDocumentStatus(FsscEums.IN_ACCOUNTING.getValue());
            headNewEntity.setUpdateBy(null);
            headNewEntity.setAccountStatus("Y");
            avManualVoucherHeadService.saveOrUpdate(headNewEntity);
            for(AvManualVoucherLine p:oldLineList){
                p.setJeHeaderId(headNewEntity.getId());
                p.setId(null);
                p.setDocumentNum(headNewEntity.getDocumentNum());
                p.setErrorMessage(null);
                p.setAccountedCr(p.getAccountedCr().multiply(new BigDecimal(-1)));
                p.setAccountedDr(p.getAccountedDr().multiply(new BigDecimal(-1)));
                p.setEnteredCr(p.getEnteredCr().multiply(new BigDecimal(-1)));
                p.setEnteredDr(p.getEnteredDr().multiply(new BigDecimal(-1)));
            }
            avManualVoucherLineService.saveBatch(oldLineList);
            //原单据信息更新

            AvManualVoucherHead headEntity2 = new  AvManualVoucherHead();
            headEntity2.setAccrualRevFlag("Y");
            headEntity2.setId(id);
            headEntity2.setDocumentStatus(FsscEums.CHARGE_AGAINSTING.getValue());
            headEntity2.setAccrualRevJeHeaderId(headNewEntity.getId());//更新原表数据中配置冲销凭证Id
            avManualVoucherHeadService.updateById(headEntity2);
            //上传到EBS校验和上传凭证
            List<Long> jeHeadIds = new ArrayList<Long>();
            jeHeadIds.add(headNewEntity.getId());
            if(jeHeadIds.size()>0){
                String  url ="";
               /* headEntity.setDefaultEffectiveDate(defaultEffectiveDate);//先保存会计日期
                avManualVoucherHeadService.saveOrUpdate(headEntity);*/
                //对于业务单据生成的会计凭证,先进行会计凭证的交叉验证规则验证，完成之后在能提交会计凭证到ebs;
                EbsReturnStatusVo msg= CrossValidationRuleSendToEsb(url,jeHeadIds);
                if(msg.getStatus()){
                     sendAccountVoucherSendToEbs(url,jeHeadIds);
                }else{
                    throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,msg.getErrMsg());
                }

            }
        }
    }

    /**
     * 传送会计凭证到BES系统
     * @param url
     * @param jeHeaderIds
     */
   public EbsReturnStatusVo sendAccountVoucherSendToEbs(String url,List<Long> jeHeaderIds){
       EbsReturnStatusVo ebsVoEntity = new EbsReturnStatusVo();
       UserVo userVo = commonServices.getCurrentUser();
       List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
        try{
            List<AvAccountInfoToEbs> ebsList = new ArrayList<AvAccountInfoToEbs>();
            for (Long jeHeaderId:jeHeaderIds){
                QueryWrapper<AvManualVoucherHead> queryWrapperHead = new QueryWrapper <AvManualVoucherHead>();
                queryWrapperHead.eq(AvManualVoucherHead.ID,jeHeaderId);
                AvManualVoucherHead entity = avManualVoucherHeadService.getOne(queryWrapperHead);
                if(entity!=null){
                    entity.setPostedBy(Long.parseLong(userVo.getId()));//添加入账人ID
                    entity.setPostedUserName(userVo.getName());//添加入账人名称
                    entity.setApprovedBy(Long.parseLong(userVo.getId()));//复核人ID
                    entity.setApprovedUserName(userVo.getName());//复核人姓名
                    entity.setDocumentStatus(FsscEums.IN_ACCOUNTING.getValue());
                    avManualVoucherHeadService.updateById(entity);
                    if(entity.getDocumentType().equals(FsscTableNameEums.AV_MANUAL_VOUCHER_HEAD.getValue())){
                        processMapper.updateDocumentStatus(entity.getId(),entity.getDocumentType(), FsscEums.IN_ACCOUNTING.getValue());//正常单据显示入账中
                    }else{
                        processMapper.updateDocumentStatus(entity.getAccountResourceTypeId(),entity.getDocumentType(), FsscEums.IN_ACCOUNTING.getValue());//正常单据显示入账中
                    }
                    QueryWrapper<AvManualVoucherLine> queryWrapperLine = new QueryWrapper <AvManualVoucherLine>();
                    queryWrapperLine.eq(AvManualVoucherLine.JE_HEADER_ID,jeHeaderId);
                    List<AvManualVoucherLine> lineList = avManualVoucherLineService.list(queryWrapperLine);
                    for(AvManualVoucherLine line: lineList){
                        Map<String, Object> map1 =  Maps.newLinkedHashMap();
                        AvAccountInfoToEbs ebsEntity = new AvAccountInfoToEbs();
                        map1.put("SOURCE_BATCH_ID",1);
                        map1.put("SOURCE_HEAD_ID",jeHeaderId+"");
                        map1.put("SOURCE_LINE_ID",line.getId()+"");
                        map1.put("LEDGER_ID",entity.getLedgerId()+"");
                        map1.put("PERIOD_NAME",entity.getDefaultEffectiveDate().format(DateTimeFormatter.ofPattern("yyyy-MM"))+"");
                        map1.put("ACCOUNTING_DATE",entity.getDefaultEffectiveDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));//LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                        map1.put("CURRENCY_CODE",entity.getCurrencyCode()+"");
                        map1.put("DATE_CREATED",entity.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                        map1.put("ACTUAL_FLAG","A");
                        map1.put("JE_CATEGORY_NAME",entity.getJeCategory()+"");
                        map1.put("JE_SOURCE_NAME","报账系统");
                        //if(entity.getCurrencyConversionType().equals("CNY"))
                        if(entity.getCurrencyConversionDate()==null){
                            map1.put("CONVERSION_DATE",entity.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        }else{
                            map1.put("CONVERSION_DATE",entity.getCurrencyConversionDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                        }
                        map1.put("CONVERSION_TYPE","Corporate");
                        map1.put("CONVERSION_RATE",entity.getCurrencyConversionRate());
                        map1.put("SEGMENT1",line.getSegment1());
                        map1.put("SEGMENT2",line.getSegment2());
                        map1.put("SEGMENT3",line.getSegment3());
                        map1.put("SEGMENT4",line.getSegment4());
                        map1.put("SEGMENT5",line.getSegment5());
                        map1.put("SEGMENT6",line.getSegment6());
                        map1.put("SEGMENT7",line.getSegment7());
                        map1.put("SEGMENT8",line.getSegment8());
                        map1.put("SEGMENT9",line.getSegment9());
                        map1.put("SEGMENT10",line.getSegment10());
                        map1.put("SEGMENT11",line.getSegment11());
                        map1.put("SEGMENT12",line.getSegment12());
                        map1.put("SEGMENT13",line.getSegment13());
                        map1.put("SEGMENT14",line.getSegment14());
                        map1.put("SEGMENT15",line.getSegment15());
                        map1.put("SEGMENT16",line.getSegment16());
                        map1.put("SEGMENT17",line.getSegment17());
                        map1.put("SEGMENT18",line.getSegment18());
                        map1.put("SEGMENT19",line.getSegment19());
                        map1.put("SEGMENT20",line.getSegment20());
                        map1.put("ENTERED_DR",line.getEnteredDr());
                        map1.put("ENTERED_CR",line.getEnteredCr());
                        map1.put("ACCOUNTED_DR",line.getAccountedDr());
                        map1.put("ACCOUNTED_CR",line.getAccountedCr());
                        map1.put("REFERENCE1","");
                        map1.put("REFERENCE2","");
                        map1.put("REFERENCE3","");
                        map1.put("REFERENCE4",entity.getName());
                        map1.put("REFERENCE5",entity.getDescription());
                        map1.put("REFERENCE6","");
                        map1.put("REFERENCE7","");
                        map1.put("REFERENCE8",line.getLineTypeCode());
                        map1.put("REFERENCE9","");
                        map1.put("REFERENCE10",line.getDescription());
                        listMaps.add(map1);
                    }
                }
            }
        }catch (Exception e){
            log.error("传送会计凭证到EBS出错"+e);
            throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_SEND_TO_EBS_ERROR.getMsg());
        }
       try{
           url = avToEbs+"webservices/rest/create_gl/voucher_interface/";
           //构建请求头
           HttpHeaders requestHeaders = new HttpHeaders();
           requestHeaders.setContentType(MediaType.APPLICATION_JSON);
           MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
           requestHeaders.setContentType(type);
           //使用base64进行加密
           String auth = "sysadmin" + ":" + "sysadmin";
           byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
           String authHeader = "Basic " + new String(encodedAuth);

           //把认证信息发到header中
           requestHeaders.add("Authorization", authHeader);
           Map<String,Object> map2 = new HashMap<String,Object>();
           map2.put("VOUCHER_RECORD",listMaps);
           Map<String,Object> map3 = new HashMap<String,Object>();
           map3.put("P_VOUCHER_TABLE",map2);
           Map<String,Object> map4 = new HashMap<String,Object>();
           map4.put("@xmlns",url);
           map4.put("Responsibility","");
           map4.put("RespApplication","");
           map4.put("SecurityGroup","");
           map4.put("NLSLanguage","");
           Map<String,Object> map5 = new HashMap<String,Object>();
           map5.put("@xmlns",url);
           map5.put("RESTHeader",map4);
           map5.put("InputParameters",map3);
           Map<String,Object> map6 = new HashMap<String,Object>();
           map6.put("VOUCHER_INTERFACE_Input",map5);
           String objectToheader = JSONUtils.toJSONString(map6);
           //发送请求

           HttpEntity<String> requestEntity = new HttpEntity<String>(objectToheader, requestHeaders);
           String  backStr = restTemplate.postForEntity(url,requestEntity,String.class).getBody();
           JSONObject obj = new JSONObject(backStr);
           JSONObject OutputParameters =  (JSONObject)obj.get("OutputParameters");
           String X_RESPONSE_CODE = OutputParameters.get("X_RESPONSE_CODE").toString();
           if(!"SUCCESS".equals(X_RESPONSE_CODE)){
               String msg = "";
              // JSONArray err = (JSONArray)OutputParameters.get("X_ERROR_RECORD");
               if(OutputParameters.get("X_ERROR_RECORD").toString().equals("null")){
                   msg = OutputParameters.get("X_RESPONSE_MSG").toString();
               }else{
                   JSONObject err = (JSONObject)OutputParameters.get("X_ERROR_RECORD");

                   //返回单条数据
                   if(err.get("X_ERROR_RECORD_ITEM").toString().substring(0,1).equals("{")){
                       JSONObject objerr = (JSONObject)err.get("X_ERROR_RECORD_ITEM");
                       String ERROR_MESSAGE = objerr.get("ERROR_MESSAGE").toString();
                       String SOURCE_HEAD_ID = objerr.get("SOURCE_HEAD_ID").toString();
                       String SOURCE_LINE_ID = objerr.get("SOURCE_LINE_ID").toString();
                       msg =ERROR_MESSAGE;
                   }else{//返回多条数据
                       JSONArray arr = err.getJSONArray("X_ERROR_RECORD_ITEM");
                       for(int i=0;i<arr.length();i++){
                           try{
                               JSONObject objerr = (JSONObject)arr.get(i);
                               String ERROR_MESSAGE = objerr.get("ERROR_MESSAGE").toString();
                               String SOURCE_HEAD_ID = objerr.get("SOURCE_HEAD_ID").toString();
                               String SOURCE_LINE_ID = objerr.get("SOURCE_LINE_ID").toString();
                               msg += ERROR_MESSAGE;
                           }catch(Exception e){
                               log.error("解析会计凭证回传信息出错:"+e);
                               continue;
                           }

                       }
                   }
               }

               ebsVoEntity.setErrMsg(msg);
               ebsVoEntity.setStatus(false);
           }else{
               ebsVoEntity.setStatus(true);
           }

       }catch (Exception e){
           log.error("ebs项目错误"+e);
           throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_SEND_TO_EBS_ERROR.getMsg());
       }
       return ebsVoEntity;
    }

    /**
     * 删除之前的预制凭证
     * @param documentType
     * @param documentId
     */
    private void deleteOldDocumentType(String documentType,Long documentId){
        QueryWrapper<AvManualVoucherHead> oldWrapper = new QueryWrapper<AvManualVoucherHead>();
        oldWrapper.eq(AvManualVoucherHead.DOCUMENT_TYPE,documentType);
        oldWrapper.eq(AvManualVoucherHead.ACCOUNT_RESOURCE_TYPE_ID,documentId);
        List<AvManualVoucherHead> headList =avManualVoucherHeadMapper.selectList(oldWrapper);
        for(AvManualVoucherHead p:headList){
           QueryWrapper<AvManualVoucherLine> lineWrapper = new QueryWrapper<AvManualVoucherLine>();
            lineWrapper.eq(AvManualVoucherLine.JE_HEADER_ID,p.getId());
            avManualVoucherLineMapper.delete(lineWrapper);
        }
        if(headList.size()>0){
            avManualVoucherHeadMapper.delete(oldWrapper);
        }
    }

    /**
     * 会计引擎逻辑设置
     * @param documentType 该单据的类型
     * @param documentId 该单据的Id
     * @return
     */
    @Transactional
    public String generatePrefabricatedCredentials(String documentType,Long documentId){
       try{
           deleteOldDocumentType(documentType,documentId);
           DeptVo deptVo = commonServices.getCurrentDept();
           UserVo userVo = commonServices.getCurrentUser();
           String unitCode = deptVo.getDeptCode();//获取得到该用户下的单位编码
           Map<String,Object> origDocument = avManualVoucherLineMapper.selectMapLimitOne(documentType,documentId);
           if(origDocument.containsKey("PROJECT_UNIT_CODE")){//如果单据上面有承担单位，这时候需要根据该单据的承担单位来获取到账薄信息
               if(origDocument.get("PROJECT_UNIT_CODE").toString()!=null){
                   unitCode = origDocument.get("PROJECT_UNIT_CODE").toString();
               }
           }
           List<AvVoucherLogicInfo> logicList = avVoucherLogicInfoMapper.getLedgerInfo(documentType,unitCode);//获取得到该公司下面的凭证逻辑设置

           if(logicList.size()>0){//只能确保一条会计引擎凭证逻辑。
                 QueryWrapper<AvAccountLogicHead> queryWrapperHead = new QueryWrapper <AvAccountLogicHead>();
               queryWrapperHead.eq(AvAccountLogicHead.LOGIC_ID,logicList.get(0).getId());
                 QueryWrapper<AvAccountLogicLine> queryWrapperLine = new QueryWrapper <AvAccountLogicLine>();
               queryWrapperLine.eq(AvAccountLogicLine.LOGIC_ID,logicList.get(0).getId());
                 List<AvAccountLogicHead> headList = avAccountLogicHeadService.list(queryWrapperHead);
                 List<AvAccountLogicLine> lineList = avAccountLogicLineService.list(queryWrapperLine);
                 AvManualVoucherHead headEntity = new AvManualVoucherHead();
                 List<AvManualVoucherLine> lineEntityList = new ArrayList<>();

                 if(headList.size()>0){
                     for(AvAccountLogicHead p:headList){//凭证头信息解析
                         String judgementCondition = p.getJudgeCondition();
                         List<AvJudgementVo> judementVoList = parseJsonJudgement(judgementCondition);
                         if(getJudgementStatus(documentType,judementVoList,documentId )){
                             getValue(parseField(p),documentType, documentId, headEntity,unitCode);
                         }
                     }
                 }else{
                     throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_AUTO_LOGIC_NOVALUE_ERROR.getMsg());

                 }
                 if(lineList.size()>0){
                     for(AvAccountLogicLine p:lineList){//凭证行信息解析
                         String judgementCondition = p.getJudgeCondition();
                         List<AvJudgementVo> judementVoList = parseJsonJudgement(judgementCondition);//获取得到判断逻辑 存在多个判断逻辑，
                         AvManualVoucherLine lineEntity = new AvManualVoucherLine();
                         lineEntity.setVoucherType(p.getDebitCreditFrom());
                         lineEntity.setLineTypeCode(p.getEntryTypeFrom());
                         QueryWrapper<AvLogicHeadLineDic> dicQueryWrapper = new QueryWrapper<>();
                         dicQueryWrapper.eq(AvLogicHeadLineDic.ID,p.getAvDicId());
                         AvLogicHeadLineDic dicEntity = avLogicHeadLineDicService.getOne(dicQueryWrapper);
                         if(getJudgementStatus(documentType,judementVoList,documentId )){
                             getLineValue(parseLineField(p),documentType, documentId,lineEntity, headEntity,dicEntity,lineEntityList);
                         }
                     }
                 }else{
                     throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_AUTO_LOGIC_NOVALUE_ERROR.getMsg());
                 }
               avManualVoucherHeadService.saveOrUpdate(headEntity);

               BigDecimal entered = new BigDecimal(0);
               //判断借贷是否平
               judgementLineInfo(lineEntityList);
               for (AvManualVoucherLine p:lineEntityList){
                   BigDecimal entered1 = new BigDecimal(0);
                   p.setJeHeaderId(headEntity.getId());
                   p.setDocumentNum(headEntity.getDocumentNum());
                   entered =entered.add(p.getEnteredCr());
                   avManualVoucherLineService.saveOrUpdate(p);
               }
               headEntity.setTotalOriginalAmount(entered);
               headEntity.setTotalStandardAmount(entered.multiply(headEntity.getCost()));
               headEntity.setName(headEntity.getCreateUserName()+"_"+headEntity.getJeCategory()+"_"+origDocument.get("DOCUMENT_NUM").toString());//新增获取凭证名
               avManualVoucherHeadService.updateById(headEntity);
               //交叉验证提前到凭证生成时
               List<Long> jeHeaders = new ArrayList<>();
               jeHeaders.add(headEntity.getId());
               CrossValidationRuleSendToEsb("", jeHeaders);
           }else{
               throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_AUTO_LOGIC_NOVALUE_ERROR.getMsg());
           }

       }catch (Exception e){
           log.error("生成预制凭证出错"+e);
           throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_AUTO_LOGIC_NOVALUE_ERROR.getMsg());
       }
       return "";
    }


    //主要判断凭证行信息的借贷相等
    private  void judgementLineInfo( List<AvManualVoucherLine>lineFormList ){
        if(lineFormList.size()<=1){
            //凭证行信息不能少于2行数据（至少是一借一贷）
            throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_MANUALVOUCHERhEAD_IS_NULL.getMsg());
        }else{
            BigDecimal cJTotal = new BigDecimal("0");//财-借
            BigDecimal cDTotal = new BigDecimal("0");//财-贷
            BigDecimal yJTotal = new BigDecimal("0");//预-借
            BigDecimal yDTotal = new BigDecimal("0");//预-贷
            for(int i=0;i<lineFormList.size();i++){
                AvManualVoucherLine p = lineFormList.get(i);
                /*if(p.getEnteredCr().compareTo(BigDecimal.ZERO)>0&&p.getEnteredDr().compareTo(BigDecimal.ZERO)>0){//主要判断借贷是否都是大于0
                    throw new FSSCException(FsscErrorType.AV_MANUALVOUCHERhEAD_IS_GT_ZERO);
                }*/
                if(p.getLineTypeCode().equals(FsscEums.AV_ACCOUNT_CAI.getValue())){//财
                    cJTotal= cJTotal.add(p.getEnteredDr());
                    cDTotal =cDTotal.add(p.getEnteredCr());
                }
                if(p.getLineTypeCode().equals(FsscEums.AV_ACCOUNT_YU.getValue())){//预
                    yJTotal=yJTotal.add(p.getEnteredDr());
                    yDTotal=yDTotal.add(p.getEnteredCr());
                }
            }
            if(cJTotal.compareTo(cDTotal)!=0){
                throw new FSSCException(FsscErrorType.AV_MANUALVOUCHERhEAD_IS_JD_NO_EQUAL);
            }
            if(yJTotal.compareTo(yDTotal)!=0){
                throw new FSSCException(FsscErrorType.AV_MANUALVOUCHERhEAD_IS_JD_NO_EQUAL);
            }
        }


    }

    /**
     * 凭证逻辑里面的凭证逻辑判断
     * @param documentType
     * @param judementVoList
     * @param documentId
     * @return
     */
    private  boolean getJudgementStatus(String documentType,List<AvJudgementVo>judementVoList,Long documentId ){
        Boolean status =false;
        String judgementAll="";
        Boolean statusCondition =false;
        String table = documentType;
        String idType ="ID";
        if(judementVoList.size()>0){
            Map<String,Integer> map = new HashMap<String,Integer>();
            for(int i=0;i<judementVoList.size();i++){//循环判断逻辑
                AvJudgementVo entity = new AvJudgementVo();
                entity = judementVoList.get(i);
                String[] field = entity.getField().split("\\.");
                String judgement = swatchJudgement(field[1],entity.getJudgment(),entity.getValue());
                if(!documentType.equals(field[0])){
                    table = field[0];
                    idType = "DOCUMENT_ID";
                }
                map.put(field[0],1);
                if(i==1){//如果达到两条数据
                    judgementAll =judgementAll+" "+entity.getRetaltion()+" "+judgement;
                }else{
                    judgementAll =judgement;
                }
            }
            judgementAll =judgementAll+" AND " + idType+" = "+ documentId;
            List<Map<String,Object>> mapList = new ArrayList<>();
            if(map.size()==2){
                String sql = "select H.* from " + documentType +" H left join " +table +" L ON H.ID = L.DOCUMENT_ID AND L.DOCUMENT_TYPE ='" + documentType+"' " +" WHERE "+ judgementAll ;
                mapList = avManualVoucherLineMapper.getMapByJudgement(sql);
            }else{
                mapList = avManualVoucherLineMapper.getJudgement(judgementAll,table);
            }

            if(mapList.size()>0){
                statusCondition =true;
            }
        }else{
            // Map<String,AvPraseFieldInfo> map  =parseField(p);
            statusCondition =true;
        }
        return statusCondition;
    }

    /**
     * 计算判断条件里面的
     * @param field
     * @param judgement
     * @param value
     * @return
     */
    private String swatchJudgement(String field,String judgement,String value){
        String sc = "";
        switch (judgement){
            case "eq":
                sc = field+"="+"'"+value+"' ";
                break;
            case "ne":
                sc =field+ "!="+"'"+value+"' ";
                break;
            case "gt":
                sc = field+">"+"'"+value+"' ";
                break;
            case "lt":
                sc = "<"+"'"+value+"' ";
                break;
            case "ge":
                sc =field+ ">="+"'"+value+"' ";
                break;
            case "le":
                sc = field+"<="+"'"+value+"' ";
                break;
            case "in":
                sc =field+ " in "+value +" ";
                break;
            case "notIn":
                sc =field+ " not in " +value+" ";
                break;
            case "isNull":
                sc = field +" IS NULL ";
                break;
            case "isNotNull":
                sc = field +" IS NOT NULL ";
                break;
            default:
                break;
        }
        return sc;
    }

    /**
     * 计算当前业务单据的汇率
     * @param localDateTime
     * @param fromCurrency
     * @return
     */
    private AvDailyRates getRate(String localDateTime,String fromCurrency){
        AvDailyRates entity = new AvDailyRates();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(localDateTime,df);
        QueryWrapper<AvDailyRates> queryWrapper = new QueryWrapper <AvDailyRates>();
        queryWrapper.eq(AvDailyRates.FROM_CURRENCY,fromCurrency);
        LocalDateTime today_start = LocalDateTime.of(ldt.toLocalDate(), LocalTime.MIN);//获取当前选取时间的当天开始时间
        LocalDateTime today_End = LocalDateTime.of(ldt.toLocalDate(), LocalTime.MAX);//获取当前选取时间的当天结束时间
        queryWrapper.ge(AvDailyRates.CONVERSION_DATE,today_start);
        queryWrapper.le(AvDailyRates.CONVERSION_DATE,today_End);
        entity = avDailyRatesService.getOne(queryWrapper);
        if(entity==null){
            throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_AUTO_LOGIC_NOVALUE_ERROR.getMsg());
        }
        return entity;
    }

    /**
     * 计算得到会计凭证头信息
     * @param keyMap
     * @param documentType
     * @param documentId
     * @param headEntity
     */
    @Transactional
    private void getValue(Map<String,AvPraseFieldInfo> keyMap,String documentType,Long documentId,AvManualVoucherHead headEntity,String unitCode){
        Object value ="" ;
        DeptVo deptVo = commonServices.getCurrentDept();
        UserVo userVo = commonServices.getCurrentUser();
        for (String key : keyMap.keySet()) {
            AvPraseFieldInfo entity = keyMap.get(key);
            if(key.equals("typeFrom")){//业务类别
                headEntity.setJeCategory(getHeadField(entity,documentId,documentType).toString());
            }else if(key.equals("numFrom")){//附件数量
                String count = getHeadField(entity,documentId,documentType).toString();
                headEntity.setAttachCount(Integer.parseInt(count.equals("")?"0":count));
            }else if(key.equals("voucherFrom")){//币种
                String CurrencyCode = getHeadField(entity,documentId,documentType).toString();
                headEntity.setCurrencyCode(CurrencyCode);
                if(!"CNY".equals(CurrencyCode)){
                    Map<String,Object> mapForSelect = avManualVoucherLineMapper.selectMapLimitOne(documentType,documentId);//主要选取当前业务单据上面的创建时间
                    if(mapForSelect.size()>0){
                        AvDailyRates  rateEntity =  getRate(mapForSelect.get("CREATE_TIME").toString(),CurrencyCode);
                        headEntity.setCost(rateEntity.getConversionRate());
                        headEntity.setCurrencyConversionRate(rateEntity.getConversionRate());
                    }
                }else{
                    headEntity.setCost(new BigDecimal(1));
                    headEntity.setCurrencyConversionRate(new BigDecimal(1));
                }
            }else if(key.equals("rateFrom")){//汇率
               // BigDecimal cost = new BigDecimal( getHeadField(entity,documentId,documentType).toString() );
               // headEntity.setCost(cost);
            }else if(key.equals("headFrom")){//头说明
                headEntity.setDescription(getHeadField(entity,documentId,documentType).toString());
            }
        }
        headEntity.setDocumentStatus(FsscEums.APPROVED.getValue());
        headEntity.setUnitId(Long.parseLong(deptVo.getDeptId()));
        headEntity.setUnitName(deptVo.getDeptName());
        headEntity.setPostStatus("N");
        headEntity.setJeSource(documentType);
        headEntity.setAccrualRevFlag("N");
        headEntity.setCreateBy(Long.parseLong(userVo.getId()));
        headEntity.setCreateTime(LocalDateTime.now());
        headEntity.setCreateUserName(userVo.getName());
        headEntity.setDocumentType(documentType);
        headEntity.setAccountResourceTypeId(documentId);
        headEntity.setCurrencyConversionType("CNY");
        headEntity.setAccountStatus("N");//预制凭证
        QueryWrapper<AvLedgerUnitRelation> queryWrapper = new QueryWrapper <AvLedgerUnitRelation>();
        queryWrapper.eq(AvLedgerUnitRelation.BALANCE_CODE,unitCode);
        AvLedgerUnitRelation aurEntity =  avLedgerUnitRelationMapper.selectOne(queryWrapper);
        if(aurEntity!=null){
            headEntity.setLedgerId(aurEntity.getLedgerId());
        }else{
            throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_AUTO_LOGIC_NOLEDGER_ERROR.getMsg());
        }
    }


    private void getLineValue(Map<String,AvPraseFieldInfo> keyMap,String documentType,Long documentId,AvManualVoucherLine lineEntity,AvManualVoucherHead headEntity,AvLogicHeadLineDic dicEntity, List<AvManualVoucherLine> lineList){
        Object value ="" ;
        DeptVo deptVo = commonServices.getCurrentDept();
        UserVo userVo = commonServices.getCurrentUser();
        List<Map<String,Object>> queryList = new ArrayList<>();
        if(FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue().equals(documentType)){//报账单是付款单的时候特殊处理
          if(dicEntity==null){//凭证逻辑没有选择行映射
              queryList = avManualVoucherLineMapper.getHeadQueryList(documentType,documentId);
          }else{
              if(dicEntity.getDocumentModule().equals(documentType)){//凭证逻辑选择的是付款单行
                  queryList = avManualVoucherLineMapper.getQueryList(documentType,documentId,dicEntity.getLineTable());
              }else{//凭证逻辑选择的是其他的报账单据
                  List<Map<String,Object>> queryList1 = new ArrayList<>();
                  queryList1 = avManualVoucherLineMapper.getQueryPayTypeList(documentType,documentId,"PY_PAMENT_BUSINESS_LINE",dicEntity.getDocumentModule());
                  Map<String,String> mapDocument = new HashMap<>();
                  for(int n=0;n<queryList1.size();n++){
                      Map<String,Object> map = queryList1.get(n);
                      if(!mapDocument.containsKey(map.get("CONNECT_DOCUMENT_NUM").toString())){
                          mapDocument.put(map.get("CONNECT_DOCUMENT_NUM").toString(),map.get("CONNECT_DOCUMENT_NUM").toString());
                          List<Map<String,Object>> queryList2 = new ArrayList<>();
                          queryList2 =avManualVoucherLineMapper.getPayTypeList(dicEntity.getHeadTable(),dicEntity.getLineTable(),map.get("CONNECT_DOCUMENT_NUM").toString(),dicEntity.getLineType());
                          queryList.addAll(queryList2);
                      }

                  }
              }
          }
        }else{
            if(dicEntity==null){
                queryList = avManualVoucherLineMapper.getHeadQueryList(documentType,documentId);
            }else{
                queryList = avManualVoucherLineMapper.getQueryList(documentType,documentId,dicEntity.getLineTable());
            }
        }

        //获取得到多少列数据需要生成会计凭证
        for(Map<String,Object> map:queryList){
            String code = "";
            String descForCode ="";
            AvManualVoucherLine lineEntity1 = new AvManualVoucherLine();
            lineEntity1.setVoucherType(lineEntity.getVoucherType());
            lineEntity1.setLineTypeCode(lineEntity.getLineTypeCode());
            for (String key : keyMap.keySet()) {
                AvPraseFieldInfo entity = keyMap.get(key);
                String desc = "";
                String descForCode1="";
                Long id = Long.parseLong(map.get("ID").toString());
                Long documentIdForPay =0L;//设置默认值（没用）
                if(map.containsKey("DOCUMENT_ID")){
                     documentIdForPay =Long.parseLong(map.get("DOCUMENT_ID").toString());
                }
                if(key.equals("rowCommentFrom")){//行说明
                    lineEntity1.setDescription(getLineField(entity,documentId,documentType,dicEntity,id,documentIdForPay).toString());
                }else if(key.equals("moneyFrom")){//行金额
                    String  moneyStr = getLineField(entity,documentId,documentType,dicEntity,id,documentIdForPay).toString();
                    BigDecimal money = new BigDecimal(moneyStr.equals("")?"0":moneyStr);
                    if(lineEntity1.getVoucherType().equals("DR")){
                        lineEntity1.setEnteredDr(money);
                        lineEntity1.setEnteredCr(new BigDecimal(0));
                        lineEntity1.setAccountedDr(money.multiply(headEntity.getCost()));
                        lineEntity1.setAccountedCr(new BigDecimal(0));
                    }else if(lineEntity1.getVoucherType().equals("CR")){
                        lineEntity1.setEnteredCr(money);
                        lineEntity1.setEnteredDr(new BigDecimal(0));
                        lineEntity1.setAccountedCr(money.multiply(headEntity.getCost()));
                        lineEntity1.setAccountedDr(new BigDecimal(0));
                    }
                }else if(key.equals("segment1")){//主体段
                    desc =getLineField(entity,documentId,documentType,dicEntity,id,documentIdForPay).toString();
                    descForCode  = getBaseEle(desc,headEntity.getLedgerId(),1L);
                    lineEntity1.setSegment1(desc.equals("")?"0":desc);
                    code+=desc.equals("")?"0":desc+".";
                }else if(key.equals("segment2")){//部门段
                    desc =getLineField(entity,documentId,documentType,dicEntity,id,documentIdForPay).toString();
                    descForCode  = descForCode+"."+getBaseEle(desc,headEntity.getLedgerId(),2L);
                    lineEntity1.setSegment2(desc.equals("")?"0":desc);
                    code+=desc.equals("")?"0":desc+".";
                }else if(key.equals("segment3")){//科目段
                    desc =getLineField(entity,documentId,documentType,dicEntity,id,documentIdForPay).toString();
                    descForCode  = descForCode+"."+getBaseEle(desc,headEntity.getLedgerId(),3L);
                    lineEntity1.setSegment3(desc.equals("")?"0":desc);
                    code+=desc.equals("")?"0":desc+".";
                }else if(key.equals("segment4")){//项目段
                    desc =getLineField(entity,documentId,documentType,dicEntity,id,documentIdForPay).toString();
                    descForCode  = descForCode+"."+getBaseEle(desc,headEntity.getLedgerId(),4L);
                    code+=(desc.equals("")?"0":desc)+".";
                    lineEntity1.setSegment4(desc.equals("")?"0":desc);
                }else if(key.equals("segment5")){//往来段
                    desc =getLineField(entity,documentId,documentType,dicEntity,id,documentIdForPay).toString();
                    descForCode  = descForCode+"."+getBaseEle(desc,headEntity.getLedgerId(),5L);
                    lineEntity1.setSegment5(desc.equals("")?"0":desc);
                    code+=desc.equals("")?"0":desc+".";
                }else if(key.equals("segment6")){//预留
                    desc =getLineField(entity,documentId,documentType,dicEntity,id,documentIdForPay).toString();
                    descForCode  = descForCode+"."+getBaseEle(desc,headEntity.getLedgerId(),6L);
                    lineEntity1.setSegment6(desc.equals("")?"0":desc);
                    code+=desc.equals("")?"0":desc+".";
                }else if(key.equals("segment7")){//预留
                    desc =getLineField(entity,documentId,documentType,dicEntity,id,documentIdForPay).toString();
                    descForCode  = descForCode+"."+getBaseEle(desc,headEntity.getLedgerId(),7L);
                    lineEntity1.setSegment7(desc.equals("")?"0":desc);
                    code+=desc.equals("")?"0":desc+".";
                }else if(key.equals("segment8")){//预留
                    desc =getLineField(entity,documentId,documentType,dicEntity,id,documentIdForPay).toString();
                    descForCode  = descForCode+"."+getBaseEle(desc,headEntity.getLedgerId(),8L);
                    lineEntity1.setSegment8(desc.equals("")?"0":desc);
                    code+=desc.equals("")?"0":desc+".";
                }else if(key.equals("segment9")){//预留
                    desc =getLineField(entity,documentId,documentType,dicEntity,id,documentIdForPay).toString();
                    descForCode  = descForCode+"."+getBaseEle(desc,headEntity.getLedgerId(),9L);
                    lineEntity1.setSegment9(desc.equals("")?"0":desc);
                    code+=desc.equals("")?"0":desc+".";
                }else if(key.equals("segment10")){//预留
                    desc =getLineField(entity,documentId,documentType,dicEntity,id,documentIdForPay).toString();
                    descForCode  = descForCode+"."+getBaseEle(desc,headEntity.getLedgerId(),10L);
                    lineEntity1.setSegment10(desc.equals("")?"0":desc);
                    code+=desc.equals("")?"0":desc+"";
                }


            }
            lineEntity1.setCreateBy(Long.parseLong(userVo.getId()));
            lineEntity1.setCreateTime(LocalDateTime.now());
            lineEntity1.setCreateUserName(userVo.getName());
            lineEntity1.setLedgerId(headEntity.getLedgerId());
            lineEntity1.setAccountStructureCode(code);
            lineEntity1.setAccountStructureDesc(descForCode);
            lineList.add(lineEntity1);
        }




    }

    private String  getBaseEle(String code,Long ledgerId,Long segmentNum){
        String value = "";
        if(code.equals("")){
            value = "缺省";
        }else{
            List<AvBaseElement> list =avBaseElementMapper.selectBaseElementByCode(code,ledgerId,segmentNum);
            if(list.size()>0){
                 value = list.get(0).getDataDesc();
            }else{
                value = "缺省";
            }
        }
        return value;
    }

    /**
     * 解析凭证头信息来源
     * @param entity
     * @param documentId
     * @return
     */
    private Object getLineField(AvPraseFieldInfo entity,Long documentId,String documentType,AvLogicHeadLineDic dicEntity,Long id,Long documentIdForPay){
        Object value ="";
        try{
            switch (entity.getMethod()){
                case "default":
                    value = entity.getValue();
                    break;
                case "field":
                    String[] field = entity.getValue().split("\\.");//包含表.字段
                    String fieldId ="ID";
                    List<Object> objList = new ArrayList<>();
                    if(!documentType.equals(field[0])){
                        if(dicEntity==null){
                           throw new FSSCException(FsscErrorType.AV_VOUCHERHEADER_PARSE_NOT_IS_ERROR);
                        }else{
                            if(dicEntity.getHeadTable().equals(field[0])){
                                objList = avManualVoucherLineMapper.getValue(field[0],field[1],documentIdForPay,fieldId);
                            }else{
                                objList =  avManualVoucherLineMapper.getValue(field[0],field[1],id,fieldId);
                            }
                        }

                    }else{
                        objList =  avManualVoucherLineMapper.getValue(field[0],field[1],documentId,fieldId);
                    }
                    if(objList.size()>0){
                            value=objList.get(0)==null?"":objList.get(0).toString();
                    }else{
                        throw new FSSCException(FsscErrorType.AV_AUTO_LOGIC_NOVALUE_ERROR);
                    }
                    break;
                case "map":
                    String map = entity.getValue().replace("-"," ");
                    if(map.indexOf("{LINE_ID}")!=-1){//行表信息关联
                        String[] mapArry  = map.split(":");
                        map = mapArry[0]+id;
                    }
                    if(map.indexOf("{DOCUMENT_ID}")!=-1){//头表信息关联
                        String[] mapArry  = map.split(":");
                        map = mapArry[0]+documentId;
                    }
                    List<Object> mapList =  avManualVoucherLineMapper.getMap(map);
                    if(mapList.size()>0){
                        if(mapList.size()>2){
                            throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_AUTO_LOGIC_OVERVALUE_ERROR.getMsg());
                        }
                        value = mapList.get(0)==null?"":mapList.get(0).toString();
                    }else{
                        throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_AUTO_LOGIC_NOVALUE_ERROR.getMsg());
                    }
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_VOUCHERHEADER_PARSE_IS_ERROR.getMsg());
        }
        return value;
    }


    /**
     * 解析凭证头信息来源
     * @param entity
     * @param documentId
     * @return
     */
    private Object getHeadField(AvPraseFieldInfo entity,Long documentId,String documentType){
        Object value ="";
        try{
            switch (entity.getMethod()){
                case "default":
                    value = entity.getValue();
                    break;
                case "field":
                    String[] field = entity.getValue().split("\\.");//包含表.字段
                    String fieldId ="ID";
                    List<Object> objList = new ArrayList<>();
                    if(!documentType.equals(field[0])){
                        fieldId ="DOCUMENT_ID";
                        objList =  avManualVoucherLineMapper.getValue(field[0],field[1],documentId,fieldId);
                    }else{
                        objList =  avManualVoucherLineMapper.getValue(field[0],field[1],documentId,fieldId);
                    }

                    if(objList.size()>0){
                        value = objList.get(0).toString();
                    }else{
                        throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_AUTO_LOGIC_NOVALUE_ERROR.getMsg());
                    }
                    break;
                case "map":
                    String map = entity.getValue().replace("-"," ");
                    List<Object> mapList =  avManualVoucherLineMapper.getMap(map);
                    if(mapList.size()>0){
                        value = mapList.get(0).toString();
                    }else{
                        throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_AUTO_LOGIC_NOVALUE_ERROR.getMsg());
                    }
                    break;
                default:
                    break;
            }
        }catch (Exception e){

        }
        return value;
    }


    /**
     * 解析得到凭证逻辑判断条件
     * @param msg
     * @return
     */
    private  List<AvJudgementVo> parseJsonJudgement(String msg){
        List<AvJudgementVo> list =new ArrayList<AvJudgementVo>();
        try{
            list  = com.alibaba.fastjson.JSONObject.parseArray(msg, AvJudgementVo.class);
        }catch (Exception e){
            log.error("解析出错");
        }
        return list;
    }

    /**
     * 解析凭证头信息
     * @param p
     * @return
     */
    private Map<String,AvPraseFieldInfo>  parseField(AvAccountLogicHead p){
        Map<String,AvPraseFieldInfo> map = Maps.newLinkedHashMap();
        try{
            AvPraseFieldInfo entity = new AvPraseFieldInfo();
            if(p.getTypeFrom()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getTypeFrom(), AvPraseFieldInfo.class);
                map.put("typeFrom",entity);
            }
            if(p.getNumFrom()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getNumFrom(), AvPraseFieldInfo.class);
                map.put("numFrom",entity);
            }
            if(p.getVoucherFrom()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getVoucherFrom(), AvPraseFieldInfo.class);
                map.put("voucherFrom",entity);
            }
            if(p.getRateFrom()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getRateFrom(), AvPraseFieldInfo.class);
                map.put("rateFrom",entity);
            }
            if(p.getHeadFrom()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getHeadFrom(), AvPraseFieldInfo.class);
                map.put("headFrom",entity);
            }
            if(map.size()<5){
                throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,FsscErrorType.AV_AUTO_LOGIC_HEAD_ERROR.getMsg());
            }

        }catch (Exception e){
            log.error("解析出错");
        }
        return map;
    }

    /**
     * 解析凭证行信息
     * @param p
     * @return
     */
    private Map<String,AvPraseFieldInfo>parseLineField(AvAccountLogicLine p){
        Map<String,AvPraseFieldInfo> map = Maps.newLinkedHashMap();
        try{
            AvPraseFieldInfo entity = new AvPraseFieldInfo();
            if(p.getRowCommentFrom()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getRowCommentFrom(), AvPraseFieldInfo.class);
                map.put("rowCommentFrom",entity);
            }
            if(p.getMoneyFrom()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getMoneyFrom(), AvPraseFieldInfo.class);
                map.put("moneyFrom",entity);
            }
            if(p.getSegment1()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getSegment1(), AvPraseFieldInfo.class);
                map.put("segment1",entity);
            }
            if(p.getSegment2()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getSegment2(), AvPraseFieldInfo.class);
                map.put("segment2",entity);
            }
            if(p.getSegment3()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getSegment3(), AvPraseFieldInfo.class);
                map.put("segment3",entity);
            }
            if(p.getSegment4()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getSegment4(), AvPraseFieldInfo.class);
                map.put("segment4",entity);
            }
            if(p.getSegment5()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getSegment5(), AvPraseFieldInfo.class);
                map.put("segment5",entity);
            }
            if(p.getSegment6()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getSegment6(), AvPraseFieldInfo.class);
                map.put("segment6",entity);
            }
            if(p.getSegment7()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getSegment7(), AvPraseFieldInfo.class);
                map.put("segment7",entity);
            }
            if(p.getSegment8()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getSegment8(), AvPraseFieldInfo.class);
                map.put("segment8",entity);
            }
            if(p.getSegment9()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getSegment9(), AvPraseFieldInfo.class);
                map.put("segment9",entity);
            }
            if(p.getSegment10()!=null){
                entity  = com.alibaba.fastjson.JSONObject.parseObject(p.getSegment10(), AvPraseFieldInfo.class);
                map.put("segment10",entity);
            }
            if(map.size()<12){
                throw new FSSCException(FsscErrorType.AV_AUTO_LOGIC_LINE_ERROR);
            }

        }catch (Exception e){
            log.error("解析出错");
        }
        return map;
    }

    /**
     *发送项目的基本信息到EBS系统中
     * @param code
     */
    public void  sendProjectInfoToEbs(String code,String projectName){
        try{
            String url = avToEbs+"webservices/rest/incept_project/project_interface/";
            //构建请求头
            DeptVo deptVo = commonServices.getCurrentDept();
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.setContentType(MediaType.APPLICATION_JSON);
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            requestHeaders.setContentType(type);
            //使用base64进行加密
            String auth = "sysadmin" + ":" + "sysadmin";
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
            String authHeader = "Basic " + new String(encodedAuth);

            //把认证信息发到header中
            requestHeaders.add("Authorization", authHeader);

            List<Map<String, Object>> listMaps = new ArrayList<Map<String, Object>>();
            Map<String, Object> map1 =  Maps.newLinkedHashMap();
            map1.put("DATA_TYPE", deptVo.getDeptCode());
            map1.put("DATA_CODE", code);
            map1.put("DATA_DESC",projectName);
            map1.put("DATA_STATUS", "Y");
            listMaps.add(map1);
            Map<String,Object> map2 = new HashMap<String,Object>();
            map2.put("PROJECT_RECORD",listMaps);
            Map<String,Object> map3 = new HashMap<String,Object>();
            map3.put("P_PROJECT_TABLE",map2);
            Map<String,Object> map4 = new HashMap<String,Object>();
            map4.put("@xmlns",url);
            map4.put("Responsibility","");
            map4.put("RespApplication","");
            map4.put("SecurityGroup","");
            map4.put("NLSLanguage","");
            Map<String,Object> map5 = new HashMap<String,Object>();
            map5.put("@xmlns",url);
            map5.put("RESTHeader",map4);
            map5.put("InputParameters",map3);
            Map<String,Object> map6 = new HashMap<String,Object>();
            map6.put("PROJECT_INTERFACE_Input",map5);
            String objectToheader = JSONUtils.toJSONString(map6);
            //发送请求

            HttpEntity<String> requestEntity = new HttpEntity<String>(objectToheader, requestHeaders);
            String  backStr = restTemplate.postForEntity(url,requestEntity,String.class).getBody();
            JSONObject obj = new JSONObject(backStr);
            JSONObject OutputParameters =  (JSONObject)obj.get("OutputParameters");
            String X_RESPONSE_CODE = OutputParameters.get("X_RESPONSE_CODE").toString();
            if(!"SUCCESS".equals(X_RESPONSE_CODE)){
                JSONObject err = (JSONObject)OutputParameters.get("X_ERROR_RECORD");

            }

        }catch (Exception e){
              log.error("ebs项目错误"+e);
            throw new ServiceException(FsscErrorType.AV_MANUALVOUCHER_IS_ERROR,e.getMessage());
        }
    }

}
