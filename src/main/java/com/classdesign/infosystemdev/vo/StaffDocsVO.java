package com.classdesign.infosystemdev.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

/**
 *文件员工数据体
 */
@Data
public class StaffDocsVO implements Serializable {

    private static final long seriaVersionUID = 1L;

    private Integer id;

    @ApiModelProperty("文件名称")
    private String name;

    @ApiModelProperty("员工姓名")
    private String staffName;

    @ApiModelProperty("文件类型")
    private String type;

    @ApiModelProperty("文件原名称")
    private String oldName;

    @ApiModelProperty("文件MD5信息")
    private String md5;

    @ApiModelProperty("文件大小kb")
    private Long size;

    @ApiModelProperty("文件上传员工id")
    private Integer staffId;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("修改时间")
    private Timestamp uodateTime;
}
