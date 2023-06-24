package com.classdesign.infosystemdev.utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类，专门用来生成JWT
 */

public class JWTUtil {



    /**
     * 生成token
     * @param id
     * @param password
     * @return
     */
    public static  String generateToken(Integer id,String password){
        /*return JWT.create().withAudience(id.toString())
                .withExpiresAt(DateUtil.offsetHour(new Date(),1))
                .sign(Algorithm.HMAC256(password));*/
        Date currentTime=new Date();
        Map<String,Object>  claims=new HashMap<>();
        String token =JWT.create().withAudience(id.toString())
                .withIssuer("group2")
                .withExpiresAt(DateUtil.offsetDay(currentTime,1))
                .sign(Algorithm.HMAC256(password));

        return  token;
    }
}
