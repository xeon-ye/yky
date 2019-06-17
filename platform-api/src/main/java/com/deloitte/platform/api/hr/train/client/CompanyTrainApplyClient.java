package com.deloitte.platform.api.hr.train.client;

import com.deloitte.platform.api.hr.train.param.CompanyTrainApplyQueryForm;
import com.deloitte.platform.api.hr.train.vo.CompanyTrainApplyForm;
import com.deloitte.platform.api.hr.train.vo.CompanyTrainApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-02
 * @Description :  CompanyTrainApply控制器接口
 * @Modified :
 */
public interface CompanyTrainApplyClient {

    public static final String path="/hr/company-train-apply";

    /**
     *  POST---新增
     * @param companyTrainApplyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CompanyTrainApplyForm companyTrainApplyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, companyTrainApplyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody CompanyTrainApplyForm companyTrainApplyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CompanyTrainApplyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   companyTrainApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CompanyTrainApplyVo>> list(@Valid @RequestBody CompanyTrainApplyQueryForm companyTrainApplyQueryForm);


    /**
     *  POST----复杂查询
     * @param  companyTrainApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CompanyTrainApplyVo>> search(@Valid @RequestBody CompanyTrainApplyQueryForm companyTrainApplyQueryForm);
}
