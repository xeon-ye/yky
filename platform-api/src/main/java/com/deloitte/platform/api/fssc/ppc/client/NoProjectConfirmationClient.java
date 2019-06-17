package com.deloitte.platform.api.fssc.ppc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.ppc.param.NoProjectConfirmationQueryForm;
import com.deloitte.platform.api.fssc.ppc.vo.NoProjectConfirmationForm;
import com.deloitte.platform.api.fssc.ppc.vo.NoProjectConfirmationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :  NoProjectConfirmation控制器接口
 * @Modified :
 */
public interface NoProjectConfirmationClient {

    public static final String path="/ppc/no-project-confirmation";


    /**
     *  POST---新增
     * @param noProjectConfirmationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute NoProjectConfirmationForm noProjectConfirmationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, noProjectConfirmationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody NoProjectConfirmationForm noProjectConfirmationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<NoProjectConfirmationVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   noProjectConfirmationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<NoProjectConfirmationVo>> list(@Valid @RequestBody NoProjectConfirmationQueryForm noProjectConfirmationQueryForm);


    /**
     *  POST----复杂查询
     * @param  noProjectConfirmationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<NoProjectConfirmationVo>> search(@Valid @RequestBody NoProjectConfirmationQueryForm noProjectConfirmationQueryForm);
}
