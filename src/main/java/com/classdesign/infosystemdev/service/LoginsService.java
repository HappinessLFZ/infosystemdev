package com.classdesign.infosystemdev.service;

import cn.hutool.json.JSONUtil;
import com.classdesign.infosystemdev.dto.ResponseDTO;
import com.classdesign.infosystemdev.entity.Staff;

/**
 * 实现github登录
 */
public interface LoginsService {
    ResponseDTO login(Staff staff);
}
