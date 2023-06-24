package com.classdesign.infosystemdev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.classdesign.infosystemdev.dto.Response;
import com.classdesign.infosystemdev.dto.ResponseDTO;
import com.classdesign.infosystemdev.entity.StaffRole;
import com.classdesign.infosystemdev.enums.BusinessStatusEnum;
import com.classdesign.infosystemdev.exception.ServiceException;
import com.classdesign.infosystemdev.mapper.StaffRoleMapper;
import com.classdesign.infosystemdev.service.StaffRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.server.ServerCloneException;
import java.util.List;

/**
 * <p>
 * 员工角色关系表 服务实现类
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Service
public class StaffRoleServiceImpl extends ServiceImpl<StaffRoleMapper, StaffRole> implements StaffRoleService {

    /**
     * 给指定员工分配职务
     * @param staffId
     * @param roleIds
     * @return
     */
    @Transactional
    public ResponseDTO setRole(Integer staffId, List<Integer> roleIds){
        LambdaQueryWrapper<StaffRole> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(StaffRole::getStaffId,staffId);
        //获取员工职务
        List<StaffRole> list=list(wrapper);
        //禁用不需要的职务
        for(StaffRole staffRole : list){
            if(roleIds.contains(staffRole.getRoleId())){
                staffRole.setStatus(1);
            }else {
                staffRole.setStatus(0);
            }
            updateById(staffRole);
        }
        //根据条件添加或更新
        for(Integer roleId :roleIds){
            StaffRole staffRole=new StaffRole();
            //向员工职务对象数据表中添加一条数据
            staffRole.setStaffId(staffId);
            staffRole.setRoleId(roleId);
            staffRole.setStatus(1);
            LambdaQueryWrapper<StaffRole> queryWrapper=new LambdaQueryWrapper<>();
            //设置查询条件 ，根据员工id和职务id构建条件
            queryWrapper.eq(StaffRole::getStaffId,staffId)
                        .eq(StaffRole::getRoleId,roleId);
            //saveOrUpdate方法当传入实体类中没有设置id则执行插入操作
            //如果设置了id，那么先通过id去执行查询操作看看有没有数据，有的话会根据id去修改
            if(!saveOrUpdate(staffRole,queryWrapper)){
                throw  new ServiceException(BusinessStatusEnum.ERROR);
            }
        }
        return Response.success();
    }

    /**
     * 根据用户id获取员工职务所有信息
     * @param staffId
     * @return
     */
    public ResponseDTO getRole(Integer staffId){
        List<StaffRole> list =list(new LambdaQueryWrapper<StaffRole>()
                .eq(StaffRole::getId,staffId)
                .eq(StaffRole::getStatus,1));
        return Response.success(list);
    }
}
