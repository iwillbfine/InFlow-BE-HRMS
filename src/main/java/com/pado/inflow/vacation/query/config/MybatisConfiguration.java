package com.pado.inflow.vacation.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("vacationMybatisConfiguration")
@MapperScan(basePackages = "com.pado.inflow.vacation.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
