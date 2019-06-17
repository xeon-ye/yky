package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.AcceptVo;
import com.deloitte.platform.api.dss.finance.vo.IncomeBudgetVo;
import com.deloitte.platform.api.dss.finance.vo.ProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.service.IFinAchivementService;
import com.deloitte.services.dss.finance.service.IFinProjectService;
import com.deloitte.services.dss.util.TimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : chitose
 * @Date : Create in 2019-04-09
 * @Description : Budget服务类接口
 * @Modified :
 */
@Api(tags = "14.经费管控-三年项目库")
@Slf4j
@RestController
@RequestMapping("/dss/finance")
public class ProjectController {
    @Autowired
    private IFinProjectService finProjectService;

    @ApiOperation(value = "经费管控-三年项目库",notes = "经费管控-三年项目库")
    @PostMapping("/selectProject")
    public Result selectProject(@Valid @RequestBody AcceptVo myData){
        Map paramMap = new HashMap();
        if(myData.getIndexCodes().isEmpty() || myData.getIndexCodes() == null){
            List<String> list = new ArrayList<String>();
            list.add("FIND0067");
            paramMap.put("indexCodes",list);
        }else{
            paramMap.put("indexCodes",myData.getIndexCodes());
        }
        paramMap.put("comCode",myData.getComCode());
        Map result = new HashMap();

        //项目类型
        List<ProjectVo> proIndexCode = finProjectService.selectProIndexCode();
        result.put("proIndexCode",proIndexCode);

        //三年项目库预算
        List<ProjectVo> project = finProjectService.selectProject(paramMap);
        result.put("project",project);

        //三年项目库预算分布情况
        List<ProjectVo> proEveStu = finProjectService.selectProEveStu(paramMap);
        result.put("proEveStu",proEveStu);

        //三年项目库预算情况
        paramMap = new HashMap();
        paramMap.put("indexCodes",myData.getIndexCodes());
        paramMap.put("comCode",myData.getComCode());
        List<ProjectVo> proStu = finProjectService.selectProStu(paramMap);
        result.put("proStu",proStu);

        return new Result().sucess(result);
    }


}
