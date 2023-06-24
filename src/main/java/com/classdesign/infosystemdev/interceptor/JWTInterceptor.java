package com.classdesign.infosystemdev.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.classdesign.infosystemdev.entity.Staff;
import com.classdesign.infosystemdev.enums.BusinessStatusEnum;
import com.classdesign.infosystemdev.exception.ServiceException;
import com.classdesign.infosystemdev.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import sun.plugin.cache.JarCacheVersionException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
/**
 * 创建JWT拦截器
 */
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {

    @Autowired
    private StaffService staffService;


    /**
     * 处理器拦截器的预处理，在请求到达处理器之前被调用，返回类型为bool，如果返回是false，那么请求并不会发送到处理器
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("token");//从http请求头中获取token
        if (StrUtil.isBlank(token)) {

            //获取员工id
            Integer id = Integer.valueOf(JWT.decode(token).getAudience().get(0));
            //根据id查询员工
            Staff staff = staffService.getById(id);
            if (staff != null) {
                //获取员工状态
                if (staff.getStatus() == 1) {
                    JWTVerifier verifier = JWT.require(Algorithm.HMAC256(staff.getPwd())).build();
                    try {
                        verifier.verify(token);//验证token
                    } catch (JWTVerificationException e) {
                        throw new ServiceException(BusinessStatusEnum.TOKEN_INVALID);
                    }
                    return true;
                }
                throw new ServiceException(BusinessStatusEnum.STAFF_STATUS_ERROR);
            }
            throw new ServiceException(BusinessStatusEnum.STAFF_NOT_EXIST);
        }
        throw new ServiceException(BusinessStatusEnum.TOKEN_NOT_EXIST);
    }
}
