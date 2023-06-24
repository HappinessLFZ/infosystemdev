package com.classdesign.infosystemdev.service.impl;

import com.classdesign.infosystemdev.entity.City;
import com.classdesign.infosystemdev.mapper.CityMapper;
import com.classdesign.infosystemdev.service.CityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 参保城市表 服务实现类
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City> implements CityService {

}
