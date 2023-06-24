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
 * 加班表
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Getter
@Setter
@TableName("att_overtime")
@ApiModel(value = "Overtime对象", description = "加班表")
public class Overtime implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("工资倍数，如按照小时计算，就是员工平均小时工资乘以倍数")
    @TableField("salary_multiple")
    private BigDecimal salaryMultiple;

    @ApiModelProperty("加班奖金")
    @TableField("bonus")
    private BigDecimal bonus;

    @ApiModelProperty("加班类型")
    @TableField("type_num")
    private Integer typeNum;

    @ApiModelProperty("部门id")
    @TableField("dept_id")
    private Integer deptId;

    @ApiModelProperty("0小时，1天，默认0，计数加班工资的类型")
    @TableField("count_type")
    private Integer countType;

    @TableField("remark")
    private String remark;

    @ApiModelProperty("0不补休，1补休，默认0")
    @TableField("is_time_off")
    private Integer isTimeOff;

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
