package com.classdesign.infosystemdev.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.classdesign.infosystemdev.annotation.ExcelColumn;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.List;

/**
 * 利用hutool工具实现数据的导入和导出
 */
public class HutoolExcelUtil {

    /**
     * 实现数据的导出
     * @param response
     * @param list
     * @param filename
     * @param clazz
     * @param <T>
     */
      public static <T> void writeExcel(HttpServletResponse response, List<T> list,String filename,Class<T> clazz) throws IOException {
            //创建列名总数量
            Integer  columnTotal=0;
            //通过工具类创建对象，可以指定磁盘路径，调动磁盘
            ExcelWriter writer= ExcelUtil.getWriter(true);       //判断是否是xlsx文件
          //判断是否序列化
            boolean isSerializable=false;
            //获取类中所有字段
          Field[] fields=clazz.getFields();
          for(Field field :fields){
                //设置私有变量可访问化
              field.setAccessible(true);
              if(field.getName().equals("serializationUID")){
                    isSerializable=true;
              }
              //判断是否有ExcelColumn这个注解
              if(field.isAnnotationPresent(ExcelColumn.class)){
                  ExcelColumn excelColumn=field.getAnnotation(ExcelColumn.class);
                  //增加标题别名，不取别名的话，后面所有的字段都以原名输出
                    writer.addHeaderAlias(field.getName(),excelColumn.value());
                    columnTotal++;
              }
          }

          if(columnTotal==0){
                if(isSerializable){
                    /**
                     *merge方法在这里是用来合并单元格的，第一个参数表示将从列索引0要合并的最后一列的索引，并不代表合并的列数，只是要合并到最终列的列数
                     *此处减2是因为多出一个字段Serializable，需要先消除多余的字段列
                     */
                    writer.merge(fields.length-2,filename);
                }else{
                    writer.merge(fields.length-1,filename);
                }
          }else {
              //输出字段默认是所有字段，这个地方需要设置为仅标有别名的字段才能输出
                writer.setOnlyAlias(true);
                writer.merge(columnTotal-1,filename);
          }
          //这个地方需要注意的是，必须先将合并的文件名输出，之后才能输出具体字段信息
          writer.write(list,true);
          //设置浏览器响应的格式
          response.setContentType("application/vnd.ms-excel;charset=utf-8");
          response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(filename,"UTF-8") +".xlsx");
          ServletOutputStream outputStream=response.getOutputStream();
          //清空所有输出流
          writer.flush(outputStream,true);
          //关闭writer,释放内存
          writer.close();
          //关闭Serclet输出流
          IoUtil.close(outputStream);
      }


    /**
     * 数据导入
     * @param inputStream
     * @param headerRowIndex
     * @param clazz
     * @param <T>
     * @return
     */
      public static <T> List<T> readExcel(InputStream inputStream,Integer headerRowIndex,Class<T> clazz){
          //获取输入流
          ExcelReader reader=ExcelUtil.getReader(inputStream);
          Field[] fields=clazz.getDeclaredFields();
          for(Field field : fields){
              //设置私有变量可访问
              field.setAccessible(true);
              if(field.isAnnotationPresent(ExcelColumn.class)){
                  ExcelColumn excelColumn=field.getAnnotation(ExcelColumn.class);
                  reader.addHeaderAlias(excelColumn.value(),field.getName());
              }
          }
          //从起始行开始读数据，headerRowIndex是从0开始，
          //read的第一个参数是：标题开始行数，第二个是具体数据开始行数，第三个参数是结束行数，第四个是具体JavaBean
          List<T> list=reader.read(headerRowIndex,headerRowIndex+1,Integer.MAX_VALUE,clazz);
          reader.close();
          return list;
      }

}
