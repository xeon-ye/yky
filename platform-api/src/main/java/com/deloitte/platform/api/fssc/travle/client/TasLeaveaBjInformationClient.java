package com.deloitte.platform.api.fssc.travle.client;

import com.deloitte.platform.api.fssc.travle.param.TasLeaveaBjInformationQueryForm;
import com.deloitte.platform.api.fssc.travle.vo.TasLeaveaBjInformationForm;
import com.deloitte.platform.api.fssc.travle.vo.TasLeaveaBjInformationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description :  TasLeaveaBjInformation控制器接口
 * @Modified :
 */
public interface TasLeaveaBjInformationClient {

    public static final String path="/travle/tas-leavea-bj-information";


    /**
     *  POST---新增
     * @param tasLeaveaBjInformationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute TasLeaveaBjInformationForm tasLeaveaBjInformationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, tasLeaveaBjInformationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody TasLeaveaBjInformationForm tasLeaveaBjInformationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<TasLeaveaBjInformationVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   tasLeaveaBjInformationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<TasLeaveaBjInformationVo>> list(@Valid @RequestBody TasLeaveaBjInformationQueryForm tasLeaveaBjInformationQueryForm);


    /**
     *  POST----复杂查询
     * @param  tasLeaveaBjInformationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<TasLeaveaBjInformationVo>> search(@Valid @RequestBody TasLeaveaBjInformationQueryForm tasLeaveaBjInformationQueryForm);
}
