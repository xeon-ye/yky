package com.deloitte.platform.api.fssc.engine.manual;

import com.deloitte.platform.api.fssc.engine.manual.param.AvManualVoucherLineQueryForm;
import com.deloitte.platform.api.fssc.engine.manual.vo.AvManualVoucherLineForm;
import com.deloitte.platform.api.fssc.engine.manual.vo.AvManualVoucherLineVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-20
 * @Description :  AvManualVoucherLine控制器接口
 * @Modified :
 */
public interface AvManualVoucherLineClient {

    public static final String path="/fssc/av-manual-voucher-line";


    /**
     *  POST---新增
     * @param avManualVoucherLineForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute AvManualVoucherLineForm avManualVoucherLineForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, avManualVoucherLineForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody AvManualVoucherLineForm avManualVoucherLineForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<AvManualVoucherLineVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   avManualVoucherLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<AvManualVoucherLineVo>> list(@Valid @RequestBody AvManualVoucherLineQueryForm avManualVoucherLineQueryForm);


    /**
     *  POST----复杂查询
     * @param  avManualVoucherLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<AvManualVoucherLineVo>> search(@Valid @RequestBody AvManualVoucherLineQueryForm avManualVoucherLineQueryForm);
}
