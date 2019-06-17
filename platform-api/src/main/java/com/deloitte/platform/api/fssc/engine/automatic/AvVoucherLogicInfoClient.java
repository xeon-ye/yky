package com.deloitte.platform.api.fssc.engine.automatic;

import com.deloitte.platform.api.fssc.engine.automatic.param.AvVoucherLogicInfoQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvVoucherLogicInfoForm;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvVoucherLogicInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description :  AvVoucherLogicInfo控制器接口
 * @Modified :
 */
public interface AvVoucherLogicInfoClient {

    public static final String path="/fssc/av-voucher-logic-info";


    /**
     *  POST---新增
     * @param avVoucherLogicInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute AvVoucherLogicInfoForm avVoucherLogicInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, avVoucherLogicInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody AvVoucherLogicInfoForm avVoucherLogicInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<AvVoucherLogicInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   avVoucherLogicInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<AvVoucherLogicInfoVo>> list(@Valid @RequestBody AvVoucherLogicInfoQueryForm avVoucherLogicInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  avVoucherLogicInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<AvVoucherLogicInfoVo>> search(@Valid @RequestBody AvVoucherLogicInfoQueryForm avVoucherLogicInfoQueryForm);
}
