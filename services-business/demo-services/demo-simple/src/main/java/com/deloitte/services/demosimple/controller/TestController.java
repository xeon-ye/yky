package com.deloitte.services.demosimple.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.feign.BpmOperatorFeignService;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryWrapper;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.demomybatiesauto.feign.UserDemoFeignService;
import com.deloitte.platform.api.demomybatiesauto.param.UserDemoQueryForm;
import com.deloitte.platform.api.demomybatiesauto.vo.UserDemoForm;
import com.deloitte.platform.api.demomybatiesauto.vo.UserDemoVo;
import com.deloitte.platform.api.fileservice.feign.FileOperatorFeignService;
import com.deloitte.platform.api.fileservice.vo.FileInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.demosimple.properties.TestPropertis;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : jackliu
 * @Date : Create in 11:51 24/01/2019
 * @Description :
 * @Modified :
 */

@RestController
@Api("测试接口")
@Slf4j
@RequestMapping("/demo/test")
@RefreshScope
public class TestController {

    @Autowired
    private TestPropertis testPropertis;

    @Autowired
    private UserDemoFeignService userFeignService;

    @Autowired
    private FileOperatorFeignService fileOperatorFeignService;

    @Autowired
    private BpmProcessTaskFeignService bpmProcessTaskFeignService;

    @GetMapping("/propertis")
    @ApiOperation(value = "测试配置文件", notes = "测试配置文件是否会自动刷新")
    public String testPropertis() throws JsonProcessingException  {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuffer sb=new StringBuffer("");
        sb.append("username: ").append(testPropertis.getUsername()).append("\r\n");
        sb.append("Password: ").append(testPropertis.getPassword()).append("\r\n");
        sb.append("strlist: ").append(objectMapper.writeValueAsString(testPropertis.getStrlist()));

        return  sb.toString();
    }

    @DeleteMapping("/addUser")
    public void deleteUser(@RequestBody UserDemoForm userDemoForm){
        log.error("==================新增==================");
        Result result=userFeignService.add(userDemoForm);
        result.getCode();
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable long id){
        log.error("==================根据ID删除==================");
        Result result=userFeignService.delete(id);
        result.getCode();
    }

    @GetMapping("/getUser/{id}")
    public void findUserById(@PathVariable long id){
        log.error("==================根据ID查询==================");
        Result result=userFeignService.get(id);
        if(result.getData()!=null){
            UserDemoVo userVo=(UserDemoVo)result.getData();
            log.error(userVo.getName());
        }
    }
    @GetMapping("/getUserList/{id}")
    public void findUserList(){
        log.error("==================列表查询==================");
        Result resultList=userFeignService.list(new UserDemoQueryForm());
        if(resultList.getData()!=null){
            List<UserDemoVo> userVos=(List<UserDemoVo>) resultList.getData();
            for(UserDemoVo vo:userVos){
                log.error(vo.getName());
            }
        }
    }


    @GetMapping("/getUserListPage/{id}")
    public void findUserPage(){
        log.error("==================分页查询==================");
        Result<GdcPage<UserDemoVo>> resultPage=userFeignService.search(new UserDemoQueryForm());
        if(resultPage.getData()!=null) {
            GdcPage<UserDemoVo> userPage = (GdcPage<UserDemoVo>) resultPage.getData();
            log.error("" + userPage.getTotal());
            for (UserDemoVo vo : userPage.getContent()) {
                log.error(vo.getName());
            }
        }
    }


    @PostMapping("/uploadfile")
    public void uploadfile(@RequestPart("file") MultipartFile file){
        log.error("==================开始上传文件==================");

        Result<FileInfoVo> fileInfoVoResult= fileOperatorFeignService.uploadFile(file);

        log.error(fileInfoVoResult.getMesg());
    }

    @PostMapping("/uploadfiles")
    public void uploadfiles(@RequestPart("files") MultipartFile[] files){
        log.error("==================开始批量文件上传==================");

        Result<List<FileInfoVo>> fileInfoVoResult= fileOperatorFeignService.uploadFiles(files);

        log.error(fileInfoVoResult.getMesg());
    }

    @PostMapping("/downloadFile")
    public void downloadFile(@RequestPart("file") MultipartFile file){
        log.error("==================开始下载文件==================");

        Result result= fileOperatorFeignService.downloadFile(1104018858647224321L);

        log.error(result.getMesg());
    }


    @GetMapping("/getTask/{id}")
    public void findTaskById(@PathVariable long id){
        log.error("==================根据ID查询==================");

        BpmProcessTaskQueryWrapper wrapper=new BpmProcessTaskQueryWrapper();

        Map<String,String[]> queryMap=new HashMap<>();
        String[] ss={"between","2019-02-12 12:01:23,2019-04-15 15:33:00"};
        String[] ss1={"eq",String.valueOf(id)};
        String[] ss3={"like","借款"};
        String[] ss4={"in","已批准,待审批"};
        String[] ss5={"notIn","请假单,调休单"};

        queryMap.put(BpmProcessTaskQueryForm.CREATE_TIME,ss);
        queryMap.put(BpmProcessTaskQueryForm.APPROVER_ACOUNT,ss1);
        queryMap.put(BpmProcessTaskQueryForm.TASK_TITLE,ss3);
        queryMap.put(BpmProcessTaskQueryForm.TASK_STAUTS,ss4);
        queryMap.put(BpmProcessTaskQueryForm.OBJECT_TYPE,ss5);

        wrapper.setWrapperMap(queryMap);

        Result<GdcPage<BpmProcessTaskVo>> taskVopage=bpmProcessTaskFeignService.searchByWrapper(wrapper);
        if(taskVopage.getData()!=null) {
            GdcPage<BpmProcessTaskVo> taskVo = (GdcPage<BpmProcessTaskVo>) taskVopage.getData();
            log.error("" + taskVo.getTotal());
            for (BpmProcessTaskVo vo : taskVo.getContent()) {
                log.error(vo.getTaskKey());
            }
        }
    }

}
