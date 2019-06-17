package com.deloitte.services.srpmp.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.common.service.impl.DataHanderService;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-14
 * @Description : SysDict控制器实现类
 * @Modified :
 */
@Slf4j
@RequestMapping("/srpmp")
@RestController
public class DataHanderController {

	@Autowired
	public DataHanderService service;

	@Autowired
	private SrpmsProjectMapper srpmsProjectMapper;

	@GetMapping(path = "/doData")
	public Result doData() throws Exception {

		JSONArray arr = new JSONArray();

		QueryWrapper<SrpmsProject> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq(SrpmsProject.DEL_FLG, "0");

		IPage<SrpmsProject> page = srpmsProjectMapper.selectPage(new Page<>(0, 200), queryWrapper);

		List<SrpmsProject> list= page.getRecords();
		log.info("总长度是" + list.size());

		for (int i = 0; i < list.size(); i ++) {
			SrpmsProject project = list.get(i);
			service.doWork3(project);
			service.doWork4(project);
		}
		return Result.success("");
	}
}