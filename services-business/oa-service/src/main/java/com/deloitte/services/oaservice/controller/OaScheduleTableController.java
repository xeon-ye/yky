package com.deloitte.services.oaservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.client.OaScheduleTableClient;
import com.deloitte.platform.api.oaservice.param.OaScheduleTableQueryForm;
import com.deloitte.platform.api.oaservice.param.OaScheduleTableQueryParam;
import com.deloitte.platform.api.oaservice.param.OaWorkflowParamForm;
import com.deloitte.platform.api.oaservice.vo.*;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.oa.util.OaBeanUtils;
import com.deloitte.services.oaservice.entity.OaScheduleHistory;
import com.deloitte.services.oaservice.entity.OaScheduleTable;
import com.deloitte.services.oaservice.service.IOaScheduleHistoryService;
import com.deloitte.services.oaservice.service.IOaScheduleTableService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * @Author : fuqiao
 * @Date : Create in 2019-03-26
 * @Description :   OaScheduleTable控制器实现类
 * @Modified :
 */
@Api(tags = "OaScheduleTable操作接口")
@Slf4j
@RestController
public class OaScheduleTableController implements OaScheduleTableClient {

    @Autowired
    public IOaScheduleTableService  oaScheduleTableService;

    @Autowired
    public IOaScheduleHistoryService  oaScheduleHistoryService;

    @Autowired
    UserFeignService userService;

    @Override
    @ApiOperation(value = "新增OaScheduleTable", notes = "新增一个OaScheduleTable")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result addFormOtherSys(@Valid @RequestBody @ApiParam(name="oaScheduleTableForm",value="新增OaScheduleTable的form表单",required=true)  OaScheduleTableForm oaScheduleTableForm) {
        log.info("form:",  oaScheduleTableForm.toString());
        OaScheduleTable oaScheduleTable =new OaBeanUtils<OaScheduleTable>().copyObj(oaScheduleTableForm,OaScheduleTable.class);
        String empNo = oaScheduleTableForm.getUserId();
        UserQueryForm userQueryForm = new UserQueryForm();
        userQueryForm.setEmpNo(empNo);
        //Result<UserVo>  rs2 = userService.getByEmpNo(empNo);
        Result<List<UserVo>>  rs = userService.list(userQueryForm);
        if(rs.isSuccess()){
            List<UserVo> userList = rs.getData();
            if(userList!=null&&userList.size()>0){
                if(userList.size()==1){
                    UserVo user = userList.get(0);
                    oaScheduleTable.setUserId(user.getId());
                    if(user!=null){
                        oaScheduleTable.setCreateBy(user.getId());
                        oaScheduleTable.setUpdateBy(user.getId());
                        return Result.success(oaScheduleTableService.save(oaScheduleTable));
                    }else{
                        return Result.fail("无法获取对应的人员信息");
                    }
                }else{
                    for(UserVo user:userList){
                        if(empNo.equals(user.getEmpNo())){
                            oaScheduleTable.setUserId(user.getId());
                            oaScheduleTable.setCreateBy(user.getId());
                            oaScheduleTable.setUpdateBy(user.getId());
                            break;
                        }
                    }
                    return Result.success(oaScheduleTableService.save(oaScheduleTable));
                }
            }else{
                return Result.fail("无法获取对应的人员信息");
            }
        }else{
            return Result.fail("无法获取对应的人员信息");
        }
    }

    @Override
    @ApiOperation(value = "新增OaScheduleTable", notes = "新增一个OaScheduleTable")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaScheduleTableForm",value="新增OaScheduleTable的form表单",required=true)  OaScheduleTableForm oaScheduleTableForm) {
        log.info("form:",  oaScheduleTableForm.toString());
        OaScheduleTable oaScheduleTable =new OaBeanUtils<OaScheduleTable>().copyObj(oaScheduleTableForm,OaScheduleTable.class);
        UserVo user = UserUtil.getUserVo();
        if(user!=null){
            oaScheduleTable.setCreateBy(user.getId());
            oaScheduleTable.setUpdateBy(user.getId());
        }
        return Result.success( oaScheduleTableService.save(oaScheduleTable));
    }


    @Override
    @ApiOperation(value = "删除OaScheduleTable", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaScheduleTableID", required = true, dataType = "string")
    public Result delete(@PathVariable String id) {
        //在删除之前，进行历史记录保存
        UserVo user = UserUtil.getUserVo();
        OaScheduleTable oldVo = oaScheduleTableService.getById(id);
        if(oldVo!=null){
            OaScheduleHistory history = new OaBeanUtils<OaScheduleHistory>().copyObj(oldVo,OaScheduleHistory.class);
            history.setId(null);
            history.setWorkId(oldVo.getId().toString());
            if(user!=null){
                history.setCreateBy(user.getId());
                history.setUpdateBy(user.getId());
            }
            oaScheduleHistoryService.save(history);
        }
        oaScheduleTableService.removeById(id);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "按行删除OaScheduleTable", notes = "按行删除OaScheduleTable信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path",name = "businessId", value = "OaScheduleTable的businessId", required = true, dataType = "string"),
            @ApiImplicitParam(paramType = "path",name = "rowNum", value = "OaScheduleTable的rowNum", required = true, dataType = "long")
    })
    public Result deleteRow(@PathVariable String businessId,@PathVariable int rowNum){
        if(oaScheduleTableService.removeByBusinessIdAndRowNum(businessId,rowNum)){
            return Result.success();
        }else{
            return Result.fail("未删除任何记录！");
        }
    }


    @Override
    @ApiOperation(value = "修改OaScheduleTable", notes = "修改指定OaScheduleTable信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaScheduleTable的ID", required = true, dataType = "string")
    public Result update(@PathVariable String id,
                         @Valid @RequestBody @ApiParam(name="oaScheduleTableForm",value="修改OaScheduleTable的form表单",required=true)  OaScheduleTableForm oaScheduleTableForm) {
        UserVo user = UserUtil.getUserVo();
        OaScheduleTable oaScheduleTable =new OaBeanUtils<OaScheduleTable>().copyObj(oaScheduleTableForm,OaScheduleTable.class);
        long pk = -1l;
        if(id!=null&&!"".equals(id)){
            pk = Long.valueOf(id);
        }else{
            return Result.fail("无法获取到对应的id信息");
        }
        oaScheduleTable.setId(pk);
        //在保存之前，进行历史记录保存
        OaScheduleTable oldVo = oaScheduleTableService.getById(pk);
        if("S".equals(oaScheduleTable.getWorkStatus())&&oldVo.getCreateBy().equals(user.getId())){
            log.info("no save history");
        }else {
            OaScheduleHistory history = new OaBeanUtils<OaScheduleHistory>().copyObj(oldVo, OaScheduleHistory.class);
            history.setId(null);
            history.setWorkId(oldVo.getId().toString());
            if (user != null) {
                history.setCreateBy(user.getId());
                history.setUpdateBy(user.getId());
                oaScheduleTable.setUpdateBy(user.getId());
            }
            oaScheduleHistoryService.save(history);
        }
        oaScheduleTableService.saveOrUpdate(oaScheduleTable);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取OaScheduleTable", notes = "获取指定ID的OaScheduleTable信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaScheduleTable的ID", required = true, dataType = "string")
    public Result<OaScheduleTableVo> get(@PathVariable String id) {
        log.info("get with id:{}", id);
        OaScheduleTable oaScheduleTable=oaScheduleTableService.getById(id);
        OaScheduleTableVo oaScheduleTableVo=new OaBeanUtils<OaScheduleTableVo>().copyObj(oaScheduleTable,OaScheduleTableVo.class);
        return new Result<OaScheduleTableVo>().sucess(oaScheduleTableVo);
    }

    @Override
    @ApiOperation(value = "获取OaScheduleTable", notes = "获取指定businessId的OaScheduleTable信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "businessId", value = "OaScheduleTable的businessId", required = true, dataType = "string"),
            @ApiImplicitParam(name = "param", value = "根据条件查询",  dataType = "string")
    })
    public Result<List<OaScheduleTableVo>> getScheduleByBusinessId(@PathVariable(value="businessId") String businessId,@RequestBody OaScheduleTableQueryParam param){
        log.info("get with businessId:{}", businessId);
        List<OaScheduleTable> oaScheduleTableList=oaScheduleTableService.getScheduleByBusinessId(businessId,param);
        List<OaScheduleTableVo> oaScheduleTableVoList=new OaBeanUtils<OaScheduleTableVo>().copyObjs(oaScheduleTableList,OaScheduleTableVo.class);
        return new Result<List<OaScheduleTableVo>>().sucess(oaScheduleTableVoList);
    }

    @Override
    @ApiOperation(value = "列表查询OaScheduleTable", notes = "根据条件查询OaScheduleTable列表数据")
    public Result<List<OaScheduleMonthVo>> list(@Valid @RequestBody @ApiParam(name="oaScheduleTableQueryForm",value="OaScheduleTable查询参数",required=true) OaScheduleTableQueryForm oaScheduleTableQueryForm) {
        log.info("search with oaScheduleTableQueryForm:", oaScheduleTableQueryForm.toString());
        List<OaScheduleTable> oaScheduleTableList=oaScheduleTableService.selectList(oaScheduleTableQueryForm);
        List<OaScheduleMonthVo> oaScheduleTableVoList=new OaBeanUtils<OaScheduleMonthVo>().copyObjs(oaScheduleTableList,OaScheduleMonthVo.class);
        //
        return new Result<List<OaScheduleMonthVo>>().sucess(oaScheduleTableVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaScheduleTable", notes = "根据条件查询OaScheduleTable分页数据")
    public Result<IPage<OaScheduleTableVo>> search(@Valid @RequestBody @ApiParam(name="oaScheduleTableQueryForm",value="OaScheduleTable查询参数",required=true) OaScheduleTableQueryForm oaScheduleTableQueryForm) {
        log.info("search with oaScheduleTableQueryForm:", oaScheduleTableQueryForm.toString());
        IPage<OaScheduleTable> oaScheduleTablePage=oaScheduleTableService.selectPage(oaScheduleTableQueryForm);
        IPage<OaScheduleTableVo> oaScheduleTableVoPage=new OaBeanUtils<OaScheduleTableVo>().copyPageObjs(oaScheduleTablePage,OaScheduleTableVo.class);
        return new Result<IPage<OaScheduleTableVo>>().sucess(oaScheduleTableVoPage);
    }

    @Override
    @ApiOperation(value = "更新月视图OaScheduleTable", notes = "批量更新月视图")
    public Result updateMonthSchedule(@Valid @RequestBody @ApiParam(name="oaScheduleTableForms",value="更新月视图OaScheduleTable的form表单",required=true) OaScheduleTableForm[] oaScheduleTableForms) {
        boolean flag = false;
        for(OaScheduleTableForm form : oaScheduleTableForms){
            OaScheduleTable oaScheduleTable =new OaBeanUtils<OaScheduleTable>().copyObj(form,OaScheduleTable.class);
            if(form.getId()!=null){
                UserVo user = UserUtil.getUserVo();
                if(user!=null && user.getId()!=null) {
                    oaScheduleTable.setUpdateBy(user.getId());
                }
                flag = oaScheduleTableService.saveOrUpdate(oaScheduleTable);
            }
        }
        return Result.success(flag);
    }

    @Override
    @ApiOperation(value = "新增领导视图OaScheduleTable", notes = "批量新增领导视图")
    public Result addLeaderSchedule(@Valid @RequestBody @ApiParam(name="oaScheduleTableLeadForms",value="新增领导视图OaScheduleTable的form表单",required=true) OaScheduleTableLeadForm[] oaScheduleTableLeadForms) {
        boolean flag = false;
        for(OaScheduleTableLeadForm oaScheduleTableLeadForm : oaScheduleTableLeadForms){
            String businessId = oaScheduleTableLeadForm.getBusinessId();
            List<OaScheduleTableForm> list = oaScheduleTableLeadForm.getSchedules();
            for(OaScheduleTableForm form:list){
                form.setBusinessId(businessId);
                if((form.getWorkDesc()==null||"".equals(form.getWorkDesc()))&&form.getId()==null){
                    continue;
                }
                if(form.getWorkStatus()!=null&&!"".equals(form.getWorkStatus())&&!"S".equals(form.getWorkStatus())){
                    //本方法不修改非起草状态的数据，已审批通过后的数据，不能调整showFlag
                    continue;
                }
                if(form.getId()!=null&&"S".equals(form.getWorkStatus())&&(form.getWorkDesc()==null||"".equals(form.getWorkDesc()))){
                    //已保存的数据，且状态为起草，内容为空，则将此记录删除
                    oaScheduleTableService.removeById(form.getId());
                    continue;
                }
                form.setRowNum(oaScheduleTableLeadForm.getRowNum());
                form.setUserId(oaScheduleTableLeadForm.getUserId());
                form.setUserName(oaScheduleTableLeadForm.getUserName());
                form.setDeptId(oaScheduleTableLeadForm.getDeptId());
                form.setDeptName(oaScheduleTableLeadForm.getDeptName());
                OaScheduleTable oaScheduleTable =new OaBeanUtils<OaScheduleTable>().copyObj(form,OaScheduleTable.class);
                UserVo user = UserUtil.getUserVo();
                if(user!=null && user.getId()!=null){
                    oaScheduleTable.setCreateBy(user.getId());
                }
                if(form.getId()!=null){
                    if(user!=null && user.getId()!=null) {
                        oaScheduleTable.setUpdateBy(user.getId());
                    }
                }
                flag = oaScheduleTableService.saveOrUpdate(oaScheduleTable);
            }
        }
        return Result.success(flag);
    }


    @Override
    @ApiOperation(value = "查询周视图OaScheduleTable", notes = "根据businessId查询OaScheduleTable周视图数据")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "businessId", value = "OaScheduleTable的businessId", required = true, dataType = "string"),
            @ApiImplicitParam(name = "param", value = "根据条件查询",  dataType = "string")
    })
    public Result<List<OaScheduleTableLeadVO>> getWeekViewScheduleByBusinessId(@PathVariable(value="businessId") String businessId, @RequestBody OaScheduleTableQueryParam param) {
        log.info("get with businessId:{}", businessId);
        String firstDateStr =  businessId.split("~")[0];
        LocalDateTime firstDateTime =  LocalDateTime.parse(firstDateStr+" 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<OaScheduleTable> oaScheduleTableList=oaScheduleTableService.getScheduleByBusinessId(businessId,param);
        Map<String,Map<Integer,OaScheduleTableVo>> map = new HashMap<String,Map<Integer,OaScheduleTableVo>>();
        Map<String,String> rowStatusMap = new HashMap<String,String>();
        //循环7天，没填按上午和下午，分别赋值
        //遍历此周的记录数
        for (OaScheduleTable entity : oaScheduleTableList) {
            DayOfWeek dayOfWeek = entity.getStartTime().getDayOfWeek();//返回当前的星期，1-7
            int hour = entity.getStartTime().getHour();//24小时制，0-23
            int ofWeekValue = dayOfWeek.getValue();//1-7
            //通过创建人及行号获取日程
            Map<Integer,OaScheduleTableVo> row = map.get(entity.getCreateBy()+"_"+entity.getRowNum());
            //row:column(1-14)->Schedule
            if (row == null) {
                //如果该行没存在过，则初始化
                row = new HashMap<Integer,OaScheduleTableVo>();
            }
            if(hour < 12){
                //如果是上午
                row.put(ofWeekValue*2-1,new OaBeanUtils<OaScheduleTableVo>().copyObj(entity,OaScheduleTableVo.class));
            }else{
                row.put(ofWeekValue*2,new OaBeanUtils<OaScheduleTableVo>().copyObj(entity,OaScheduleTableVo.class));
            }
            map.put(entity.getCreateBy()+"_"+entity.getRowNum(),row);
            String rowStatus = "S";
            if(rowStatusMap.get(entity.getCreateBy()+"_"+entity.getRowNum())!=null){
                if(!"S".equals(entity.getWorkStatus())){
                    rowStatus = entity.getWorkStatus();
                }
                if(!rowStatus.equals(rowStatusMap.get(entity.getCreateBy()+"_"+entity.getRowNum()))){
                    rowStatus = "O";
                }
            }else{
                rowStatus = entity.getWorkStatus();
            }
            rowStatusMap.put(entity.getCreateBy()+"_"+entity.getRowNum(),rowStatus);
        }

        List<OaScheduleTableLeadVO> oaScheduleTableLeadVoList=new ArrayList<OaScheduleTableLeadVO>();


        List rowList = new ArrayList();
        for(OaScheduleTable entity : oaScheduleTableList){
            if(rowList.contains(entity.getCreateBy()+"_"+entity.getRowNum())){
                continue;
            }
            OaScheduleTableLeadVO vo = new OaScheduleTableLeadVO();
            vo.setUserName(entity.getUserName());
            vo.setUserId(entity.getUserId());
            vo.setDeptName(entity.getDeptName());
            vo.setDeptId(entity.getDeptId());
            vo.setRowNum(entity.getRowNum());
            vo.setBusinessId(entity.getBusinessId());
            Map<Integer,OaScheduleTableVo> row = map.get(entity.getCreateBy()+"_"+entity.getRowNum());
            rowList.add(entity.getCreateBy()+"_"+entity.getRowNum());
            List<OaScheduleTableVo> list = new ArrayList<OaScheduleTableVo>();
            LocalDateTime curTime = firstDateTime;
            for(int i=1;i<=14;i++){
                OaScheduleTableVo rowData =  row.get(i);
                if(rowData == null){
                    rowData = new OaScheduleTableVo();
                    rowData.setRowNum(entity.getRowNum());
                    rowData.setBusinessId(entity.getBusinessId());
                    rowData.setWorkType(entity.getWorkType());
                    rowData.setWorkStatus("S");
                    rowData.setShowFlag("0");
                    if("院校日历".equals(entity.getWorkType())){
                        rowData.setWorkName("1");
                    }
                    if(i==1){
                        curTime = curTime.withHour(9);
                        rowData.setStartTime(curTime);
                        curTime = curTime.withHour(12);
                        rowData.setEndTime(curTime);
                    }else{
                        if(i % 2 == 1){
                            curTime = curTime.plusDays(1);
                            curTime = curTime.withHour(9);
                            rowData.setStartTime(curTime);
                            curTime = curTime.withHour(12);
                            rowData.setEndTime(curTime);
                        }else{
                            curTime = curTime.withHour(14);
                            rowData.setStartTime(curTime);
                            curTime = curTime.withHour(18);
                            rowData.setEndTime(curTime);
                        }
                    }
                }
                list.add(rowData);
            }
            vo.setRowStatus(rowStatusMap.get(entity.getCreateBy()+"_"+entity.getRowNum()));
            vo.setSchedules(list);
            oaScheduleTableLeadVoList.add(vo);
        }
        return new Result<List<OaScheduleTableLeadVO>>().sucess(oaScheduleTableLeadVoList);
    }

    @Override
    @ApiOperation(value = "提交审批", notes = "提交审批")
    public Result submit(@Valid @RequestBody @ApiParam(name="oaWorkflowParamForm",value="流程提交参数，其中需要自定义参数filterIds",required=true) OaWorkflowParamForm taskForm){
        boolean flag = oaScheduleTableService.submitApply(taskForm.getOaWorkflowDriverForm(), taskForm.getNextNodeParamVo(),taskForm.getDiyParam().get("filterIds"), UserUtil.getUserVo(), UserUtil.getDept());
        if(flag){
            return Result.success();
        }else{
            return Result.fail("流程提交出错");
        }
    }


}



