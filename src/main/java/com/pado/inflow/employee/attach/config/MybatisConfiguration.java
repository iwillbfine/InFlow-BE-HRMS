package com.pado.inflow.employee.attach.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("employeeAttachMybatisConfiguration")
@MapperScan(basePackages = "com.pado.inflow.employee.attach",annotationClass = Mapper.class)
public class MybatisConfiguration {
}