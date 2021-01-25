package com.baidu.shop.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.dto.SpecParamDTO;
import com.baidu.shop.entity.SpecParamEntity;
import com.baidu.shop.mapper.SpecParamMapper;
import com.baidu.shop.service.SpecParamService;
import com.baidu.shop.utils.BaiduBeanUtil;
import com.baidu.shop.utils.ObjectUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
@RestController
public class SpecParamServiceImpl extends BaseApiService implements SpecParamService {

    @Resource
    private SpecParamMapper specParamMapper;

    @Override
    public Result<List<SpecParamEntity>> getSpecParamInfo(SpecParamDTO specParamDTO) {
        SpecParamEntity spceParamsEntity = BaiduBeanUtil.copyProperties(specParamDTO, SpecParamEntity.class);

        Example example = new Example(SpecParamEntity.class);
        Example.Criteria criteria = example.createCriteria();

        if (ObjectUtil.isNotNull(spceParamsEntity.getGroupId()))
            criteria.andEqualTo("groupId", spceParamsEntity.getGroupId());

        if (ObjectUtil.isNotNull(spceParamsEntity.getCid()))
            criteria.andEqualTo("cid", spceParamsEntity.getCid());

        List<SpecParamEntity> spceParamsEntities = specParamMapper.selectByExample(example);
        return this.setResultSuccess(spceParamsEntities);
    }

    @Transactional
    @Override
    public Result<JSONObject> save(SpecParamDTO specParamDTO) {
        specParamMapper.insertSelective(BaiduBeanUtil.copyProperties(specParamDTO,SpecParamEntity.class));
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> edit(SpecParamDTO specParamDto) {
        specParamMapper.updateByPrimaryKeySelective(BaiduBeanUtil.copyProperties(specParamDto,SpecParamEntity.class));
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONObject> delete(Integer id) {
        specParamMapper.deleteByPrimaryKey(id);
        return this.setResultSuccess();
    }
}
