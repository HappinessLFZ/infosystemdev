package com.classdesign.infosystemdev.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
 * 员工表
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Data
@Accessors(chain = true)
@TableName("sys_staff")
@ApiModel(value = "Staff对象", description = "员工表")
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("员工id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ExcelColumn("工号")
    @ApiModelProperty("员工编码")
    @TableField("code")
    private String code;

    @ExcelColumn("姓名")
    @ApiModelProperty("员工姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty("性别，0男，1女，默认0")
    @TableField("gender")
    private Integer gender;

    @ApiModelProperty("员工密码")
    @TableField("pwd")
    private String pwd;

    @ApiModelProperty("员工头像")
    @TableField("avatar")
    private String avatar;

    @ExcelColumn("生日")
    @ApiModelProperty("员工生日")
    @TableField("birthday")
    private Date birthday;

    @ExcelColumn("电话")
    @ApiModelProperty("员工电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("地址")
    @TableField("address")
    private String address;

    @ExcelColumn("备注")
    @ApiModelProperty("员工备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("部门id")
    @TableField("dept_id")
    private Integer deptId;

    @ApiModelProperty("员工状态，0异常，1正常")
    @TableField("status")
    private Integer status;

    @ExcelColumn("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ExcelColumn("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty("逻辑删除，0未删除，1已删除")
    @TableField("is_deleted")
    @TableLogic
    private Integer isDeleted;


}
