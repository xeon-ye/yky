package com.deloitte.platform.api.fssc.engine.manual;

import com.deloitte.platform.api.fssc.engine.manual.param.AvManualVoucherHeadQueryForm;
import com.deloitte.platform.api.fssc.engine.manual.vo.AvManualVoucherHeadForm;
import com.deloitte.platform.api.fssc.engine.manual.vo.AvManualVoucherHeadVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description :  AvManualVoucherHead控制器接口
 * @Modified :
 */
public interface AvManualVoucherHeadClient {

    public static final String path="/fssc/av-manual-voucher-head";


    /**
     *  POST---新增
     * @param avManualVoucherHeadForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute AvManualVoucherHeadForm avManualVoucherHeadForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") Long id);

    /**
     *  Patch----部分更新
     * @param  id, avManualVoucherHeadForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") Long id, @Valid @RequestBody AvManualVoucherHeadForm avManualVoucherHeadForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<AvManualVoucherHeadVo> get(@PathVariable(value="id") Long id);


    /**
     *  POST----列表查询
     * @param   avManualVoucherHeadQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<AvManualVoucherHeadVo>> list(@Valid @RequestBody AvManualVoucherHeadQueryForm avManualVoucherHeadQueryForm);


    /**
     *  POST----复杂查询
     * @param  avManualVoucherHeadQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<AvManualVoucherHeadVo>> search(@Valid @RequestBody AvManualVoucherHeadQueryForm avManualVoucherHeadQueryForm);
}
