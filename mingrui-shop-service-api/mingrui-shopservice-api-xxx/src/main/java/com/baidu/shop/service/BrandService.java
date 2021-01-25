package com.baidu.shop.service;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;
import com.baidu.shop.entity.CategoryEntity;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "品牌接口")
public interface BrandService {

    @ApiOperation(value = "品牌查询")
    @GetMapping(value = "brand/list")
    Result<PageInfo<BrandEntity>> brandList(BrandDTO brandDTO);

    @ApiOperation(value = "品牌新增")
    @PostMapping(value = "brand/save")
    Result<JSONObject> brandSave(@RequestBody BrandDTO brandDTO);

    @ApiOperation(value = "品牌修改")
    @PutMapping(value = "brand/save")
    Result<JSONObject> brandEdit(@RequestBody BrandDTO brandDTO);

    @ApiOperation(value = "品牌删除")
    @DeleteMapping(value = "brand/delete")
    Result<JSONObject> brandDelete(Integer id);

}
