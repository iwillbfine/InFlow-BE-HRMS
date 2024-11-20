package com.pado.inflow.department.query.config;


import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("departmentMybatisConfiguration")
@MapperScan(basePackages = "com.pado.inflow.department.query.repository", annotationClass=Mapper.class)
public class MyBatisConfiguration {
}
