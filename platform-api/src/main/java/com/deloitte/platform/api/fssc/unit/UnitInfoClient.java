package com.deloitte.platform.api.fssc.unit;

import com.deloitte.platform.api.fssc.unit.vo.UnitInfoForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description :  UnitInfo控制器接口
 * @Modified :
 */
public interface UnitInfoClient {

    public static final String path="/unit/unit-info";


    /**
     *  POST---新增
     * @param unitInfoForm
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateUnit")
    Result addOrUpdateUnit(@Valid @ModelAttribute UnitInfoForm unitInfoForm);

    /**
    *  Delete---删除
    * @param  ids
    * @return
    */
    @DeleteMapping(value = path+"/deleteByIds")
    Result delete(@RequestBody List<Long> ids);


}
