package com.baidu.shop.service.impl;

import com.baidu.shop.base.BaseApiService;
import com.baidu.shop.base.Result;
import com.baidu.shop.entity.CategoryEntity;
import com.baidu.shop.mapper.CategoryMapper;
import com.baidu.shop.service.CategoryService;
import com.baidu.shop.utils.JSONUtil;
import com.baidu.shop.utils.ObjectUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CategoryServiceImpl extends BaseApiService implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    @Transactional
    @Override
    public Result<JSONUtil> categoryEdit (CategoryEntity categoryEntity) {
        try {
            categoryMapper.updateByPrimaryKeySelective(categoryEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONUtil> categorySave (CategoryEntity entity) {
        CategoryEntity categoryEntity1 = new CategoryEntity();
        categoryEntity1.setId(entity.getParentId());
        categoryEntity1.setIsParent(1);
        categoryMapper.updateByPrimaryKeySelective(categoryEntity1);
        categoryMapper.insertSelective(entity);
        return this.setResultSuccess();
    }

    @Transactional
    @Override
    public Result<JSONUtil> categoryDelete(Integer id) {
        //判断当前id是否合法
        if(ObjectUtil.isNotNull(id) && id > 0){
            //查询当前节点的信息
            CategoryEntity categoryEntity = categoryMapper.selectByPrimaryKey(id);
            //判断当前节点是否为父级节点
            if(categoryEntity.getParentId() ==1 ) return this.setResultError("当前节点为父节点不能删除");
            //通过当前节点的id查询当前节点的父节点下是否存在叶子节点
            Example example = new Example(CategoryEntity.class);
            example.createCriteria().andEqualTo("parentId",categoryEntity.getParentId());
            List<CategoryEntity> categoryEntities = categoryMapper.selectByExample(example);
            //如果size的长度大于1当前节点下还存在叶子节点不能删除,如果长度小于或者等于一的话当前节点下没有叶子节点就把当前节点修改为叶子节点
            if(categoryEntities.size() <= 1 ){
                CategoryEntity categoryEntity1 = new CategoryEntity();
                //把父节点的状态改为0就变成了叶子节点
                categoryEntity1.setIsParent(0);
                categoryEntity1.setId(categoryEntity.getParentId());
                //修改父节点为叶子节点
                categoryMapper.updateByPrimaryKeySelective(categoryEntity1);
            }
            //执行删除
            categoryMapper.deleteByPrimaryKey(id);
            return this.setResultSuccess();
        }
        return this.setResultError("id不合法");
    }

    @Override
    public Result<List<CategoryEntity>> categoryList(Integer pid) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setParentId(pid);
        List<CategoryEntity> list = categoryMapper.select(categoryEntity);
        return this.setResultSuccess(list);
    }
}
