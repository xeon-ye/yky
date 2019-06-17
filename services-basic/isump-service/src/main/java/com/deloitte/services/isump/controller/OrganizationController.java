package com.deloitte.services.isump.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.OrganizationClient;
import com.deloitte.platform.api.isump.param.OrganizationQueryForm;
import com.deloitte.platform.api.isump.vo.OrganizationEBSForm;
import com.deloitte.platform.api.isump.vo.OrganizationForm;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.isump.entity.Dept;
import com.deloitte.services.isump.entity.Organization;
import com.deloitte.services.isump.returnEntity.OrganizationEBSFlage;
import com.deloitte.services.isump.service.IDeptService;
import com.deloitte.services.isump.service.IOrganizationService;
import com.deloitte.services.isump.util.ObjectUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :   Organization控制器实现类
 * @Modified :
 */
@Api(tags = "Organization操作接口")
@Slf4j
@RestController
public class OrganizationController implements OrganizationClient {

    @Autowired
    public IOrganizationService  organizationService;
    @Autowired
    private IDeptService deptService;


    @Override
    @ApiOperation(value = "新增Organization", notes = "新增一个Organization")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="organizationForm",value="新增Organization的form表单",required=true)  OrganizationForm organizationForm) {
        log.info("form:",  organizationForm.toString());
        Organization organization =new BeanUtils<Organization>().copyObj(organizationForm,Organization.class);

        OrganizationVo organizationVo = organizationService.getByCode(organization.getCode());
        if(organizationVo!=null){
            throw new BaseException(PlatformErrorType.ORG_CODE_ERROR);
        }
        return Result.success( organizationService.save(organization));
    }


    @Override
    @ApiOperation(value = "删除Organization", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "OrganizationID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        organizationService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Organization", notes = "修改指定Organization信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Organization的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="organizationForm",value="修改Organization的form表单",required=true)  OrganizationForm organizationForm) {
        Organization organization =new BeanUtils<Organization>().copyObj(organizationForm,Organization.class);
        organization.setId(id);
        organizationService.saveOrUpdate(organization);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Organization", notes = "获取指定ID的Organization信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Organization的ID", required = true, dataType = "long")
    public Result<OrganizationVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Organization organization=organizationService.getById(id);
        OrganizationVo organizationVo=new BeanUtils<OrganizationVo>().copyObj(organization,OrganizationVo.class);
        return new Result<OrganizationVo>().sucess(organizationVo);
    }

    @Override
    @ApiOperation(value = "根据Code获取Organization", notes = "获取指定code的Organization信息")
    @ApiImplicitParam(paramType = "path", name = "code", value = "Organization的code", required = true, dataType = "String")
    public Result<OrganizationVo> getByCode(@PathVariable String code) {
        log.info("get with code:{}", code);
        OrganizationVo organization=organizationService.getByCode(code);
        return new Result<OrganizationVo>().sucess(organization);
    }

    @Override
    @ApiOperation(value = "列表查询Organization", notes = "根据条件查询Organization列表数据")
    public Result<List<OrganizationVo>> list(@Valid @RequestBody @ApiParam(name="organizationQueryForm",value="Organization查询参数",required=true) OrganizationQueryForm organizationQueryForm) {
        log.info("search with organizationQueryForm:", organizationQueryForm.toString());
        List<Organization> organizationList=organizationService.selectList(organizationQueryForm);
        List<OrganizationVo> organizationVoList=new BeanUtils<OrganizationVo>().copyObjs(organizationList,OrganizationVo.class);
        return new Result<List<OrganizationVo>>().sucess(organizationVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Organization", notes = "根据条件查询Organization分页数据")
    public Result<IPage<OrganizationVo>> search(@Valid @RequestBody @ApiParam(name="organizationQueryForm",value="Organization查询参数",required=true) OrganizationQueryForm organizationQueryForm) {
        log.info("search with organizationQueryForm:", organizationQueryForm.toString());
        IPage<Organization> organizationPage=organizationService.selectPage(organizationQueryForm);
        IPage<OrganizationVo> organizationVoPage=new BeanUtils<OrganizationVo>().copyPageObjs(organizationPage,OrganizationVo.class);
        return new Result<IPage<OrganizationVo>>().sucess(organizationVoPage);
    }

    @Override
    @ApiOperation(value = "分页查询Organization", notes = "根据条件查询Organization分页数据")
    @ApiParam(name="organizationQueryForm",value="Organization查询参数",required=true)
    public Result<GdcPage<OrganizationVo>> search2(@Valid @RequestBody OrganizationQueryForm organizationQueryForm) {
        log.info("search with organizationQueryForm:", organizationQueryForm.toString());
        IPage<Organization> organizationPage = organizationService.selectPage(organizationQueryForm);
        IPage<OrganizationVo> organizationVoPage=new BeanUtils<OrganizationVo>().copyPageObjs(organizationPage,OrganizationVo.class);
        return new Result<GdcPage<OrganizationVo>>().sucess(new GdcPage<OrganizationVo>(organizationVoPage));
    }

    @Override
    public Result<HashMap<String, String>> searchCodeAndName(
            OrganizationQueryForm organizationQueryForm) {
        log.info("search with organizationQueryForm:", organizationQueryForm.toString());
        List<Organization> organizationList = organizationService.selectList(organizationQueryForm);
        if (CollectionUtils.isEmpty(organizationList)){
            return Result.success();
        }
        HashMap<String,String> codeNameMap = new HashMap<>(organizationList.size());
        for (Organization organization : organizationList){
            if (StringUtils.isNotBlank(organization.getCode())) {
                codeNameMap.put(organization.getCode(), organization.getName());
            }
        }
        return Result.success(codeNameMap);
    }

    @Override
    @ApiOperation(value = "根据组织编码查询下级组织")
    public Result<List<OrganizationVo>> getOrgTreeByCode(@PathVariable(value = "orgCode") String orgCode) {
        List<OrganizationVo> orgVoList = organizationService.getOrgTreeByCode(orgCode);
        return new Result<List<OrganizationVo>>().sucess(orgVoList);
    }

    @Override
    @ApiOperation(value = "根据组织编码查询下级虚拟组织")
    public Result<List<OrganizationVo>> getOrgFinctionByCode(@PathVariable(value = "orgCode") String orgCode) {
        List<Organization> orgList = organizationService.getOrgFinctionByCode(orgCode);
        List<OrganizationVo> orgListVo = new BeanUtils<OrganizationVo>().copyObjs(orgList, OrganizationVo.class);
        return new Result<List<OrganizationVo>>().sucess(orgListVo);
    }

    @Override

    public Result<List<OrganizationVo>> getOrgDeptByCode(@PathVariable(value = "orgCode") String orgCode) {
        List<Organization> orgList = organizationService.getOrgDeptByCode(orgCode);
        List<OrganizationVo> orgListVo = new BeanUtils<OrganizationVo>().copyObjs(orgList, OrganizationVo.class);
        return new Result<List<OrganizationVo>>().sucess(orgListVo);
    }

    @Override
    @ApiOperation(value = "批量添加")
    public Result addList(@Valid @RequestBody List<OrganizationEBSForm> organizationEBSForm) {
        List<OrganizationEBSFlage> errList = new ArrayList<>();
        for(OrganizationEBSForm org : organizationEBSForm){
            if(StringUtils.isBlank(org.getCode())){
                OrganizationEBSFlage orgFlage = new OrganizationEBSFlage();
                orgFlage.setName(org.getName());
                orgFlage.setErrorMessage("组织编码为空");
                errList.add(orgFlage);
                continue;
            }
            if(StringUtils.isBlank(org.getName())){
                OrganizationEBSFlage orgFlage = new OrganizationEBSFlage();
                orgFlage.setCode(org.getCode());
                orgFlage.setErrorMessage("组织名称为空");
                errList.add(orgFlage);
                continue;
            }
            OrganizationVo oVo = organizationService.getByCode(org.getCode());
            if(oVo!=null){
                OrganizationEBSFlage orgFlage = new OrganizationEBSFlage();
                orgFlage.setName(org.getName());
                orgFlage.setErrorMessage("该组织的code已经存在！");
                errList.add(orgFlage);
                continue;
            }

            try {
                Organization organization = new BeanUtils<Organization>().copyObj(org, Organization.class);
                //查询组织编码是否存在
                OrganizationVo orgVo = organizationService.getByName(org.getName());
                if(orgVo != null){
                    organization.setId(Long.parseLong(orgVo.getId()));
                    organization.setType(orgVo.getType());
                    organizationService.updateById(organization);
                    //更新单位表
                }else{
                    organization.setState("1");
                    //添加单位表
                    if(organization.getCode().length() == 4){
                        organization.setType("org");
                        //添加单位表
                        Dept dept = new Dept();
                        dept.setDeptCode(org.getCode());
                        dept.setDeptName(org.getName());
                        dept.setState("1");
                        dept.setCreditCode(org.getCommonCreditCode());
                        dept.setBankAccount(org.getBankAccount());
                        dept.setBankName(org.getBankName());
                        dept.setBankAccountNumber(org.getBankAccountNumber());
                        //添加单位表
                        deptService.save(dept);
                    }else{
                        organization.setType("dept");
                    }
                    //根据编码查询上级信息
                    OrganizationVo organizationVo = organizationService.getByCode(org.getParentCode());
                    if(organizationVo != null){
                        organization.setParentId(Long.parseLong(organizationVo.getId()));
                    }
                    //根据code生成path
                    String pathString = ObjectUtil.getOrgPath(org.getCode());
                    if(StringUtils.isNotBlank(pathString)){
                        organization.setPath(pathString);
                    }
                    //添加组织表
                    organizationService.save(organization);
                }
            } catch (Exception e){
                e.printStackTrace();
                OrganizationEBSFlage orgFlage = new OrganizationEBSFlage();
                orgFlage.setCode(org.getCode());
                orgFlage.setName(org.getName());
                orgFlage.setErrorMessage(e.getMessage());
                errList.add(orgFlage);
                continue;
            }
        }
        if(errList.size() > 0){
            return  Result.fail(errList);
        }
        return Result.success();
    }
}



