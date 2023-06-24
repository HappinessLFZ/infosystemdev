package com.classdesign.infosystemdev.service;

import com.classdesign.infosystemdev.dto.ResponseDTO;
import com.classdesign.infosystemdev.entity.Docs;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 文件表 服务类
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
public interface DocsService extends IService<Docs> {

    ResponseDTO upload(MultipartFile uploadFile, HttpServletRequest request) throws IOException;

    ResponseDTO download(String filename, HttpServletResponse response) throws IOException;

    ResponseDTO add(Docs docs);

    ResponseDTO deleteById(Integer id);

    ResponseDTO deleteBatch(List<Integer> ids);

    ResponseDTO edit(Docs docs);

    ResponseDTO findById(Integer id);

    ResponseDTO list(Integer current, Integer size, String oldName, String staffName);

    ResponseDTO export(HttpServletResponse response) throws IOException;

    ResponseDTO imp(MultipartFile file) throws IOException;

}
