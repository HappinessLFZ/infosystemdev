package com.classdesign.infosystemdev.vo;

import com.classdesign.infosystemdev.annotation.ExcelColumn;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 员工缺勤记录表
 */
@Data
public class MonthAttendanceVO implements Serializable {
    private static final long  seriaVersionUID=1L;

    @ApiModelProperty("员工id")
    private Integer staffId;

    @ApiModelProperty("部门id")
    private Integer deptId;

    @ExcelColumn("员工工号")
    @ApiModelProperty("员工工号")
    private String code;

    @ExcelColumn("员工姓名")
    @ApiModelProperty("员工姓名")
    private String name;

    @ExcelColumn("电话")
    @ApiModelProperty("电话")
    private String phone;

    @ExcelColumn("地址")
    @ApiModelProperty("地址")
    private String address;

    @ExcelColumn("部门名称")
    @ApiModelProperty("部门名称")
    private String  deptName;

    @ApiModelProperty("迟到次数")
    private Integer lateTimes;

    @ApiModelProperty("早退次数")
    private Integer leaveEarlyTimes;

    @ApiModelProperty("旷工次数")
    private Integer absenteeismTimes;

    @ApiModelProperty("请假天数")
    private Integer leaveDays;
}
