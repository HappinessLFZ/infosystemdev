package com.classdesign.infosystemdev.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Getter
@Setter
@TableName("sys_dept")
@ApiModel(value = "Dept对象", description = "部门表")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("部门id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("部门编码")
    @TableField("code")
    private String code;

    @ApiModelProperty("部门名称")
    @TableField("name")
    private String name;

    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    @ApiModelProperty("上午上班时间")
    @TableField("mor_start_time")
    private Date morStartTime;

    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    @ApiModelProperty("上午下班时间")
    @TableField("mor_end_time")
    private Date morEndTime;

    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    @ApiModelProperty("下午上班时间")
    @TableField("aft_start_time")
    private Date aftStartTime;

    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm", timezone = "GMT+8")
    @ApiModelProperty("下午下班时间")
    @TableField("aft_end_time")
    private Date aftEndTime;

    @ApiModelProperty("员工工作总时长")
    @TableField("total_work_time")
    private BigDecimal totalWorkTime;

    @ApiModelProperty("部门备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("父级部门id，0根部门")
    @TableField("parent_id")
    private Integer parentId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("逻辑删除，0未删除，1删除")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    @ApiModelProperty("子部门")
    private List<Dept> children;


}
