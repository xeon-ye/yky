package com.deloitte.services.contract.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.deloitte.platform.api.contract.vo.BasicAttamentMapForm;
import com.deloitte.platform.api.contract.vo.BasicAttamentMapVo;
import com.deloitte.platform.api.contract.vo.BasicInfoForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.contract.entity.BasicAttamentMap;
import com.deloitte.services.contract.service.IBasicAttamentMapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author : hemingzheng
 * @Date : Create in 2019-04-25
 * @Description :   扫描件控制器实现类
 * @Modified :
 */
@Api(tags = "合同扫描件操作接口")
@Slf4j
@RestController
public class ContractScanController {
    @Autowired
    private IBasicAttamentMapService basicAttamentMapService;
    @ApiOperation(value = "上传扫描件", notes="上传扫描件")
    @PostMapping("/saveFileUrl")
    public Result saveFileUrl(@Valid @RequestBody BasicInfoForm basicInfoForm){
        ArrayList<BasicAttamentMapForm> list = basicInfoForm.getBasicAttamentList();
        Long contractId = basicInfoForm.getId();
        QueryWrapper<BasicAttamentMap> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BasicAttamentMap.CONTRACT_ID, contractId);
        queryWrapper.in(BasicAttamentMap.FILE_TYPE,"FIL6000","FIL7000");
        basicAttamentMapService.remove(queryWrapper);
        if(null !=list && list.size()>0){
            for(BasicAttamentMapForm basicAttamentMapForm : list){
                BasicAttamentMap basicAttamentMap = new BeanUtils<BasicAttamentMap>().copyObj(basicAttamentMapForm, BasicAttamentMap.class);
                basicAttamentMap.setContractId(contractId);
                basicAttamentMapService.save(basicAttamentMap);
            }
        }
        return Result.success();
    }

    @ApiOperation(value = "下载正文", notes = "下载正文")
    @PostMapping("/downFile")
    public  Result<BasicAttamentMapVo> downFile(@Valid @RequestBody BasicInfoForm basicInfoForm){
        QueryWrapper<BasicAttamentMap> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(BasicAttamentMap.CONTRACT_ID, basicInfoForm.getId());
        queryWrapper.eq(BasicAttamentMap.FILE_TYPE, "FIL6000");
        BasicAttamentMap basicAttamentMap = basicAttamentMapService.getOne(queryWrapper);
        BasicAttamentMapVo basicAttamentMapVo = new BeanUtils<BasicAttamentMapVo>().copyObj(basicAttamentMap, BasicAttamentMapVo.class);
        return new Result<BasicAttamentMapVo>().sucess(basicAttamentMapVo);
    }


}
