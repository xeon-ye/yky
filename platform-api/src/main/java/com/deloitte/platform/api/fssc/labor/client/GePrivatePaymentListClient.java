package com.deloitte.platform.api.fssc.labor;

import com.deloitte.platform.api.fssc.labor.param.GePrivatePaymentListQueryForm;
import com.deloitte.platform.api.fssc.labor.vo.GePrivatePaymentListForm;
import com.deloitte.platform.api.fssc.labor.vo.GePrivatePaymentListVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description :  GePrivatePaymentList控制器接口
 * @Modified :
 */
public interface GePrivatePaymentListClient {

    public static final String path="/labor/ge-private-payment-list";


    /**
     *  POST---新增
     * @param gePrivatePaymentListForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GePrivatePaymentListForm gePrivatePaymentListForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gePrivatePaymentListForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody GePrivatePaymentListForm gePrivatePaymentListForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GePrivatePaymentListVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   gePrivatePaymentListQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GePrivatePaymentListVo>> list(@Valid @RequestBody GePrivatePaymentListQueryForm gePrivatePaymentListQueryForm);


    /**
     *  POST----复杂查询
     * @param  gePrivatePaymentListQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GePrivatePaymentListVo>> search(@Valid @RequestBody GePrivatePaymentListQueryForm gePrivatePaymentListQueryForm);
}
