package com.deloitte.platform.api.hr.ebs.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.ebs.param.EmployeeMesTrainQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesTrainForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesTrainVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeMesTrain控制器接口
 * @Modified :
 */
public interface EmployeeMesTrainClient {

    public static final String path="/hr/employee-mes-train";


    /**
     *  POST---新增
     * @param employeeMesTrainForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesTrainForm employeeMesTrainForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesTrainForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesTrainForm employeeMesTrainForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesTrainVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesTrainQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesTrainVo>> list(@Valid @RequestBody EmployeeMesTrainQueryForm employeeMesTrainQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesTrainQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesTrainVo>> search(@Valid @RequestBody EmployeeMesTrainQueryForm employeeMesTrainQueryForm);

    /**
     *  POST---新增  修改  无效
     * @param employeeMesTrainForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody EmployeeMesTrainForm employeeMesTrainForm);

    /**
     * 推送到人员教育信息EBS
     * @param employeeMesTrainForm
     * @return
     */
    @PostMapping(value = path+"/publishToEbs")
    Result publishToEbs(@Valid @RequestBody EmployeeMesTrainForm employeeMesTrainForm);
}
