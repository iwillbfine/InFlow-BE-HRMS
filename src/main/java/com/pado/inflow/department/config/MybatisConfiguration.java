package com.pado.inflow.department.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("departmentMybatisConfiguration")
@MapperScan(basePackages = "com.pado.inflow.department",annotationClass = Mapper.class)
public class MybatisConfiguration {
}