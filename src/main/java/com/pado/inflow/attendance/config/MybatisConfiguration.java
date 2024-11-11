package com.pado.inflow.attendance.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("attendanceMybatisConfiguration")
@MapperScan(basePackages = "com.pado.inflow.attendance",annotationClass = Mapper.class)
public class MybatisConfiguration {
}