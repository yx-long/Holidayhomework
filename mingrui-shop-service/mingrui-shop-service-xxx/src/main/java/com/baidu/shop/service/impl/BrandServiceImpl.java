package com.baidu.shop.service.impl;

import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.BaseDTO;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.BrandDTO;
import com.baidu.shop.entity.BrandEntity;
import com.baidu.shop.mapper.BrandMapper;
import com.baidu.shop.service.BrandService;
import com.baidu.shop.utils.BaiduBeanUtil;
import com.baidu.shop.utils.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class BrandServiceImpl extends BaseApiService implements BrandService {

    @Resource
    private BrandMapper brandMapper;

    @Override
    public Result<PageInfo<BrandEntity>> brandList(BrandDTO brandDTO) {
        if (!StringUtils.isEmpty(brandDTO.getSort())) PageHelper.orderBy(brandDTO.getOrder());

        BrandEntity brandEntity = BaiduBeanUtil.copyProperties(brandDTO, BrandEntity.class);

        PageHelper.startPage(brandDTO.getPage(), brandDTO.getRows());
        Example example = new Example(BrandEntity.class);

        if (!StringUtils.isEmpty(brandEntity.getName())) {
            example.createCriteria().andLike("name", "%" + brandEntity.getName() + "%");
        }

        List<BrandEntity> brandEntities = brandMapper.selectByExample(example);
        PageInfo<BrandEntity> pageInfo = new PageInfo<>(brandEntities);

        return this.setResultSuccess(pageInfo);
    }
}
