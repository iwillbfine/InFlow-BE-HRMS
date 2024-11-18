package com.pado.inflow.evaluation.query.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration("evaluationMybatisConfiguration")
@MapperScan(basePackages = "com.pado.inflow.evaluation.query.repository", annotationClass= Mapper.class)
public class MybatisConfiguration {
}
