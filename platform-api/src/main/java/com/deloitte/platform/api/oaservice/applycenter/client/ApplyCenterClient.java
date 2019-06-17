package com.deloitte.platform.api.oaservice.applycenter.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.applycenter.param.ApplyCenterQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.ApplyCenterForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.ApplyCenterVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description :  ApplyCenter控制器接口
 * @Modified :
 */
public interface ApplyCenterClient {

    public static final String path = "/oaservice/apply-center";


    /**
     * POST---新增
     *
     * @param applyCenterForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ApplyCenterForm applyCenterForm);

    /**
     * Delete---删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = path + "/{id}")
    Result delete(@PathVariable(value = "id") String id);

    /**
     * Patch----部分更新
     *
     * @param id, applyCenterForm
     * @return
     */
    @PatchMapping(value = path + "/{id}")
    Result update(@PathVariable(value = "id") String id, @Valid @RequestBody ApplyCenterForm applyCenterForm);

    /**
     * GET----根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = path + "/{id}")
    Result<ApplyCenterVo> get(@PathVariable(value = "id") String id);


    /**
     * POST----列表查询
     *
     * @param applyCenterQueryForm
     * @return
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<ApplyCenterVo>> list(@Valid @RequestBody ApplyCenterQueryForm applyCenterQueryForm);


    /**
     * 门户办公应用中心展示
     *
     * @param applyCenterQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditions")
    Result<IPage<ApplyCenterVo>> search(@Valid @RequestBody ApplyCenterQueryForm applyCenterQueryForm);

    /**
     * 协同办公应用中心展示
     * @param applyCenterQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/data/conditions")
    Result<IPage<ApplyCenterVo>> searchPage(@Valid @RequestBody ApplyCenterQueryForm applyCenterQueryForm);
}
