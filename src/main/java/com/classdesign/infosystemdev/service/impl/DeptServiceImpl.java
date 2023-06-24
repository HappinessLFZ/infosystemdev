package com.classdesign.infosystemdev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classdesign.infosystemdev.dto.Response;
import com.classdesign.infosystemdev.dto.ResponseDTO;
import com.classdesign.infosystemdev.entity.Dept;
import com.classdesign.infosystemdev.mapper.DeptMapper;
import com.classdesign.infosystemdev.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.classdesign.infosystemdev.utils.HutoolExcelUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    /**
     *添加部门
     * @param dept
     * @return
     */
    public ResponseDTO add(Dept dept){
        //父部不需要计算上班时间
        if(dept.getParentId()!=null){
            dept.setTotalWorkTime(calculateTotalWorkTime(dept));
        }
        if(save(dept)){
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

    public ResponseDTO edit(Dept dept) {
        dept.setTotalWorkTime(calculateTotalWorkTime(dept));
        QueryWrapper<Dept> queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", dept.getId());
        if (saveOrUpdate(dept, queryWrapper)) {
            return Response.success();
        }
        return Response.error();
    }

    /**
     * 查找所有部门
     *
     * @return
     */
    public ResponseDTO findAll() {
        List<Dept> list = list();
        // 为父级部门设置子部门
        return Response.success(setSubDept(list));
    }

    public ResponseDTO findById(Integer id) {
        Dept dept = getById(id);
        if (dept != null) {
            return Response.success(dept);
        }
        return Response.error();
    }

    public ResponseDTO list(Integer current,Integer size,String name){
        IPage<Dept> config=new Page<>(current,size);
        if(name == null){
            name = "";
        }
        IPage<Dept> page = deptMapper.listParentDept(config,name);
        //查询所有菜单
        List<Dept> list=deptMapper.findSubDept();
        //父级部门
        List<Dept> parentList = page.getRecords();
        for(Dept parentDept : parentList){
            //设置子部门
            List<Dept> subList = list.stream().filter(dept -> dept.getParentId() == parentDept.getId())
                    .collect(Collectors.toList());
            parentDept.setChildren(subList);
        }
        //将响应数据填充到map中
        Map<String,Object> map=new HashMap<>();
        map.put("pages",page.getPages());
        map.put("total",page.getTotal());
        map.put("list",page.getRecords());
        return  Response.success(map);
     }

    /**
     * 为父级菜单设置子部门，使用流来处理数据，并返回父级部门
     * @param list
     * @return
     */
    public List<Dept> setSubDept(List<Dept> list){
        //父级菜单
        List<Dept> parentList =list.stream().parallel()
                .filter(dept -> dept.getParentId() == 0).collect(Collectors.toList());
        for(Dept parentDept : parentList){
            //子级菜单
            List<Dept> subList =list.stream().parallel()
                    .filter(dept -> dept.getParentId() == parentDept.getId()).collect(Collectors.toList());
            parentDept.setChildren(subList);
        }
        return parentList;
    }

    /**
     * 数据导出
     *
     * @param response
     * @return
     */
    public ResponseDTO export(HttpServletResponse response) throws IOException {
        List<Dept> list = this.deptMapper.findSubDept();
        HutoolExcelUtil.writeExcel(response, list, "部门数据", Dept.class);
        return Response.success();
    }

    /**
     * 数据导入
     *
     * @param file
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO imp(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<Dept> list = HutoolExcelUtil.readExcel(inputStream, 1, Dept.class);
        // IService接口中的方法.批量插入数据
        if (saveBatch(list)) {
            return Response.success();
        }
        return Response.error();
    }

    /**
     * 查找所有的子部门
     * @return
     */
    public ResponseDTO findAllSubDept(){
        LambdaQueryWrapper<Dept> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ne(Dept::getParentId,0);
        List<Dept> list =list(lambdaQueryWrapper);
        return Response.success(list);
    }

    /**
     * 计算一天上班时间
     * @param dept
     * @return
     */
    public BigDecimal calculateTotalWorkTime(Dept dept){
        long morDiff = dept.getMorEndTime().getTime()-dept.getMorStartTime().getTime();
        long aftDiff = dept.getAftEndTime().getTime()-dept.getAftStartTime().getTime();
        BigDecimal totalWorkTime = BigDecimal.valueOf((morDiff + aftDiff) / (1000 * 60* 60.0));
        return totalWorkTime;
    }
}
