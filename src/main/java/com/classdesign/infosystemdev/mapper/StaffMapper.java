package com.classdesign.infosystemdev.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.classdesign.infosystemdev.entity.Staff;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.classdesign.infosystemdev.vo.MonthAttendanceVO;
import com.classdesign.infosystemdev.vo.StaffAttendanceVO;
import com.classdesign.infosystemdev.vo.StaffDeptVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 员工表 Mapper 接口
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Mapper
public interface StaffMapper extends BaseMapper<Staff> {

    /**
     *根据姓名进行员工考勤查询
     * @param config
     * @param name
     * @return
     */
    @Select("select ss.id staff_id,ss.dept_id,ss.code,ss.name,ss.phone,ss.address,sd.name dept_name from sys_staff ss inner join sys_dept sd on ss.dept_id = sd.id " +
            "where ss.is_deleted = 0 and ss.name like concat('%',#{name},'%')")
    IPage<StaffAttendanceVO> listStaffAttendanceVO(IPage<StaffAttendanceVO> config, @Param("name") String name);

    /**
     * 根据姓名和部门id进行员工考勤查询
     * @param config
     * @param name
     * @param deptId
     * @return
     */
    @Select("select ss.id staff_id,ss.dept_id,ss.code,ss.name,ss.phone,ss.address,sd.name dept_name from sys_staff ss inner join sys_dept sd on ss.dept_id = sd.id " +
            "where ss.is_deleted = 0 and ss.dept_id = #{deptId} and ss.name like concat('%',#{name},'%')")
    IPage<StaffAttendanceVO> listStaffDeptAttendanceVO(IPage<StaffAttendanceVO> config, @Param("name") String name,@Param("deptId") Integer deptId);

    /**
     * 查询所有用户的信息
     * @return
     */
    @Select("select ss.id staff_id,ss.dept_id,ss.code,ss.name,ss.phone,ss.address,sd.name dept_name from sys_staff ss inner join sys_dept sd on ss.dept_id = sd.id where ss.is_deleted = 0")
    List<MonthAttendanceVO> findAttendanceMonthVO();

    /**
     * 根据编码和密码进行查询
     * @param code
     * @param password
     * @return
     */
    @Select("select ss.id , ss.code, ss.name, ss.gender, ss.pwd password, ss.avatar, ss.birthday, ss.phone, ss.address, ss.remark,ss.status, ss.dept_id,sd.name dept_name from sys_staff ss left join sys_dept sd on ss.dept_id = sd.id where ss.is_deleted = 0 and ss.code = #{code} and ss.pwd = #{pwd} ")
    StaffDeptVO findStaffInfo(@Param("code") String code,@Param("pwd") String password);

    /**
     * 根据id查找员工部门
     * @param id
     * @return
     */
    @Select("select ss.id , ss.code, ss.name, ss.gender, ss.avatar, ss.birthday, ss.phone, ss.address, ss.remark,ss.status, ss.dept_id,sd.name dept_name from sys_staff ss left join sys_dept sd on ss.dept_id = sd.id where ss.is_deleted = 0 and ss.id = #{id}")
    StaffDeptVO findInfo(@Param("id") Integer id);

    /**
     * 查找所有员工部门
     * @return
     */
    @Select("select ss.id , ss.code, ss.name, ss.gender, ss.pwd password, ss.avatar, ss.birthday, ss.phone, ss.address, ss.remark,ss.status, ss.dept_id,sd.name dept_name from sys_staff ss left join sys_dept sd on ss.dept_id = sd.id where ss.is_deleted = 0")
    List<StaffDeptVO> findStaffDeptVO();

}
