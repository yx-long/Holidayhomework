package com.baidu.shop.service;

import com.baidu.shop.base.Result;
import com.baidu.shop.dto.CategoryDTO;
import com.baidu.shop.entity.CategoryEntity;
import com.baidu.shop.utils.JSONUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "分类接口")
public interface CategoryService {

    @ApiOperation(value = "通过pid查询商品分类")
    @GetMapping(value = "category/list")
    Result<List<CategoryEntity>> categoryList(Integer pid);

    @ApiOperation(value = "通过id删除商品分类")
    @DeleteMapping(value = "category/delete")
    Result<JSONUtil> categoryDelete(Integer id);

    @ApiOperation(value = "商品分类修改")
    @PutMapping(value = "category/edit")
    Result<JSONUtil> categoryEdit(@RequestBody CategoryDTO categoryDTO);

    @ApiOperation(value = "商品分类新增")
    @PostMapping(value = "category/save")
    Result<JSONUtil> categorySave(@RequestBody CategoryDTO categoryDTO);

    @GetMapping(value = "category/brand")
    @ApiOperation(value = "通过品牌id查询商品分类")
    Result<List<CategoryEntity>> getByBrand (Integer brandId);

}
