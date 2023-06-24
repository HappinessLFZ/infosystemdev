package com.classdesign.infosystemdev.service.impl;

import com.classdesign.infosystemdev.entity.Salary;
import com.classdesign.infosystemdev.mapper.SalaryMapper;
import com.classdesign.infosystemdev.service.SalaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工工资表 服务实现类
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Service
public class SalaryServiceImpl extends ServiceImpl<SalaryMapper, Salary> implements SalaryService {

}
