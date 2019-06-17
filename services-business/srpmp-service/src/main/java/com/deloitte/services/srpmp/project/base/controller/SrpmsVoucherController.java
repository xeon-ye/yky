package com.deloitte.services.srpmp.project.base.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsVoucherQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsVoucherForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsVoucherVo;
import com.deloitte.platform.api.srpmp.project.base.SrpmsVoucherClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.srpmp.project.base.service.ISrpmsVoucherService;
import com.deloitte.services.srpmp.project.base.entity.SrpmsVoucher;


/**
 * @Author : lixin
 * @Date : Create in 2019-03-15
 * @Description :   SrpmsVoucher控制器实现类
 * @Modified :
 */
@Api(tags = "SrpmsVoucher操作接口")
@Slf4j
@RestController
public class SrpmsVoucherController implements SrpmsVoucherClient {

    @Autowired
    public ISrpmsVoucherService  srpmsVoucherService;

    @Override
    @ApiOperation(value = "分页查询SrpmsVoucher", notes = "根据条件查询SrpmsVoucher分页数据")
    public Result<IPage<SrpmsVoucherVo>> search(@Valid @RequestBody @ApiParam(name="srpmsVoucherQueryForm",value="SrpmsVoucher查询参数",required=true) SrpmsVoucherQueryForm srpmsVoucherQueryForm
                                                , UserVo userVo) {
        log.info("search with srpmsVoucherQueryForm:", srpmsVoucherQueryForm.toString());
        IPage<SrpmsVoucher> srpmsVoucherPage=srpmsVoucherService.selectPage(srpmsVoucherQueryForm, userVo);
        IPage<SrpmsVoucherVo> srpmsVoucherVoPage=new BeanUtils<SrpmsVoucherVo>().copyPageObjs(srpmsVoucherPage,SrpmsVoucherVo.class);
        return new Result<IPage<SrpmsVoucherVo>>().sucess(srpmsVoucherVoPage);
    }

}



