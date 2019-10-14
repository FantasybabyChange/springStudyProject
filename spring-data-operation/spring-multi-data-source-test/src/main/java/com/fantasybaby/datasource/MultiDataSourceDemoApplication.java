package com.fantasybaby.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		JdbcTemplateAutoConfiguration.class})
@Slf4j
public class MultiDataSourceDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiDataSourceDemoApplication.class, args);
	}

	@Bean
	@ConfigurationProperties("bat.datasource")
	public DataSourceProperties batDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource batDataSource() {
		DataSourceProperties dataSourceProperties = batDataSourceProperties();
		log.info("bat datasource: {}", dataSourceProperties.getUrl());
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	@Resource
	public PlatformTransactionManager batTxManager(DataSource batDataSource) {
		return new DataSourceTransactionManager(batDataSource);
	}

	@Bean
	@ConfigurationProperties("flash.datasource")
	public DataSourceProperties flashDataSourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	public DataSource flashDataSource() {
		DataSourceProperties dataSourceProperties = flashDataSourceProperties();
		log.info("flash datasource: {}", dataSourceProperties.getUrl());
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}

	@Bean
	@Resource
	public PlatformTransactionManager flashTxManager(DataSource flashDataSource) {
		return new DataSourceTransactionManager(flashDataSource);
	}
}

