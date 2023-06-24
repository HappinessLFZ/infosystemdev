package com.classdesign.infosystemdev.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.classdesign.infosystemdev.entity.StaffLeave;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.classdesign.infosystemdev.vo.StaffLeaveVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 员工请假表 Mapper 接口
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Mapper
public interface StaffLeaveMapper extends BaseMapper<StaffLeave> {
    /**
     * 根据id查找所有的员工请假数据
     * @param config
     * @param id
     * @return
     */
    @Select("select * from att_staff_leave where is_deleted = 0 and staff_id = #{id}")
    IPage<StaffLeave> listStaffLeaveByStaffId(IPage<StaffLeave> config, @Param("id") Integer id);

    /**
     * 不显示已经撤销的请假申请
     * @param config
     * @param status
     * @param name
     * @return
     */
    @Select("select asl.*,ss.code,ss.name ,ss.phone,sd.name dept_name from att_staff_leave asl inner join sys_staff ss on asl.staff_id = ss.id " +
            "inner join sys_dept sd on ss.dept_id = sd.id " +
            "where asl.is_deleted = 0 and asl.status != #{status} and ss.name like concat('%',#{name},'%')")
    IPage<StaffLeaveVO> listStaffLeaveVO(IPage<StaffLeaveVO> config,@Param("status") Integer status,@Param("name") String name);

    /**
     *查找员工部门请假数据
     * @param config
     * @param status
     * @param name
     * @param deptId
     * @return
     */
    @Select("select asl.*,ss.code,ss.name ,ss.phone,sd.name dept_name from att_staff_leave asl inner join sys_staff ss on asl.staff_id = ss.id " +
            "inner join sys_dept sd on ss.dept_id = sd.id " +
            "where asl.is_deleted = 0 and ss.dept_id = #{deptId} and asl.status != #{status} and ss.name like concat('%',#{name},'%')")
    IPage<StaffLeaveVO> listStaffDeptLeaveVO(IPage<StaffLeaveVO> config,@Param("status") String status,@Param("name") String name,@Param("deptId") Integer deptId );
}
