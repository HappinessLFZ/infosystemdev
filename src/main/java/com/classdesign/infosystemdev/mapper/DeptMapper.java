package com.classdesign.infosystemdev.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.classdesign.infosystemdev.entity.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 部门表 Mapper 接口
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
    /**
     * 查找所偶的父部门
     * @param config
     * @param name
     * @return
     */
    @Select("select * from sys_dept where is_deleted = 0 and parent_id = 0 and name like concat('%',#{name},'%')")
    IPage<Dept> listParentDept(IPage<Dept> config, @Param("name") String name);

    /**
     * 查找所有子部门
     *
     * @return
     */
    @Select("select * from sys_dept where is_deleted = 0 and parent_id != 0")
    List<Dept> findSubDept();


    /**
     * 查找员工所在的部门
     */
    @Select("select sd.* from sys_dept sd right join sys_staff ss on sd.id = ss.dept_id where ss.is_deleted = 0 and ss.id = #{id} ")
    Dept findDeptByStaffId(@Param("id") Integer id);


}
