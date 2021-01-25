package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecGroupDTO;
import com.baidu.shop.entity.SpecGroupEntity;
import com.baidu.shop.mapper.SpecGroupMapper;
import com.baidu.shop.service.SpecGroupService;
import com.baidu.shop.utils.BaiduBeanUtil;
import com.baidu.shop.utils.ObjectUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SpecGroupServiceImpl extends BaseApiService implements SpecGroupService {

    @Resource
    private SpecGroupMapper specGroupMapper;

    //规格组查询
    @Override
    public Result<List<SpecGroupEntity>> getSpecGroupInfo(SpecGroupDTO specGroupDto) {
        Example example = new Example(SpecGroupEntity.class);
        SpecGroupEntity spceGroupEntity = BaiduBeanUtil.copyProperties(specGroupDto, SpecGroupEntity.class);
        Example.Criteria criteria = example.createCriteria();
        if (ObjectUtil.isNotNull(spceGroupEntity.getCid()))
            criteria.andEqualTo("cid", spceGroupEntity.getCid());
        List<SpecGroupEntity> spceGroupEntities = specGroupMapper.selectByExample(example);
        return this.setResultSuccess(spceGroupEntities);
    }

    //规格组新增
    @Transactional
    @Override
    public Result<JSONObject> save(SpecGroupDTO specGroupDto) {
        specGroupMapper.insertSelective(BaiduBeanUtil.copyProperties(specGroupDto, SpecGroupEntity.class));
        return this.setResultSuccess();
    }

    //规格组修改
    @Transactional
    @Override
    public Result<JSONObject> edit(SpecGroupDTO specGroupDto) {
        specGroupMapper.updateByPrimaryKeySelective(BaiduBeanUtil.copyProperties(specGroupDto, SpecGroupEntity.class));
        return this.setResultSuccess();
    }

    //规格组删除
    @Transactional
    @Override
    public Result<JSONObject> delete(Integer id) {
        specGroupMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }
}
