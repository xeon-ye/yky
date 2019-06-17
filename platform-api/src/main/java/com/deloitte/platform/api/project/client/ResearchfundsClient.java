package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.ResearchfundsQueryForm;
import com.deloitte.platform.api.project.vo.ResearchfundsForm;
import com.deloitte.platform.api.project.vo.ResearchfundsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description :  Researchfunds控制器接口
 * @Modified :
 */
public interface ResearchfundsClient {

    public static final String path="/project/researchfunds";


    /**
     *  POST---新增
     * @param researchfundsForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ResearchfundsForm researchfundsForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, researchfundsForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ResearchfundsForm researchfundsForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ResearchfundsVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   researchfundsQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ResearchfundsVo>> list(@Valid @RequestBody ResearchfundsQueryForm researchfundsQueryForm);


    /**
     *  POST----复杂查询
     * @param  researchfundsQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ResearchfundsVo>> search(@Valid @RequestBody ResearchfundsQueryForm researchfundsQueryForm);
}
