package com.classdesign.infosystemdev.service.impl;

import com.classdesign.infosystemdev.entity.Role;
import com.classdesign.infosystemdev.mapper.RoleMapper;
import com.classdesign.infosystemdev.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工角色表 服务实现类
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
