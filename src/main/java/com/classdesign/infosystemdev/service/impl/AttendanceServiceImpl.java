package com.classdesign.infosystemdev.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classdesign.infosystemdev.dto.Response;
import com.classdesign.infosystemdev.dto.ResponseDTO;
import com.classdesign.infosystemdev.entity.Attendance;
import com.classdesign.infosystemdev.mapper.AttendanceMapper;
import com.classdesign.infosystemdev.mapper.DeptMapper;
import com.classdesign.infosystemdev.mapper.StaffMapper;
import com.classdesign.infosystemdev.service.AttendanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.classdesign.infosystemdev.vo.StaffAttendanceVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 员工考勤表 服务实现类
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Service
public class AttendanceServiceImpl extends ServiceImpl<AttendanceMapper, Attendance> implements AttendanceService {

    @Resource
    private AttendanceMapper attendanceMapper;

    @Resource
    private DeptMapper deptMapper;

    @Resource
    private StaffMapper staffMapper;

    /**
     * 添加
     * @param attendance
     * @return
     */
    public ResponseDTO add(Attendance attendance){
        if(save(attendance)){
            return Response.success();
        }
        return Response.error();
    }

    /**
     * 逻辑删除id
     * @param id
     * @return
     */
    public ResponseDTO deleteById(Integer id){
        if(removeById(id)){
            return  Response.success();
        }
        return Response.error();
    }

    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO deleteBatchById(List<Integer> ids){
        if(removeBatchByIds(ids)){
            return  Response.success();
        }
        return Response.error();
    }

    /**
     * 修改
     * @param attendance
     * @return
     */
    public ResponseDTO edit(Attendance attendance) {
        if (updateById(attendance)) {
            return Response.success();
        }
        return Response.error();
    }

    /**
     *  查找
     * @param id
     * @return
     */
    public ResponseDTO findById(Integer id) {
        Attendance attendance = getById(id);
        if (attendance != null) {
            return Response.success(attendance);
        }
        return Response.error();
    }

    /*public ResponseDTO list(Integer current,Integer size,String name,Integer deptId,String month){
        IPage<StaffAttendanceVO> config=new Page<>(current,size);//第一个参数表示页码数，第二个表示每页多少条数据
        //解决搜索条件未空时，默认查询所有数据
        if(name == null){
            name="";
        }
        IPage<StaffAttendanceVO> page;
    }*/
}
