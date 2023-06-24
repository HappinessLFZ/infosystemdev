package com.classdesign.infosystemdev.service;

import com.classdesign.infosystemdev.dto.ResponseDTO;
import com.classdesign.infosystemdev.entity.Staff;

public interface LoginsService {
    ResponseDTO login(Staff staff);
}
