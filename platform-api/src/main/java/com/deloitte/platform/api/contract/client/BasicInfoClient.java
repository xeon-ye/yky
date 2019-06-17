package com.deloitte.platform.api.contract.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.BasicInfoQueryForm;
import com.deloitte.platform.api.contract.vo.BasicInfoForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.contract.vo.FinanceInfoVo;
import com.deloitte.platform.api.contract.vo.FinanceInfoVoToFssc;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  BasicInfo控制器接口
 * @Modified :
 */
public interface BasicInfoClient {

    public static final String path="/contract/basic-info";


    /**
     *  POST---新增
     * @param basicInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BasicInfoForm basicInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, basicInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody BasicInfoForm basicInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BasicInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   basicInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BasicInfoVo>> list(@Valid @RequestBody BasicInfoQueryForm basicInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  basicInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<BasicInfoVo>> search(@Valid @RequestBody BasicInfoQueryForm basicInfoQueryForm);

    /**
     * 保存财务系统返回印花税信息
     * @param basicInfoForm
     * @return
     */
    @PostMapping(value = path+"/saveImprint")
    Result saveImprint(@RequestBody BasicInfoForm basicInfoForm);

    /**
     * 保存财务系统返回财务信息
     * @param listFinanceInfoVo
     * @return
     */
    @PostMapping(value = path+"/saveFinanceInfo")
    Result saveFinanceInfo(@RequestBody List<FinanceInfoVoToFssc> listFinanceInfoVo);
}
