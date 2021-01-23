package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;
import com.baidu.shop.entity.CategoryBrandEntity;
import com.baidu.shop.mapper.BrandMapper;
import com.baidu.shop.mapper.CategoryBrandMapper;
import com.baidu.shop.service.BrandService;
import com.baidu.shop.utils.BaiduBeanUtil;
import com.baidu.shop.utils.PinyinUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BrandServiceImpl extends BaseApiService implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    @Resource
    private CategoryBrandMapper categoryBrandMapper;

    @Transactional
    @Override
    public Result<JSONObject> brandSave(BrandDTO brandDTO) {
        BrandEntity brandEntity = BaiduBeanUtil.copyProperties(brandDTO, BrandEntity.class);
        brandEntity.setLetter(PinyinUtil.getUpperCase(String.valueOf(brandEntity.getName().toCharArray()[0]), false).toCharArray()[0]);
        brandMapper.insertSelective(brandEntity);

        this.addToList(brandDTO.getCategories(), brandEntity.getId());
        return this.setResultSuccess();
    }

    @Override
    public Result<PageInfo<BrandEntity>> brandList(BrandDTO brandDTO) {
        if (!StringUtils.isEmpty(brandDTO.getSort())) PageHelper.orderBy(brandDTO.getOrder());

        BrandEntity brandEntity = BaiduBeanUtil.copyProperties(brandDTO, BrandEntity.class);

        PageHelper.startPage(brandDTO.getPage(), brandDTO.getRows());
        Example example = new Example(BrandEntity.class);

        if (!StringUtils.isEmpty(brandEntity.getName()))
            example.createCriteria().andLike("name", "%" + brandEntity.getName() + "%");

        List<BrandEntity> brandEntities = brandMapper.selectByExample(example);
        PageInfo<BrandEntity> pageInfo = new PageInfo<>(brandEntities);

        return this.setResultSuccess(pageInfo);
    }

    private void addToList(String categories, Integer id) {
        if (StringUtils.isEmpty(categories)) throw new RuntimeException("没有获取到分类");

        if (categories.contains(",")) {
            String[] categoryArr = categories.split(",");
            List<String> strings = new ArrayList<>();
            for (String str : categoryArr) {
                strings.add(str);
            }
            categoryBrandMapper.insertList(
                    Arrays.asList(categories.split(","))
                            .stream()
                            .map(categoryIdStr ->
                                    new CategoryBrandEntity(Integer.parseInt(categoryIdStr)
                                            , id))
                            .collect(Collectors.toList()));
        } else {
            CategoryBrandEntity categoryBrandEntity = new CategoryBrandEntity();
            categoryBrandEntity.setCategoryId(id);
            categoryBrandEntity.setBrandId(Integer.parseInt(categories));
            categoryBrandMapper.insertSelective(categoryBrandEntity);
        }
    }
}
