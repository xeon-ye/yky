package com.deloitte.services.contract.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmApprovalMatrixVo;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.contract.param.BasicInfoQueryForm;
import com.deloitte.platform.api.contract.vo.*;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.api.isump.OrganizationClient;
import com.deloitte.platform.api.isump.UserClient;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.mpr.vo.MprEvaFileInfoFieldForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.enums.VoucherStatusEnums;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.common.util.Fileutil;
import com.deloitte.services.contract.entity.*;
import com.deloitte.services.contract.entity.Process;
import com.deloitte.services.contract.service.*;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Api(tags = "3-合同处理操作接口")
@Slf4j
@RestController
@RequestMapping("/contractInfoDispose")
public class ContractDisposeController {

    @Autowired
    private IBasicInfoService iBasicInfoService;
    @Autowired
    private ISignInfoService iSignInfoService;
    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;
    @Autowired
    private IBasicAttamentMapService iBasicAttamentMapService;
    @Autowired
    private IApprovalVoucherService iApprovalVoucherService;
    @Autowired
    private IBPMService ibpmService;
    @Autowired
    private IProcessOperatorTransferService iProcessOperatorTransferService;
    @Autowired
    private ICommonService iCommonService;
    @Autowired
    private ISysSignSubjectInfoService sysSignSubjectInfoService;
    @Autowired
    private IApprovalOpinionService approvalOpinionService;
    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;
    @Autowired
    private OrganizationClient organizationClient;

    @Autowired
    private OrganizationFeignService organizationFeignService;
    @Autowired
    private UserClient userClient;
    @Autowired
    private ICommonService commonService;
    @Autowired
    private IProcessService processService;
    @Autowired
    private IApprovalVoucherService approvalVoucherService;

    /**
     * yangyuanqing
     * @param basicInfoForm
     * @return
     */
    @ApiOperation(value = "3-1-1合同定稿按钮保存", notes = "3-1-1合同定稿按钮保存")
    @PostMapping("/saveContractFinalize")
    public Result<BasicInfoVo> saveContractFinalize(@RequestBody BasicInfoForm basicInfoForm){
        UserVo userVo = commonService.getCurrentUser();
        BasicInfoVo basicInfoVo = iBasicInfoService.saveContractFinalize(basicInfoForm, userVo);
        return new Result<BasicInfoVo>().sucess(basicInfoVo);
    }
    @ApiOperation(value = "3-2-1保存/修改合同签署信息", notes = "3-2-1保存/修改合同签署信息")
    @PostMapping("/save")
    public Result insertOrUpdateSign(@Valid @RequestBody SignInfoForm signInfoForm){
//        ApprovalVoucher approvalVoucher=iApprovalVoucherService.getById(signInfoForm.getApproveId());
//        BasicInfo basicInfo=iBasicInfoService.getById(approvalVoucher.getContractId());
//        signInfoForm.setContractId(basicInfo.getId());
//        signInfoForm.setContractName(basicInfo.getContractName());
        UserVo userVo = commonService.getCurrentUser();
        iSignInfoService.insertOrUpdateSign(signInfoForm,userVo);
        return Result.success();
    }
//    @ApiOperation(value = "3-2-1合同打印界面提交", notes = "3-2-1合同打印界面提交")
//    @PostMapping("/insertOrUpdateContract")
//    public Result<BasicInfoVo> insertOrUpdateContract(@Valid @RequestBody BasicInfoForm basicInfoForm, UserVo userVo, DeptVo deptVo){
//        BasicInfo result = iBasicInfoService.addContractInfo(basicInfoForm, userVo, deptVo);
//        BasicInfoVo basicInfoVo =new BeanUtils<BasicInfoVo>().copyObj(result, BasicInfoVo.class);
//        return new Result<BasicInfoVo>().sucess(basicInfoVo);
//    }

    @ApiOperation(value = "3-2-1合同打印界面下载正文及附件", notes = "3-2-1合同打印界面下载正文及附件")
    @GetMapping("/downStampFile")
    public void downStampFile( HttpServletRequest request, HttpServletResponse response){
        SignInfoForm signInfoForm = new SignInfoForm();
        signInfoForm.setContractId(request.getParameter("contractId"));
        signInfoForm.setStampWay(request.getParameter("stampWay"));
        List<BasicAttamentMap> list= iSignInfoService.downStampFile(signInfoForm);
        BasicInfo contract = iBasicInfoService.getById(signInfoForm.getContractId());
        if(null != contract && list.size()>0){
            this.download(contract.getContractName(),response,list);
        }
    }

    @ApiOperation(value = "3-4-1合同签署界面原型", notes = "3-4-1合同签署界面原型")
    @PostMapping("/selectSignInfo")
    public Result<List<SignInfoVo>> selectSignInfo(@Valid @RequestBody SignInfoForm signInfoForm){
       List<SignInfo> signInfo=iSignInfoService.selectSignInfo(signInfoForm);
       List<SignInfoVo> signInfoVo=new BeanUtils<SignInfoVo>().copyObjs(signInfo,SignInfoVo.class);
        return new Result<List<SignInfoVo>>().success(signInfoVo);
    }
    @ApiOperation(value = "查询履行人/经办人机构名称", notes = "查履行人/经办人机构名称")
    @PostMapping("/selectOrganization/{orgCode}")
    public Result selectOrganization(@PathVariable(value = "orgCode") String orgCode){
        //需调用Hr就接口获取履行人信息
//          organizationClient.getOrgTreeByCode(orgCode);
        return new Result<List<OrganizationVo>>().success(organizationFeignService.getOrgTreeByCode(orgCode));
    }

    @ApiOperation(value = "查询履行人/经办人", notes = "查履行人/经办人")
    @PostMapping("/selectUser/{orgCode}")
    public Result selectUser(@PathVariable(value = "orgCode") String orgCode){
        //需调用Hr就接口获取履行人信息
//          organizationClient.getOrgTreeByCode(orgCode);
        return new Result<List<UserVo>>().success(userClient.getByOrgCode(orgCode));
    }

    @ApiOperation(value = "3-4-1合同签署界面上传授权委托书", notes = "3-4-1合同签署界面上传授权委托书")
    @PostMapping("/uploadFile")
    public Result uploadFile(@Valid @RequestBody BasicAttamentMapForm basicAttamentMapForm){
        iBasicAttamentMapService.addBasicAttamentMap(basicAttamentMapForm);
//        Result<FileInfoVo> result =  fileOperatorFeignService.uploadFile(file);
        return Result.success();
    }
    @ApiOperation(value = "3-4-1合同签署界面提交", notes = "3-4-1合同签署界面提交")
    @PostMapping("/updateSignInfo")
    public Result updateSignInfo(@Valid @RequestBody BasicInfoVo basicInfoVo){
//        BasicInfo basicInfo=iBasicInfoService.getById(signInfoForm.getContractId());
//        basicInfo.setSignStatueTime(LocalDateTime.now());
//        basicInfo.setExecuterCode(basicInfoForm.getExecuterCode());
//        basicInfo.setExecuter(basicInfoForm.getExecuter());
//        iBasicInfoService.updateById(basicInfo);
        BasicInfo basicInfo = iBasicInfoService.getById(basicInfoVo.getId());
        basicInfo.setExecuterCode(basicInfoVo.getExecuterCode());
        basicInfo.setExecuter(basicInfoVo.getExecuter());
        basicInfo.setExecuterOrg(basicInfoVo.getExecuterOrg());
        basicInfo.setExecuterOrgId(basicInfoVo.getExecuterOrgId());
        iBasicInfoService.updateById(basicInfo);
        List<SignInfoVo> signInfoList = new BeanUtils<SignInfoVo>().copyObjs(basicInfoVo.getSignInfoVo(),SignInfoVo.class);
       iSignInfoService.updateSignInfo(signInfoList);

        return Result.success();
    }

    @ApiOperation(value = "3-6-1合同移交审批单页面查询", notes = "3-6-1合同移交审批单页面查询")
    @PostMapping("/selectContractTransfer")
    public Result<BasicInfoVo> selectContractTransfer(@Valid @RequestBody BasicInfoVo basicInfoVo){
        BasicInfo basicInfo = iBasicInfoService.getById(basicInfoVo.getContractId());
        basicInfoVo.setTransferTime(LocalDateTime.now());
        basicInfoVo.setOperator(basicInfo.getOperator());
        basicInfoVo.setOrg(basicInfo.getOrg());
        basicInfoVo.setContactNum(basicInfo.getContactNum());
        basicInfoVo.setOperatorCode(basicInfo.getOperatorCode());
        basicInfoVo.setOrgCode(basicInfo.getOrgCode());
        return new Result<BasicInfoVo>().success(basicInfoVo);
    }

    @ApiOperation(value = "3-6-1合同移交审批单页面发起经办人移交流程", notes = "3-6-1合同移交审批单页面发起经办人移交流程")
    @PostMapping("/operatorTransferApprove")
    public Result operatorTransferApprove(@Valid @RequestBody ProcessOperatorTransferVo processOperatorTransferVo){
        AssertUtils.asserts(null == processOperatorTransferVo ,ContractErrorType.CONTRACT_OPERATOR_TRANSFER_PARAMETER_ERROR);
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();

        ProcessOperatorTransfer processOperatorTransfer = new BeanUtils<ProcessOperatorTransfer>().copyObj(processOperatorTransferVo, ProcessOperatorTransfer.class);
        List<BasicInfoVo> list = processOperatorTransferVo.getListBasicInfoVo();
        StringBuffer str = new StringBuffer();
        if(list.size()>0){
            for(int i=0;i<list.size();i++){
                if (i == 0) {
                    str.append(list.get(i).getId());
                }else {
                    str.append("," + list.get(i).getId());
                }
            }
            processOperatorTransfer.setContractId(str.toString());
        }
        String contractId = str.toString();
        //判断单据是否提交过审批
        QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ApprovalVoucher.BUSINESS_ID,contractId);
        queryWrapper.eq(ApprovalVoucher.VOUCHER_STATUS,VoucherStatusEnums.AUDITING.getCode());
        queryWrapper.eq(ApprovalVoucher.VOUCHER_TYPE,VoucherTypeEnums.CONTRACT_OPERATOR_TRANSFER.getCode());
        ApprovalVoucher voucher = iApprovalVoucherService.getOne(queryWrapper);
        AssertUtils.asserts(null != voucher, ContractErrorType.CONTRACT_VOUCHER_OPERATOR_IS_DOING);

        //数据存入经办人移交关系表
        iProcessOperatorTransferService.save(processOperatorTransfer);
        //新建一个单据
        ApprovalVoucher approvalVoucher = iApprovalVoucherService.generateNewVoucher(processOperatorTransfer.getId(), VoucherTypeEnums.CONTRACT_OPERATOR_TRANSFER);
        Map<String, String> map = new HashMap<>();
        //获取流程信息
        QueryWrapper<Process> processQueryWrapper = new QueryWrapper<>();
        processQueryWrapper.eq(Process.PROCESS_TYPE,VoucherTypeEnums.CONTRACT_OPERATOR_TRANSFER.getCode());
        Process process = processService.getOne(processQueryWrapper);
        AssertUtils.asserts(null == process , ContractErrorType.PROCESS_IS_NULL);
        //流程提交参数
        Result result = ibpmService.startAuditProcess(approvalVoucher, userVo, organizationVo, process.getProcessDefineKey(), map);
        //流程发起成功
        if (result.isSuccess()) {
            processOperatorTransfer.setProcessId( process.getProcessDefineKey());
            processOperatorTransfer.setProcessInstanceId(result.getData().toString());
            processOperatorTransfer.setStatue("EXA2000");
            //保存流程信息到经办人移交关系表
            iProcessOperatorTransferService.updateById(processOperatorTransfer);
        }
        return Result.success(true);
    }

    @ApiOperation(value = "3-3-1 合同作废",notes = "3-3-1 合同作废")
    @PostMapping("/queryCancelContract")
    public Result<BasicInfoVo> queryCancelContract(@Valid @RequestBody BasicInfoForm basicInfoForm){
        return  iCommonService.getContractByContractId(basicInfoForm.getId().toString());
    }
    @ApiOperation(value = "3-3-1 合同作废提交流程",notes = "3-3-1 合同作废提交流程")
    @PostMapping("/contractCancelApprove")
    public Result contractCancelApprove(@Valid @RequestBody TaskNodeActionVO actionVo){
        AssertUtils.asserts(null == actionVo || null == actionVo.getContractId() || null == actionVo.getProcessDefineKey(),ContractErrorType.CONTRACT_UNABLE_PARAMETER_ERROR);
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();

        //流程提交参数
        Map<String,String> processVariables = new HashMap<>();
        if(null != actionVo.getNextNodeVO() && null !=  actionVo.getNextNodeVO().getConditionText()){
            //审批路由参数
            String conditionText =  actionVo.getNextNodeVO().getConditionText();
            if(!"".equals(conditionText)){
                String key = conditionText.replaceAll("\\{","").replaceAll("\\}","").split("==")[0].trim();
                String value = conditionText.replaceAll("\\{","").replaceAll("\\}","").split("==")[1].trim();
                processVariables.put(key,value);
            }
        }
        //用户选择的下一个节点处理人
        UserVo accepVo = new UserVo();
        if(null !=  actionVo.getNextNodeVO() && null !=  actionVo.getNextNodeVO().getBpmApprovalMatrixVoList()){
            List<BpmApprovalMatrixVo> bpmApprovalMatrixVoList =  actionVo.getNextNodeVO().getBpmApprovalMatrixVoList();
            if(null != bpmApprovalMatrixVoList && bpmApprovalMatrixVoList.size() > 0){
                BpmApprovalMatrixVo bpmApprovalMatrixVo = bpmApprovalMatrixVoList.get(0);
                accepVo.setId(bpmApprovalMatrixVo.getAccountNum());
                accepVo.setName(bpmApprovalMatrixVo.getAccountName());
            }
        }
        //判断单据是否提交过审批
        QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ApprovalVoucher.BUSINESS_ID,actionVo.getContractId());
        queryWrapper.eq(ApprovalVoucher.VOUCHER_STATUS,VoucherStatusEnums.AUDITING.getCode());
        queryWrapper.eq(ApprovalVoucher.VOUCHER_TYPE,VoucherTypeEnums.CONTRACT_UNABLE.getCode());
        ApprovalVoucher voucher = iApprovalVoucherService.getOne(queryWrapper);
        AssertUtils.asserts(null != voucher, ContractErrorType.CONTRACT_VOUCHER_CANCEL_IS_DOING);

        //新建一个单据
        ApprovalVoucher approvalVoucher = iApprovalVoucherService.generateNewVoucher(Long.parseLong(actionVo.getContractId()),VoucherTypeEnums.CONTRACT_UNABLE);
        //流程传入参数
        Result result = ibpmService.autoStartAuditProcess(approvalVoucher,userVo,organizationVo,accepVo,actionVo.getProcessDefineKey(),processVariables);
        //流程启动成功
        if(result.isSuccess()){
            //修改合同状态
            BasicInfo basicInfo = iBasicInfoService.getById(Long.parseLong(actionVo.getContractId()));
            String oldStatue = basicInfo.getStatue();
            // 将作废前的合同信息状态保存在备用字段里
            basicInfo.setSpareField2(oldStatue);
            basicInfo.setStatue("EXA14000");
            basicInfo.setStatueList("STA4000");
            iBasicInfoService.updateById(basicInfo);
            //将作废审批原因写入审批意见表
            //新增actionVO.getObjectId()所有审批节点
            BpmProcessTaskQueryForm bpmProcessTaskQueryForm = new BpmProcessTaskQueryForm();
            bpmProcessTaskQueryForm.setProcessInstanceId(result.getData()+"");
            bpmProcessTaskQueryForm.setTaskKey("start");
            BpmProcessTaskVo  currTask = bpmProcessTaskFeignService.list(bpmProcessTaskQueryForm).getData().get(0);
            ApprovalOpinion approvalOpinion = new ApprovalOpinion();
            approvalOpinion.setContractId(actionVo.getContractId());
            List<ApprovalOpinionVo> ApprovalOpinionList = actionVo.getApprovalOpinionList();
            if (null != ApprovalOpinionList && ApprovalOpinionList.size() > 0) {
                for (ApprovalOpinionVo approvalOpinionVo : ApprovalOpinionList) {
                    approvalOpinionVo.setContractId(approvalVoucher.getBusinessId() + "");
                    approvalOpinionVo.setProcessTaskId(currTask.getTaskId() + "");
                    ApprovalOpinion opinion = new BeanUtils<ApprovalOpinion>().copyObj(approvalOpinionVo, ApprovalOpinion.class);
                    approvalOpinionService.save(opinion);
                }
            }

        }
        return result;
    }
    /**
     * 文件打包zip并下载
     * @param response
     * @param
     */
    private void download(String contractName , HttpServletResponse response, List<BasicAttamentMap> list) {
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        ZipOutputStream zipOutputStream = null;
        FileInputStream fileInputStream = null;

        try {

            response.setHeader("Content-Disposition", "attachment;fileName=" + new String((contractName+".zip").getBytes("GB2312"), "ISO8859-1") );
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
            zipOutputStream.setMethod(ZipOutputStream.DEFLATED);
            for (int i = 0; i < list.size(); i++) {
//                File file = files.get(i);
                String url = list.get(i).getFileUrl();
                String contractId = list.get(i).getContractId().toString();
                String name = list.get(i).getFileName();
                Long size = list.get(i).getFileSize();
//                FileInputStream fileInputStream = new FileInputStream(file);
                InputStream inputStream = Fileutil.getInputStreamFromUrl(url);
//                byte[] data = new byte[Integer.parseInt(String.valueOf(size))];
//                byte[] data = new byte[inputStream.available()];
                // 添加ZipEntry，name加上i防止文件重名
                zipOutputStream.putNextEntry(new ZipEntry(i + name));
                int byteCount = 0;
                byte[] data = new byte[1024];
                while ((byteCount = inputStream.read(data)) != -1) {
                    zipOutputStream.write(data, 0, byteCount);
                }
                zipOutputStream.closeEntry();
                inputStream.close();
            }
//
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (Objects.nonNull(zipOutputStream)) {
                    zipOutputStream.flush();
                    zipOutputStream.close();
                }
            } catch (IOException e) {
                log.error("文件下载异常！", e);
                throw new BaseException(PlatformErrorType.SYSTEM_ERROR, "下载失败！");
            }
        }


    }

    @ApiOperation(value = "3-5-1合同基本信息推送财务系统",notes = "3-5-1合同基本信息推送财务系统")
    @PostMapping("/selectBasicInfoToFssc")
    public Result selectBasicInfoToFssc(@Valid @RequestBody BasicInfoForm basicInfoForm){
        Long contractId = basicInfoForm.getId();
        Result result = iBasicInfoService.selectBasicInfoToFssc(contractId);
        return result;
    }

    @ApiOperation(value = "3-5-2合同基本信息推送财务系统(定时器)",notes = "3-5-1合同基本信息推送财务系统(定时器)")
    @PostMapping("/selectBasicInfoToFsscTask")
    public Result selectBasicInfoToFsscTask(@Valid @RequestBody BasicInfoForm basicInfoForm){
        Result result = iBasicInfoService.selectBasicInfoByFlag();
        return result;
    }

    @ApiOperation(value = "3-2-2查询合同基本信息和审批历史", notes = "3-2-2查询合同基本信息和审批历史")
    @PostMapping("/getContractAndHistory")
    public Result getContractAndHistory(@Valid @RequestBody BasicInfoForm basicInfoForm){
        String id = basicInfoForm.getId().toString();
        ApprovalVoucher approvalVoucher = approvalVoucherService.getApprovalFrist(id);
        return commonService.getContractByObjectId(approvalVoucher.getId().toString());
    }
}
