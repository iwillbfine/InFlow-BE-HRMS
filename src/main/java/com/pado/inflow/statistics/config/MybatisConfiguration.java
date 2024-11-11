package com.pado.inflow.statistics.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("statisticsMybatisConfiguration")
@MapperScan(basePackages = "com.pado.inflow.statistics",annotationClass = Mapper.class)
public class MybatisConfiguration {
}
