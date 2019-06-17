package com.deloitte.services.oaservice.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.param.OaMenuFavoritesQueryForm;
import com.deloitte.platform.api.oaservice.vo.OaMenuFavoritesForm;
import com.deloitte.platform.api.oaservice.vo.OaMenuFavoritesVo;
import com.deloitte.platform.api.oaservice.client.OaMenuFavoritesClient;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.services.oaservice.service.IOaMenuFavoritesService;
import com.deloitte.services.oaservice.entity.OaMenuFavorites;


/**
 * @Author : fuqiao
 * @Date : Create in 2019-05-31
 * @Description :   OaMenuFavorites控制器实现类
 * @Modified :
 */
@Api(tags = "OaMenuFavorites操作接口")
@Slf4j
@RestController
public class OaMenuFavoritesController implements OaMenuFavoritesClient {

    @Autowired
    public IOaMenuFavoritesService  oaMenuFavoritesService;


    @Override
    @ApiOperation(value = "新增OaMenuFavorites", notes = "新增一个OaMenuFavorites")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="oaMenuFavoritesForm",value="新增OaMenuFavorites的form表单",required=true)  OaMenuFavoritesForm oaMenuFavoritesForm) {
        log.info("form:",  oaMenuFavoritesForm.toString());
        OaMenuFavorites oaMenuFavorites =new BeanUtils<OaMenuFavorites>().copyObj(oaMenuFavoritesForm,OaMenuFavorites.class);
        UserVo user = UserUtil.getUserVo();
        if(user!=null&&user.getId()!=null) {
            oaMenuFavorites.setCreateBy(user.getId());
            oaMenuFavorites.setUpdateBy(user.getId());
        }else{
            return Result.fail("无法获取用户信息，请重新登录！");
        }
        return Result.success( oaMenuFavoritesService.save(oaMenuFavorites));
    }


    @Override
    @ApiOperation(value = "删除OaMenuFavorites", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMenuFavoritesID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        oaMenuFavoritesService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改OaMenuFavorites", notes = "修改指定OaMenuFavorites信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "OaMenuFavorites的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="oaMenuFavoritesForm",value="修改OaMenuFavorites的form表单",required=true)  OaMenuFavoritesForm oaMenuFavoritesForm) {
        OaMenuFavorites oaMenuFavorites =new BeanUtils<OaMenuFavorites>().copyObj(oaMenuFavoritesForm,OaMenuFavorites.class);
        oaMenuFavorites.setId(id);
        UserVo user = UserUtil.getUserVo();
        if(user!=null&&user.getId()!=null) {
            oaMenuFavorites.setUpdateBy(user.getId());
        }else{
            return Result.fail("无法获取用户信息，请重新登录！");
        }
        oaMenuFavoritesService.saveOrUpdate(oaMenuFavorites);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取OaMenuFavorites", notes = "获取指定ID的OaMenuFavorites信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OaMenuFavorites的ID", required = true, dataType = "long")
    public Result<OaMenuFavoritesVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        OaMenuFavorites oaMenuFavorites=oaMenuFavoritesService.getById(id);
        OaMenuFavoritesVo oaMenuFavoritesVo=new BeanUtils<OaMenuFavoritesVo>().copyObj(oaMenuFavorites,OaMenuFavoritesVo.class);
        return new Result<OaMenuFavoritesVo>().sucess(oaMenuFavoritesVo);
    }

    @Override
    @ApiOperation(value = "列表查询OaMenuFavorites", notes = "根据条件查询OaMenuFavorites列表数据")
    public Result<List<OaMenuFavoritesVo>> list(@Valid @RequestBody @ApiParam(name="oaMenuFavoritesQueryForm",value="OaMenuFavorites查询参数",required=true) OaMenuFavoritesQueryForm oaMenuFavoritesQueryForm) {
        log.info("search with oaMenuFavoritesQueryForm:", oaMenuFavoritesQueryForm.toString());
        List<OaMenuFavorites> oaMenuFavoritesList=oaMenuFavoritesService.selectList(oaMenuFavoritesQueryForm);
        List<OaMenuFavoritesVo> oaMenuFavoritesVoList=new BeanUtils<OaMenuFavoritesVo>().copyObjs(oaMenuFavoritesList,OaMenuFavoritesVo.class);
        return new Result<List<OaMenuFavoritesVo>>().sucess(oaMenuFavoritesVoList);
    }


    @Override
    @ApiOperation(value = "分页查询OaMenuFavorites", notes = "根据条件查询OaMenuFavorites分页数据")
    public Result<IPage<OaMenuFavoritesVo>> search(@Valid @RequestBody @ApiParam(name="oaMenuFavoritesQueryForm",value="OaMenuFavorites查询参数",required=true) OaMenuFavoritesQueryForm oaMenuFavoritesQueryForm) {
        log.info("search with oaMenuFavoritesQueryForm:", oaMenuFavoritesQueryForm.toString());
        IPage<OaMenuFavorites> oaMenuFavoritesPage=oaMenuFavoritesService.selectPage(oaMenuFavoritesQueryForm);
        IPage<OaMenuFavoritesVo> oaMenuFavoritesVoPage=new BeanUtils<OaMenuFavoritesVo>().copyPageObjs(oaMenuFavoritesPage,OaMenuFavoritesVo.class);
        return new Result<IPage<OaMenuFavoritesVo>>().sucess(oaMenuFavoritesVoPage);
    }

}



