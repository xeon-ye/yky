package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.FsscUserClient;
import com.deloitte.platform.api.isump.vo.FsscUserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.isump.service.IFsscUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhangdi
 * @Date 15/04/2019
 */
@Api(tags = "FsscUser操作接口")
@Slf4j
@RestController
public class FsscUserController implements FsscUserClient {

    @Autowired
    private IFsscUserService iFsscUserService;

    @Override
    @ApiOperation(value = "列表查询(不分页)FsscUser")
    public Result<List<FsscUserVo>> queryFsscUserInfo(
            @ApiParam(value = "员工编号", required = false) @RequestParam(required = false) String empNo,
            @ApiParam(value = "员工名称", required = false) @RequestParam(required = false) String userName) {
        List<FsscUserVo> list = iFsscUserService.queryFsscUserInfo(empNo, userName);
        return new Result<List<FsscUserVo>>().sucess(list);
    }

    @Override
    @ApiOperation(value = "列表查询(分页)FsscUser")
    public Result<GdcPage<FsscUserVo>> queryFsscUserInfoPage(
            @ApiParam(value = "员工编号", required = false) @RequestParam(required = false) String empNo,
            @ApiParam(value = "员工名称", required = false) @RequestParam(required = false) String userName,
            @ApiParam(value = "国籍(0:国内  1：国外)", required = false) @RequestParam(required = false) String country,
            @ApiParam(value = "证件号码", required = false) @RequestParam(required = false) String idCard,
            @ApiParam(value = "收款人类型", required = false) @RequestParam(required = false) List<String> payeeType,
            @ApiParam(value = "页码", required = false) @RequestParam(defaultValue = "1") int currentPage,
            @ApiParam(value = "每页条数", required = false) @RequestParam(defaultValue = "15") int pageSize) {

        IPage<FsscUserVo> pageList = iFsscUserService.queryFsscUserInfoPage(empNo, userName, country,idCard, payeeType, currentPage, pageSize);
        return new Result<GdcPage<FsscUserVo>>().sucess(new GdcPage<>(pageList));
    }

}
