package com.classdesign.infosystemdev.vo;

import com.classdesign.infosystemdev.annotation.ExcelColumn;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 *
 */
@Data
public class StaffAttendanceVO  implements Serializable {

    private static final long seriaVersionUID = 1L;

    @ApiModelProperty("员工id")
    private Integer staffId;

    @ApiModelProperty("部门id")
    private Integer deptId;

    @ApiModelProperty("员工工号")
    @ExcelColumn("员工工号")
    private Integer code;

    @ApiModelProperty("员工姓名")
    @ExcelColumn("员工姓名")
    private String  name;

    @ApiModelProperty("号码")
    @ExcelColumn("号码")
    private String phone;

    @ApiModelProperty("地址")
    @ExcelColumn("地址")
    private String address;

    @ExcelColumn("部门名称")
    @ApiModelProperty("部门名称")
    private String deptName;

    @ApiModelProperty("员工考勤数据")
    private List<HashMap<String,Object>> attendanceList;

}
