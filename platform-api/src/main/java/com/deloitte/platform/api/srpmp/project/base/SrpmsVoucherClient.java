package com.deloitte.platform.api.srpmp.project.base;

import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsVoucherQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsVoucherForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsVoucherVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : lixin
 * @Date : Create in 2019-03-15
 * @Description :  SrpmsVoucher控制器接口
 * @Modified :
 */
public interface SrpmsVoucherClient {

    public static final String path="/srpmp/project/voucher";

    /**
     *  POST----复杂查询
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SrpmsVoucherVo>> search(@Valid @RequestBody SrpmsVoucherQueryForm srpmsVoucherQueryForm, UserVo userVo);
}
