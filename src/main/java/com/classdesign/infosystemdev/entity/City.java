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

import com.classdesign.infosystemdev.annotation.ExcelColumn;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 参保城市表
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Data
@TableName("soc_city")
@ApiModel(value = "City对象", description = "参保城市表")
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("参保城市")
    @ExcelColumn("城市")
    @TableField("name")
    private String name;

    @ApiModelProperty("职工上年度平均月工资")
    @TableField("average_salary")
    private BigDecimal averageSalary;

    @ApiModelProperty("职工上年度最低月工资")
    @TableField("lower_salary")
    private BigDecimal lowerSalary;

    @ExcelColumn("职工社保缴纳基数上限")
    @ApiModelProperty("职工社保缴纳基数上限")
    @TableField("soc_upper_limit")
    private BigDecimal socUpperLimit;

    @ExcelColumn("职工社保缴纳基数下限")
    @ApiModelProperty("职工社保缴纳基数下限")
    @TableField("soc_lower_limit")
    private BigDecimal socLowerLimit;

    @ExcelColumn("公积金缴纳基数上限")
    @ApiModelProperty("公积金缴纳基数上限")
    @TableField("hou_upper_limit")
    private BigDecimal houUpperLimit;

    @ExcelColumn("公积金缴纳基数下限")
    @ApiModelProperty("公积金缴纳基数下限")
    @TableField("hou_lower_limit")
    private BigDecimal houLowerLimit;

    @ExcelColumn("个人养老保险缴费比例")
    @ApiModelProperty("个人养老保险缴费比例")
    @TableField("per_pension_rate")
    private BigDecimal perPensionRate;

    @ExcelColumn("企业养老保险缴费比例")
    @ApiModelProperty("企业养老保险缴费比例")
    @TableField("com_pension_rate")
    private BigDecimal comPensionRate;

    @ExcelColumn("个人医疗保险缴费比例")
    @ApiModelProperty("个人医疗保险缴费比例")
    @TableField("per_medical_rate")
    private BigDecimal perMedicalRate;

    @ExcelColumn("企业医疗保险缴费比例")
    @ApiModelProperty("企业医疗保险缴费比例")
    @TableField("com_medical_rate")
    private BigDecimal comMedicalRate;

    @ExcelColumn("个人失业保险缴费比例")
    @ApiModelProperty("个人失业保险缴费比例")
    @TableField("per_unemployment_rate")
    private BigDecimal perUnemploymentRate;

    @ExcelColumn("企业失业保险缴费比例")
    @ApiModelProperty("企业失业保险缴费比例")
    @TableField("com_unemployment_rate")
    private BigDecimal comUnemploymentRate;

    @ExcelColumn("企业生育保险缴费比例")
    @ApiModelProperty("企业生育保险缴费比例")
    @TableField("com_maternity_rate")
    private BigDecimal comMaternityRate;

    @ApiModelProperty("备注")
    @TableField("remark")
    @ExcelColumn("备注")
    private String remark;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @TableField("is_deleted")
    @TableLogic
    @ApiModelProperty("逻辑删除，0未删除，1删除")
    private Integer isDeleted;


}
