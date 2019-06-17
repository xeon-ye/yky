package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccCommitteeRecommendQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccCommitteeRecommendForm;
import com.deloitte.platform.api.hr.gcc.vo.GccCommitteeRecommendVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccCommitteeRecommend控制器接口
 * @Modified :
 */
public interface GccCommitteeRecommendClient {

    public static final String path="/hr/gcc-committee-recommend";


    /**
     *  POST---新增
     * @param gccCommitteeRecommendForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccCommitteeRecommendForm gccCommitteeRecommendForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccCommitteeRecommendForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccCommitteeRecommendForm gccCommitteeRecommendForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccCommitteeRecommendVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccCommitteeRecommendQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccCommitteeRecommendVo>> list(@Valid @RequestBody GccCommitteeRecommendQueryForm gccCommitteeRecommendQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccCommitteeRecommendQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccCommitteeRecommendVo>> search(@Valid @RequestBody GccCommitteeRecommendQueryForm gccCommitteeRecommendQueryForm);

    /**
     *  POST---批量新增或更新
     * @param gccCommitteeRecommendForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<GccCommitteeRecommendForm> gccCommitteeRecommendForms);

    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);
}
