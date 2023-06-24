package com.classdesign.infosystemdev.mapper;

import com.classdesign.infosystemdev.entity.Leave;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 请假表 Mapper 接口
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Mapper
public interface LeaveMapper extends BaseMapper<Leave> {
    /**
     * 根据id查找所有的请假记录
     * @return
     */
    @Select("select * from att_leave where is_deleted = 0 and dept_id = #{id}")
    List<Leave> findByDeptId(@Param("id") Integer id);
}
