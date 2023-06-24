package com.classdesign.infosystemdev.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.classdesign.infosystemdev.dto.Response;
import com.classdesign.infosystemdev.dto.ResponseDTO;
import com.classdesign.infosystemdev.entity.Dept;
import com.classdesign.infosystemdev.entity.Staff;
import com.classdesign.infosystemdev.enums.BusinessStatusEnum;
import com.classdesign.infosystemdev.exception.ServiceException;
import com.classdesign.infosystemdev.mapper.StaffMapper;
import com.classdesign.infosystemdev.service.DeptService;
import com.classdesign.infosystemdev.service.StaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.classdesign.infosystemdev.utils.HutoolExcelUtil;
import com.classdesign.infosystemdev.utils.MD5Util;
import com.classdesign.infosystemdev.vo.StaffDeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 员工表 服务实现类
 * </p>
 *
 * @author lfz
 * @since 2023-06-08
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements StaffService {

    @Resource
    private DeptService deptService;

    @Resource
    private StaffMapper staffMapper;

    /**
     * 新增staff
     * @param staff
     * @return
     */
    public ResponseDTO add(Staff staff){
        if(save(staff)){
            staff.setPwd(MD5Util.MD55("123"))
            .setCode("staff_"+staff.getId());
            updateById(staff);
            return Response.success();
        }
        return Response.error();
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    public ResponseDTO deleteById(Integer id){
        if(removeById(id)){
            return Response.success();
        }
        return Response.error();
    }

    /**
     * 编辑员工信息
     * @param staff
     * @return
     */
    public ResponseDTO edit(Staff staff){
        if(updateById(staff)){
            return Response.success();
        }
        return Response.error();
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    public ResponseDTO findById(Integer id){
        Staff staff=getById(id);
        if(staff !=null){
            return Response.success(staff);
        }
        return Response.error();
    }

    /**
     * 多条件分页查询
     * @param current
     * @param size
     * @param staff
     * @return
     */
    public ResponseDTO list(Integer current,Integer size,Staff staff){
        //分页构造，第一个参数用来声明所查分页页码，第二个参数用来查询每页数据量
        IPage<Staff> pageConfig=new Page<>(current,size);
        //采用Lambda条件构造器
        LambdaQueryWrapper<Staff> wrapper=new LambdaQueryWrapper<>();
        if(staff.getName() != "" && staff.getName() != null){
            wrapper.like(Staff::getName,staff.getName());
        }
        if( staff.getBirthday() != null){
            wrapper.like(Staff::getBirthday,staff.getBirthday());
        }
        if( staff.getDeptId() != null){
            wrapper.like(Staff::getDeptId,staff.getDeptId());
        }
        if( staff.getStatus() != null){
            wrapper.like(Staff::getStatus,staff.getStatus());
        }
        //将分页条件和查询条件都注入page中
        IPage<Staff> page=page(pageConfig,wrapper);
        //获得记录条数
        List<Staff> records=page.getRecords();
        List<StaffDeptVO> staffDeptVOList=new ArrayList<>();
        for(Staff record : records){
            LambdaQueryWrapper<Dept> lambdaQueryWrapper=new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Dept::getId,record.getDeptId());
            Dept dept=deptService.getOne(lambdaQueryWrapper);
            //设置部门和年龄
            StaffDeptVO staffDeptVO=new StaffDeptVO();
            staffDeptVO.setDeptName(dept.getName());
            //根据生日计算年龄
            if(record.getBirthday() != null){
                staffDeptVO.setAge(DateUtil.ageOfNow(record.getBirthday()));
            }
            //将record中的属性复制到staffDeptVO中
            BeanUtil.copyProperties(record,staffDeptVO);
            staffDeptVOList.add(staffDeptVO);
        }
        Map map =new HashMap<>();
        map.put("pages",page.getPages());
        map.put("tital",page.getTotal());
        map.put("list",staffDeptVOList);
        return Response.success(map);
    }

    /**
     * 批量删除
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO deleteBatch(List<Integer> ids){
        if(removeBatchByIds(ids)){
            return Response.success();
        }
        return Response.error();
    }

    /**
     * 数据导出
     * @param response
     * @return
     * @throws IOException
     */
    public ResponseDTO export(HttpServletResponse response) throws IOException {
        List<StaffDeptVO> list=staffMapper.findStaffDeptVO();
        for(StaffDeptVO staffDeptVO : list){
            if(staffDeptVO.getBirthday() !=null){
                //计算年龄
                staffDeptVO.setAge(DateUtil.ageOfNow(staffDeptVO.getBirthday()));
            }
        }
        HutoolExcelUtil.writeExcel(response,list,"员工信息表",StaffDeptVO.class);
        return  Response.success();
    }

    /**
     * 数据导入
     * @param file
     * @return
     * @throws IOException
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO imp(MultipartFile file) throws  IOException{
        InputStream inputStream=file.getInputStream();
        List<Staff> list=HutoolExcelUtil.readExcel(inputStream,1,Staff.class);
        for(Staff staff :list){
            if(save(staff)){
                staff.setPwd(MD5Util.MD55("123"))
                        .setCode("staff_"+staff.getId())
                        .setDeptId(13);

            }else {
                return Response.error();
            }

        }
        return  Response.success();
    }

    /**
     * 检查员工密码
     * @param pwd
     * @param id
     * @return
     */
    public ResponseDTO checkPassword(String pwd,String id){
        Staff staff=getById(id);
        //检查是否存在对象
        if(staff != null){
            //检查密码是否为kong
            if(StrUtil.isNotBlank(pwd)){
                if(MD5Util.MD55(pwd).equals(staff.getPwd())){
                    return Response.success();
                }
                throw  new ServiceException(BusinessStatusEnum.ERROR.getCode(),"密码错误");
            }
            throw  new ServiceException(BusinessStatusEnum.BLANKET_ERROR.getCode(),"密码不能为空");
        }
        throw  new ServiceException(BusinessStatusEnum.STAFF_NOT_EXIST.getCode(),"此员工不存在");
    }

    /**
     * 更新密码
     * @param staff
     * @return
     */
    public ResponseDTO updatePassword(Staff staff){
        staff.setPwd(MD5Util.MD55(staff.getPwd()));
        if(updateById(staff)){
            return Response.success();
        }
        return  Response.error();
    }

    /**
     * 获取员工信息
     * @param id
     * @return
     */
    public ResponseDTO findInfoById(Integer id){
        StaffDeptVO staffDeptVO =staffMapper.findInfo(id);
        if(staffDeptVO !=null){
            return  Response.success(staffDeptVO);
        }
        return Response.error();
    }
}
