package com.deloitte.platform.api.hr.staffAllotment.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.staffAllotment.param.HrExternalTransferQueryForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrExternalTransferForm;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrExternalTransferVo;
import com.deloitte.platform.api.hr.staffAllotment.vo.HrInternalTransferForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


import java.util.List;

/**
 * @Author : zengshuai
 * @Date : Create in 2019-04-03
 * @Description :  HrExternalTransfer控制器接口
 * @Modified :
 */
public interface HrExternalTransferClient {

    public static final String path="/hr/staffAllotment/hr-external-transfer";


    /**
     *  POST---新增
     * @param hrExternalTransferForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrExternalTransferForm hrExternalTransferForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrExternalTransferForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrExternalTransferForm hrExternalTransferForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrExternalTransferVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrExternalTransferQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrExternalTransferVo>> list(@Valid @RequestBody HrExternalTransferQueryForm hrExternalTransferQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrExternalTransferQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrExternalTransferVo>> search(@Valid @RequestBody HrExternalTransferQueryForm hrExternalTransferQueryForm);

    /**
     *  POST---保存
     * @param hrExternalTransferForm
     * @return
     */
    @PostMapping(value = path+"/save")
    Result save(@Valid @ModelAttribute HrExternalTransferForm hrExternalTransferForm);

    /**
     *  POST---提交
     * @param hrExternalTransferForm
     * @return
     */
    @PostMapping(value = path+"/submit")
    Result submit(@Valid @ModelAttribute HrExternalTransferForm hrExternalTransferForm);

    /**
     * POST---根据员工编号获取信息
     * @param hrExternalTransferForm
     * @return
     */
    @PostMapping(value = path+"/selectByCode")
    Result<HrExternalTransferVo> selectByCode(@Valid @RequestBody  HrExternalTransferVo hrExternalTransferForm);

    /**
     * POST---根据员工编号获取信息（全中文）
     * @param hrExternalTransferForm
     * @return
     */
    @PostMapping(value = path+"/getByCode")
    Result<HrExternalTransferVo> getByCode(@Valid @RequestBody @ApiParam(name="hrExternalTransferForm",value="新增HrExternalTransfer的form表单",required=true) HrExternalTransferVo hrExternalTransferForm);

    /**
     * POST---审批结束对数据进行更新
     * @param hrExternalTransferForm
     * @return
     */
    @PostMapping(value = path+"/updateDate")
    Result updateDate(@Valid @ModelAttribute HrExternalTransferForm hrExternalTransferForm);
}
