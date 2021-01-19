package com.baidu.shop.com.baidu.shop;

import com.baidu.shop.base.Result;
import com.baidu.shop.entity.CategoryEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Api(value = "商品分类接口")
public interface CategoryService {

    @ApiOperation(value = "通过pid查询商品分类")
    @GetMapping(value = "category/list")
    Result<List<CategoryEntity>> categoryList (Integer pid);
}
