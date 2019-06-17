package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccPatentSituationQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccPatentSituationForm;
import com.deloitte.platform.api.hr.gcc.vo.GccPatentSituationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccPatentSituation控制器接口
 * @Modified :
 */
public interface GccPatentSituationClient {

    public static final String path="/hr/gcc-patent-situation";


    /**
     *  POST---新增
     * @param gccPatentSituationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccPatentSituationForm gccPatentSituationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccPatentSituationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccPatentSituationForm gccPatentSituationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccPatentSituationVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccPatentSituationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccPatentSituationVo>> list(@Valid @RequestBody GccPatentSituationQueryForm gccPatentSituationQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccPatentSituationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccPatentSituationVo>> search(@Valid @RequestBody GccPatentSituationQueryForm gccPatentSituationQueryForm);


    /**
     *  POST---批量新增或更新
     * @param gccPatentSituationForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<GccPatentSituationForm> gccPatentSituationForms);

    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);
}
