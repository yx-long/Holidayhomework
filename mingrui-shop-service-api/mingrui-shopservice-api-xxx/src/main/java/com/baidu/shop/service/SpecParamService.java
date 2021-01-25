package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecParamDTO;
import com.baidu.shop.entity.SpecParamEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "规格参数接口")
public interface SpecParamService {

    @ApiOperation(value = "查询规格参数")
    @GetMapping(value = "specparams/list")
    Result<List<SpecParamEntity>> getSpecParamInfo(SpecParamDTO specParamDto);

    @ApiOperation(value = "新增规格参数")
    @PostMapping(value = "specparams/save")
    Result<JSONObject> save(@RequestBody SpecParamDTO specParamDto);

    @ApiOperation(value = "修改规格参数")
    @PutMapping(value = "specparams/save")
    Result<JSONObject> edit(@RequestBody SpecParamDTO specParamDto);

    @ApiOperation(value = "删除规格参数")
    @DeleteMapping(value = "specparams/delete/{id}")
    Result<JSONObject> delete(@PathVariable Integer id);


}