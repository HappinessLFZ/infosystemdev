package com.classdesign.infosystemdev.mapper;

import com.classdesign.infosystemdev.entity.City;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 参保城市表 Mapper 接口
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Mapper
public interface CityMapper extends BaseMapper<City> {

}
