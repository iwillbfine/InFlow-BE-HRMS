package com.pado.inflow.attendance.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("attendanceMybatisConfiguration")
@MapperScan(basePackages = "com.pado.inflow.attendance.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
