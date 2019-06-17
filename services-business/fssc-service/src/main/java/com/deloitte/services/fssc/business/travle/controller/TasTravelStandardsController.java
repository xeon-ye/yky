package com.deloitte.services.fssc.business.travle.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.travle.param.TasTravelStandardsQueryForm;
import com.deloitte.platform.api.fssc.travle.vo.StandardsVo;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelStandardsForm;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelStandardsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.fssc.business.travle.entity.TasTravelStandards;
import com.deloitte.services.fssc.business.travle.service.ITasTravelStandardsService;
import com.deloitte.services.fssc.common.constant.FsscConstants;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import com.deloitte.services.fssc.system.dic.entity.DicValue;
import com.deloitte.services.fssc.system.dic.service.IDicValueService;
import com.deloitte.services.fssc.util.*;
import com.deloitte.services.fssc.util.excel.ExcelHeader;
import com.deloitte.services.fssc.util.excel.ExcelImportUtil;
import com.deloitte.services.fssc.util.excel.ExcelResult;
import com.deloitte.services.fssc.util.excel.MultipleExcelExportUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;


/**
 * @Author : hjy
 * @Date : Create in 2019-04-17
 * @Description :   TasTravelStandards控制器实现类
 * @Modified :
 */
@Api(tags = "差旅标准维护操作接口")
@Slf4j
@RestController
@RequestMapping(value="/travle/travel-standars")
public class TasTravelStandardsController  {

    @Autowired
    public ITasTravelStandardsService  tasTravelStandardsService;
    @Autowired
    public IDicValueService dicValueService;

    @ApiOperation("差旅地址导入")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件对象", required = true, dataType = "MultipartFile")
    })
    @PostMapping(value = "/importBudget")
    public Result importTravelAdress(@RequestPart("file") MultipartFile file,TasTravelStandardsQueryForm tasTravelStandardsQueryForm ) {
        if (file != null) {
            MultipartFile excelFile = file;
            //得到文件的原始名称
            String originalFilename = excelFile.getOriginalFilename();
            //得到文件扩展名
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            if (!FsscEums.EXCEL_2003.getValue().equals(ext) && !FsscEums.EXCEL_2007.getValue()
                    .equals(ext)) {
                throw new FSSCException(FsscErrorType.FILE_FORMAT_NOT_EXCEL);
            }
            File cacheFile;
            try {
                cacheFile = File.createTempFile(UUIDUtil.getRandom32PK(), ext);
                excelFile.transferTo(cacheFile);
                ExcelResult excelResult = ExcelImportUtil.getExcelDate(cacheFile);
                if (!excelResult.readSuccess()) {
                    return Result.fail(excelResult.getErrorMsgBeanList());
                }
                List<Map<String, String>> rowMapList = excelResult.getTableData();
                List<TasTravelStandards> tasTravelStandardsList = new ArrayList<>(rowMapList.size());
                int rowNum = 0;
                for(Map<String, String> rowMap : rowMapList){
                    rowNum++;
                    TasTravelStandards tasTravelStandards=new TasTravelStandards();
                    String country=rowMap.get(TasTravelStandards.COUNTRY);
                    if("".equals(country)||StringUtil.isEmpty(country)){
                        excelResult.addErrorMsg(rowNum, TasTravelStandards.COUNTRY, "国家不能为空");
                    }
                    String nationAdminCode=rowMap.get(TasTravelStandards.NATION_ADMIN_CODE);
                    if("".equals(nationAdminCode)||StringUtil.isEmpty(nationAdminCode)){
                        excelResult.addErrorMsg(rowNum,TasTravelStandards.NATION_ADMIN_CODE,"行政区划代码");
                    }
                    QueryWrapper<DicValue>  queryWrapper=new QueryWrapper<>();
                    queryWrapper.eq("DIC_VALUE",nationAdminCode);
                    queryWrapper.eq("DIC_CODE","TRAVEL_SITE");
                    if(dicValueService.count(queryWrapper)==0){
                        excelResult.addErrorMsg(rowNum,TasTravelStandards.NATION_ADMIN_CODE,"行政区划代码在数据字典值集不存在");
                    }
                    String placeName=rowMap.get(TasTravelStandards.PLACE_NAME);
                    if("".equals(placeName)||StringUtil.isEmpty(placeName)){
                        excelResult.addErrorMsg(rowNum,TasTravelStandards.PLACE_NAME,"地区不能为空");
                    }
                    tasTravelStandards.setCountry(country);
                    tasTravelStandards.setNationAdminCode(nationAdminCode);
                    tasTravelStandards.setPlaceName(placeName);
                    String ministerialLevel=rowMap.get(TasTravelStandards.MINISTERIAL_LEVEL);
                    if(StringUtil.isEmpty(ministerialLevel) ||"#N/A".equals(ministerialLevel)){
                        excelResult.addErrorMsg(rowNum,TasTravelStandards.MINISTERIAL_LEVEL,"部级（普通套间）不能为空");
                    }
                    BigDecimal sterialLevel;
                    BigDecimal compare=new BigDecimal(0);
                    try {
                        if(StringUtil.isNotEmpty(ministerialLevel)){
                            sterialLevel = new BigDecimal(ministerialLevel);
                            if(sterialLevel.compareTo(compare)==-1){
                                excelResult.addErrorMsg(rowNum,TasTravelStandards.MINISTERIAL_LEVEL,"部级（普通套间）不能为负数");
                            }
                            tasTravelStandards.setMinisterialLevel(sterialLevel);
                        }
                    } catch (Exception e) {
                        excelResult
                                .addErrorMsg(rowNum, TasTravelStandards.MINISTERIAL_LEVEL, "部级住宿标准不能转换为数值类型");
                    }
                    //司级标准
                    String secrets=rowMap.get(TasTravelStandards.SECRETARIES);
                    if(StringUtil.isEmpty(secrets) ||"#N/A".equals(secrets)){
                        excelResult.addErrorMsg(rowNum,TasTravelStandards.SECRETARIES,"司局级（单间或标准间）不能为空");
                    }
                    BigDecimal secretaries;
                    try {
                        if(StringUtil.isNotEmpty(secrets)) {
                            secretaries = BigDecimalUtil.convert(new BigDecimal(secrets));
                            if(secretaries.compareTo(compare)==-1) {
                                excelResult.addErrorMsg(rowNum,TasTravelStandards.SECRETARIES,"司局级（单间或标准间）不能为负数");
                            }
                            tasTravelStandards.setSecretaries(secretaries);
                        }
                    }catch (Exception e){
                        excelResult.addErrorMsg(rowNum, TasTravelStandards.SECRETARIES, "司局级住宿标准不能转换为数值类型");
                    }
                    //其他人员标准
                    String other=rowMap.get(TasTravelStandards.OTHER_PERSONNEL);
                    if(StringUtil.isEmpty(other) ||"#N/A".equals(other)){
                        excelResult.addErrorMsg(rowNum,TasTravelStandards.OTHER_PERSONNEL,"其他人员住宿标准不能为空");
                    }
                    BigDecimal otherPersonnel;
                    try {
                        if(StringUtil.isNotEmpty(other)) {
                            otherPersonnel = new BigDecimal(other);
                            if(otherPersonnel.compareTo(compare)==-1) {
                                excelResult.addErrorMsg(rowNum,TasTravelStandards.OTHER_PERSONNEL,"其他人员住宿标准不能为负数");
                            }
                            tasTravelStandards.setOtherPersonnel(otherPersonnel);
                        }
                    }catch (Exception e){
                        excelResult.addErrorMsg(rowNum, TasTravelStandards.SECRETARIES, "其他住宿标准不能转换为数值类型");
                    }
                    //伙食补助
                    String food=rowMap.get(TasTravelStandards.FOOD_ALLOWANCE);
                    if(StringUtil.isEmpty(food) ||"#N/A".equals(food)){
                        excelResult.addErrorMsg(rowNum,TasTravelStandards.FOOD_ALLOWANCE,"伙食补助不能为空");
                    }
                    BigDecimal foodAllowance;
                    try {
                        if(StringUtil.isNotEmpty(food)) {
                            foodAllowance = new BigDecimal(food);
                            if(foodAllowance.compareTo(compare)==-1) {
                                excelResult.addErrorMsg(rowNum,TasTravelStandards.FOOD_ALLOWANCE,"伙食补助不能为负数");
                            }
                            tasTravelStandards.setFoodAllowance(foodAllowance);
                        }
                    }catch (Exception e){
                        excelResult.addErrorMsg(rowNum, TasTravelStandards.FOOD_ALLOWANCE, "伙食补助不能转换为数值类型");
                    }
                    //交通补助
                    String traffic=rowMap.get(TasTravelStandards.TRAFFIC_SUBSIDY);
                    if(StringUtil.isEmpty(traffic) ||"#N/A".equals(traffic)){
                        excelResult.addErrorMsg(rowNum,TasTravelStandards.TRAFFIC_SUBSIDY,"交通补助不能为空");
                    }
                    BigDecimal trafficSubsidy;
                    try {
                        if(StringUtil.isNotEmpty(traffic)) {
                            trafficSubsidy = new BigDecimal(traffic);
                            if(trafficSubsidy.compareTo(compare)==-1) {
                                excelResult.addErrorMsg(rowNum,TasTravelStandards.TRAFFIC_SUBSIDY,"交通补助不能为负数");
                            }
                            tasTravelStandards.setTrafficSubsidy(trafficSubsidy);
                        }
                    }catch (Exception e){
                        excelResult.addErrorMsg(rowNum, TasTravelStandards.TRAFFIC_SUBSIDY, "伙食补助不能转换为数值类型");
                    }
                    //旺季月份
                    String peakMonth=rowMap.get(TasTravelStandards.PEAK_MONTH);
                    if(StringUtil.isNotEmpty(peakMonth)){
                       int  peakMonth1=Integer.parseInt(peakMonth);
                       if(peakMonth1>12 || peakMonth1<=0 ){
                           excelResult.addErrorMsg(rowNum,TasTravelStandards.PEAK_MONTH,"导入月份只能为1到12");
                       }
                    }
                    tasTravelStandards.setPeakMonth(peakMonth);
                    tasTravelStandardsList.add(tasTravelStandards);
                }
                if (excelResult.readSuccess()) {
                    for (TasTravelStandards tas : tasTravelStandardsList) {
                        TasTravelStandards tasTravelStandards=tasTravelStandardsService.selectTravelStandar(tas.getPeakMonth(),
                                tas.getNationAdminCode(), tas.getCountry(), tas.getPlaceName());
                        if(tasTravelStandards==null){
                            tasTravelStandards=new TasTravelStandards();
                            tasTravelStandards.setTrafficSubsidy(tas.getTrafficSubsidy());
                            tasTravelStandards.setFoodAllowance(tas.getFoodAllowance());
                            tasTravelStandards.setPeakMonth(tas.getPeakMonth());
                            tasTravelStandards.setOtherPersonnel(tas.getOtherPersonnel());
                            tasTravelStandards.setSecretaries(tas.getSecretaries());
                            tasTravelStandards.setMinisterialLevel(tas.getMinisterialLevel());
                            tasTravelStandards.setNationAdminCode(tas.getNationAdminCode());
                            tasTravelStandards.setCountry(tas.getCountry());
                            tasTravelStandards.setPlaceName(tas.getPlaceName());
                            tasTravelStandardsService.save(tasTravelStandards);
                        }else{
                            tas.setId(tasTravelStandards.getId());
                            tasTravelStandardsService.updateById(tas);
                        }

                    }
                   // tasTravelStandardsService.saveOrUpdateBatch(tasTravelStandards);
                    //去重(有点问题)
                   // List<TasTravelStandards> tasTravelStandardLists = tasTravelStandardsList.stream().filter(item -> !tasTravelStandards.contains(item)).collect(toList());
                    return Result.success(tasTravelStandardsList);
                } else {
                    return Result.fail(excelResult.getErrorMsgBeanList());
                }

            }catch (IOException ioe) {
                log.error("读取Excel文件出错: {}", ioe.getMessage());
                throw new FSSCException(FsscErrorType.FILE_UPLOAD_FIAL);
            }
        }
        throw new FSSCException(FsscErrorType.FILE_UPLOAD_FIAL);
    }



    /**
     * 方法名: export 方法描述: 导出对公费用报账单
     */
    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response, TasTravelStandardsQueryForm tasTravelStandardsQueryForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(tasTravelStandardsQueryForm));
        List<TasTravelStandards> records = tasTravelStandardsService.selectPage(tasTravelStandardsQueryForm).getRecords();
        String[] title = {"序号", "国家", "行政区划代码",  "地点名称", "部级住宿标准", "司局级住宿标准","其他人员住宿标准","伙食费补助标准",
                "交通费补助标准", "旺季月份"};
        String fileName = "差旅标准维护列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s:title){
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        String[][] content = new String[records.size()][];
        for (int i = 0; i < records.size(); i++) {
            content[i] = new String[title.length];
            TasTravelStandards vo = records.get(i);
            content[i][0] = i + 1 + "";
            content[i][1] = vo.getCountry();
            content[i][2] = vo.getNationAdminCode();
            content[i][3] =vo.getPlaceName();
            // content[i][3] = vo.getUnitName();;
            content[i][4] = StringUtil.objectToString(vo.getMinisterialLevel());
            // content[i][5] = StringUtil.obj;ectToString(vo.getBorrowAmount());

            content[i][5] = StringUtil.objectToString(vo.getSecretaries());
            content[i][6] = StringUtil.objectToString(vo.getOtherPersonnel());
            content[i][7] = StringUtil.objectToString(vo.getFoodAllowance());
            content[i][8] = StringUtil.objectToString(vo.getTrafficSubsidy());
            content[i][9] = StringUtil.objectToString(vo.getPeakMonth());
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.setData2Array(content);
        exportUtil.exportExcel();
    }

    /**
     * 方法名: 下载模板
     */
    @GetMapping(value = "/download")
    @ResponseBody
    public void  download(HttpServletResponse response, TasTravelStandardsQueryForm tasTravelStandardsQueryForm)
            throws IOException {
        log.info("search with unitInfoQueryForm:{}", JSON.toJSONString(tasTravelStandardsQueryForm));
        String[] title = { "国家", "行政区划代码",  "地点名称", "部级住宿标准", "司局级住宿标准","其他人员住宿标准","伙食费补助标准",
                "交通费补助标准", "旺季月份"};
        String fileName = "差旅标准维护列表";
        List<ExcelHeader> headerList = new ArrayList<>();
        for (String s:title){
            headerList.add(new ExcelHeader(s).setOneCellWidth(FsscConstants.WIDTH));
        }
        MultipleExcelExportUtil exportUtil =
                MultipleExcelExportUtil.getSimpleInstance2(headerList, FsscHttpUtils.currentResponse());
        exportUtil.setFileName(fileName);
        exportUtil.exportExcel();
    }

    @GetMapping(value="/page/conditions")
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @ResponseBody
    public Result<IPage<TasTravelStandardsVo>> search(@Valid  @ApiParam(name="tasTravelStandardsQueryForm",value="TasTravelStandards查询参数",required=true) TasTravelStandardsQueryForm tasTravelStandardsQueryForm) {
        log.info("search with tasTravelStandardsQueryForm:", tasTravelStandardsQueryForm.toString());
        String nationAdminCode="";
        if(StringUtil.isNotEmpty(tasTravelStandardsQueryForm.getPlaceName())){
             QueryWrapper<TasTravelStandards> standardsQueryWrapper=new QueryWrapper<>();
             standardsQueryWrapper.eq("PLACE_NAME",tasTravelStandardsQueryForm.getPlaceName());
             TasTravelStandards tasTravelStandards=tasTravelStandardsService.getOne(standardsQueryWrapper);
             if(tasTravelStandards!=null){
                 nationAdminCode=tasTravelStandards.getNationAdminCode().substring(0,2);
                 String code=tasTravelStandards.getNationAdminCode().substring(2,6);//330000
                if(code.equals("0000")){
                    tasTravelStandardsQueryForm.setNationAdminCode(nationAdminCode);
                    tasTravelStandardsQueryForm.setPlaceName("");
                }

            }
        }
        IPage<TasTravelStandards> tasTravelStandardsPage=tasTravelStandardsService.selectPage(tasTravelStandardsQueryForm);
        IPage<TasTravelStandardsVo> tasTravelStandardsVoPage=new BeanUtils<TasTravelStandardsVo>().copyPageObjs(tasTravelStandardsPage,TasTravelStandardsVo.class);
        return new Result<IPage<TasTravelStandardsVo>>().sucess(tasTravelStandardsVoPage);
    }

    @GetMapping(value="/list")
    @ApiOperation(value = "根据条件列表查询差旅维护信息", notes = "根据条件列表查询差旅维护信息")
    @ResponseBody
    public Result<List<StandardsVo>> list(@Valid  @ApiParam(name="tasTravelStandardsQueryForm",value="TasTravelStandards查询参数",required=true) TasTravelStandardsQueryForm tasTravelStandardsQueryForm) {
        log.info("search with tasTravelStandardsQueryForm:", tasTravelStandardsQueryForm.toString());
        List<TasTravelStandards> tasTravelStandardsPage=new ArrayList<TasTravelStandards>();
        //tasTravelStandardsService.selectList(tasTravelStandardsQueryForm);
        int travelBeginTime=tasTravelStandardsQueryForm.getTimeStart().getMonth().getValue();//默认去掉为0位
        int travelEndTime=tasTravelStandardsQueryForm.getTimeEnd().getMonth().getValue();
        for (int i = travelBeginTime; i <= travelEndTime; i++) {
                String travelDate = i + "";
                QueryWrapper<TasTravelStandards> standardWrapper = new QueryWrapper<>();
                standardWrapper.eq("PEAK_MONTH", travelDate).like("NATION_ADMIN_CODE", tasTravelStandardsQueryForm.getNationAdminCode());

                QueryWrapper<TasTravelStandards> queryWrapper = new QueryWrapper <TasTravelStandards>();
                queryWrapper.like(StringUtil.isNotEmpty(tasTravelStandardsQueryForm.getNationAdminCode()),"NATION_ADMIN_CODE",tasTravelStandardsQueryForm.getNationAdminCode());
                if (tasTravelStandardsService.count(standardWrapper) == 0) {
                    tasTravelStandardsQueryForm.setPeakMonth("");
                    queryWrapper.isNull("PEAK_MONTH");
                } else {
                    tasTravelStandardsQueryForm.setPeakMonth(travelDate + "");
                    queryWrapper.eq(StringUtil.isNotEmpty(tasTravelStandardsQueryForm.getPeakMonth()),"PEAK_MONTH",tasTravelStandardsQueryForm.getPeakMonth());
                }
            TasTravelStandards tasTravelStandards= tasTravelStandardsService.getOne(queryWrapper);
            if(tasTravelStandards!=null){
                tasTravelStandardsPage.add(tasTravelStandards);
            }
        }
        List<StandardsVo> standardsVos=new ArrayList<StandardsVo>();
        //住宿费
        if(CollectionUtils.isNotEmpty(tasTravelStandardsPage)){
            if("accommodation".equals(tasTravelStandardsQueryForm.getTravelType())){
                for(int k=0; k<tasTravelStandardsPage.size();k++){
                   if("部级正职".equals(tasTravelStandardsQueryForm.getPersonnelLevel())
                   ||"部级副职".equals(tasTravelStandardsQueryForm.getPersonnelLevel())){
                       StandardsVo standardsVo=new StandardsVo();
                       BigDecimal sec=tasTravelStandardsPage.get(k).getMinisterialLevel();
                       standardsVo.setOverMoney(sec);
                       standardsVos.add(standardsVo);
                   }
                   if("厅级正职".equals(tasTravelStandardsQueryForm.getPersonnelLevel())
                           ||"厅级副职".equals(tasTravelStandardsQueryForm.getPersonnelLevel())){
                       StandardsVo standardsVo=new StandardsVo();
                       BigDecimal sec=tasTravelStandardsPage.get(k).getSecretaries();
                       standardsVo.setOverMoney(sec);
                       standardsVos.add(standardsVo);
                   }
                   if("处级正职".equals(tasTravelStandardsQueryForm.getPersonnelLevel())
                           ||"处级副职".equals(tasTravelStandardsQueryForm.getPersonnelLevel())
                           ||"科级正职".equals(tasTravelStandardsQueryForm.getPersonnelLevel())
                           ||"科级副职".equals(tasTravelStandardsQueryForm.getPersonnelLevel())
                           ||"科员".equals(tasTravelStandardsQueryForm.getPersonnelLevel())
                           ||"办事员".equals(tasTravelStandardsQueryForm.getPersonnelLevel())){
                       StandardsVo standardsVo=new StandardsVo();
                       BigDecimal sec=tasTravelStandardsPage.get(k).getOtherPersonnel();
                       standardsVo.setOverMoney(sec);
                       standardsVos.add(standardsVo);
                   }
               }
            }
              //补助费
              if("subsidies".equals(tasTravelStandardsQueryForm.getTravelType())){
                  for(int k=0; k<tasTravelStandardsPage.size();k++) {
                      StandardsVo standardsVo = new StandardsVo();
                      BigDecimal foodAllowance = tasTravelStandardsPage.get(k).getFoodAllowance();
                      BigDecimal trafficSubsidy = tasTravelStandardsPage.get(k).getTrafficSubsidy();
                      standardsVo.setOverMoney(trafficSubsidy.add(foodAllowance));
                      standardsVos.add(standardsVo);

              }
           }
        }
        //去重
            for  ( int  i  =   0 ; i  <  standardsVos.size()  -   1 ; i ++ )  {
                for  ( int  j  =  standardsVos.size()  -   1 ; j  >  i; j -- )  {
                    if  (standardsVos.get(j).equals(standardsVos.get(i)))  {
                        standardsVos.remove(j);
                    }
                }
            }

        if(standardsVos.size()>1){
           throw new FSSCException(FsscErrorType.TRAVLE_APPLY_TIME);//只能有一条数据
       }
        //List<TasTravelStandardsVo> tasTravelStandardsVs=new BeanUtils<TasTravelStandardsVo>().copyObjs(tasTravelStandardsPage,TasTravelStandardsVo.class);
        return new Result<List<StandardsVo>>().sucess(standardsVos);
    }

    @PostMapping(value="/doSaveOrUpdate")
    @ApiOperation(value="新增或修改" ,notes="新增或修改")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @ResponseBody
    @Transactional
    public Result doSaveOrUpdate(@RequestBody@Valid List<TasTravelStandardsForm> tasTravelStandardsForm){
        log.info("tasTravelApplyInfoForm:"+ tasTravelStandardsForm.toString());
        return saveOrUpdate(tasTravelStandardsForm);
    }

    public  Result  saveOrUpdate(List<TasTravelStandardsForm> tasTravelStandardsFormList){
        for(TasTravelStandardsForm tasTravelStandardsForm:tasTravelStandardsFormList){
            TasTravelStandards tasTravelApplyInfo =new BeanUtils<TasTravelStandards>().copyObj(tasTravelStandardsForm, TasTravelStandards.class);
            QueryWrapper<TasTravelStandards> standardsWrapper=new QueryWrapper<>();
            standardsWrapper.eq("NATION_ADMIN_CODE",tasTravelApplyInfo.getNationAdminCode()).
                    eq(StringUtil.isNotEmpty(tasTravelApplyInfo.getPeakMonth()),"PEAK_MONTH",tasTravelApplyInfo.getPeakMonth());
            TasTravelStandards tasTravelStandards=tasTravelStandardsService.getOne(standardsWrapper);
            if(tasTravelStandards!=null){
                throw new FSSCException(FsscErrorType.TRAVEL_READY_PAYMENT);
            }
            QueryWrapper<DicValue>  queryWrapper=new QueryWrapper<>();
            queryWrapper.eq("DIC_VALUE",tasTravelStandardsForm.getNationAdminCode());
            queryWrapper.eq("DIC_CODE","TRAVEL_SITE");
            if(dicValueService.count(queryWrapper)==0){
                throw new FSSCException(FsscErrorType.TRAVEL_UNCONTAIN_DICVALUE);
            }
            tasTravelStandardsService.saveOrUpdate(tasTravelApplyInfo);
        }
        return Result.success();
    }

    @DeleteMapping(value = "/deleteById")
    @ApiOperation(value = "tasTravelApplyInfo", notes = "根据url的id来指定删除对象")
    @Transactional
    public Result delete(@RequestBody List<Long> ids) {
        log.info("id:"+ids);
        tasTravelStandardsService.removeByIds(ids);
        return Result.success();
    }

}



