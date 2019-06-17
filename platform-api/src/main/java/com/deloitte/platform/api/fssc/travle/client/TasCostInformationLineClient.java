package com.deloitte.platform.api.fssc.travle;

import com.deloitte.platform.api.fssc.travle.param.TasCostInformationLineQueryForm;
import com.deloitte.platform.api.fssc.travle.vo.TasCostInformationLineForm;
import com.deloitte.platform.api.fssc.travle.vo.TasCostInformationLineVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description :  TasCostInformationLine控制器接口
 * @Modified :
 */
public interface TasCostInformationLineClient {

    public static final String path="/travle/tas-cost-information-line";


    /**
     *  POST---新增
     * @param tasCostInformationLineForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute TasCostInformationLineForm tasCostInformationLineForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, tasCostInformationLineForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody TasCostInformationLineForm tasCostInformationLineForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<TasCostInformationLineVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   tasCostInformationLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<TasCostInformationLineVo>> list(@Valid @RequestBody TasCostInformationLineQueryForm tasCostInformationLineQueryForm);


    /**
     *  POST----复杂查询
     * @param  tasCostInformationLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<TasCostInformationLineVo>> search(@Valid @RequestBody TasCostInformationLineQueryForm tasCostInformationLineQueryForm);
}
