package com.classdesign.infosystemdev.service;

import com.classdesign.infosystemdev.dto.ResponseDTO;
import com.classdesign.infosystemdev.entity.Staff;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 员工表 服务类
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
public interface StaffService extends IService<Staff> {

    ResponseDTO add(Staff staff);

    ResponseDTO deleteById(Integer id);

    ResponseDTO edit(Staff staff);

    ResponseDTO findById(Integer id);

    ResponseDTO list(Integer current,Integer size,Staff staff);

    ResponseDTO deleteBatch(List<Integer> ids);

    ResponseDTO export(HttpServletResponse response) throws IOException;

    ResponseDTO imp(MultipartFile file) throws IOException;

    ResponseDTO checkPassword(String pwd,String id);

    ResponseDTO updatePassword(Staff staff);

    ResponseDTO findInfoById(Integer id);
}
