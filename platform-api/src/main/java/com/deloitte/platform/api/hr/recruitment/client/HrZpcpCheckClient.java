package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.HrZpcpCheckQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpCheckForm;
import com.deloitte.platform.api.hr.recruitment.vo.HrZpcpCheckVo;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpCheckAndNumberVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-01
 * @Description :  HrZpcpCheck控制器接口
 * @Modified :
 */
public interface HrZpcpCheckClient {

    public static final String path="/hr/recruitment/hr-zpcp-check";


    /**
     *  POST---新增
     * @param hrZpcpCheckForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrZpcpCheckForm hrZpcpCheckForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrZpcpCheckForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrZpcpCheckForm hrZpcpCheckForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrZpcpCheckVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrZpcpCheckQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrZpcpCheckVo>> list(@Valid @RequestBody HrZpcpCheckQueryForm hrZpcpCheckQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrZpcpCheckQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrZpcpCheckVo>> search(@Valid @RequestBody HrZpcpCheckQueryForm hrZpcpCheckQueryForm);

    /**
     *  Post----通过审核类型和申报信息表id查询评审结果
     * @param  queryForm
     * @return
     */
    @PostMapping(value = path+"/getCheckByDeclareIdAndCheckType")
    Result getCheckByDeclareIdAndCheckType(@Valid @RequestBody HrZpcpCheckQueryForm queryForm );


    /**
     *  POST---新增
     * @param hrZpcpCheckForm
     * @return
     */
    @PostMapping(value = path+"/addOrUpdate")
    Result addOrUpdate(@Valid @ModelAttribute HrZpcpCheckForm hrZpcpCheckForm);


    @PostMapping(value = path+"/importChcekLearning")
    @ApiOperation(value = "学术委员会导入维护评审结果", notes = "导入维护评审结果")
    Result importChcekLearning(MultipartFile file);

    @PostMapping(value = path+"/importChcekProfessor")
    @ApiOperation(value = "学术委员会导入维护评审结果", notes = "导入维护评审结果")
    Result importChcekProfessor(MultipartFile file);

    @PostMapping(value = path+"/importChcekTeaching")
    @ApiOperation(value = "学术委员会导入维护评审结果", notes = "导入维护评审结果")
    Result importChcekTeaching(MultipartFile file);

    @PostMapping(value = path+"/getNumberAndCheck")
    Result<ZpcpCheckAndNumberVo> getNumberAndCheck(@Valid @RequestBody @ApiParam(name = "hrZpcpCheckQueryForm", value = "HrZpcpCheck查询参数", required = true) HrZpcpCheckQueryForm hrZpcpCheckQueryForm);
}
