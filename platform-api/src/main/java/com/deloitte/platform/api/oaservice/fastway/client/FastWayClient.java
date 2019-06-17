package com.deloitte.platform.api.oaservice.fastway.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.oaservice.fastway.param.FastWayQueryForm;
import com.deloitte.platform.api.oaservice.fastway.vo.FastWayForm;
import com.deloitte.platform.api.oaservice.fastway.vo.FastWayVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-05-17
 * @Description :  FastWay控制器接口
 * @Modified :
 */
public interface FastWayClient {

    public static final String path = "/oaservice/fast-way";


    /**
     * POST---新增
     *
     * @param fastWayForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute FastWayForm fastWayForm);

    /**
     * Delete---删除
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = path + "/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     * Patch----部分更新
     *
     * @param id, fastWayForm
     * @return
     */
    @PatchMapping(value = path + "/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody FastWayForm fastWayForm);

    /**
     * GET----根据ID获取
     *
     * @param id
     * @return
     */
    @GetMapping(value = path + "/{id}")
    Result<FastWayVo> get(@PathVariable(value = "id") long id);


    /**
     * POST----列表查询
     *
     * @param fastWayQueryForm
     * @return
     */
    @PostMapping(value = path + "/list/conditions")
    Result<List<FastWayVo>> list(@Valid @RequestBody FastWayQueryForm fastWayQueryForm);


    /**
     * POST----复杂查询
     *
     * @param fastWayQueryForm
     * @return
     */
    @PostMapping(value = path + "/page/conditions")
    Result<IPage<FastWayVo>> search(@Valid @RequestBody FastWayQueryForm fastWayQueryForm);
}
