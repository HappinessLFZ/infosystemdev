package com.classdesign.infosystemdev.mapper;

import com.classdesign.infosystemdev.entity.Attendance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 员工考勤表 Mapper 接口
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Mapper
public interface AttendanceMapper extends BaseMapper<Attendance> {

    /**
     * 根据员工id和考勤日期进行查询考勤情况
     * @param id
     * @param day
     * @return
     */
    @Select("select * from att_attendance where is_deleted = 0 and staff_id = #{id} and date_format(attendance_date,'%Y%m%d') = #{day}")
    Attendance findByStaffId(@Param("id") Integer id,@Param("day") String day);

    /**
     * 统计每月员工迟到，早退，旷工次数
     * @param id
     * @param status
     * @param month
     * @return
     */
    @Select("select count(*) from att_attendance where is_deleted = 0 and staff_id = #{id} and status = #{status} and date_format(attendance_date,'%Y%m') = #{month} ")
    Integer countTimes(@Param("id") Integer id,@Param("status") Integer status,@Param("month") String month);

    /**
     * 查找员工休假的日期
     * @param id
     * @param status
     * @param month
     * @return
     */
    @Select("select attendance_date from att_attendance where is_deleted = 0 and staff_id = #{id} and status=#{status} and date_format(attendance_date,'%Y%m') = #{month} ")
    List<Date> findLeaveDate(@Param("id") Integer id,@Param("status") Integer status,@Param("month") String month);

}
