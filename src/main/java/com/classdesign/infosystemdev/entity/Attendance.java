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
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 员工考勤表
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Data
@Accessors(chain = true)
@TableName("att_attendance")
@ApiModel(value = "Attendance对象", description = "员工考勤表")
public class Attendance implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ExcelColumn("员工id")
    @ApiModelProperty("员工id")
    @TableField("staff_id")
    private Integer staffId;

    @ApiModelProperty("上午上班时间")
    @TableField("mor_start_time")
    @ExcelColumn()
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm",timezone = "GMT+8")
    private Date morStartTime;

    @ExcelColumn()
    @ApiModelProperty("上午下班时间")
    @TableField("mor_end_time")
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm",timezone = "GMT+8")
    private Date morEndTime;

    @ExcelColumn()
    @ApiModelProperty("下午上班时间")
    @TableField("aft_start_time")
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm",timezone = "GMT+8")
    private Date aftStartTime;

    @ExcelColumn()
    @ApiModelProperty("下午下班时间")
    @TableField("aft_end_time")
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm",timezone = "GMT+8")
    private Date aftEndTime;

    @ApiModelProperty("考勤日期")
    @TableField("attendance_date")
    @ExcelColumn("考勤日期")
    private Date attendanceDate;

    @ApiModelProperty("0正常，1迟到，2早退，3旷工，4休假")
    @TableField("status")
    private Integer status;

    @TableField("remark")
    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    private Date updateTime;

    @TableField("is_deleted")
    @TableLogic
    @ApiModelProperty("逻辑删除，0未删除，1删除")
    private Integer isDeleted;


}
