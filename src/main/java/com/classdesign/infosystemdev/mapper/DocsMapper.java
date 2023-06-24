package com.classdesign.infosystemdev.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.classdesign.infosystemdev.entity.Docs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.classdesign.infosystemdev.vo.StaffDocsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 文件表 Mapper 接口
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Mapper
public interface DocsMapper extends BaseMapper<Docs> {

    /**
     * 通过文件名和员工名找到问价所有信息和员工名
     * @return
     */
    @Select("select sd.*,ss.name staff_name from sys_staff ss left join sys_docs sd on ss.id = sd.staff_id " +
            "where sd.is_deleted = 0 and sd.old_name like concat('%',#{oldName},'%') and ss.name like concat('%',#{staffName},'%')")
    IPage<StaffDocsVO> listStaffDocsVO(IPage<StaffDocsVO> config, @Param("olderName") String oldName,@Param("staffName") String staffName);
}
