package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.CommonReasonQueryForm;
import com.deloitte.platform.api.contract.vo.CommonReasonForm;
import com.deloitte.platform.api.contract.vo.CommonReasonVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-09
 * @Description :  CommonReason控制器接口
 * @Modified :
 */
public interface CommonReasonClient {

    public static final String path="/contract/common-reason";


    /**
     *  POST---新增
     * @param commonReasonForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CommonReasonForm commonReasonForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, commonReasonForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CommonReasonForm commonReasonForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CommonReasonVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   commonReasonQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CommonReasonVo>> list(@Valid @RequestBody CommonReasonQueryForm commonReasonQueryForm);


    /**
     *  POST----复杂查询
     * @param  commonReasonQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CommonReasonVo>> search(@Valid @RequestBody CommonReasonQueryForm commonReasonQueryForm);
}
