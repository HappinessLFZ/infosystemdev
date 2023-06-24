package com.classdesign.infosystemdev.service;

import com.classdesign.infosystemdev.dto.ResponseDTO;
import com.classdesign.infosystemdev.entity.StaffRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 员工角色关系表 服务类
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
public interface StaffRoleService extends IService<StaffRole> {

    ResponseDTO setRole(Integer staffId, List<Integer> roleIds);

    ResponseDTO getRole(Integer staffId);
}
