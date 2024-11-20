package com.pado.inflow.payroll.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("payrollMybatisConfiguration")
@MapperScan(basePackages = "com.pado.inflow.payroll.query.repository", annotationClass = Mapper.class)
public class MybatisConfiguration {
}
