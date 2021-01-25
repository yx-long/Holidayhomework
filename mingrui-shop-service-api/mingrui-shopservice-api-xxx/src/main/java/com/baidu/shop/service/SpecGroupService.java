package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecGroupDTO;
import com.baidu.shop.entity.SpecGroupEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "规格组接口")
public interface SpecGroupService {

    @ApiModelProperty(value = "规格组查询")
    @GetMapping(value = "specGroup/list")
    Result<List<SpecGroupEntity>> getSpecGroupInfo(SpecGroupDTO specGroupDto);

    @ApiModelProperty(value = "规格组新增")
    @PostMapping(value = "specGroup/save")
    Result<JSONObject> save(@RequestBody SpecGroupDTO specGroupDto);

    @ApiModelProperty(value = "规格组修改")
    @PutMapping(value = "specGroup/save")
    Result<JSONObject> edit(@RequestBody SpecGroupDTO specGroupDto);

    @ApiModelProperty(value = "通过id删除规格")
    @DeleteMapping(value = "specGroup/delete/{id}")
    Result<JSONObject> delete(@PathVariable(value = "id") Integer id);

}
