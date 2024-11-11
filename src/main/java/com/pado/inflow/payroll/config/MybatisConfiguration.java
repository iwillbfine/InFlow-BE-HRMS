package com.pado.inflow.payroll.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("payRollMybatisConfiguration")
@MapperScan(basePackages = "com.pado.inflow.payroll",annotationClass = Mapper.class)
public class MybatisConfiguration {
}

