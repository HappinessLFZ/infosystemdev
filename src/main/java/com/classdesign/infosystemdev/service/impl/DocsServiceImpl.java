package com.classdesign.infosystemdev.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.classdesign.infosystemdev.dto.Response;
import com.classdesign.infosystemdev.dto.ResponseDTO;
import com.classdesign.infosystemdev.entity.Docs;
import com.classdesign.infosystemdev.enums.BusinessStatusEnum;
import com.classdesign.infosystemdev.exception.ServiceException;
import com.classdesign.infosystemdev.mapper.DocsMapper;
import com.classdesign.infosystemdev.service.DocsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.classdesign.infosystemdev.utils.HutoolExcelUtil;
import com.classdesign.infosystemdev.vo.StaffDocsVO;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Service
public class DocsServiceImpl extends ServiceImpl<DocsMapper, Docs> implements DocsService {

    @Value("${file.upload.path}")  //引入上传文件的存储路径
    private String fileUploadPath;

    @Resource
    private DocsMapper docsMapper;

    /**
     * 文件上传
     * @param uploadFile
     * @param request
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO upload(MultipartFile uploadFile, HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");//从http请求头中取出token
        if(StrUtil.isNotBlank(token)){
            Integer staffId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
            File  fold=new File(fileUploadPath);
            if(!fold.exists()){
                fold.mkdir();   //如果不存在文件，那么就建立目录
            }
            //判断上传的文件是否为空
            if(!uploadFile.isEmpty()){
                //获取文件的原名称
                String originalFilename=uploadFile.getOriginalFilename();
                //获取文件的后缀名
                String extName = FileUtil.extName(originalFilename);
                //组装成新的文件名
                String filename= IdUtil.fastSimpleUUID().substring(2,22)+"."+extName;

                //获取文件的md5信息
                String md5= SecureUtil.md5(uploadFile.getInputStream());
                List<Docs> docsList = list(new LambdaQueryWrapper<Docs>()
                .eq(Docs::getMd5,md5));
                if(docsList != null && docsList.size()>0){
                    filename = docsList.get(0).getName();
                }else{
                    File file =new File(fileUploadPath+filename);
                    //将文件存储到磁盘
                    uploadFile.transferTo(file);
                }
                //将文件数据保存到数据库
                Docs docs=new Docs();
                docs.setName(filename);
                docs.setStaffId(staffId);
                docs.setType(extName);
                docs.setOldName(originalFilename);
                docs.setMd5(md5);
                docs.setSize(uploadFile.getSize() / 1024); //KB
                if(save(docs)){
                    return Response.success("文件上传成功！",docs);
                }
                throw new ServiceException(BusinessStatusEnum.ERROR);
            }
            throw  new ServiceException(BusinessStatusEnum.FILE_NOT_EXIST);
        }
        throw new ServiceException(BusinessStatusEnum.TOKEN_NOT_EXIST);
    }

    /**
     * 文件下载
     * @param filename
     * @param response
     * @return
     * @throws IOException
     */
    public ResponseDTO download(String filename, HttpServletResponse response) throws IOException {
        //通知浏览器已下载的方式打开
        response.addHeader("Content-Type","application/octet-stream");
        response.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(filename,"utf-8"));

        //使用文件流读取文件
        File downloadFile = new File(fileUploadPath+filename);
        OutputStream out= response.getOutputStream();

        //读取文件的字节流
        out.write(FileUtil.readBytes(downloadFile));
        out.flush();
        out.close();
        return  Response.success();
    }

    public ResponseDTO add(Docs docs){
        if(save(docs)){
            return Response.success();
        }
        return  Response.error();
    }

    public ResponseDTO deleteById(Integer id) {
        if (removeById(id)) {
            return Response.success();
        }
        return Response.error();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO deleteBatch(List<Integer> ids) {
        if (removeBatchByIds(ids)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO edit(Docs docs) {
        if (updateById(docs)) {
            return Response.success();
        }
        return Response.error();
    }


    public ResponseDTO findById(Integer id) {
        Docs docs = getById(id);
        if (docs != null) {
            return Response.success(docs);
        }
        return Response.error();
    }


    public ResponseDTO list(Integer current, Integer size, String oldName, String staffName) {
        if (oldName == null) {
            oldName = "";
        }
        if(staffName == null){
            staffName = "";
        }
        IPage<StaffDocsVO> config = new Page<>(current, size);
        IPage<StaffDocsVO> page = this.docsMapper.listStaffDocsVO(config, oldName,staffName);
        // 将响应数据填充到map中
        Map map = new HashMap();
        map.put("pages", page.getPages());
        map.put("total", page.getTotal());
        map.put("list", page.getRecords());
        return Response.success(map);
    }

    public ResponseDTO export(HttpServletResponse response) throws IOException {
        List<Docs> list = list();
        HutoolExcelUtil.writeExcel(response, list, "文件信息表", Docs.class);
        return Response.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<Docs> list = HutoolExcelUtil.readExcel(inputStream, 1, Docs.class);
        // IService接口中的方法.批量插入数据
        if (saveBatch(list)) {
            return Response.success();
        }
        return Response.error();
    }
}
