package com.deloitte.platform.api.hr.vacation.client;

import com.deloitte.platform.api.hr.vacation.param.VacationtypeExplainQueryForm;
import com.deloitte.platform.api.hr.vacation.vo.VacationtypeExplainForm;
import com.deloitte.platform.api.hr.vacation.vo.VacationtypeExplainVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-17
 * @Description :  VacationtypeExplain控制器接口
 * @Modified :
 */
public interface VacationtypeExplainClient {

    public static final String path="/hr/vacationtype-explain";


    /**
     *  POST---新增
     * @param vacationtypeExplainForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute VacationtypeExplainForm vacationtypeExplainForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, vacationtypeExplainForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody VacationtypeExplainForm vacationtypeExplainForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<VacationtypeExplainVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   vacationtypeExplainQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<VacationtypeExplainVo>> list(@Valid @RequestBody VacationtypeExplainQueryForm vacationtypeExplainQueryForm);


    /**
     *  POST----复杂查询
     * @param  vacationtypeExplainQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<VacationtypeExplainVo>> search(@Valid @RequestBody VacationtypeExplainQueryForm vacationtypeExplainQueryForm);
}
