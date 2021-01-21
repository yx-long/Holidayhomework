package com.baidu.shop.dto;

import com.baidu.shop.validate.group.MingruiOperation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.catalina.mapper.Mapper;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "分类DTO")
public class CategoryDTO {

    @ApiModelProperty(value = "分类主键",example = "1")
    @NotNull(message = "主键不能为空",groups = {MingruiOperation.Update.class})
    private Integer id;

    @ApiModelProperty(value = "分类名称")
    @NotEmpty(message = "分类名称不能为空",groups = {MingruiOperation.Add.class})
    private String name;

    @ApiModelProperty(value = "父级分类",example = "1")
    @NotNull(message = "分类父级id不能为空",groups = {MingruiOperation.Add.class})
    private Integer parentId;

    @ApiModelProperty(value = "是否是父级节点",example = "1")
    private Integer isParent;

    @ApiModelProperty(value = "排序",example = "1")
    private Integer sort;
}
