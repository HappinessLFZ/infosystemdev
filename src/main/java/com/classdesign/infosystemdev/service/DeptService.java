package com.classdesign.infosystemdev.service;

import com.classdesign.infosystemdev.dto.ResponseDTO;
import com.classdesign.infosystemdev.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
public interface DeptService extends IService<Dept> {

    ResponseDTO add(Dept dept);

    ResponseDTO deleteById(Integer id);

    ResponseDTO deleteBatch(List<Integer> ids);

    ResponseDTO edit(Dept dept);

    ResponseDTO findAll();

    ResponseDTO findById(Integer id);

    ResponseDTO list(Integer current, Integer size, String name);

    ResponseDTO export(HttpServletResponse response) throws IOException;

    ResponseDTO imp(MultipartFile file) throws IOException;

    List<Dept> setSubDept(List<Dept> list);

    ResponseDTO findAllSubDept();
}
