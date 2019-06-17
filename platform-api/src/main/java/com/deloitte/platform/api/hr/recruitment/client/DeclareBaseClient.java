package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.DeclareBaseQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeclareBaseForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeclareBaseVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-04
 * @Description :  DeclareBase控制器接口
 * @Modified :
 */
public interface DeclareBaseClient {

    public static final String path="/hr/recruitment/declare-base";


    /**
     *  POST---新增
     * @param declareBaseForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute DeclareBaseForm declareBaseForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, declareBaseForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody DeclareBaseForm declareBaseForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<DeclareBaseVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   declareBaseQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<DeclareBaseVo>> list(@Valid @RequestBody DeclareBaseQueryForm declareBaseQueryForm);


    /**
     *  POST----复杂查询
     * @param  declareBaseQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<DeclareBaseVo>> search(@Valid @RequestBody DeclareBaseQueryForm declareBaseQueryForm);


    /**
     *  POST---提交
     * @param declareBaseForm
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute DeclareBaseForm declareBaseForm);



    /**
     *  POST---新增
     * @param declareBaseForm
     * @return
     */
    @PostMapping(value = path+"/addByUserId")
    Result addByUserId(@Valid @ModelAttribute DeclareBaseForm declareBaseForm);

}
