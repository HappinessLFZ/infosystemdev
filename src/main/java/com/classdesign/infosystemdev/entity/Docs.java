package com.classdesign.infosystemdev.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文件表
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Data
@TableName("sys_docs")
@ApiModel(value = "Docs对象", description = "文件表")
public class Docs implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("文件名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("文件类型")
    @TableField("type")
    private String type;

    @ApiModelProperty("文件的原名称")
    @TableField("old_name")
    private String oldName;

    @ApiModelProperty("文件md5信息")
    @TableField("md5")
    private String md5;

    @ApiModelProperty("文件大小KB")
    @TableField("size")
    private Long size;

    @ApiModelProperty("文件上传者id")
    @TableField("staff_id")
    private Integer staffId;

    @ApiModelProperty("文件备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("0未删除，1已删除，默认为0")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
