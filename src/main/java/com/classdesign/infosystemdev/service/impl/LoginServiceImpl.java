package com.classdesign.infosystemdev.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.classdesign.infosystemdev.dto.Response;
import com.classdesign.infosystemdev.dto.ResponseDTO;
import com.classdesign.infosystemdev.entity.Staff;
import com.classdesign.infosystemdev.enums.BusinessStatusEnum;
import com.classdesign.infosystemdev.mapper.StaffMapper;
import com.classdesign.infosystemdev.service.LoginsService;
import com.classdesign.infosystemdev.utils.JWTUtil;
import com.classdesign.infosystemdev.utils.MD5Util;
import com.classdesign.infosystemdev.vo.StaffDeptVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl extends ServiceImpl<StaffMapper, Staff> implements LoginsService {

    @Resource
    private StaffMapper staffMapper;

    public ResponseDTO login(Staff staff){
        String pwd= MD5Util.MD55(staff.getPwd());
        StaffDeptVO staffDeptVO =staffMapper.findStaffInfo(staff.getCode(),pwd);
        //判断是否存在该对象
        if(staffDeptVO != null){
            //验证用户状态
            if(staffDeptVO.getStatus() == 1){
                String token = JWTUtil.generateToken(staffDeptVO.getId(),pwd);
                return Response.success(staffDeptVO,token);  //返回员工信息和token
            }
            return  Response.error(BusinessStatusEnum.STAFF_STATUS_ERROR);
        }
        return  Response.error("用户名或密码错误");
    }
}
