package com.fantasybaby.mybatis.plsql.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.fantasybaby.mybatis.**.mapper")
public class MybatisConfig {
}
