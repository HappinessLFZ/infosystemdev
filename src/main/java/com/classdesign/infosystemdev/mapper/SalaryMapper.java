package com.classdesign.infosystemdev.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.classdesign.infosystemdev.entity.Salary;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.classdesign.infosystemdev.vo.StaffSalaryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 员工工资表 Mapper 接口
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Mapper
public interface SalaryMapper extends BaseMapper<Salary> {
    /**
     * 查找所有员工薪资报酬
     * @return
     */
    @Select("select ss.id staff_id,ss.dept_id,ss.code,ss.name,ss.phone,ss.address,sd.name dept_name,si.per_social_pay social_pay,si.per_house_pay house_pay " +
            "from sys_staff ss inner join sys_dept sd on ss.dept_id=sd.id left join soc_insurance si on ss.id = si.staff_id where ss.is_deleted =0")
    List<StaffSalaryVO> findStaffSalaryVO();

    /**
     * 根据名字查找行营的薪资部门信息
     * @param config
     * @param name
     * @return
     */
    @Select("select ss.id staff_id , ss.dept_id , ss.code , ss.name ,ss.phone , ss.address , sd.name dept_name , si.per_social_pay social_pay , si.per_house_pay house_pay" +
            "from sys_staff ss inner join sys_dept sd on ss.dept_id = sd.id left join soc_insurance si on ss.id = si.staff_id " +
            "where ss.is_deleted = 0 and ss.name like concat('%',#{name},''%)")
    IPage<StaffSalaryVO> listStaffSalaryVO(IPage<StaffSalaryVO> config, @Param("name") String name);


    /**
     * 根据部门id和名字进行查询
     * @param config
     * @param name
     * @param deptId
     * @return
     */
    @Select("select ss.id staff_id ,ss.dept_id ,ss.code ,ss.name ,ss.phone ,ss.address ,sd.name dept_name ,si.per_social_pay social_pay ,si.per_house_pay house_pay" +
            "from sys_staff ss inner join sys_dept sd on ss.dept_id = sd.id left join soc_insurance si on ss.id =si.staff_id " +
            "where ss.is_deleted = 0 and ss.dept_id =#{deptId} and ss.name like concat('%',#{name},'%')")
    IPage<StaffSalaryVO> listStaffDeptSalaryVO(IPage<StaffSalaryVO> config,@Param("name") String name,@Param("deptId") Integer deptId);
}
