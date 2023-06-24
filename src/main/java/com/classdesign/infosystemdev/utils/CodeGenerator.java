package com.classdesign.infosystemdev.utils;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

public class CodeGenerator {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/hrm?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2b8";
        String opDir=System.getProperty("user.dir")+"/src/main/java";
        /*System.out.println(System.getProperty("user.dir"));
        System.out.println(opDir);*/
        String pathInfo=System.getProperty("user.dir")+"/src/main/resources/mapper";

        FastAutoGenerator.create(url,"root","12345")
                //全局配置
                .globalConfig(builder -> {
                    builder
                            .enableSwagger()
                            .author("lfz")
                            .dateType(DateType.ONLY_DATE)  //时间策略
                            .commentDate("yyyy-MM-dd")
                            .outputDir(opDir)
                            .disableOpenDir()
                            .fileOverride(); //覆盖已生成文件

                })

                //包配置
                .packageConfig(builder -> {
                    builder
                            .parent("com.classdesign.infosystemdev")
                            .moduleName(null)
                            .entity("entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .mapper("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,pathInfo))
                            .controller("controller");
                })
                //策略配置
                .strategyConfig(builder -> {
                    builder               //增加数据表的策略配置
                            .addTablePrefix("att_","per_","sal_","soc_","sys_")
                            .addInclude()

                            .entityBuilder()
                            .logicDeleteColumnName("is_deleted")
                            .enableLombok()    //开启lombok
                            /*.enableChainModel()*/
                            .enableTableFieldAnnotation()
                            .naming(NamingStrategy.underline_to_camel)
                            .columnNaming(NamingStrategy.underline_to_camel)
                            .idType(IdType.AUTO)
                            .formatFileName("%s")
                            .addTableFills(new Column("create_time", FieldFill.INSERT))
                            .addTableFills(new Column("update_time",FieldFill.INSERT_UPDATE))

                            .controllerBuilder()
                            .enableRestStyle()
                            .formatFileName("%sController")

                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")

                            .mapperBuilder()
                            .enableMapperAnnotation()
                            .enableBaseColumnList()
                            .enableBaseResultMap()
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper");



                })

                /* .injectionConfig(consumer->{
                     Map<String,String> customFile=new HashMap<>();
                     customFile.put("VO","/templates/vo.java.vm");
                     consumer.customFile(customFile);
                 })*/

                .templateEngine(new VelocityTemplateEngine())
                .execute();
    }
}
