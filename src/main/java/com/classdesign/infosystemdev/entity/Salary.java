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

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 员工工资表
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Getter
@Setter
@TableName("sal_salary")
@ApiModel(value = "Salary对象", description = "员工工资表")
public class Salary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("员工id")
    @TableField("staff_id")
    private Integer staffId;

    @ApiModelProperty("基础工资")
    @TableField("base_salary")
    private BigDecimal baseSalary;

    @ApiModelProperty("加班费")
    @TableField("overtime_salary")
    private BigDecimal overtimeSalary;

    @ApiModelProperty("生活补贴")
    @TableField("subsidy")
    private BigDecimal subsidy;

    @ApiModelProperty("奖金")
    @TableField("bonus")
    private BigDecimal bonus;

    @ApiModelProperty("总工资")
    @TableField("total_salary")
    private BigDecimal totalSalary;

    @ApiModelProperty("早退扣款")
    @TableField("late_deduct")
    private BigDecimal lateDeduct;

    @ApiModelProperty("休假扣款")
    @TableField("leave_deduct")
    private BigDecimal leaveDeduct;

    @ApiModelProperty("早退扣款")
    @TableField("leave_early_deduct")
    private BigDecimal leaveEarlyDeduct;

    @ApiModelProperty("旷工扣款")
    @TableField("absenteeism_deduct")
    private BigDecimal absenteeismDeduct;

    @ApiModelProperty("月份")
    @TableField("month")
    private String month;

    @TableField("remark")
    private String remark;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
