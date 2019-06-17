package com.deloitte.platform.api.hr.ebs.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.ebs.param.EmployeeMesZpcpQueryForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesZpcpForm;
import com.deloitte.platform.api.hr.ebs.vo.EmployeeMesZpcpVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : LJH
 * @Date : Create in 2019-06-04
 * @Description :  EmployeeMesZpcp控制器接口
 * @Modified :
 */
public interface EmployeeMesZpcpClient {

    public static final String path="/hr/employee-mes-zpcp";


    /**
     *  POST---新增
     * @param employeeMesZpcpForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute EmployeeMesZpcpForm employeeMesZpcpForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, employeeMesZpcpForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody EmployeeMesZpcpForm employeeMesZpcpForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<EmployeeMesZpcpVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   employeeMesZpcpQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<EmployeeMesZpcpVo>> list(@Valid @RequestBody EmployeeMesZpcpQueryForm employeeMesZpcpQueryForm);


    /**
     *  POST----复杂查询
     * @param  employeeMesZpcpQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<EmployeeMesZpcpVo>> search(@Valid @RequestBody EmployeeMesZpcpQueryForm employeeMesZpcpQueryForm);

    /**
     *  POST---新增  修改  无效
     * @param employeeMesZpcpForm
     * @return
     */
    @PostMapping(value = path+"/saveOrUpdate")
    Result saveOrUpdate(@Valid @RequestBody EmployeeMesZpcpForm employeeMesZpcpForm);

    /**
     * 推送到人员教育信息EBS
     * @param employeeMesZpcpForm
     * @return
     */
    @PostMapping(value = path+"/publishToEbs")
    Result publishToEbs(@Valid @RequestBody EmployeeMesZpcpForm employeeMesZpcpForm);
}
