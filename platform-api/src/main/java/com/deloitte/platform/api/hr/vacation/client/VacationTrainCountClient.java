package com.deloitte.platform.api.hr.vacation.client;

import com.deloitte.platform.api.hr.vacation.param.VacationTrainCountQueryForm;
import com.deloitte.platform.api.hr.vacation.vo.VacationTrainCountForm;
import com.deloitte.platform.api.hr.vacation.vo.VacationTrainCountVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-01
 * @Description :  VacationTrainCount控制器接口
 * @Modified :
 */
public interface VacationTrainCountClient {

    public static final String path="/hr/vacation-train-count";

    /**
     *  POST---新增
     * @param vacationTrainCountForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute VacationTrainCountForm vacationTrainCountForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, vacationTrainCountForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody VacationTrainCountForm vacationTrainCountForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<VacationTrainCountVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   vacationTrainCountQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<VacationTrainCountVo>> list(@Valid @RequestBody VacationTrainCountQueryForm vacationTrainCountQueryForm);


    /**
     *  POST----复杂查询
     * @param  vacationTrainCountQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<VacationTrainCountVo>> search(@Valid @RequestBody VacationTrainCountQueryForm vacationTrainCountQueryForm);
}
