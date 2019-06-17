package com.deloitte.platform.api.bpmservice.clinet;

import com.deloitte.platform.api.bpmservice.param.BpmPositionRelationshipQueryForm;
import com.deloitte.platform.api.bpmservice.vo.BpmPositionRelationshipForm;
import com.deloitte.platform.api.bpmservice.vo.BpmPositionRelationshipVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description :  BpmPositionRelationship控制器接口
 * @Modified :
 */
public interface BpmPositionRelationshipClient {

    public static final String path="/bpmservice/bpm-position-relationship";


    /**
     *  POST---新增
     * @param bpmPositionRelationshipForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BpmPositionRelationshipForm bpmPositionRelationshipForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, bpmPositionRelationshipForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody BpmPositionRelationshipForm bpmPositionRelationshipForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BpmPositionRelationshipVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   bpmPositionRelationshipForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BpmPositionRelationshipVo>> list(@Valid @RequestBody BpmPositionRelationshipQueryForm bpmPositionRelationshipQueryForm);


    /**
     *  POST----复杂查询
     * @param  bpmPositionRelationshipQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<BpmPositionRelationshipVo>> search(@Valid @RequestBody BpmPositionRelationshipQueryForm bpmPositionRelationshipQueryForm);
}
